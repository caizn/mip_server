package com.lingtoo.wechat.pojo;

import java.util.Date;

public class UserIntegralBill {
	private Integer userIntegralBillId;
	
	private Integer userId;
	
	private Double integralFloat;
	
	private Double integralFinal;
	
	private String type;				//积分浮动类型（introduce：介绍获取积分；orderEvaluate：订单评价获取积分；orderRecieve：接单消耗积分；orderCancel：取消订单积分返还）
	
	private Integer decorationOrderId;
	
	private Date createTime;
	
	private Integer state;
	/*介绍获取的积分*/
	public static String TYPE_INTRODUCE="introduce";
	/*订单评价获得的积分*/
	public static String TYPE_ORDER_EVALUATE="order_evaluate";
	/*抢单所消耗的积分*/
	public static String TYPE_ORDER_RECIEVE="order_recieve";
	/*订单取消返回的积分*/
	public static String TYPE_ORDER_CANCEL="order_cancel";
	
	public Integer getUserIntegralBillId() {
		return userIntegralBillId;
	}
	public void setUserIntegralBillId(Integer userIntegralBillId) {
		this.userIntegralBillId = userIntegralBillId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getIntegralFloat() {
		return integralFloat;
	}
	public void setIntegralFloat(Double integralFloat) {
		this.integralFloat = integralFloat;
	}
	public Double getIntegralFinal() {
		return integralFinal;
	}
	public void setIntegralFinal(Double integralFinal) {
		this.integralFinal = integralFinal;
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
}
