package com.lingtoo.wechat.bean;


import java.io.Serializable;

public class ResponseInfo implements Serializable {
	private static final long serialVersionUID = 3814575800489648411L;
	private String respCode;
	private String respDesc;
	
	private Object result;

	public ResponseInfo() {
	}

	public ResponseInfo(String respCode, String respDesc) {
		this.respCode = respCode;
		this.respDesc = respDesc;
	}

	public String respCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getRespCode() {
		return respCode;
	}


}
