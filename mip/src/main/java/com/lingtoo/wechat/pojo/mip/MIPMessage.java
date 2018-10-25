package com.lingtoo.wechat.pojo.mip;

public class MIPMessage {
	private Integer mipMessageId;
	
	private Integer mipAccountId;
	
	private String mipMessageTypeId;
	
	private String jumpUrl;
	
	private String imgUrl;

	public Integer getMipMessageId() {
		return mipMessageId;
	}

	public void setMipMessageId(Integer mipMessageId) {
		this.mipMessageId = mipMessageId;
	}

	public Integer getMipAccountId() {
		return mipAccountId;
	}

	public void setMipAccountId(Integer mipAccountId) {
		this.mipAccountId = mipAccountId;
	}

	public String getMipMessageTypeId() {
		return mipMessageTypeId;
	}

	public void setMipMessageTypeId(String mipMessageTypeId) {
		this.mipMessageTypeId = mipMessageTypeId;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
