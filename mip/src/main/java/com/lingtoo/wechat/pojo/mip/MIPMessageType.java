package com.lingtoo.wechat.pojo.mip;

import java.util.List;

public class MIPMessageType {
	private String mipMessageTypeId;
	
	private String mipMessagesTypeDesc;

	private Integer maxCount;
	
	private List<MIPMessage> mipMessageList;
	
	public String getMipMessageTypeId() {
		return mipMessageTypeId;
	}

	public void setMipMessageTypeId(String mipMessageTypeId) {
		this.mipMessageTypeId = mipMessageTypeId;
	}

	public String getMipMessagesTypeDesc() {
		return mipMessagesTypeDesc;
	}

	public void setMipMessagesTypeDesc(String mipMessagesTypeDesc) {
		this.mipMessagesTypeDesc = mipMessagesTypeDesc;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public List<MIPMessage> getMipMessageList() {
		return mipMessageList;
	}

	public void setMipMessageList(List<MIPMessage> mipMessageList) {
		this.mipMessageList = mipMessageList;
	}
}
