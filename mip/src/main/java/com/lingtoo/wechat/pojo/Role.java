package com.lingtoo.wechat.pojo;

import java.util.Date;

public class Role {

	private Integer roleId;
	private Integer merchantId;
	private String name;//角色名称
	private String remark;//角色备注
	private int menus;//菜单数
	private int merchants;//商家数
	private Date createTime;//创建时间
	private int type;

    
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getMenus() {
		return menus;
	}
	public void setMenus(int menus) {
		this.menus = menus;
	}
	public int getMerchants() {
		return merchants;
	}
	public void setMerchants(int merchants) {
		this.merchants = merchants;
	}
}
