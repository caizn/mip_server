package com.lingtoo.wechat.controller.backend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.dao.DecorationWorkerDAO;
import com.lingtoo.wechat.pojo.DecorationOrder;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.service.DecorationOrderService;

@Controller("backendOrderController")
@RequestMapping("/backend/order")
public class OrderController {
	@Autowired
	private DecorationOrderService dOrderService;
	@Autowired
	private DecorationWorkerDAO dWorkerDao;
	
	@RequestMapping("list")
	public String list(
			String orderNo,String telephone,String name,Integer emergencyStatus,Integer type,Integer status,
			Integer pageNo,  Integer pageSize,
			HttpServletRequest request, HttpSession session, Model model) {
		model.addAttribute("pageBean", dOrderService.getDOrderPage(orderNo, telephone, name, emergencyStatus, type, status, pageNo, pageSize));
		return "backend/dorder/list";
	}
	
	@RequestMapping("detail")
	public String detail(
			Integer dOrderId,
			HttpServletRequest request, HttpSession session, Model model) {
		DecorationOrder dOrder=dOrderService.getDOrderById(dOrderId);
		model.addAttribute("dOrder", dOrder);
		List<DecorationWorker> dWorkerList = dWorkerDao.selectDWorkersByArea(dOrder.getReceiveProvince(),
				dOrder.getReceiveCity(), dOrder.getReceiveArea(),dOrder.getDecorationItemId().toString());
		model.addAttribute("dWorkerList", dWorkerList);
		return "backend/dorder/detail";
	}

	@RequestMapping("set-status")
	@ResponseBody
	public String setStatus(
    		Integer dOrderId,Double evaluatePrice,Double dispatchPrice,Integer status,Integer dWorkerId,
			HttpSession session, Model model, HttpServletRequest request) {
    	return dOrderService.setStatus(dOrderId, evaluatePrice, dispatchPrice,null,null,dWorkerId,null,"backend", status);
	}
}
