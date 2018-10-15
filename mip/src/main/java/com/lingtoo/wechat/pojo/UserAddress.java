package com.lingtoo.wechat.pojo;

import java.util.Date;

public class UserAddress {
	private Integer userAddressId;
	
	private Integer userId;
	
	private String name;
	
	private String phone;
	
	private String address;
	
	private String provinceValue;
	
	private String cityValue;
	
	private String areaValue;
	
	private String provinceCode;
	
	private String cityCode;
	
	private String areaCode;
	
	private Integer status;
	
	private Date createTime;
	
	private Integer state;

	public Integer getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Integer userAddressId) {
		this.userAddressId = userAddressId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
}
