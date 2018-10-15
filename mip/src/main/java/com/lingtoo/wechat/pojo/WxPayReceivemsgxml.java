package com.lingtoo.wechat.pojo;

import org.w3c.dom.Document;

public class WxPayReceivemsgxml {
	private Integer wxPayReceivemsgxmlId;
	private Integer merchantId;
	private String openid;
	private String appid;
	private Integer issubscribe;
	private Long timestamp;
	private String noncestr;
	private String appsignnature;
	private String signmethod;
	private String msgtype;
	private String feedbackid;
	private String transid;
	private String reason;
	private String solution;
	private String extinfo;
	private String picurl;
	private String type;
	private String errortype;
	private String description;
	private String alarmcontent;
	public void read(Document document){
	    this.openid = document.getElementsByTagName("OpenId").item(0).getTextContent();
	    this.appid = document.getElementsByTagName("AppId").item(0).getTextContent();
	    this.issubscribe = Integer.parseInt(document.getElementsByTagName("IsSubscribe").item(0).getTextContent());
	    this.timestamp = Long.parseLong(document.getElementsByTagName("TimeStamp").item(0).getTextContent());
	    this.noncestr = document.getElementsByTagName("NonceStr").item(0).getTextContent();
	    this.appsignnature = document.getElementsByTagName("AppSignature").item(0).getTextContent();
	    this.signmethod = document.getElementsByTagName("SignMethod").item(0).getTextContent();
	    this.type="notify";
	}
	
	public void readGaojing(Document document){
		this.openid = document.getElementsByTagName("OpenId").item(0).getTextContent();
	    this.appid = document.getElementsByTagName("AppId").item(0).getTextContent();
	    this.timestamp = Long.parseLong(document.getElementsByTagName("TimeStamp").item(0).getTextContent());
	    this.errortype = document.getElementsByTagName("AppSignature").item(0).getTextContent();
	    this.signmethod = document.getElementsByTagName("SignMethod").item(0).getTextContent();
	    this.msgtype = document.getElementsByTagName("MsgType").item(0).getTextContent();
	    this.feedbackid = document.getElementsByTagName("FeedBackId").item(0).getTextContent();
	    if(document.getElementsByTagName("TransId").item(0)!=null)
	    	this.transid = document.getElementsByTagName("TransId").item(0).getTextContent();
	    if(document.getElementsByTagName("Reason").item(0)!=null)
	    	this.reason = document.getElementsByTagName("Reason").item(0).getTextContent();
	    if(document.getElementsByTagName("Solution").item(0)!=null)
	    	this.solution = document.getElementsByTagName("Solution").item(0).getTextContent();
	    if(document.getElementsByTagName("ExtInfo").item(0)!=null)
	    	this.extinfo = document.getElementsByTagName("ExtInfo").item(0).getTextContent();
	    if(document.getElementsByTagName("PicUrl").item(0)!=null)
	    	this.picurl = document.getElementsByTagName("PicUrl").item(0).getTextContent();
	    this.type="weiquan";
	}
	
	public void readWeiquan(Document document){
	    this.openid = document.getElementsByTagName("OpenId").item(0).getTextContent();
	    this.appid = document.getElementsByTagName("AppId").item(0).getTextContent();
	    this.timestamp = Long.parseLong(document.getElementsByTagName("TimeStamp").item(0).getTextContent());
	    this.appsignnature = document.getElementsByTagName("AppSignature").item(0).getTextContent();
	    this.signmethod = document.getElementsByTagName("SignMethod").item(0).getTextContent();
	    this.msgtype = document.getElementsByTagName("MsgType").item(0).getTextContent();
	    this.feedbackid = document.getElementsByTagName("FeedBackId").item(0).getTextContent();
	    if(document.getElementsByTagName("TransId").item(0)!=null)
	    	this.transid = document.getElementsByTagName("TransId").item(0).getTextContent();
	    if(document.getElementsByTagName("Reason").item(0)!=null)
	    	this.reason = document.getElementsByTagName("Reason").item(0).getTextContent();
	    if(document.getElementsByTagName("Solution").item(0)!=null)
	    	this.solution = document.getElementsByTagName("Solution").item(0).getTextContent();
	    if(document.getElementsByTagName("ExtInfo").item(0)!=null)
	    	this.extinfo = document.getElementsByTagName("ExtInfo").item(0).getTextContent();
	    if(document.getElementsByTagName("PicUrl").item(0)!=null)
	    	this.picurl = document.getElementsByTagName("PicUrl").item(0).getTextContent();
	    this.type="weiquan";
	}
	public Integer getWxPayReceivemsgxmlId() {
		return wxPayReceivemsgxmlId;
	}
	public void setWxPayReceivemsgxmlId(Integer wxPayReceivemsgxmlId) {
		this.wxPayReceivemsgxmlId = wxPayReceivemsgxmlId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public Integer getIssubscribe() {
		return issubscribe;
	}
	public void setIssubscribe(Integer issubscribe) {
		this.issubscribe = issubscribe;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getAppsignnature() {
		return appsignnature;
	}
	public void setAppsignnature(String appsignnature) {
		this.appsignnature = appsignnature;
	}
	public String getSignmethod() {
		return signmethod;
	}
	public void setSignmethod(String signmethod) {
		this.signmethod = signmethod;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getExtinfo() {
		return extinfo;
	}
	public void setExtinfo(String extinfo) {
		this.extinfo = extinfo;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getErrortype() {
		return errortype;
	}
	public void setErrortype(String errortype) {
		this.errortype = errortype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAlarmcontent() {
		return alarmcontent;
	}
	public void setAlarmcontent(String alarmcontent) {
		this.alarmcontent = alarmcontent;
	}

}
