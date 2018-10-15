package com.lingtoo.wechat.service;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.lingtoo.common.http.HttpHandler;
import com.lingtoo.common.http.HttpResult;
import com.lingtoo.common.utils.StringUtils;
import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.pojo.WechatUser;
import com.lingtoo.wechat.servlet.InitServlet;
import com.lingtoo.wechat.utils.JsonUtil;
import com.lingtoo.wechat.utils.wxpay.WeixinHttpUtil;

/**
 * Created: 2015/10/10.
 * Author: Qiannan Lu
 */
@Service
public class WechatAPIService {
    private static final Logger logger = LoggerFactory.getLogger(WechatAPIService.class);
    
    /**
     * 获取永久二维码
     * @param access_token
     * @param scene_id
     * @return
     */
    public static JSONObject getQrcode(String appID,Integer scene_id){
    	String accessToken=InitServlet.accessToken.get(T1TConstants.APPID_DECORATION);
        String jsonString=
        		"{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+scene_id+"}}}";
        JSONObject object=WeixinHttpUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken, "POST", jsonString);
		return object;
    }
    
    public HashMap<String, Object> getOAuthAccessToken(String code,String appID, String appSecret) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("appid", appID);
        params.put("secret", appSecret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        logger.info("----getOAuthAccessToken appID="+appID+" code="+code);
        HttpResult result = null;
        try {
            result = HttpHandler.get("https://api.weixin.qq.com/sns/oauth2/access_token", null, params);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
//        logger.info("wechat get access_token by code result="+result);
        return JsonUtil.parseMap(result);
    }

    public WechatUser getWechatUser(String accessToken, String openID) {
        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(openID))
            return null;
        HashMap<String, String> params = Maps.newHashMap();
        params.put("access_token", accessToken);
        params.put("openid", openID);
        params.put("lang", "zh_CN");
        HttpResult result = null;
        try {
            result = HttpHandler.get("https://api.weixin.qq.com/sns/userinfo", null, params);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return JsonUtil.parse(result, WechatUser.class);
    }

    public WechatUser getWechatUserByOpenId(String accessToken, String openId) {
        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(openId))
            return null;
        HashMap<String, String> params = Maps.newHashMap();
        params.put("access_token", accessToken);
        params.put("openid", openId);
        params.put("lang", "zh_CN");
        HttpResult result = null;
        try {
            result = HttpHandler.get("https://api.weixin.qq.com/cgi-bin/user/info", null, params);
            logger.info(result.getBody());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return JsonUtil.parse(result, WechatUser.class);
    }

    public HashMap<String, String> isOAuthAccessTokenValid(String access_token,String openId) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("access_token", access_token);
        params.put("openid", openId);
        logger.info("----isOAuthAccessTokenValid access_token="+access_token+" openId="+openId);
        HttpResult result = null;
        try {
            result = HttpHandler.get("https://api.weixin.qq.com/sns/auth", null, params);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("isOAuthAccessTokenValid result="+result);
        return JsonUtil.parseMapString(result);
    }
}
