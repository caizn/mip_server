package com.lingtoo.wechat.controller.backend;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.bean.ResponseInfo;
import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.dao.MerchantDAO;
import com.lingtoo.wechat.pojo.Manager;
import com.lingtoo.wechat.pojo.Menu;
import com.lingtoo.wechat.pojo.Merchant;
import com.lingtoo.wechat.pojo.MerchantApp;
import com.lingtoo.wechat.service.ManagerService;
import com.lingtoo.wechat.service.RoleService;
import com.lingtoo.wechat.utils.StringUtil;

@Controller
@RequestMapping("/backend")
public class LoginController extends BaseController {
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MerchantDAO merchantDAO;

	// 登录
	@RequestMapping(value = "login")
	public String login(Integer checkAccount,String act, String _account, String _pwd, 
			HttpServletRequest request, HttpSession session,HttpServletResponse response,
			Model model) {
		String msg = "";
		if ("login".equals(act)) {
			ResponseInfo responseInfo = managerService.managerLogin(_account, _pwd, request.getRemoteAddr());
			if ("0".equals(responseInfo.getRespCode())) {
				String appid = "";
				// 菜单列表
				Manager manager = (Manager) responseInfo.getResult();
				Merchant merchant = null;
				// 获取APPID
				if (!StringUtil.isEmpty(manager.getMerchantId())) {
					MerchantApp merchantApp = merchantDAO.getMerchantAppByMerchantId(manager.getMerchantId());
					if (merchantApp != null) {
						manager.setAppId(merchantApp.getAppId());
						appid = manager.getAppId();
					}
					merchant = merchantDAO.getMerchantById(manager.getMerchantId());
				}

				// 系统管理可以查询所有的数据
				if (Manager.PLATFORM_SYS.equals(manager.getPlatform())) {
					appid = "-1";
				}

				if (session.getAttribute(T1TConstants.SESSION_BACKEND_APPID) != null)
					session.removeAttribute(T1TConstants.SESSION_BACKEND_APPID);
				session.setAttribute(T1TConstants.SESSION_BACKEND_APPID, appid);

				if (session.getAttribute(T1TConstants.SESSION_BACKEND_MERCHANT_ID) != null)
					session.removeAttribute(T1TConstants.SESSION_BACKEND_MERCHANT_ID);
				session.setAttribute(T1TConstants.SESSION_BACKEND_MERCHANT_ID, manager.getMerchantId());

				if (session.getAttribute(T1TConstants.SESSION_MY_MENU_MAP) != null)
					session.removeAttribute(T1TConstants.SESSION_MY_MENU_MAP);
				List<Menu> menus = roleService.findMenuListByRoleId(manager.getRoleId(), session);

				if (session.getAttribute(T1TConstants.SESSION_MY_MENU) != null)
					session.removeAttribute(T1TConstants.SESSION_MY_MENU);
				session.setAttribute(T1TConstants.SESSION_MY_MENU, menus);

				if (session.getAttribute(T1TConstants.SESSION_BACKEND) != null)
					session.removeAttribute(T1TConstants.SESSION_BACKEND);
				session.setAttribute(T1TConstants.SESSION_BACKEND, manager);

				if (session.getAttribute(T1TConstants.SESSION_BACKEND_MERCHANT) != null)
					session.removeAttribute(T1TConstants.SESSION_BACKEND_MERCHANT);
				session.setAttribute(T1TConstants.SESSION_BACKEND_MERCHANT, merchant);
				
				if(checkAccount==1){
		            Cookie cookie = new Cookie("_account",manager.getAccount());
		            cookie.setMaxAge(36000000);
		            response.addCookie(cookie);		            
				}else{
					Cookie NewCookie=new Cookie("_account",null); //将之前设置NewCookie值设置为空
					NewCookie.setMaxAge(0);
				}

                /*if(merchant.getType() > 0){//区/市/省一级的教育局主办方
                    return "redirect:/backend/contest/list";
                }else{*/
                    return "redirect:/backend/user/list";
                //}
			} else {
				msg = responseInfo.getRespDesc();
			}
		}
		model.addAttribute("msg", msg);
		return "backend/login";
	}

	// 登录
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request, HttpSession session, Model model) {
		if (session.getAttribute(T1TConstants.SESSION_BACKEND) != null) {
			session.removeAttribute(T1TConstants.SESSION_BACKEND);
		}
		if (session.getAttribute(T1TConstants.SESSION_MY_MENU) != null)
			session.removeAttribute(T1TConstants.SESSION_MY_MENU);
		return "backend/login";
	}

}
