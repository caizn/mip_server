package com.lingtoo.wechat.pojo;

import java.util.Date;

public class DecorationItem {
	private Integer decorationItemId;
	
	private String title;
	
	private Integer type;	//类型：1：安装；2：维修；3：清洗
	
	private String typeShow;
	
	private Integer state;
	
	private Date createTime;

    private Integer subType;//子类别

    private String subTitle;//子类别名称
	
	public static Integer TYPE_CLEAR=3;
	
	public static Integer TYPE_INSTALL=1;
	
	public static Integer TYPE_REPAIR=2;

	public Integer getDecorationItemId() {
		return decorationItemId;
	}

	public void setDecorationItemId(Integer decorationItemId) {
		this.decorationItemId = decorationItemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
		if(type!=null) {
			if(type.equals(TYPE_INSTALL)) setTypeShow("安装");
			if(type.equals(TYPE_REPAIR)) setTypeShow("维修");
			if(type.equals(TYPE_CLEAR)) setTypeShow("清洗");
		}
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTypeShow() {
		return typeShow;
	}

	public void setTypeShow(String typeShow) {
		this.typeShow = typeShow;
	}

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
