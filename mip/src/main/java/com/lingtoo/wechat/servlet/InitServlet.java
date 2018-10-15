package com.lingtoo.wechat.servlet;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.lingtoo.wechat.T1TConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.common.collect.Maps;
import com.lingtoo.wechat.pojo.MerchantApp;
import com.lingtoo.wechat.service.MerchantService;
import com.lingtoo.wechat.thread.T1TTokenThread;

/**
 * Created: 2015/10/22.
 * Author: Qiannan Lu
 */
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = -5230682484210122506L;
    private static Logger logger = LoggerFactory.getLogger(InitServlet.class);

    @Autowired
    private MerchantService merchantService;
    public static HashMap<String, String> appMap = Maps.newHashMap();
    public static HashMap<String, String> accessToken = Maps.newHashMap();
    public static HashMap<String, String> jsAPITicketMap = Maps.newHashMap();

    @Override
    public void init() throws ServletException {
        /*SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        List<MerchantApp> merchantAppList = merchantService.findMerchantApp();
        for (MerchantApp app:merchantAppList){
            logger.info("----------InitServlet appid:"+app.getAppId()+" , appSecret:"+app.getAppSecret());
            appMap.put(app.getAppId(), app.getAppSecret());
        }*/
        appMap.put(T1TConstants.APPID_DECORATION, T1TConstants.APPSECRET_DECORATION);
        new Thread(new T1TTokenThread()).start();
    }
}