package com.lingtoo.wechat.pojo;

import java.util.Date;

public class UserWithdraw {
	private Integer userWithdrawId;
	
	private Integer userId;
	
	private String withdrawNo;
	
	private Double moneyWithdraw;
	
	private Double moneyFinal;
	
	private Integer status;
	
	private Date createTime;		//提现状态（-1：发起提现请求；0：完成提现；1：驳回提现请求）
	
	private Integer state;
	
	private User user;
	
	private String bankName;
	
	private String bankPlace;
	
	private String bankCardName;
	
	private String bankCardCode;
	

	public Integer getUserWithdrawId() {
		return userWithdrawId;
	}

	public void setUserWithdrawId(Integer userWithdrawId) {
		this.userWithdrawId = userWithdrawId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWithdrawNo() {
		return withdrawNo;
	}

	public void setWithdrawNo(String withdrawNo) {
		this.withdrawNo = withdrawNo;
	}

	public Double getMoneyWithdraw() {
		return moneyWithdraw;
	}

	public void setMoneyWidthdraw(Double moneyWithdraw) {
		this.moneyWithdraw = moneyWithdraw;
	}

	public Double getMoneyFinal() {
		return moneyFinal;
	}

	public void setMoneyFinal(Double moneyFinal) {
		this.moneyFinal = moneyFinal;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMoneyWithdraw(Double moneyWithdraw) {
		this.moneyWithdraw = moneyWithdraw;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankPlace() {
		return bankPlace;
	}

	public void setBankPlace(String bankPlace) {
		this.bankPlace = bankPlace;
	}

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getBankCardCode() {
		return bankCardCode;
	}

	public void setBankCardCode(String bankCardCode) {
		this.bankCardCode = bankCardCode;
	}
}
