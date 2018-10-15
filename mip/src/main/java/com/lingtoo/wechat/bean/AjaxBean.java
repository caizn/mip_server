package com.lingtoo.wechat.bean;

public class AjaxBean {

	public AjaxBean(int code, String desc) {
		this.setCode(code);
		this.setDesc(desc);
	}
	public AjaxBean(){
		
	}
	private int code;
	private String desc;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
