package com.lingtoo.wechat.pojo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tenpay.RequestHandler;

public class WxPayRefund {
	private Integer wxPayRefundId;
	
	private String signType;
	
	private String inputCharset;
	
	private String signKeyIndex;
	
	private String serviceVersion;
	
	private String partner;
	
	private String outTradeNo;
	
	private String transactionId;
	
	private String outRefundNo;
	
	private String totalFee;
	
	private String refundFee;
	
	private String opUserId;
	
	private String opUserPasswd;
	
	private String recvUserId;
	
	private String recvUserName;
	
	private String useSpbillNoFlag;
	
	private String refundType;
	
	public WxPayRefund() {
		
	}
	
	public WxPayRefund(WxPaySet wxset, WxPayProductorder porder) {
		signType = "MD5";
		inputCharset = "GBK";
		signKeyIndex = "1";
		serviceVersion = "1.1";
		partner = wxset.getPartnerid();
		outTradeNo = porder.getOutTradeNo();
		transactionId = porder.getTradeNo();
		if ("wechat".equals(porder.getPaytype())) {
			totalFee = String.valueOf(porder.getTotalFee());
			refundFee = String.valueOf(porder.getTotalFee());
		}
		opUserId = wxset.getPartnerid();
		opUserPasswd = wxset.getPartnerpwd();
	}

	public void writeReqHandle(RequestHandler reqHandler) {
		reqHandler.setParameter("service_version", serviceVersion);
		reqHandler.setParameter("partner", partner);
		reqHandler.setParameter("out_trade_no", outTradeNo);
		reqHandler.setParameter("transaction_id",transactionId);
		reqHandler.setParameter("out_refund_no", outRefundNo);
		reqHandler.setParameter("total_fee", totalFee);
		reqHandler.setParameter("refund_fee", totalFee);
		reqHandler.setParameter("op_user_id", opUserId);
		reqHandler.setParameter("op_user_passwd",opUserPasswd);
		reqHandler.setParameter("recv_user_id", "");
		reqHandler.setParameter("reccv_user_name", "");
	}

	public void writeDoc(Document document) {
		Element root = document.createElement("root");

		Element sign_type = document.createElement("sign_type");
		sign_type.setTextContent(this.signType);
		Element input_charset = document.createElement("input_charset");
		input_charset.setTextContent(this.inputCharset);
		Element sign_key_index = document.createElement("sign_key_index");
		sign_key_index.setTextContent(this.signKeyIndex);
		Element service_version = document.createElement("service_version");
		service_version.setTextContent(this.serviceVersion);
		Element partner = document.createElement("partner");
		partner.setTextContent(this.partner);
		Element out_trade_no = document.createElement("out_trade_no");
		out_trade_no.setTextContent(this.outTradeNo);
		Element op_user_id = document.createElement("op_user_id");
		op_user_id.setTextContent(this.opUserId);
		Element op_user_passwd = document.createElement("op_user_passwd");
		op_user_passwd.setTextContent(this.opUserPasswd);

		root.appendChild(sign_type);
		root.appendChild(input_charset);
		root.appendChild(sign_key_index);
		root.appendChild(service_version);
		root.appendChild(partner);
		root.appendChild(out_trade_no);
		root.appendChild(op_user_id);
		root.appendChild(op_user_passwd);
		document.appendChild(root);
	}
	public Integer getWxPayRefundId() {
		return wxPayRefundId;
	}

	public void setWxPayRefundId(Integer wxPayRefundId) {
		this.wxPayRefundId = wxPayRefundId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getSignKeyIndex() {
		return signKeyIndex;
	}

	public void setSignKeyIndex(String signKeyIndex) {
		this.signKeyIndex = signKeyIndex;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	public String getOpUserPasswd() {
		return opUserPasswd;
	}

	public void setOpUserPasswd(String opUserPasswd) {
		this.opUserPasswd = opUserPasswd;
	}

	public String getRecvUserId() {
		return recvUserId;
	}

	public void setRecvUserId(String recvUserId) {
		this.recvUserId = recvUserId;
	}

	public String getRecvUserName() {
		return recvUserName;
	}

	public void setRecvUserName(String recvUserName) {
		this.recvUserName = recvUserName;
	}

	public String getUseSpbillNoFlag() {
		return useSpbillNoFlag;
	}

	public void setUseSpbillNoFlag(String useSpbillNoFlag) {
		this.useSpbillNoFlag = useSpbillNoFlag;
	}

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	private Integer refundStatus;
}
