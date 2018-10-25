package com.lingtoo.wechat.pojo.mip;

public class MIPAccount {
	private Integer mipAccountId;
	
	private String accountName;
	
	private String baiduAccount;

	private String baiduPwd;
	
	private String moduleType;
	
	private String moduleTypeName;
	
	private String spCode;
	
	private String webUrl;
	
	public Integer getMipAccountId() {
		return mipAccountId;
	}

	public void setMipAccountId(Integer mipAccountId) {
		this.mipAccountId = mipAccountId;
	}

	public String getBaiduPwd() {
		return baiduPwd;
	}

	public void setBaiduPwd(String baiduPwd) {
		this.baiduPwd = baiduPwd;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBaiduAccount() {
		return baiduAccount;
	}

	public void setBaiduAccount(String baiduAccount) {
		this.baiduAccount = baiduAccount;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getModuleTypeName() {
		return moduleTypeName;
	}

	public void setModuleTypeName(String moduleTypeName) {
		this.moduleTypeName = moduleTypeName;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
}
