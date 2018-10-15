package com.lingtoo.wechat.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.lingtoo.common.http.HttpResult;
import com.lingtoo.common.mapper.JsonMapper;

/**
 * Created: 2015/4/23.
 * Author: Qiannan Lu
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final JsonMapper binder = new JsonMapper();

    public static HashMap<String, Object> parseMap(HttpResult httpResult) {
        HashMap<String, Object> dataMap = Maps.newHashMap();
        if (null == httpResult)
            return dataMap;
        logger.info(httpResult.getBody());
        JavaType type = binder.contructMapType(HashMap.class, String.class, Object.class);
        return binder.fromJson(httpResult.getBody(), type);
    }

    public static HashMap<String, String> parseMapString(HttpResult httpResult) {
        HashMap<String, String> dataMap = Maps.newHashMap();
        if (null == httpResult)
            return dataMap;
        logger.info(httpResult.getBody());
        JavaType type = binder.contructMapType(HashMap.class, String.class, String.class);
        return binder.fromJson(httpResult.getBody(), type);
    }

    public static HashMap<String, String> parseMapString(String jsonStr) {
        HashMap<String, String> dataMap = Maps.newHashMap();
        if (null == jsonStr)
            return dataMap;

        try {

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = new HashMap<String, Object>();

            // convert JSON string to Map
            dataMap = mapper.readValue(jsonStr, new TypeReference<Map<String, String>>(){});

            System.out.println(map);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataMap;
    }

    public static <T> T parse(HttpResult result, Class<T> clazz) {
        return null == result ?  null : binder.fromJson(result.getBody(), clazz);
    }

    public static <T> T parseString(String source, Class<T> clazz) {
        return null == source ?  null : binder.fromJson(source, clazz);
    }

/*    public static ArrayList<LiveActivity> parseLiveList(String result, Class<LiveActivity> clazz) {
        JavaType tType = binder.createCollectionType(ArrayList.class, clazz);
        return binder.fromJson(result, tType);
    }*/


    
	/**
	 * 转成JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
