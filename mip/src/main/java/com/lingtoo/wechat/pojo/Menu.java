package com.lingtoo.wechat.pojo;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private String menuId;//菜单ID
	private String name;//菜单名称
	private String icon;//菜单图标
	private String href;//菜单链接
	private String parentMenuId;//菜单父ID
	private String remark;//备注
	private long orderBy;//排序
	private String dataId;
	
	
	private List<Menu> subMenuList=new ArrayList<Menu>();//子菜单  
	
	public List<Menu> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<Menu> subMenuList) {
		this.subMenuList = subMenuList;
	}
	
	private boolean checked;//是否选中
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(long orderBy) {
		this.orderBy = orderBy;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
