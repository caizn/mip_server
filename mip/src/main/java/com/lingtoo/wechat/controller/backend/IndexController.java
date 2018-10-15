package com.lingtoo.wechat.controller.backend;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.pojo.Manager;

/**
 * 赛事控制类
 * 
 * @author wj.chen
 *
 */
@Controller
@RequestMapping("/backend")
public class IndexController extends BaseController {
	@Autowired
	private UserDAO userDao;
	// 首页
	@RequestMapping(value = "show-index")
	public String showIndex(String sort, String type, Integer pageNo, Integer pageNum, HttpServletRequest request,
			HttpSession session, Model model) {
		return "/backend/show-index";
	}

	// 首页
	@RequestMapping(value = "index")
	public String index(String sort, String type, Integer pageNo, Integer pageNum, HttpServletRequest request,
			HttpSession session, Model model) {
		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		Date now=new Date();
		Date yesterDay=new Date(now.getTime()-24*60*60*1000);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String yesterDayString=sdf.format(yesterDay);
		Integer yesterDayVisitCount=userDao.selectUserVisitCount(managerSession.getMerchantId(), yesterDayString).size();
		model.addAttribute("yesterDayVisitCount", yesterDayVisitCount);
		
		return "/backend/index";
	}

	// 没权限页面
	@RequestMapping(value = "noPrivilege")
	public String noPrivilege() {
		return "backend/noPrivilege";
	}

}
