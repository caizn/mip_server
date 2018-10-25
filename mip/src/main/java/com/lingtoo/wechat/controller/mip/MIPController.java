package com.lingtoo.wechat.controller.mip;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.pojo.mip.MIPAccount;
import com.lingtoo.wechat.pojo.mip.MIPMessage;
import com.lingtoo.wechat.pojo.mip.MIPMessageType;
import com.lingtoo.wechat.service.mip.MIPService;

@Controller("MIPController")
@RequestMapping("/mip/mobile")
public class MIPController {
	@Autowired
	private MIPService mipService;
	
	@RequestMapping("list")
	public String list(
			HttpServletRequest request, HttpSession session, Model model) {
		List<MIPAccount> mipAccountList=mipService.getMIPAccountList();
		model.addAttribute("mipAccountList", mipAccountList);
		return "mip/mobile/list";
	}

	@RequestMapping("edit")
	public String edit(
			String spCode,
			HttpServletRequest request, HttpSession session, Model model) {
		MIPAccount mipAccount=mipService.getMIPAccountBySpCode(spCode);
		List<MIPMessageType> mipMsgTypeList=mipService.getMIPMsgType();
		List<MIPMessage> mipMsgList=mipService.getMIPMsgByAcountId(mipAccount.getMipAccountId());
		List<MIPMessageType> nowMipMsgTypeList=mipMsgTypeList;
		for(int i=0;i<nowMipMsgTypeList.size();i++) {
			List<MIPMessage> newmipmsgList=new ArrayList<MIPMessage>();
			for(int j=0;j<mipMsgList.size();j++) {
				if(mipMsgList.get(j).getMipMessageTypeId().equals(nowMipMsgTypeList.get(i).getMipMessageTypeId())) {
					newmipmsgList.add(mipMsgList.get(j));
				}
			}
			nowMipMsgTypeList.get(i).setMipMessageList(newmipmsgList);
		}
		model.addAttribute("mipAccount", mipAccount);
		model.addAttribute("mipMsgTypeList", mipMsgTypeList);
		model.addAttribute("mipMsgList", mipMsgTypeList);
		return "mip/mobile/edit";
	}

	@RequestMapping("delete-mip-msg")
	@ResponseBody
	public String deleteMipMsg(
			Integer mipMsgId,
			HttpServletRequest request, HttpSession session, Model model) {
		mipService.deleteMIPMsg(mipMsgId);
		return "success";
	}
	
	@RequestMapping("save-mip-msg")
	@ResponseBody
	public String saveMipMsg(
			String spCode,String MipMsgTypeId,String jumpUrl,String imgUrl,Integer mipMsgId,
			HttpServletRequest request, HttpSession session, Model model) {
		MIPAccount mipAccount=mipService.getMIPAccountBySpCode(spCode);
		MIPMessage mipMsg=new MIPMessage();
		if(!mipMsgId.equals(0)) {
			mipMsg=mipService.getMIPMsgByMsgId(mipMsgId);
		}
		mipMsg.setJumpUrl(jumpUrl);
		mipMsg.setImgUrl(imgUrl);
		if(mipMsgId.equals(0)) {
			mipMsg.setMipAccountId(mipAccount.getMipAccountId());
			mipMsg.setMipMessageTypeId(MipMsgTypeId);
			mipService.addMIPMsg(mipMsg);
		}else {
			mipService.updateMIPMsg(mipMsg);
		}
		return "success";
	}

	@RequestMapping("show")
	public String show(
			String spCode,
			HttpServletRequest request, HttpSession session, Model model) {
		return edit(spCode, request, session, model);
	}
}
