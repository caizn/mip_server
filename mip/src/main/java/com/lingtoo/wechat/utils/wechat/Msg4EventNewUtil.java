package com.lingtoo.wechat.utils.wechat;

import org.marker.weixin.msg.Msg4Head;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lingtoo.wechat.bean.wechat.Msg4EventNew;

public class Msg4EventNewUtil extends Msg4EventNew {

	public Msg4EventNewUtil(Msg4Head head) {
		super(head);
		// TODO Auto-generated constructor stub
	}

	public void read(Document document) {
		String event = document.getElementsByTagName("Event").item(0)
				.getTextContent();
		this.setEvent(event);
		if (document.getElementsByTagName("EventKey").item(0) != null) {
			this.setEventKey(document.getElementsByTagName("EventKey").item(0)
					.getTextContent());
		}
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
}
