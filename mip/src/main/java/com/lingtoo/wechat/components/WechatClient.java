package com.lingtoo.wechat.components;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lingtoo.common.mapper.JsonMapper;
import com.lingtoo.wechat.message.WechatTemplate;
import com.lingtoo.wechat.thread.T1TTokenThread;
import com.lingtoo.wechat.utils.DateUtil;
import com.lingtoo.wechat.utils.StringUtil;

/**
 * Created: 2015/11/1.
 * Author: Qiannan Lu
 */
public class WechatClient {
    private static final Logger logger = LoggerFactory.getLogger(WechatClient.class);

    public static final String TEMPLATE_MESSAGE_POST = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    //取当前用户数
    public static final String GET_USER_CUMULATE_POST = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=";
    
    
    public static String getusercumulate(String appid){
    	String cumulate_user="0";
    	
    	String date=DateUtil.DateBefAft(1,"yyyy-MM-dd");
    	
    	String postParam="{\"begin_date\": \""+date+"\", \"end_date\": \""+date+"\"}";
    	if(!StringUtil.isEmpty(T1TTokenThread.getAccessToken(appid))){
    		
    		String requestUrl=GET_USER_CUMULATE_POST + T1TTokenThread.getAccessToken(appid);
    		logger.info("用户分析数据接口：requestUrl=" + requestUrl+" \ncontent=" + postParam);
    		String respJson= post(requestUrl, postParam);
    		logger.info("用户分析数据接口返回：" + respJson);
    		
    		JSONObject jsonObject =JSONObject.parseObject(respJson);//先读取串数组 
    		JSONArray jsonArray= jsonObject.getJSONArray("list");
    		
    		if(jsonArray!=null&&jsonArray.size()>0){
    			cumulate_user=((JSONObject)jsonArray.get(0)).getString("cumulate_user");
    		}
    	}

    	return cumulate_user;
    }
    
    
    public static void sendTemplateMessage(WechatTemplate template) {
    	String requestUrl=TEMPLATE_MESSAGE_POST + T1TTokenThread.getAccessToken();
    	String content=JsonMapper.toJsonString(template);
    	 logger.info("模板消息发送：requestUrl=" + requestUrl+" \ncontent=" + content);
         String respJson= post(requestUrl,content);
        logger.info("模板消息发送成功：" + respJson);
    }

    public static void sendTemplateMessage(WechatTemplate template, String appid) {

    	String requestUrl=TEMPLATE_MESSAGE_POST + T1TTokenThread.getAccessToken(appid);
    	String content=JsonMapper.toJsonString(template);
    	logger.info("模板消息发送：requestUrl=" + requestUrl+" \ncontent=" + content);
        String respJson= post(requestUrl,content);
        logger.info("模板消息发送成功：" + respJson);
    }

    public static String post(String requestUrl, String content) {
       
        StringBuilder buffer = new StringBuilder();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("POST");

            // 当有数据需要提交时
            if (null != content) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(content.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            httpUrlConn.disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
       

        return buffer.toString();
    }
}
