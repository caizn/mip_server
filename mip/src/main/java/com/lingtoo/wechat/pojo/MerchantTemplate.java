package com.lingtoo.wechat.pojo;

import java.util.Date;

/**
 * 商家推送模板表
 * @author Administrator
 *
 */
public class MerchantTemplate {

	private Integer templateId;//	int	11		主键ID				-1
	private String appId;
	private Integer merchantId	;	//	varchar	64	-1		商户ID		utf8	utf8_general_ci	
	private String orderCreate;		//	varchar	64	-1		订单生成通知	utf8	utf8_general_ci	
	private String orderPay;		//	varchar	64	-1		订单支付通知	utf8	utf8_general_ci	
	private String orderLogistics;	//	varchar	64	-1		订单发货通知	utf8	utf8_general_ci	
	private String orderCancel;		//	varchar	64	-1		订单取消通知	utf8	utf8_general_ci	
	private Date createDate;//datetime		创建时间			
	private String creator;//	varchar	32		创建人	utf8	utf8_general_ci	
	private Date updateTime;//	datetime	-1		修改时间			
	
	
	
	//sms
	private String smsCode;//	varchar	32		短信CODE	utf8	utf8_general_ci	
	private String smsSign;//	varchar	32		短信签名	utf8	utf8_general_ci	
	private String smsContent;//	varchar	128		短信内容	utf8	utf8_general_ci	
	
	
	
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getOrderCreate() {
		return orderCreate;
	}
	public void setOrderCreate(String orderCreate) {
		this.orderCreate = orderCreate;
	}
	public String getOrderPay() {
		return orderPay;
	}
	public void setOrderPay(String orderPay) {
		this.orderPay = orderPay;
	}
	public String getOrderLogistics() {
		return orderLogistics;
	}
	public void setOrderLogistics(String orderLogistics) {
		this.orderLogistics = orderLogistics;
	}
	public String getOrderCancel() {
		return orderCancel;
	}
	public void setOrderCancel(String orderCancel) {
		this.orderCancel = orderCancel;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getSmsSign() {
		return smsSign;
	}
	public void setSmsSign(String smsSign) {
		this.smsSign = smsSign;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	

}
