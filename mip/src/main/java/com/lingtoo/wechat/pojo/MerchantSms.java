package com.lingtoo.wechat.pojo;

import java.util.Date;

/**
 * 商家短信设置表
 * @author Administrator
 *
 */
public class MerchantSms {

	private Integer smsId;//	int	11		主键ID	
	private String appId;//
	private Integer merchantId;//	int	11		商家ID			
	private String smsCode;//	varchar	32		短信CODE	utf8	utf8_general_ci	
	private String smsSign;//	varchar	32		短信签名	utf8	utf8_general_ci	
	private String smsContent;//	varchar	128		短信内容	utf8	utf8_general_ci	
	private Date createDate;//	datetime		创建时间			
	private String creator;//	varchar	32		创建人	utf8	utf8_general_ci	
	private Date updateTime;//	datetime	-1		修改时间	
	
	
	public Integer getSmsId() {
		return smsId;
	}
	public void setSmsId(Integer smsId) {
		this.smsId = smsId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
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
	

}
