package com.lingtoo.wechat.bean.wechat;

/**
 * 图文实体类
 * @author 黄文清
 *
 *20130929
 */
public class WxReplyNews {
	private Integer id;					//id,主键
	private Integer pid;				//父id，对应自定义回复id
	private String title;				//标题
	private String author;
	private String description;			//描述
	private String picurl;				//图片地址
	private String url;					//正文地址链接
	private String mainText;			//正文内容
	private Integer imgStatus;
	private String media_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getImgStatus() {
		return imgStatus;
	}

	public void setImgStatus(Integer imgStatus) {
		this.imgStatus = imgStatus;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	
}
