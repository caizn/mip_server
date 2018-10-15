package com.lingtoo.wechat.pojo;

import com.lingtoo.common.utils.Encodes;

/**
 * 微信菜单设置
 * @author Administrator
 *
 */
public class MerchantMenu {

	private Integer menuId;//	int	11		主键ID				-1
	private String menuName;//	varchar	20		菜单名称	utf8	utf8_general_ci	
	private String menuUrl;//	varchar	128		URL	utf8	utf8_general_ci	
	
	private String menuUrlEncode;
	
	private String appid;
	
	public String getMenuUrlEncode() {
		menuUrlEncode="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + Encodes.urlEncode(menuUrl) + "&response_type=code&scope=snsapi_userinfo&state="+appid+"#wechat_redirect";
		return menuUrlEncode;
	}
	public void setMenuUrlEncode(String menuUrlEncode) {
		this.menuUrlEncode = menuUrlEncode;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	

}
