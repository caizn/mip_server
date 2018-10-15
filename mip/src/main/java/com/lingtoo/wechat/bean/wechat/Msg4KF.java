package com.lingtoo.wechat.bean.wechat;

import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Head;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Msg4KF extends Msg{
	private String msgId;
	
	public void read(Document document) {
		this.msgId = document.getElementsByTagName("MsgId").item(0).getTextContent();
	}

	public void write(Document document) {
		Element root = document.createElement("xml");
	    this.head.write(root, document);
	    document.appendChild(root);
	}
	
	public Msg4KF()
	{
	    this.head = new Msg4Head();
	    this.head.setMsgType("transfer_customer_service");
	}

	public Msg4KF(Msg4Head head)
	{
	    this.head = head;
	}
	
	public String getMsgId() {
	    return this.msgId;
	}
	
	public void setMsgId(String msgId) {
	    this.msgId = msgId;
	}
}
