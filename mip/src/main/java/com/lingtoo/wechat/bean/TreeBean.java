package com.lingtoo.wechat.bean;

import com.lingtoo.wechat.pojo.DecorationItem;
import com.lingtoo.wechat.pojo.Menu;



/**  
 * @Description: 菜单树对象
 * @author 陈为金
 * @version 1.0   
 * @Copyright: Copyright (c) 2015 FFCS All Rights Reserved
 * @history:
 * @创建时间：2015-10-10 下午2:05:10  
 */
public class TreeBean {

	private String id;
	private String pId;
	private String name;
	private boolean open;
	private boolean nocheck;
	private boolean checked;
	private boolean chkDisabled;
	private String iconSkin="";//头像

	public TreeBean(Menu menu,boolean checked){
		this.setId(menu.getMenuId().toString());
		this.setpId(menu.getParentMenuId().toString());
		this.setName(menu.getName());
		this.setOpen(true);
		this.setNocheck(false);
		this.setChecked(checked);
	}
	
	public TreeBean(DecorationItem dItem,boolean checked) {
		this.setId(dItem.getDecorationItemId().toString());
		this.setpId(dItem.getType().toString());
		this.setName(dItem.getTitle());
		this.setOpen(true);
		this.setNocheck(false);
		this.setChecked(checked);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public boolean isChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	
	
}


