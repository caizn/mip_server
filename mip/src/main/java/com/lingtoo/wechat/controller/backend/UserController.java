package com.lingtoo.wechat.controller.backend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.UserAddress;
import com.lingtoo.wechat.service.UserService;

@Controller("backendUserController")
@RequestMapping("/backend/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping("list")
	public String list(
			Integer introducerId,
			String nickname, Integer managerSign,
			Integer pageNo, Integer pageNum, 
			HttpServletRequest request, HttpSession session, Model model) {
		PageBean pageBean=userService.getUserPageBean(introducerId,null,  nickname,null, null, 0, managerSign, pageNo, pageNum);
		model.addAttribute("pageBean", pageBean);
		if(introducerId!=null){
			User introducer=userDao.selectUserById(introducerId);
			model.addAttribute("introducer", introducer);
		}
		return "/backend/user/user-list";
	}
	
	@RequestMapping("user-address")
	public String userAddress(Integer userId,
			HttpServletRequest request, HttpSession session, Model model) {
		List<UserAddress> userAddressList=userDao.selectUserAddressByUserId(userId);
		model.addAttribute("userAddressList", userAddressList);
		return "/backend/user/user-address";
	}
	
	@RequestMapping("set-manager-sign")
	@ResponseBody
	public String setManagerSign(
			Integer userId,Integer managerSign,
			HttpServletRequest request, HttpSession session, Model model) {
		userService.setManagerSign(userId, managerSign);
		return "success";
	}
	
	@RequestMapping("detail")
	public String detail(
			Integer userId,
			HttpServletRequest request, HttpSession session, Model model) {
		User user=userService.getUser(userId);
		model.addAttribute("user", user);
		return "/backend/user/detail";
	}
}
