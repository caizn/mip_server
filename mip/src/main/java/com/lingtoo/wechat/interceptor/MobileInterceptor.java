package com.lingtoo.wechat.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lingtoo.common.utils.StringUtils;
import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.dao.MerchantDAO;
import com.lingtoo.wechat.dao.RoleDAO;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.pojo.Merchant;
import com.lingtoo.wechat.pojo.MerchantApp;
import com.lingtoo.wechat.pojo.Role;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.WebStatistics;
import com.lingtoo.wechat.pojo.WechatUser;
import com.lingtoo.wechat.service.MerchantService;
import com.lingtoo.wechat.service.UserService;
import com.lingtoo.wechat.service.WebStatisticsService;
import com.lingtoo.wechat.service.WechatAPIService;
import com.lingtoo.wechat.servlet.InitServlet;
import com.lingtoo.wechat.utils.StringUtil;
import com.lingtoo.wechat.utils.WechatUtil;

/**
 * Created: 2015/10/10. Author: Qiannan Lu
 */
public class MobileInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(MobileInterceptor.class);
	@Autowired
	private MerchantService mService;
	@Autowired
	private MerchantDAO mDao;
	@Value("${t1t.wechat.oauth2.url}")
	protected String oauth2Url;

	@Value("${t1t.wechat.redirect.url}")
	protected String redirectUrl;
    @Autowired
    private RoleDAO rDao;
    @Autowired
    private WebStatisticsService wsService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDao;
    
    @Autowired
    private WechatAPIService wechatAPIService;

    ThreadLocal<String> localAppId = new ThreadLocal<>();

	private String[] expUrl = { "/backend", "market", "notify-api" };// 不过滤的 backend 后台 market
														// 市场推广
	/**
	 * 不拦截的URL
	 * 
	 * @param request
	 * @return
	 */
	private String appId;
	private String appSecret;

	private boolean isExp(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		for (String exp : expUrl) {
			if (url.indexOf(exp) > -1) {
				return true;
			}
		}
		return false;
	}

	private void printRequestParams(HttpServletRequest request) {
		Map map = new HashMap();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					map.put(paramName, paramValue);
				}
			}
		}

		Set<Map.Entry<String, String>> set = map.entrySet();
		System.out.println("--------------request param start----------------");
		for (Map.Entry entry : set) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println("---------------request param end---------------");

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		String url=request.getRequestURL().toString();
        appId = request.getParameter(T1TConstants.WECHAT_STATE);
		logger.info("[register]Request URL::" + url + ":"+appId+": Start Time=" + System.currentTimeMillis());
        
        if(isExp(request))return true;
		
        HttpSession session = request.getSession();
        WechatUser wechatuser =new WechatUser();
        
        if(appId==null) appId=T1TConstants.APPID_DECORATION;
        MerchantApp app=mService.findMerchantAppByAppId(appId);        
        
        Merchant merchant=(Merchant) session.getAttribute(T1TConstants.SESSION_MERCHANT);
        if(merchant!=null){
        	if(!merchant.getMerchantId().equals(app.getMerchantId())){
        		merchant=mDao.getMerchantById(app.getMerchantId());
        		Role role=rDao.getRole(merchant.getRoleId());
        		merchant.setRoleName(role.getName());
        	}
        }else{
    		merchant=mDao.getMerchantById(app.getMerchantId());
    		Role role=rDao.getRole(merchant.getRoleId());
    		merchant.setRoleName(role.getName());
        }
        
        if (Boolean.valueOf(request.getParameter("debug")) || Boolean.valueOf((String) session.getAttribute("debug"))) {
        	wechatuser = userDao.selectWechatUserByOpenId("o2pbuv5k3xAe6ZwJP84LgjOTb6eQ");
            session.setAttribute("debug", "true");
        }else {
        	wechatuser=(WechatUser) session.getAttribute(T1TConstants.SESSION_USER);
        	if(wechatuser==null) {

                String code = request.getParameter(T1TConstants.WECHAT_CODE);
                String openId = request.getParameter(T1TConstants.WECHAT_OPEN_ID);
                if(!StringUtil.isEmpty(openId)){
	            	wechatuser = userDao.selectWechatUserByOpenId(openId);
	            	if(wechatuser==null){
	            		wechatuser=new WechatUser();
	            		wechatuser.setMerchantId(merchant.getMerchantId());
	        			wechatuser.setAppid(T1TConstants.APPID_DECORATION);
	            		wechatuser.setCreateTime(new Date());
	            		userDao.insertWechatUser(wechatuser);
	            		wechatuser=userDao.selectWechatUserByOpenId(openId);
	            	}
                }else if(code==null) {
                	if(request.getQueryString()!=null)
                		response.sendRedirect(WechatUtil.buildWechatUrl(appId, redirectUrl + request.getRequestURI().toString()+"?"+(request.getQueryString()).toString().replaceAll("//","/").replaceAll(":/", "://")));
                	else
                		response.sendRedirect(WechatUtil.buildWechatUrl(appId, redirectUrl + request.getRequestURI().toString()));
                		
                	return false;
                }else {
	                localAppId.set(request.getParameter(T1TConstants.WECHAT_STATE));
	                HashMap<String, Object> params = wechatAPIService.getOAuthAccessToken(code, localAppId.get(), InitServlet.appMap.get(localAppId.get()));
	
	                openId = (String) params.get(T1TConstants.WECHAT_OPEN_ID);
	                logger.info("--------open Id: " + openId);
	                if (StringUtils.isEmpty(openId)) {
	                    /*return false;*/
	                    try {
	                    	String newUrl="";
	                    	if(request.getQueryString()!=null)
	                    		newUrl=WechatUtil.buildWechatShareLink(T1TConstants.APPID_DECORATION,
		            					URLEncoder.encode(redirectUrl + request.getRequestURI()+"?state=" + request.getParameter(T1TConstants.WECHAT_STATE)+(request.getQueryString()).toString().replaceAll("//","/").replaceAll(":/", "://"), "utf-8"),
		            					T1TConstants.SCOPE_BASE, request.getParameter(T1TConstants.WECHAT_STATE));
	                    	else
	                    		newUrl=WechatUtil.buildWechatShareLink(T1TConstants.APPID_DECORATION,
	            					URLEncoder.encode(redirectUrl + request.getRequestURI()+"?state=" + request.getParameter(T1TConstants.WECHAT_STATE), "utf-8"),
	            					T1TConstants.SCOPE_BASE, request.getParameter(T1TConstants.WECHAT_STATE));
	
	        				response.sendRedirect(newUrl);
	            			return false;
	            		} catch (UnsupportedEncodingException e) {
	            			// TODO Auto-generated catch block
	            			e.printStackTrace();
	            		}
	                }
	            	wechatuser = userDao.selectWechatUserByOpenId(openId);
	            	if(wechatuser==null){
	                    String oAuthAccessToken = (String) params.get("access_token");
	            		wechatuser=wechatAPIService.getWechatUser(oAuthAccessToken, openId);
	            		wechatuser.setMerchantId(merchant.getMerchantId());
	        			wechatuser.setAppid(T1TConstants.APPID_DECORATION);
	            		wechatuser.setCreateTime(new Date());
	            		userDao.insertWechatUser(wechatuser);
	            		wechatuser=userDao.selectWechatUserByOpenId(openId);
	            	}else {
	                    String oAuthAccessToken = (String) params.get("access_token");
	            		WechatUser newWechatuser=wechatAPIService.getWechatUser(oAuthAccessToken, openId);
	            		wechatuser.setHeadimgurl(newWechatuser.getHeadimgurl());
	            		wechatuser.setUnionid(newWechatuser.getUnionid());
	            		wechatuser.setNickname(newWechatuser.getNickname());
	            		wechatuser.setGender(newWechatuser.getGender());
	            		wechatuser.setCity(newWechatuser.getCity());
	            		wechatuser.setProvince(newWechatuser.getProvince());
	            		wechatuser.setCountry(newWechatuser.getCountry());
	            		userDao.updateWechatUser(wechatuser);
	            	}
                }
        	}
        }
        session.setAttribute(T1TConstants.SESSION_USER, wechatuser);
        
        if(Objects.nonNull(appId)){
            session.setAttribute(T1TConstants.SESSION_APPID, appId);//公众号对应的appId
            session.setAttribute(T1TConstants.SESSION_MERCHANT_ID, app.getMerchantId());
    		session.setAttribute(T1TConstants.SESSION_MERCHANT, merchant);

    		User new_user=userDao.selectUserByOpenId(wechatuser.getOpenid());
    		if(new_user==null) {
    			new_user=new User();
    			new_user.setCreateTime(new Date());
    			new_user.setState(0);
    			new_user.setMoney(0.0);
    			new_user.setIntegral(0);
    			new_user.setIntroduceCount(0);
    			new_user.setManagerSign(0);
    			new_user.setWechatUserId(wechatuser.getWechatUserId());
    			userDao.insertUser(new_user);
    			new_user=userDao.selectUserByOpenId(wechatuser.getOpenid());
    		}
			session.setAttribute(T1TConstants.SESSION_USER_INFO, new_user);
            webStatistics(request, new_user, app.getMerchantId());     
            

            return true;
        }else{
        	return false;
        }
	}
    
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

    private void webStatistics(HttpServletRequest request,User user,Integer merchantId){
        if(request.getHeader("x-requested-with")==null){
        	WebStatistics ws=new WebStatistics();
        	String ip=null;
        	if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
                ip = request.getHeader("Proxy-Client-IP");  
            }  
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
                ip = request.getRemoteAddr();  
            }  
            ws.setIp(ip);
        	ws.setCreateTime(new Date());
        	ws.setLink(request.getRequestURI());
        	ws.setMerchantId(merchantId);
        	ws.setUserId(user.getUserId());
        	wsService.add(ws);
        }
    }
}
