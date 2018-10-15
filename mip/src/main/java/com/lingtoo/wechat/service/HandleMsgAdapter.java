package com.lingtoo.wechat.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.marker.weixin.HandleMessageListener;
import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4ImageText;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;

import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.bean.wechat.Msg4EventNew;
import com.lingtoo.wechat.bean.wechat.Msg4KF;
import com.lingtoo.wechat.bean.wechat.Msg4Order;
import com.lingtoo.wechat.bean.wechat.WxReplyInfo;
import com.lingtoo.wechat.bean.wechat.WxReplyLocation;
import com.lingtoo.wechat.bean.wechat.WxSendmsgInfo;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.WechatUser;
import com.lingtoo.wechat.service.UserService;
import com.lingtoo.wechat.utils.StringUtil;

/**
 * 自定义信息读写工具
 * 
 * @author kirby24
 * 
 */
public class HandleMsgAdapter implements HandleMessageListener {

	private static Logger log = Logger.getLogger(HandleMsgAdapter.class);

	private QiduSessionAction session = null;
	private String cid = null;
	private String rid = null;
	private UserService userService=null;
	private UserDAO userDao=null;

	public HandleMsgAdapter(UserService userService,UserDAO userDao,QiduSessionAction session, String cid, String rid) {
		this.userService=userService;
		this.userDao=userDao;
		this.session = session;
		this.cid = cid;
		this.rid = rid;
	}

	/**
	 * 接收到文本信息
	 * 
	 * @param msg
	 * @param url
	 */
	public void onTextMsg(Msg4Text msg, String url) {
		log.warn("收到的信息：" + msg.getContent());
		Msg reply = null;
		String infoType = "text";
		String info = msg.getContent();
		reply = getReplyMsg(infoType, info, msg, url);
		reply.setFromUserName(msg.getToUserName());
		reply.setToUserName(msg.getFromUserName());
		WxSendmsgInfo infox = new WxSendmsgInfo();
		if (msg.getCreateTime().equals(reply.getCreateTime())) {
			infox.setMsgid(msg.getMsgId());
		} else
			infox.setMsgid("");
		session.callback(reply, cid, infox);
		return;
	}

	/**
	 * 接收到图片信息
	 * 
	 * @param msg
	 * @param url
	 */
	public void onImageMsg(Msg4Image msg, String url) {
		log.warn("收到的信息：" + msg.getPicUrl());
		String infoType = "image";
		Msg reply = getReplyMsg(infoType, null, msg, url);
		reply.setFromUserName(msg.getToUserName());
		reply.setToUserName(msg.getFromUserName());
		WxSendmsgInfo info = new WxSendmsgInfo();
		if (msg.getCreateTime().equals(reply.getCreateTime())) {
			info.setMsgid(msg.getMsgId());
		} else
			info.setMsgid("");
		session.callback(reply, cid, info);
		return;
	}

	/**
	 * 接收到事件信息
	 * 
	 * @param msg
	 * @param url
	 */
	public void onEventMsg(Msg4EventNew msg, String url) {
		log.warn("收到的信息：" + msg.getEvent());
		String infoType = "event";
		String event = msg.getEvent();
		String eventkey = msg.getEventKey();
		Msg reply = null;
		if (event.equals("unsubscribe") || event.equals("UNSUBSCRIBE")) {
			reply = getReplyMsg(infoType, event, eventkey, msg, url);
		} else if (event.equals("subscribe") || event.equals("SUBSCRIBE")) {
			reply = getReplyMsg(infoType, event, eventkey, msg, url);
		} else if (event.equals("SCAN") || event.equals("SCAN")) {
			reply = getReplyMsg(infoType, event, eventkey, msg, url);
		} else if (event.equals("click") || event.equals("CLICK"))
			reply = getReplyMsg(infoType, event, eventkey, msg, url);
		else if (event.equals("merchant_order")
				|| event.equals("MERCHANT_ORDER"))
			reply = getReplyMsg(infoType, event, eventkey, msg, url);
		else if (event.equals("user_get_card") || event.equals("USER_GET_CARD"))
			reply = getReplyMsg(infoType, event, eventkey, msg, url);
		else
			reply = getReplyMsg(infoType, event, eventkey, msg, url);
		if (reply != null) {
			reply.setFromUserName(msg.getToUserName());
			reply.setToUserName(msg.getFromUserName());
		}else {
			reply=new Msg4Text();
			reply.setFromUserName(msg.getToUserName());
			reply.setToUserName(msg.getFromUserName());			
		}
		WxSendmsgInfo info = new WxSendmsgInfo();
		info.setMsgid("");
		session.callback(reply, cid, info);
		return;
	}

