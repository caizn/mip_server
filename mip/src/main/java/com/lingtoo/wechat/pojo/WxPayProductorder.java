package com.lingtoo.wechat.pojo;

import java.util.Date;

public class WxPayProductorder {
	private Integer wxPayProductorderId;
	private Integer merchantId;
	private String bankType;
	private String body;
	private String attach;
	private String partner;
	private String tradeNo;
	private String outTradeNo;
	private Double totalFee;
	private Integer feeType;
	private String notifyUrl;
	private String spbillCreateIp;
	private String timeStart;
	


    public static Integer UNCONFIRM = 0;	//未确认
    public static Integer CONFIRM = 1;		//已确认
    public static Integer REFUND = 2;		//已退款
	public Integer getWxPayProductorderId() {
		return wxPayProductorderId;
	}
	public void setWxPayProductorderId(Integer wxPayProductorderId) {
		this.wxPayProductorderId = wxPayProductorderId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeExpire() {
		return timeExpire;
	}
	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}
	public Integer getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(Integer transportFee) {
		this.transportFee = transportFee;
	}
	public Integer getProductFee() {
		return productFee;
	}
	public void setProductFee(Integer productFee) {
		this.productFee = productFee;
	}
	public String getGoodsTag() {
		return goodsTag;
	}
	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOrderstr() {
		return orderstr;
	}
	public void setOrderstr(String orderstr) {
		this.orderstr = orderstr;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public Date getDealtime() {
		return dealtime;
	}
	public void setDealtime(Date dealtime) {
		this.dealtime = dealtime;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	private String timeExpire;
	private Integer transportFee;
	private Integer productFee;
	private String goodsTag;
	private Integer status;
	private String orderstr;
	private String paytype;
	private Date dealtime;
	private String openid;
}
