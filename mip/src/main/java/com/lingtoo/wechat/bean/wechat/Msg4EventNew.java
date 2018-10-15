package com.lingtoo.wechat.bean.wechat;

import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Head;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lingtoo.wechat.utils.wechat.xmlNodeUtil;

public class Msg4EventNew extends Msg{
	public static final String SUBSCRIBE = "subscribe";
	public static final String UNSUBSCRIBE = "unsubscribe";
	public static final String CLICK = "CLICK";
	private String event;
	private String eventKey;
	private String ScanValue;

	public Msg4EventNew(Msg4Head head) {
		this.head = head;
	}

	public void write(Document document) {
	}

	public void read(Document document) {
		this.event = document.getElementsByTagName("Event").item(0)
				.getTextContent();
		this.eventKey = document.getElementsByTagName("EventKey").item(0)
				.getTextContent();
		if(event.equals("scancode_push")||event.equals("scancode_waitmsg")){
			Node node=document.getElementsByTagName("ScanCodeInfo").item(0);
			String ScanType=xmlNodeUtil.getStringByName(node, "ScanType");
			String ScanResult=xmlNodeUtil.getStringByName(node, "ScanResult");
			JSONObject sci=new JSONObject();
			sci.put("SscanType", ScanType);
			sci.put("ScanResult", ScanResult);
			this.setScanValue(sci.toString());
		}else if(event.equals("pic_sysphoto")||
				event.equals("pic_photo_or_album")||
				event.equals("pic_weixin")){
			Node node=document.getElementsByTagName("SendPicsInfo").item(0);
			Integer Count=Integer.parseInt(xmlNodeUtil.getStringByName(node, "Count"));
			Node PicNode=xmlNodeUtil.getNodeByName(node, "PicList");
			JSONArray PicList=new JSONArray();
			NodeList nodelist = PicNode.getChildNodes();
			for (int i = 0; i < nodelist.getLength(); i++) {
				Node childNode = nodelist.item(i).getFirstChild();
				if (childNode != null) {
					if (childNode.getNodeName().equals("PicMd5Sum")) {
						PicList.add(childNode.getTextContent());
					}
				}
			}
			JSONObject spi=new JSONObject();
			spi.put("Count", Count);
			spi.put("PicList", PicList);
			this.setScanValue(spi.toString());
		}else if(event.equals("location_select")){
			Node node=document.getElementsByTagName("SendLocationInfo").item(0);
			String Location_X=xmlNodeUtil.getStringByName(node, "Location_X");
			String Location_Y=xmlNodeUtil.getStringByName(node, "Location_Y");
			String Scale=xmlNodeUtil.getStringByName(node, "Scale");
			String Label=xmlNodeUtil.getStringByName(node, "Label");
			String Poiname=xmlNodeUtil.getStringByName(node, "Poiname");
			JSONObject sli=new JSONObject();
			sli.put("Location_X", Location_X);
			sli.put("Location_Y", Location_Y);
			sli.put("Scale", Scale);
			sli.put("Label", Label);
			sli.put("Poiname", Poiname);
			this.setScanValue(sli.toString());
		}
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return this.eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getScanValue() {
		return ScanValue;
	}

	public void setScanValue(String scanValue) {
		ScanValue = scanValue;
	}

}