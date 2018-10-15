package com.lingtoo.common.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

/**
 * Created: 2015/10/9.
 * Author: Qiannan Lu
 */
public class HttpHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    private static CloseableHttpClient client;

    private static BasicCookieStore cookieStore;

    private static HttpGet get;

    private static HttpPost post;

    private static HttpResponse response;

    private static HttpResult result;

    private static HttpEntity entity;

    private static HttpClientBuilder clientBuilder;

    public static HttpResult specGet(String url, Map<String, String> headers, Map<String, String> params) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();
        clientBuilder = HttpClients.custom().setDefaultRequestConfig(requestConfig);
        return get(url, headers, params);
    }

    /**
     * @param url 请求地址
     * @param headers 请求头
     * @param params 请求参数
     * @throws IOException
     */
    public static HttpResult get(String url, Map<String, String> headers, Map<String, String> params) throws IOException {

        cookieStore = new BasicCookieStore();

        if (null == clientBuilder) {
            clientBuilder = HttpClients.custom();
        }

        client = clientBuilder.setDefaultCookieStore(cookieStore).build();

        url = (null == params ? url : url + "?" + parseParam(params));

        get = new HttpGet(url);

        get.setHeaders(parseHeader(headers));

        logger.info("Get request: " + get.toString());

        response = client.execute(get);

        entity = response.getEntity();

        result = new HttpResult();

        result.setHttpClient(client);

        result.setCookies(cookieStore.getCookies());

        result.setStatusCode(response.getStatusLine().getStatusCode());

        result.setHeaders(response.getAllHeaders());

        result.setHttpEntity(entity);

        result.setBody(EntityUtils.toString(entity, "UTF-8"));

        return result;
    }

    /**
     * @param headers 通过Map传入请求的header
     */
    private static Header[] parseHeader(Map<String, String> headers) {
        if (null == headers || headers.isEmpty()) {
            return getDefaultHeaders();
        }
        Header[] allHeader = new BasicHeader[headers.size()];
        int i = 0;
        for (String str : headers.keySet()) {
            allHeader[i] = new BasicHeader(str, headers.get(str));
            i++;
        }
        return allHeader;
    }

    /**
     * //TODO 默认header
     */
    private static Header[] getDefaultHeaders() {
        Header[] allHeader = new BasicHeader[1];
//        allHeader[0] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
        allHeader[0] = new BasicHeader("Accept-Charset", "utf-8,GB2312;q=0.7,*;q=0.7");
        return allHeader;
    }

    /**
     * 转换参数列表
     *
     * @param params get 请求的参数
     */
    private static String parseParam(Map<String, String> params) throws UnsupportedEncodingException {
        if (null == params || params.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 释放httpClient对象
     */
    public static void closeClient(CloseableHttpClient client) {
        if (null != client) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static SSLContext getSSLContext() throws HttpExecuteException {
        try {
            return SSLContexts.custom()
                    .useSSL()
                    .setSecureRandom(new SecureRandom())
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            logger.error("Exception occur when get the SSLContext!", e);
            throw new HttpExecuteException(e);
        }
    }

    public static HttpResult getThroughHttps(String url, Map<String, String> headers, Map<String, String> params) throws IOException, HttpExecuteException {
        cookieStore = new BasicCookieStore();

        clientBuilder = HttpClients.custom().setDefaultCookieStore(cookieStore);

        client = clientBuilder.setSslcontext(getSSLContext()).build();

        url = (null == params ? url : url + "?" + parseParam(params));

        get = new HttpGet(url);

        get.setHeaders(parseHeader(headers));

        logger.info("Get request: " + get.toString());

        response = client.execute(get);

        entity = response.getEntity();

        result = new HttpResult();

        result.setHttpClient(client);

        result.setCookies(cookieStore.getCookies());

        result.setStatusCode(response.getStatusLine().getStatusCode());

        result.setHeaders(response.getAllHeaders());

        result.setHttpEntity(entity);

        result.setBody(EntityUtils.toString(entity, "UTF-8"));

        return result;
    }
}
