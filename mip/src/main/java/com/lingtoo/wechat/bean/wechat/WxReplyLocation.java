package com.lingtoo.wechat.bean.wechat;

public class WxReplyLocation {
	private Integer id;
	private Integer cid;
	private String actid;
	private String title;
	private String auther;
	private String picurl;
	private String url;
	private String mainText;
	private String description;
	private String location_x;
	private String location_y;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getActid() {
		return actid;
	}
	public void setActid(String actid) {
		this.actid = actid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMainText() {
		return mainText;
	}
	public void setMainText(String mainText) {
		this.mainText = mainText;
	}
	public String getLocation_x() {
		return location_x;
	}
	public void setLocation_x(String location_x) {
		this.location_x = location_x;
	}
	public String getLocation_y() {
		return location_y;
	}
	public void setLocation_y(String location_y) {
		this.location_y = location_y;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
