package com.lingtoo.wechat.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.service.UserService;
import com.lingtoo.wechat.service.WechatAPIService;

@Controller("backendGengeralizeController")
@RequestMapping("/backend/gengeralize")
public class GengeralizeController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping("list")
	public String list(
			String phone,
			String name,Integer auditStatus, Integer pageNo, Integer pageNum, 
			HttpServletRequest request, HttpSession session, Model model) {
		PageBean pageBean=userService.getUserPageBean(null,phone,null, name, auditStatus, 1,null, pageNo, pageNum);
		model.addAttribute("pageBean", pageBean);
		return "backend/user/gengeralize-list";
	}
	
	@RequestMapping("audit-user")
	@ResponseBody
	public String auditUser(
			Integer userId,		Integer auditStatus,
			HttpServletRequest request, HttpSession session, Model model) {
		User user=userDao.selectUserById(userId);
		user.setAuditStatus(auditStatus);

		/*if(auditStatus==0) {
			JSONObject object=WechatAPIService.getQrcode(T1TConstants.APPID_DECORATION, user.getUserId());
			String url=(String) object.get("url");
			user.setGeneralizeQrcodeUrl(url);
		}*/
		userDao.updateUser(user);
		return "success";
	}
	
	@RequestMapping("detail")
	public String detail(
			Integer userId,
			HttpServletRequest request, HttpSession session, Model model) {
		model.addAttribute("user", userDao.selectUserById(userId));
		return "backend/user/gengeralize-detail";
	}
}
