package com.lingtoo.wechat.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.pojo.Manager;
import com.lingtoo.wechat.service.LogService;

@Controller
@RequestMapping(value="/backend/log")
public class LogController extends BaseController{
	@Autowired
	private LogService lService; 
	@RequestMapping(value="list")
	public String list(String name,Integer operateId,String beginDate,String endDate, 
			Integer pageNo, Integer pageNum, 
			HttpServletRequest request, HttpSession session, Model model) {
		if(name!=null){
			if("".equals(name.replaceAll(" ",""))){
				name=null;
			}
		}
    	Manager mgr = this.getBackendSession(request);
		PageBean pb=lService.findLogListByMerchant(name,mgr.getMerchantId(), beginDate, endDate, pageNo, pageNum);
		model.addAttribute("pageBean", pb);
		model.addAttribute("beginDate",beginDate);
		model.addAttribute("endDate",endDate);
		model.addAttribute("name",name);
		return "backend/log/list";
	}
}
