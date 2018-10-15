package com.lingtoo.wechat.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.lingtoo.common.wechat.WechatSignUtil;
import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.domain.Address;
import com.lingtoo.wechat.pojo.Manager;
import com.lingtoo.wechat.pojo.Merchant;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.WechatUser;
import com.lingtoo.wechat.service.BaiduAPIService;
import com.lingtoo.wechat.service.UserService;
import com.lingtoo.wechat.service.WechatAPIService;
import com.lingtoo.wechat.thread.T1TTokenThread;
import com.lingtoo.wechat.utils.SystemConfig;

/**
 * Created: 2015/10/8.
 * Author: Qiannan Lu
 */

public abstract class BaseController {
    protected static final String STATE_USER_BASE = T1TConstants.STATE_USER_BASE;
    protected static final String STATE_USER_INFO = T1TConstants.STATE_USER_INFO;

    protected static final String SCOPE_BASE = T1TConstants.SCOPE_BASE;
    protected static final String SCOPE_USER_INFO = T1TConstants.SCOPE_USER_INFO;

    protected static final String FORWARD_PAGE = "forward_page";

    protected static final String UTF8 = "utf-8";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${t1t.wechat.oauth2.url}")
    protected String oauth2Url;

    @Value("${t1t.wechat.redirect.url}")
    protected String redirectUrl;

    @Value("${system.image.server}")
    protected String imageServer;

    @Autowired
    protected BaiduAPIService baiduAPIService;

    @Autowired
    protected WechatAPIService wechatAPIService;

    @Autowired
    protected UserService userService;

    protected Map<String, String> signWechatJSAPI(HttpServletRequest request) {
        logger.info(populateFullRequestURL(request));
        Map<String, String> signMap = WechatSignUtil.sign(T1TTokenThread.getjsAPITicket(T1TConstants.APPID_DECORATION), populateFullRequestURL(request));
        signMap.put("appId", T1TConstants.APPID_DECORATION);
        return signMap;
    }
    
    protected String buildWechatShareLink(HttpServletRequest request) {
    	HttpSession session=request.getSession();
    	String appid=(String) session.getAttribute(T1TConstants.SESSION_APPID);
        try {
        	String queryString=request.getQueryString();
        	if(queryString!=null) {
            	if(queryString.indexOf("code")>=0){
            		int index=queryString.indexOf("code");
            		String newQueryString=queryString.substring(0, queryString.indexOf("code"));
            		int newIndex=queryString.indexOf("&",index+4)+1;
            		if(newIndex>=0)
            			newQueryString=newQueryString+queryString.substring(newIndex, queryString.length());
            		queryString=newQueryString;
            	}
        	}
        	String url=redirectUrl + request.getRequestURI()+"?"+queryString;
        	url=url.replaceAll("//", "/");
        	url=url.replaceAll("http:/", "http://");
        	return url;
			/*return WechatUtil.buildWechatShareLink(appid,
					URLEncoder.encode(redirectUrl + request.getRequestURI(), UTF8),
					SCOPE_BASE, appid);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    protected String populateFullRequestURL(HttpServletRequest request) {
    	String url=SystemConfig.getContextPath()+request.getRequestURI();
    	if(request.getQueryString()!=null)
    		return url+("?")+(request.getQueryString()).toString().replaceAll("//","/").replaceAll(":/", "://");
    	else 
    		return url;
    }

    protected int updateUserSubscribeStatus(WechatUser user) {
        int subscribe = wechatAPIService.getWechatUserByOpenId(T1TTokenThread.getAccessToken(), user.getOpenid()).getSubscribe();
        if (1 == subscribe) {
            userService.updateUserSubscribe(user.getWechatUserId());
        }
        return subscribe;
    }

    protected Address parseGPSToLocation(double latitude, double longitude) {
        return baiduAPIService.parseGPSToLocation(latitude, longitude);
    }

    
    protected Manager getBackendSession(HttpServletRequest request) {
    	Manager manager=(Manager)request.getSession().getAttribute(T1TConstants.SESSION_BACKEND);
    	return manager;
    }
    
    protected String getAppidSession(HttpServletRequest request) {
    	return (String)request.getSession().getAttribute(T1TConstants.SESSION_BACKEND_APPID);
    }
    protected Integer getMerchantIdSession(HttpServletRequest request) {
    	return (Integer)request.getSession().getAttribute(T1TConstants.SESSION_BACKEND_MERCHANT_ID);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleException(IllegalArgumentException e) {
        ModelAndView model = new ModelAndView("error");
        e.printStackTrace();
        model.addObject("error", e.getMessage());
        return model;
    }

    @ExceptionHandler(IllegalStateException.class)
    public ModelAndView handleStateException(IllegalStateException e) {
        ModelAndView model = new ModelAndView("error");
        e.printStackTrace();
        model.addObject("error", e.getMessage());
        return model;
    }
    
    
    protected String getAppidMobileSession(HttpServletRequest request) {
    	return (String)request.getSession().getAttribute(T1TConstants.SESSION_APPID);
    }
    
    protected WechatUser getWechatUserSession(HttpServletRequest request) {
    	return (WechatUser) request.getSession().getAttribute(T1TConstants.SESSION_USER);
    }
    
    protected User getUserSession(HttpServletRequest request) {
    	return (User) request.getSession().getAttribute(T1TConstants.SESSION_USER_INFO);
    }
    
    protected Merchant getMerchantMobileSession(HttpServletRequest request) {
    	return (Merchant) request.getSession().getAttribute(T1TConstants.SESSION_MERCHANT);
    }
    
	public boolean checkUserLogin(HttpServletRequest request) {
		User user =getUserSession(request);
		if (user == null) {
			return false;
		}else {
			return true;
		}
	}
}
