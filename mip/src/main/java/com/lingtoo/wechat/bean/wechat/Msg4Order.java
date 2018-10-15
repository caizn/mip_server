package com.lingtoo.wechat.bean.wechat;

import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Head;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Msg4Order extends Msg {
	private String orderId;
	private String orderStatus;
	private String productId;
	private String skuInfo;
	private String event;

	public void write(Document document) {
	}

	public void read(Document document) {
	    this.orderId = document.getElementsByTagName("OrderId").item(0).getTextContent();
	    this.orderStatus = document.getElementsByTagName("OrderStatus").item(0).getTextContent();
	    this.productId = document.getElementsByTagName("ProductId").item(0).getTextContent();
	    this.skuInfo = document.getElementsByTagName("SkuInfo").item(0).getTextContent();
	    this.event = document.getElementsByTagName("Event").item(0).getTextContent();
	}

	public Msg4Order() {
		this.head = new Msg4Head();
		this.head.setMsgType("merchant_order");
	}
	
	public Msg4Order(Msg4Head head){
	    this.head = head;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSkuInfo() {
		return skuInfo;
	}

	public void setSkuInfo(String skuInfo) {
		this.skuInfo = skuInfo;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}
