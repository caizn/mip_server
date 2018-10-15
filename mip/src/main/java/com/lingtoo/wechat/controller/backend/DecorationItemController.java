package com.lingtoo.wechat.controller.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.pojo.DecorationItem;
import com.lingtoo.wechat.service.DecorationItemService;
import com.lingtoo.wechat.utils.StringUtil;

import ch.qos.logback.classic.pattern.Util;

@Controller("backendDecorationItemController")
@RequestMapping("/backend/ditem")
public class DecorationItemController {
	@Autowired
	private DecorationItemService dItemService;
	
	@RequestMapping("list")
	public String list(
			Integer type, Integer pageNo, Integer pageNum, 
			HttpServletRequest request, HttpSession session, Model model) {
		List<DecorationItem> dItemList=dItemService.selectAllDItem();
		Map<String,List<DecorationItem>> repairDItemMap=new HashMap<String,List<DecorationItem>>();
		Map<String,List<DecorationItem>> clearDItemMap=new HashMap<String,List<DecorationItem>>();
		Map<String,List<DecorationItem>> installDItemMap=new HashMap<String,List<DecorationItem>>();
		for(DecorationItem dItem:dItemList) {
			List<DecorationItem> subDItem=null;
			if(dItem.getType().equals(DecorationItem.TYPE_REPAIR)) {
				if(StringUtil.isEmpty(dItem.getSubTitle())) dItem.setSubTitle("0");
				if(repairDItemMap.get(dItem.getSubTitle())==null) {
					subDItem=new ArrayList<DecorationItem>();
				}else {
					subDItem=repairDItemMap.get(dItem.getSubTitle());
				}
				subDItem.add(dItem);
				repairDItemMap.put(dItem.getSubTitle(),subDItem);
			}
			if(dItem.getType().equals(DecorationItem.TYPE_CLEAR)) {
				if(StringUtil.isEmpty(dItem.getSubTitle())) dItem.setSubTitle("0");
				if(clearDItemMap.get(dItem.getSubTitle())==null) {
					subDItem=new ArrayList<DecorationItem>();
				}else {
					subDItem=clearDItemMap.get(dItem.getSubTitle());
				}
				subDItem.add(dItem);
				clearDItemMap.put(dItem.getSubTitle(),subDItem);
			}
			if(dItem.getType().equals(DecorationItem.TYPE_INSTALL)) {
				if(StringUtil.isEmpty(dItem.getSubTitle())) dItem.setSubTitle("0");
				if(installDItemMap.get(dItem.getSubTitle())==null) {
					subDItem=new ArrayList<DecorationItem>();
				}else {
					subDItem=installDItemMap.get(dItem.getSubTitle());
				}
				subDItem.add(dItem);
				installDItemMap.put(dItem.getSubTitle(),subDItem);
			}
		}
		model.addAttribute("repairDItemMap", repairDItemMap);
		model.addAttribute("clearDItemMap", clearDItemMap);
		model.addAttribute("installDItemMap", installDItemMap);
		return "backend/ditem/list";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(
			Integer dItemId,
			HttpServletRequest request, HttpSession session, Model model) {
		dItemService.deleteDItem(dItemId);
		return "success";
	}
	
	@RequestMapping("to-edit")
	public String toEdit(
			Integer dItemId,
			HttpServletRequest request, HttpSession session, Model model) {
		if(dItemId!=0) {
			model.addAttribute("dItem", dItemService.selectDItemById(dItemId));
		}
		return "backend/ditem/edit";
	}
	
	@RequestMapping("save")
	@ResponseBody
	public String save(
			Integer dItemId,String title,Integer type,String subTitle,Integer subType,
			HttpServletRequest request, HttpSession session, Model model) {
		DecorationItem dItem=new DecorationItem();
		if(dItemId!=0) {
			dItem=dItemService.selectDItemById(dItemId);
		}
		dItem.setTitle(title);
		if(subTitle!=null)dItem.setSubTitle(subTitle);
		if(subType!=null)dItem.setSubType(subType);
		if(type!=null)dItem.setType(type);
		if(dItemId!=0) {
			dItemService.updateDItem(dItem);
		}else{
			dItem.setCreateTime(new Date());
			dItem.setState(0);
			dItemService.addDItem(dItem);
		}
		return "success";
	}
}
