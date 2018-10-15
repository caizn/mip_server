package com.lingtoo.wechat.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.pojo.UserWithdraw;
import com.lingtoo.wechat.service.UserWithdrawService;

@Controller("backendWithdrawController")
@RequestMapping("/backend/withdraw")
public class WithdrawController extends BaseController{
	@Autowired
	private UserWithdrawService uWithdrawService;
	@RequestMapping("list")
	public String list(
			String name,Integer userId,Integer status,String uWithdrawNo, Integer pageNo, Integer pageNum, 
			HttpServletRequest request, HttpSession session, Model model) {
		PageBean pageBean=uWithdrawService.getUWithdrawByPage(name,userId,status, uWithdrawNo, pageNo, pageNum);
		model.addAttribute("pageBean", pageBean);
		return "backend/withdraw/list";
	}
	
	@RequestMapping("set-status")
	@ResponseBody
	public String setStatus(
			Integer uWithdrawId,Integer status,
			HttpServletRequest request, HttpSession session, Model model) {
		UserWithdraw uWithdraw=uWithdrawService.getUWithdrawById(uWithdrawId);
		uWithdraw.setStatus(status);
		uWithdrawService.alert(uWithdraw);
		return "success";
	}
}
