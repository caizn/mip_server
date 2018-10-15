package com.lingtoo.wechat.bean;

public class ActivityBean {
	private Integer liveId;
	
	private String title;// 标题

	private String province;// 所在省份

	private String city;// 所在城市
	
	private String area;
	
	private String address;

	private String desc;// 活动描述

	private String startTime;// 开始时间

	private String endTime;// 结束时间

	private String bannerFilePath;
	
	private Integer type;
	
	private Integer scheduleId;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public Integer getLiveId() {
		return liveId;
	}

	public void setLiveId(Integer liveId) {
		this.liveId = liveId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBannerFilePath() {
		return bannerFilePath;
	}

	public void setBannerFilePath(String bannerFilePath) {
		this.bannerFilePath = bannerFilePath;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
