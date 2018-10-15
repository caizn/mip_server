package com.lingtoo.wechat.thread;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.lingtoo.common.http.HttpExecuteException;
import com.lingtoo.common.http.HttpHandler;
import com.lingtoo.common.http.HttpResult;
import com.lingtoo.common.wechat.TokenClient;
import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.servlet.InitServlet;

/**
 * Created: 2015/3/31.
 * Author: Qiannan Lu
 */
public class T1TTokenThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(T1TTokenThread.class);

    private static final String format = "获取access_token成功，有效时长 {0} 秒 token: {1}";
    public static String appID = "";
    public static String appSecret = "";
    public static List<String> noticeAppidList=new ArrayList<String>();
    
    public T1TTokenThread() {
    	//noticeAppidList.add(T1TConstants.APPID_DECORATION);
    }


    public void run() {
        while (true) {
            try {
            	JSONArray array=new JSONArray();
                for (Map.Entry<String, String> entry : InitServlet.appMap.entrySet()) {
                    boolean noticeSign=false;
                    appID = entry.getKey();
                    appSecret = entry.getValue();
                    for(String noticeAppid:noticeAppidList) {
                    	if(appID.equals(noticeAppid)) {
                    		noticeSign=true;
                    		break;
                    	}
                    }
                    HashMap<String, String> wechatToken = TokenClient.getAccessToken(appID, appSecret);
                    if(Objects.nonNull(wechatToken.get("access_token"))){
                        InitServlet.accessToken.put(appID, wechatToken.get("access_token"));
                        logger.info(MessageFormat.format(format, InitServlet.accessToken.get("expires_in"), wechatToken.get("access_token")));
                        String jsAPITicket = TokenClient.getJSAPITicket(wechatToken.get("access_token")).get("ticket");
                        InitServlet.jsAPITicketMap.put(appID, jsAPITicket);
                        if(noticeSign) {
                        	JSONObject object=new JSONObject();
                        	object.put("accessToken", wechatToken.get("access_token"));
                        	object.put("ticket", jsAPITicket);
                        	object.put("appid", appID);
                        	array.put(object);
                        }
                    }

                }
                String arrayString=array.toString();
                if(array.length()!=0) {
                    try {
                    	
                        Map<String, String> params = Maps.newHashMap();
                        params.put("jsonString", URLEncoder.encode(arrayString));
                        HttpResult result = HttpHandler.getThroughHttps("http://www.tcxiuyixiu.com/access_token/notice", null, params);
                        logger.info(result.getBody());
                    } catch (HttpExecuteException | IOException e) {
                        logger.error("error happened when getting wechat access token", e);
                    }
                }
                if (InitServlet.accessToken.size() > 0) {
                    Thread.sleep(7000 * 1000);
                } else {
                    Thread.sleep(60 * 1000);    // 如果access_token为null，60秒后再获取
                }
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException ee) {
                    logger.error(ee.getMessage());
                }
                logger.error(e.getMessage());
            }
        }
    }
    
    /**
     * 验证 APPID和 appSecret合法性
     * @param appid
     * @param appSecret
     * @return
     */
    public static boolean verifyToken(String appid,String appSecret){
    	
    	 try {
    		 	HashMap<String, String> wechatToken = TokenClient.getAccessToken(appid, appSecret);
    	    	   if(Objects.nonNull(wechatToken.get("access_token"))){
    	    		   InitServlet.accessToken.put(appID, wechatToken.get("access_token"));
    	               logger.info(MessageFormat.format(format, InitServlet.accessToken.get("expires_in"), wechatToken.get("access_token")));
    	               String jsAPITicket = TokenClient.getJSAPITicket(wechatToken.get("access_token")).get("ticket");
    	               InitServlet.jsAPITicketMap.put(appID, jsAPITicket);
    	               InitServlet.appMap.put(appid,appSecret);
    	               return true;
    	           }
    		 
    	 } catch (Exception e) {
    		 e.printStackTrace();
 
    	 }
   
    	return false;
    }
    

    public static String getAccessToken(String appid) {
        return InitServlet.accessToken.get(appid);
    }

    public static String getjsAPITicket(String appid) {
        return InitServlet.jsAPITicketMap.get(appid);
    }

    public static String getAccessToken() {
        return InitServlet.accessToken.get(T1TConstants.APPID_DECORATION);
    }
}