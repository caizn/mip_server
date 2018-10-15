package com.lingtoo.wechat.controller.backend;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.lingtoo.wechat.bean.TreeBean;
import com.lingtoo.wechat.pojo.DecorationItem;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.service.DecorationItemService;
import com.lingtoo.wechat.service.DecorationWorkerService;
import com.lingtoo.wechat.utils.JsonUtil;
import com.lingtoo.wechat.utils.StringUtil;

@Controller("backendDecorationWorkerController")
@RequestMapping("/backend/dworker")
public class DecorationWorkerController {
	@Autowired
	private DecorationWorkerService dWorkerService;
	@Autowired
	private DecorationItemService dItemService;
	
	@RequestMapping("list")
	public String list(
			String name,String phone,Integer auditStatus, Integer pageNo, Integer pageNum, 
			HttpServletRequest request, HttpSession session, Model model) {
		model.addAttribute("pageBean",dWorkerService.getDWorkerPage(name,phone, auditStatus, pageNo, pageNum));
		return "backend/user/dworker-list";
	}
	
	@RequestMapping("audit")
	@ResponseBody
	public String audit(
			Integer dWorkerId,Integer auditStatus,
			HttpServletRequest request, HttpSession session, Model model) {
		dWorkerService.auditDWorker(dWorkerId, auditStatus);
		return "success";
	}
	
	@RequestMapping("detail")
	public String detail(
			Integer dWorkerId,
			HttpServletRequest request, HttpSession session, Model model) {
		DecorationWorker dWorker=dWorkerService.getDWorkerById(dWorkerId);
		model.addAttribute("dWorker", dWorker);
		

		List<TreeBean> treeList=new ArrayList<TreeBean>();
		DecorationItem repairDItem=new DecorationItem();
		repairDItem.setTitle("维修");
		repairDItem.setDecorationItemId(-1);
		repairDItem.setType(0);
		treeList.add(new TreeBean(repairDItem, false));
		DecorationItem clearDItem=new DecorationItem();
		clearDItem.setTitle("清洗");
		clearDItem.setDecorationItemId(-2);
		clearDItem.setType(0);
		treeList.add(new TreeBean(clearDItem, false));
		DecorationItem installDItemMap=new DecorationItem();
		installDItemMap.setTitle("安装");
		installDItemMap.setDecorationItemId(-3);
		installDItemMap.setType(0);
		treeList.add(new TreeBean(installDItemMap, false));

		List<DecorationItem> dItemList=dItemService.selectAllDItem();
		for(DecorationItem dItem:dItemList) {
			if(dItem.getType().equals(DecorationItem.TYPE_REPAIR)) {
				dItem.setType(-1);
			}
			if(dItem.getType().equals(DecorationItem.TYPE_CLEAR)) {
				dItem.setType(-2);
			}
			if(dItem.getType().equals(DecorationItem.TYPE_INSTALL)) {
				dItem.setType(-3);
			}
			boolean checked=false;
			if(dWorker.getDecorationItemList()!=null) {
				JSONArray array=JSONArray.parseArray(dWorker.getDecorationItemList());
				for(int i=0;i<array.size();i++) {
					if(array.getInteger(i).equals(dItem.getDecorationItemId())) {
						checked=true;
						break;
					}
				}
			}
			treeList.add(new TreeBean(dItem, checked));
		}

		model.addAttribute("jsonTree",JsonUtil.getJsonString(treeList));
		
		return "backend/user/dworker-detail";
	}
	
	@RequestMapping("save")
	@ResponseBody
	public String save(
			Integer dWorkerId,String dWorkerItemIdsString,
			HttpServletRequest request, HttpSession session, Model model) {
		JSONArray array=new JSONArray();
		if(!StringUtil.isEmpty(dWorkerItemIdsString)) {
			String[] dWorkerItemIds=dWorkerItemIdsString.split(",");
			for(String dWorkerItemString:dWorkerItemIds) {
				if(!StringUtil.isEmpty(dWorkerItemString)) {
					Integer dWorkerItem=Integer.valueOf(dWorkerItemString);
					if(dWorkerItem>0) {
						array.add(dWorkerItem);
					}
				}
			}
		}

		DecorationWorker dWorker=dWorkerService.getDWorkerById(dWorkerId);
		dWorker.setDecorationItemList(array.toString());
		dWorkerService.updateDWorker(dWorker);
		return "success";
	}
}
