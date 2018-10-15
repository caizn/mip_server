package com.lingtoo.wechat.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.lingtoo.common.http.HttpHandler;
import com.lingtoo.common.http.HttpResult;
import com.lingtoo.common.mapper.JsonMapper;
import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.domain.Address;

/**
 * Created: 2015/12/29.
 * Author: Qiannan Lu
 */
@Service
public class BaiduAPIService extends BaseService {
    @Value("${baidu.api.key}")
    private String baiduKey;

    private JsonMapper mapper = JsonMapper.getInstance();

    public Address parseGPSToLocation(double latitude, double longitude) {
        HashMap<String, String> params = Maps.newHashMap();
        params.put("location", latitude + "," + longitude);
        params.put("output", JSON);
        params.put("ak", baiduKey);
        HttpResult result = null;
        try {
            result = HttpHandler.specGet(T1TConstants.BAIDU_GEO_CODING_API, null, params);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        if (Objects.nonNull(result)) {
            JsonNode node = mapper.fromJson(result.getBody(), JsonNode.class);
            if (node.get("status").asInt() == 0) {
                return mapper.fromJson(node.get("result").toString(), Address.class);
            }
        }
        return null;
    }
}
