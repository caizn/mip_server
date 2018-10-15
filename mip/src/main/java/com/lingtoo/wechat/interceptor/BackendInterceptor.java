package com.lingtoo.wechat.interceptor;



import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.pojo.Manager;
import com.lingtoo.wechat.service.RoleService;
import com.lingtoo.wechat.utils.StringUtil;




public class BackendInterceptor extends HandlerInterceptorAdapter {
	 private static final Logger logger = LoggerFactory.getLogger(BackendInterceptor.class);
	
	@Autowired
	private RoleService roleService;
	
	private String[] expUrl={"backend/login","backend/find-pwd"
			,"backend/update-find-pwd","backend/verification/send/code","backend/live/liveDetail"};//不过滤的
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String url=request.getRequestURL().toString();
		logger.info("[backgroud]Request URL::" + url + ":: Start Time=" + System.currentTimeMillis());
		Manager manager=(Manager)request.getSession().getAttribute(T1TConstants.SESSION_BACKEND);
		
		if(isExp(request)){
			return true;
		}
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		if(manager==null){
			logger.info("[backend]login out time:"); 
			response.sendRedirect(basePath+"/backend/login");
			return false;   
		}
		String pageURL = request.getRequestURI();
		if(!StringUtil.isEmpty(pageURL)){
			Map<String,String> roleMenuMap=(Map<String,String>)request.getSession().getAttribute(T1TConstants.SESSION_MY_MENU_MAP);
			pageURL=pageURL.replace(path+"backend/", "");
			if(roleService.getMenuMap().get(pageURL)!=null && roleMenuMap.get(pageURL)==null){
				logger.info("用户:" + manager.getAccount() +  "没有访问URL:" + pageURL + "的权限！");
				response.sendRedirect(basePath+"backend/noPrivilege");
				return false;   
			}	
		}
		return true;
	}

	/**
	 * 不拦截的URL
	 * @param request
	 * @return
	 */
	private boolean isExp(HttpServletRequest request){
		String url=request.getRequestURL().toString();
		for(String exp:expUrl){
			if(url.indexOf(exp)>-1){
				return true;
			}
		}
		return false;
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

    private void printRequestParams(HttpServletRequest request){
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
        System.out.print("request param: ");
        for (Map.Entry entry : set) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " , ");
        }
        System.out.println();

    }
}
