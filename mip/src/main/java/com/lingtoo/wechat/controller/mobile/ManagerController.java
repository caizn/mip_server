package com.lingtoo.wechat.controller.mobile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.dao.DecorationWorkerDAO;
import com.lingtoo.wechat.pojo.DecorationOrder;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.service.DecorationOrderService;

@Controller("MobileManagerController")
@RequestMapping("/mobile/manager")
public class ManagerController extends BaseController{
	@Autowired
	private DecorationOrderService dOrderService;
	@Autowired
	private DecorationWorkerDAO dWorkerDao;
	
	@RequestMapping("order-detail-show")
	public String orderDetailShow(
    		Integer dOrderId,
			HttpSession session, Model model, HttpServletRequest request) {
    	DecorationOrder dOrder=dOrderService.getDOrderById(dOrderId);
    	model.addAttribute("dOrder", dOrder);
		model.addAllAttributes(signWechatJSAPI(request));
    	return "/mobile/manager/order-detail-show";
	}

	
	@RequestMapping("order-detail")
	public String orderDetail(
    		Integer dOrderId,
			HttpSession session, Model model, HttpServletRequest request) {
    	DecorationOrder dOrder=dOrderService.getDOrderById(dOrderId);
		List<DecorationWorker> dWorkerList = dWorkerDao.selectDWorkersByArea(dOrder.getReceiveProvince(),
				dOrder.getReceiveCity(), null,dOrder.getDecorationItemId().toString());
		model.addAttribute("dWorkerList", dWorkerList);
    	model.addAttribute("dOrder", dOrder);
		model.addAllAttributes(signWechatJSAPI(request));
    	return "/mobile/manager/order-detail";
	}

	@RequestMapping("set-status")
	@ResponseBody
	public String setStatus(
    		Integer dOrderId,Double evaluatePrice,Double dispatchPrice,Integer status,Integer dWorkerId,
			HttpSession session, Model model, HttpServletRequest request) {
    	return dOrderService.setStatus(dOrderId, evaluatePrice, dispatchPrice,null,null,dWorkerId,null,"mobile", status);
	}
}
