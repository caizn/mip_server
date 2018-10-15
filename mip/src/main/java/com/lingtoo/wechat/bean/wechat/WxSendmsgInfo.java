package com.lingtoo.wechat.bean.wechat;

import java.util.Date;

/**
 * @author kirby24
 *发送信息实体类
 */
public class WxSendmsgInfo  {

	
	private Integer id;
	private Integer cid;
	private String sendxml;
	private Date createtime;
	private String msgid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getSendxml() {
		return sendxml;
	}
	public void setSendxml(String sendxml) {
		this.sendxml = sendxml;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	
}
