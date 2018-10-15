package com.lingtoo.wechat.pojo;

import java.util.Date;

public class DecorationWorker {
	private Integer decorationWorkerId;
	
	private Integer userId;
	
	private String name;
	
	private Integer gender;
	
	private String telephone;
	
	private String urgencyTelephone;
	
	private String address;
	
	private String provinceValue;
	
	private String cityValue;
	
	private String areaValue;
	
	private String provinceCode;
	
	private String cityCode;
	
	private String areaCode;
	
	private String orderReceiveAreaValue;
	
	private String orderReceiveAreaCode;
	
	private String idNumber;
	
	private String idCardPicUrl;
	
	private String decorationItemList;
	
	private Integer auditStatus;				//审核状态（-1：未审核；0：审核通过；1：审核未通过）
	
	private Date createTime;
	
	private Integer state;
	
	private User user;

	public Integer getDecorationWorkerId() {
		return decorationWorkerId;
	}

	public void setDecorationWorkerId(Integer decorationWorkerId) {
		this.decorationWorkerId = decorationWorkerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUrgencyTelephone() {
		return urgencyTelephone;
	}

	public void setUrgencyTelephone(String urgencyTelephone) {
		this.urgencyTelephone = urgencyTelephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvinceValue() {
		return provinceValue;
	}

	public void setProvinceValue(String provinceValue) {
		this.provinceValue = provinceValue;
	}

	public String getCityValue() {
		return cityValue;
	}

	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}

	public String getAreaValue() {
		return areaValue;
	}

	public void setAreaValue(String areaValue) {
		this.areaValue = areaValue;
	}

	public String getOrderReceiveAreaValue() {
		return orderReceiveAreaValue;
	}

	public void setOrderReceiveAreaValue(String orderReceiveAreaValue) {
		this.orderReceiveAreaValue = orderReceiveAreaValue;
	}

	public String getOrderReceiveAreaCode() {
		return orderReceiveAreaCode;
	}

	public void setOrderReceiveAreaCode(String orderReceiveAreaCode) {
		this.orderReceiveAreaCode = orderReceiveAreaCode;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdCardPicUrl() {
		return idCardPicUrl;
	}

	public void setIdCardPicUrl(String idCardPicUrl) {
		this.idCardPicUrl = idCardPicUrl;
	}

	public String getDecorationItemList() {
		return decorationItemList;
	}

	public void setDecorationItemList(String decorationItemList) {
		this.decorationItemList = decorationItemList;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
