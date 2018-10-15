package com.lingtoo.wechat.pojo;

import java.util.Date;

public class LoginLog {

	public final static Integer ACCOUNT_TYPE_MANAGER=1;
	public final static Integer ACCOUNT_TYPE_MERCHANT=2;
	
	public final static Integer REUSLT_SUCCESS=0;
	public final static Integer REUSLT_PWD_ERROR=1;
	public final static Integer REUSLT_NOT_ACCOUNT=2;
	public final static Integer REUSLT_ACCOUNT_EXCEPTION=3;
	public final static Integer REUSLT_STATE_NOTCHECK=4;
	
	private Integer loginLogId;//主键
	private String account;//管理员账号
	private String realName;//真实名字
	private Integer accountType;//账号类型  1:后台管理员 2：商家管理员
	private Integer result;//登录结果,0-成功,1-密码错误,2-用户不存在 3-用户状态异常
	private String logIp;//日志IP
	private Date logTime;//日志时间
	
	private Integer merchantId;//商家ID
	
	public String getAccountTypeName(){
		if(ACCOUNT_TYPE_MANAGER.equals(accountType))return "后台用户";
		else if(ACCOUNT_TYPE_MERCHANT.equals(accountType))return "商家用户";
		return "";
	}
	
	public String getResultName(){
		if(result.equals(REUSLT_SUCCESS))return "成功登录";
		else if(result.equals(REUSLT_PWD_ERROR))return "密码错误";
		else if(result.equals(REUSLT_NOT_ACCOUNT))return "账号不存在";
		else if(result.equals(REUSLT_ACCOUNT_EXCEPTION))return "账号状态异常";
		else if(result.equals(REUSLT_STATE_NOTCHECK))return "账号未审核";
		return "";
	}

	public Integer getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(Integer loginLogId) {
		this.loginLogId = loginLogId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
}
