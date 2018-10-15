package com.lingtoo.wechat.components;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created: 2016/1/8.
 * Author: Qiannan Lu
 */
@Component
public class AlidayuClient {
    private static final Logger logger = LoggerFactory.getLogger(AlidayuClient.class);

    @Value("${alibaba.dayu.appKey}")
    private String appKey;

    @Value("${alibaba.dayu.secret}")
    private String secret;

    @Value("${alibaba.dayu.url}")
    private String url;

    @Value("${alibaba.dayu.smsTemplateCode}")
    private String smsTemplateCode;

    private static final String SMS_TYPE = "normal";
    //private static final String SMS_FREE_SIGN_NAME = "身份验证";
    private static final String SMS_FREE_SIGN_NAME = "同城修一修";
    
    public void sendServiceMessage(String phone,String[] code) {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123321");  //
        req.setSmsType(SMS_TYPE);
        req.setSmsFreeSignName("踢踢青训");
        //req.setSmsFreeSignName(SMS_FREE_SIGN_NAME);
        req.setSmsParamString("{\"name\":\"" + code[0] + "\",\"pwd\":\""+code[1]+"\"}");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_77535084");
        //req.setSmsTemplateCode(smsTemplateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            logger.error(e.getMessage());
        }
        logger.info(Objects.nonNull(rsp) ? rsp.getBody() : "验证码发送失败");
    }

    public String sendMessage(String phone, String code,String merchantName) {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123321");  //
        req.setSmsType(SMS_TYPE);
        req.setSmsFreeSignName(SMS_FREE_SIGN_NAME);
        req.setSmsParamString("{\"code\":\"" + code + "\",\"product\":\""+merchantName+"\"}");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_109550193");
        //req.setSmsTemplateCode("SMS_7515999");
        //req.setSmsTemplateCode(smsTemplateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            logger.error(e.getMessage());
        }
        logger.info(Objects.nonNull(rsp) ? rsp.getBody() : "验证码发送失败");
        return  rsp.getBody();
    }
}