	/**
	 * 接收链接信息
	 * 
	 * @param msg
	 * @param url
	 */
	public void onLinkMsg(Msg4Link msg, String url) {
		log.warn("收到的信息：" + msg.getUrl());
		String infoType = "link";
		Msg reply = getReplyMsg(infoType, null, msg, url);
		reply.setFromUserName(msg.getToUserName());
		reply.setToUserName(msg.getFromUserName());
		WxSendmsgInfo info = new WxSendmsgInfo();
		if (msg.getCreateTime().equals(reply.getCreateTime())) {
			info.setMsgid(msg.getMsgId());
		} else
			info.setMsgid("");
		session.callback(reply, cid, info);
		return;
	}

	/**
	 * 物流公司转换
	 * 
	 * @param Dcompany
	 * @return
	 */
	private String change(String Dcompany) {
		String name = "";
		if (Dcompany.equals("Fsearch_code")) {
			name = "EMS";
		} else if (Dcompany.equals("002shentong")) {
			name = "申通";
		} else if (Dcompany.equals("066zhongtong")) {
			name = "中通";
		} else if (Dcompany.equals("056yuantong")) {
			name = "圆通";
		} else if (Dcompany.equals("042tiantian")) {
			name = "天天";
		} else if (Dcompany.equals("003shunfeng")) {
			name = "顺丰";
		} else if (Dcompany.equals("059Yunda")) {
			name = "韵达";
		} else if (Dcompany.equals("064zhaijisong")) {
			name = "宅急送";
		} else if (Dcompany.equals("020huitong")) {
			name = "汇通";
		} else if (Dcompany.equals("zj001yixun")) {
			name = "易迅";
		}

		return name;
	}

	public void onLocationMsg(Msg4Location msg, String url) {
		log.warn("收到的信息：" + msg.getLabel());
		String infoType = "location";
		Msg reply = getReplyMsgLocation(infoType, msg, url);
		reply.setFromUserName(msg.getToUserName());
		reply.setToUserName(msg.getFromUserName());
		WxSendmsgInfo info = new WxSendmsgInfo();
		if (msg.getCreateTime().equals(reply.getCreateTime())) {
			info.setMsgid(msg.getMsgId());
		} else
			info.setMsgid("");
		session.callback(reply, cid, info);
		return;
	}

	public void onOrderMsg(Msg4Order msg, String url) {
		log.warn("收到的信息：" + msg.getProductId());
		return;
	}

	@Override
	public void onErrorMsg(int arg0) {

	}

	private Msg getReplyMsgOrder(String infoType, Msg4Order rmsg, String url) {
		return null;
	}

	/**
	 * LBS回复
	 * 
	 * @param infoType
	 * @param rmsg
	 * @param url
	 * @return
	 */
	private Msg getReplyMsgLocation(String infoType, Msg4Location rmsg,
			String url) {
		Msg msg = null;
		return msg;
	}

	/**
	 * LBS回复再处理
	 * 
	 * @param loclist
	 * @param rmsg
	 * @param url
	 * @return
	 */
	private Msg createReplyLocationMsg_new(
			List<HashMap<String, Object>> loclist, Msg4Location rmsg, String url) {
		Msg4ImageText imageText = new Msg4ImageText();
		return imageText;
	}

	/**
	 * 根据收到信息的类型和内容以及rid查询回复信息
	 * 
	 * @param infoType
	 * @param info
	 * @param msg2
	 * @param url
	 * @return
	 */
	private Msg getReplyMsg(String infoType, String info, Msg msg2, String url) {
		Msg msg = null;
		return msg;
	}

	/**
	 * 根据收到事件的类型和标识以及rid查询回复信息
	 * 
	 * @param infoType
	 * @param event
	 * @param eventkey
	 * @return Msg
	 */
	private Msg getReplyMsg(String infoType, String event, String eventkey,
			Msg msg2, String url) {
		if(!StringUtil.isEmpty(eventkey)) {
			String senceSign=eventkey;
			if(eventkey.indexOf("qrscene_")>=0) {
				senceSign=eventkey.substring(eventkey.indexOf("_")+1, eventkey.length());
			}
			userService.introduce(msg2.getFromUserName(),Integer.valueOf(senceSign));
		}
		Msg msg = null;
		return msg;
	}


	/**
	 * 构建LocationMsg
	 * 
	 * @param replyinfo
	 * @return
	 */
	private Msg createReplyLocationMsg(List<WxReplyLocation> loclist,
			Msg4Location rmsg, String url) {
		Msg4ImageText imageText = new Msg4ImageText();
		return imageText;
	}


