package com.lingtoo.wechat.pojo;

import java.util.Date;

public class UserMoneyBill {
	private Integer userMoneyBillId;
	
	private Integer userId;
	
	private Double moneyFloat;
	
	private Double moneyFinal;
	
	private String type;				//金额浮动类型（introduce：介绍费；order：自己订单获利；commission：介绍人订单抽成；withdraw：提现）
	
	private Integer decorationOrderId;
	
	private Integer userWithdrawId;
	
	private Date createTime;
	
	private Integer state;
	/*介绍获取的金钱*/
	public static String TYPE_INTRODUCE="introduce";
	/*自己订单抽成获得的金钱*/
	public static String TYPE_ORDER="order";
	/*自己订单抽成获得的金钱*/
	public static String TYPE_ORDER_EVALUATE="order_evaluate";
	/*介绍人订单抽成获得的金钱*/
	public static String TYPE_COMMISSION="commission";
	/*提现消耗的金钱*/
	public static String TYPE_WITHDROW="withdrow";
	/*订单取消后工人退水*/
	public static String TYPE_ORDER_CANCEL="order_cancel";
	/*订单取消后介绍人退水*/
	public static String TYPE_COMMISSION_CANCEL="commission_cancel";

	public Integer getUserMoneyBillId() {
		return userMoneyBillId;
	}

	public void setUserMoneyBillId(Integer userMoneyBillId) {
		this.userMoneyBillId = userMoneyBillId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getMoneyFloat() {
		return moneyFloat;
	}

	public void setMoneyFloat(Double moneyFloat) {
		this.moneyFloat = moneyFloat;
	}

	public Double getMoneyFinal() {
		return moneyFinal;
	}

	public void setMoneyFinal(Double moneyFinal) {
		this.moneyFinal = moneyFinal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDecorationOrderId() {
		return decorationOrderId;
	}

	public void setDecorationOrderId(Integer decorationOrderId) {
		this.decorationOrderId = decorationOrderId;
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

	public Integer getUserWithdrawId() {
		return userWithdrawId;
	}

	public void setUserWithdrawId(Integer userWithdrawId) {
		this.userWithdrawId = userWithdrawId;
	}
}
