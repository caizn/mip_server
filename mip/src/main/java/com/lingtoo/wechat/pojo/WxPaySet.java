package com.lingtoo.wechat.pojo;

public class WxPaySet {
	private Integer wxPaySetId;

	private Integer merchantId;

	private String appid;

	private String appsecret;

	private String payUrl;

	private String payTestUrl;

	private String partnerid;

	private String partnerpwd;

	private String payVersion;

	private String partnerkey;

	private String paysignkey;

	private String gaojingUrl;

	private String weiquanUrl;

	private Integer payStatus;

	private String com;

	public Integer getWxPaySetId() {
		return wxPaySetId;
	}

	public void setWxPaySetId(Integer wxPaySetId) {
		this.wxPaySetId = wxPaySetId;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getPayTestUrl() {
		return payTestUrl;
	}

	public void setPayTestUrl(String payTestUrl) {
		this.payTestUrl = payTestUrl;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPartnerpwd() {
		return partnerpwd;
	}

	public void setPartnerpwd(String partnerpwd) {
		this.partnerpwd = partnerpwd;
	}

	public String getPayVersion() {
		return payVersion;
	}

	public void setPayVersion(String payVersion) {
		this.payVersion = payVersion;
	}

	public String getPartnerkey() {
		return partnerkey;
	}

	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}

	public String getPaysignkey() {
		return paysignkey;
	}

	public void setPaysignkey(String paysignkey) {
		this.paysignkey = paysignkey;
	}

	public String getGaojingUrl() {
		return gaojingUrl;
	}

	public void setGaojingUrl(String gaojingUrl) {
		this.gaojingUrl = gaojingUrl;
	}

	public String getWeiquanUrl() {
		return weiquanUrl;
	}

	public void setWeiquanUrl(String weiquanUrl) {
		this.weiquanUrl = weiquanUrl;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getPayFile() {
		return payFile;
	}

	public void setPayFile(String payFile) {
		this.payFile = payFile;
	}

	private String payFile;
}
