package com.lingtoo.common.wechat;

import com.fasterxml.jackson.databind.JavaType;
import com.google.common.collect.Maps;
import com.lingtoo.common.http.HttpExecuteException;
import com.lingtoo.common.http.HttpHandler;
import com.lingtoo.common.http.HttpResult;
import com.lingtoo.common.mapper.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created: 2015/10/22.
 * Author: Qiannan Lu
 */
public class TokenClient {
    private static final Logger logger = LoggerFactory.getLogger(TokenClient.class);
    private static final JsonMapper binder = new JsonMapper();

    public static HashMap<String, String> getAccessToken(String appID, String appSecret) {
        Map<String, String> params = Maps.newHashMap();
        params.put("grant_type", "client_credential");
        params.put("appid", appID);
        params.put("secret", appSecret);
        try {
            HttpResult result = HttpHandler.getThroughHttps("https://api.weixin.qq.com/cgi-bin/token", null, params);
            logger.info(result.getBody());
            JavaType type = binder.contructMapType(HashMap.class, String.class, String.class);
            return binder.fromJson(result.getBody(), type);
        } catch (HttpExecuteException | IOException e) {
            logger.error("error happened when getting wechat access token", e);
        }
        return Maps.newHashMap();
    }

    public static HashMap<String, String> getJSAPITicket(String token) {
        Map<String, String> params = Maps.newHashMap();
        params.put("access_token", token);
        params.put("type", "jsapi");
        try {
            HttpResult result = HttpHandler.getThroughHttps("https://api.weixin.qq.com/cgi-bin/ticket/getticket", null, params);
            JavaType type = binder.contructMapType(HashMap.class, String.class, String.class);
            logger.info(result.getBody());
            return binder.fromJson(result.getBody(), type);

        } catch (HttpExecuteException | IOException e) {
            logger.error("error happened when getting wechat jsAPI ticket", e);
        }
        return Maps.newHashMap();
    }
}