	private Msg defaultReply(Integer rcid, Msg msg, String url) {
		return null;
	}

	private boolean balanceTime(Integer created_at) {
		Date createAt = new Date(created_at.longValue() * 1000);
		Date date = new Date();
		long l_create_at = created_at.longValue() * 1000;
		long l_date = date.getTime();
		long t = l_date - l_create_at;
		if (t < 259200000) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 构建newsMsg
	 * 
	 * @param replyinfo
	 * @return
	 */
	private Msg createReplyNewsMsg(WxReplyInfo replyinfo, Msg msg, String url) {
		return null;
	}

	/**
	 * 构建TextMsg
	 * 
	 * @param replyInfo
	 * @return
	 */
	private Msg createReplyTextMsg(WxReplyInfo replyInfo) {
		Msg4Text textMsg = new Msg4Text();
		// textMsg.setFuncFlag("0");
		textMsg.setContent(replyInfo.getRreplycontext());
		textMsg.setCreateTime(String.valueOf(new Date().getTime()));
		return textMsg;
	}

	private Msg createReplyImage(WxReplyInfo replyInfo) {
		Msg4Image imageMsg = new Msg4Image();
		
		return imageMsg;
	}

	/**
	 * 多客服回复KFMsg
	 * 
	 * @param replyInfo
	 * @return
	 */
	private Msg createReplyKFMsg() {
		Msg4KF kfMsg = new Msg4KF();
		kfMsg.setCreateTime(String.valueOf(new Date().getTime()));
		return kfMsg;
	}

	/**
	 * 没有找到数据是放回值
	 * 
	 * @return
	 */
	private Msg createReplyTextMsg(Msg msg2, String url) {
		return null;
	}

	public void onVideoMsg(Msg4Video msg, String url) {
		// TODO Auto-generated method stub
		log.warn("收到的信息：" + msg.getMediaId());
		String infoType = "video";
		Msg reply = getReplyMsg(infoType, null, msg, url);
		reply.setFromUserName(msg.getToUserName());
		reply.setToUserName(msg.getFromUserName());
		WxSendmsgInfo info = new WxSendmsgInfo();
		if (msg.getCreateTime().equals(reply.getCreateTime())) {
			info.setMsgid(msg.getMsgId());
		} else
			info.setMsgid("");
		session.callback(reply, cid, info);
		return;
	}

	public void onVoiceMsg(Msg4Voice msg, String url) {
		// TODO Auto-generated method stub
		log.warn("收到的信息：" + msg.getMediaId());
		String infoType = "voice";
		Msg reply = getReplyMsg(infoType, null, msg, url);
		reply.setFromUserName(msg.getToUserName());
		reply.setToUserName(msg.getFromUserName());
		WxSendmsgInfo info = new WxSendmsgInfo();
		if (msg.getCreateTime().equals(reply.getCreateTime())) {
			info.setMsgid(msg.getMsgId());
		} else
			info.setMsgid("");
		session.callback(reply, cid, info);
		return;
	}

	@Override
	public void onTextMsg(Msg4Text arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEventMsg(Msg4Event arg0) {
		// TODO Auto-generated method stub

	}

	public void onEventMsg(Msg4EventNew arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onImageMsg(Msg4Image arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLinkMsg(Msg4Link arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLocationMsg(Msg4Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onVideoMsg(Msg4Video arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onVoiceMsg(Msg4Voice arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 组装获奖信息并发送
	 * 
	 * @param wxcfg
	 * @param fromid
	 * @param activity
	 * @param sn
	 */
	/*
	 * public void onMsg2Winner(WxUserConfig wxcfg, String fromid, WxMcActivity
	 * activity,String url,String sn) { log.warn("onMsg2Winner执行"); Msg4Head
	 * head=new Msg4Head(); head.setCreateTime(String.valueOf((new
	 * Date()).getTime())); head.setToUserName(wxcfg.getUwxpubid());
	 * head.setFromUserName(fromid); Msg4ImageText imageText=new
	 * Msg4ImageText(); imageText.setHead(head); Data4Item item=new Data4Item();
	 * item.setTitle(activity.getName()); item.setDescription("");
	 * item.setPicUrl(url+activity.getGiftimg()); String
	 * conurl=url+"/OnOLStaffServlet?type=win&&fromid="+fromid+"&&sn="+sn;
	 * item.setUrl(conurl); imageText.addItem(item); WxSendmsgInfo infox = new
	 * WxSendmsgInfo(); log.warn("准备session callback");
	 * session.callback(imageText, cid, infox); return; }
	 */
}