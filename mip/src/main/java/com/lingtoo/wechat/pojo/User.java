package com.lingtoo.wechat.pojo;

import java.util.Date;
import java.util.List;

public class User {

	private Integer userId;
	
	private Integer wechatUserId;
	
	private Double money;
	
	private Integer integral;
	
	private Date createTime;
	
	private Integer state;
	
	private Integer introducerId;
	
	private Integer introduceCount;

	private Integer managerSign;			//管理员标识（0：普通用户，1：管理员）

	private String name;
	
	private String telephone;
	
	private String urgencyTelephone;
	
	private String address;
	
	private String provinceValue;
	
	private String cityValue;
	
	private String areaValue;
	
	private String provinceCode;
	
	private String cityCode;
	
	private String areaCode;
	
	private Integer auditStatus;			//审核状态（-1：未审核；0：审核通过；1：审核未通过）
	
	private String salesman;
	
	private String remark;
	
	private String generalizeQrcodeUrl;
	
	private String bankName;
	
	private String bankPlace;
	
	private String bankCardName;
	
	private String bankCardCode;
	
	private String nickname;
	
	private String headimgurl;
	
	private WechatUser wechatUser;

	private List<UserAddress> userAddressList;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getWechatUserId() {
		return wechatUserId;
	}

	public void setWechatUserId(Integer wechatUserId) {
		this.wechatUserId = wechatUserId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
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

	public String getGeneralizeQrcodeUrl() {
		return generalizeQrcodeUrl;
	}

	public void setGeneralizeQrcodeUrl(String generalizeQrcodeUrl) {
		this.generalizeQrcodeUrl = generalizeQrcodeUrl;
	}

	public Integer getIntroducerId() {
		return introducerId;
	}

	public void setIntroducerId(Integer introducerId) {
		this.introducerId = introducerId;
	}

	public Integer getIntroduceCount() {
		return introduceCount;
	}

	public void setIntroduceCount(Integer introduceCount) {
		this.introduceCount = introduceCount;
	}

	public WechatUser getWechatUser() {
		return wechatUser;
	}

	public void setWechatUser(WechatUser wechatUser) {
		this.wechatUser = wechatUser;
	}

	public Integer getManagerSign() {
		return managerSign;
	}

	public void setManagerSign(Integer managerSign) {
		this.managerSign = managerSign;
	}

	public List<UserAddress> getUserAddressList() {
		return userAddressList;
	}

	public void setUserAddressList(List<UserAddress> userAddressList) {
		this.userAddressList = userAddressList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
}
