package com.lingtoo.wechat.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信信息
 * Created: 2015/10/9.
 * Author: Qiannan Lu
 */
public class WechatUser {

    @JsonIgnoreProperties
    private Integer wechatUserId;
    
    @JsonIgnoreProperties
    private Integer merchantId;

    @JsonProperty("openid")
    private String openid;//用户的唯一标识

    @JsonProperty("unionid")
    private String unionid;

    @JsonProperty("nickname")
    private String nickname;//昵称

    private String remarkName;
    @JsonProperty("sex")
    private Integer gender;//性别 1为男性 2为女性 0 未知

    @JsonProperty("city")
    private String city;//微信返回所在城市

    @JsonProperty("province")
    private String province;//省份

    @JsonProperty("country")
    private String country;//国家 中国为CN

    @JsonProperty("headimgurl")
    private String headimgurl;//用户头像

    @JsonIgnoreProperties
    private Integer subscribe;//是表示有没有关注我们的微信公众号

    @JsonIgnoreProperties
    private Date subscribeTime;//订阅时间

    @JsonIgnoreProperties
    private String remark;//备注

    @JsonIgnoreProperties
    private int groupId;

    @JsonIgnoreProperties
    private Integer locked;//是否锁定 1锁定 0未锁定

    @JsonIgnoreProperties
    private String locationCity;//当前定位所在城市

    @JsonIgnoreProperties
    private Date createTime;//注册时间

    @JsonIgnoreProperties
    private String clientType;//客户端类型 web android ios

    @JsonIgnoreProperties
    private String clientId;//个推cid

    @JsonIgnoreProperties
    private String appid;//公众号appId

	public Integer getWechatUserId() {
		return wechatUserId;
	}

	public void setWechatUserId(Integer wechatUserId) {
		this.wechatUserId = wechatUserId;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Date getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
