package com.lingtoo.common.components;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 2015/9/8.
 * Author: Qiannan Lu
 */
@Component
public class SMSClient {
    private static final Logger logger = LoggerFactory.getLogger(SMSClient.class);

    private static final String MESSAGE_TEMPLATE = "您的验证码是：【%s】。请不要把验证码泄露给其他人。如非本人操作，可不用理会！";

    private static CloseableHttpClient client;

    private static BasicCookieStore cookieStore;

    private static HttpClientBuilder clientBuilder;

    private static HttpResponse response;

    private static HttpPost post;

    public void sendSMS(String phone, String code) {
        try {
            post(phone, code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void post(String phone, String code) throws IOException {
        cookieStore = new BasicCookieStore();

        clientBuilder = HttpClients.custom().setDefaultCookieStore(cookieStore);

        client = clientBuilder.build();

        post = new HttpPost("http://106.ihuyi.com/webservice/sms.php?method=Submit");

        Header[] headers = new BasicHeader[1];
        headers[0] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        post.setHeaders(headers);

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("account", "cf_bsxx"));
        list.add(new BasicNameValuePair("password", "bsxx123"));
        list.add(new BasicNameValuePair("mobile", phone));
        list.add(new BasicNameValuePair("content", String.format(MESSAGE_TEMPLATE, code)));

        post.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
        response = client.execute(post);
        logger.info("发送验证码成功----------" + phone);
    }
}
