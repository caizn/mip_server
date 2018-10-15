package com.lingtoo.wechat.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.marker.weixin.Session;
import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Head;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.lingtoo.wechat.bean.wechat.Msg4EventNew;
import com.lingtoo.wechat.bean.wechat.Msg4Order;
import com.lingtoo.wechat.bean.wechat.WxReceiveMsgInfo;
import com.lingtoo.wechat.bean.wechat.WxSendmsgInfo;
import com.lingtoo.wechat.utils.wechat.Msg4EventNewUtil;

/**
 * @author kirby24
 * 
 */
public class QiduSessionAction extends Session {
	private static Logger log = Logger.getLogger(QiduSessionAction.class);
	private InputStream is;
	private OutputStream os;
	private static DocumentBuilder builder;
	private static TransformerFactory tffactory = TransformerFactory
			.newInstance();

	public static String xmlStr = null;

	@Override
	public void onErrorMsg(int errorCode) {
		for (HandleMsgAdapter currentListener : this.listeners)
			currentListener.onErrorMsg(errorCode);
	}

	public void onEventMsg(Msg4EventNew msg, String url) {
		for (HandleMsgAdapter currentListener : this.listeners)
			currentListener.onEventMsg(msg,url);
	}

	public void onImageMsg(Msg4Image msg, String url) {
		for (HandleMsgAdapter currentListener : this.listeners)
			currentListener.onImageMsg(msg,url);
	}

	public void onLinkMsg(Msg4Link msg, String url) {
		for (HandleMsgAdapter currentListener : this.listeners)
			currentListener.onLinkMsg(msg,url);
	}

	public void onLocationMsg(Msg4Location msg, String url) {
		for (HandleMsgAdapter currentListener : this.listeners)
			currentListener.onLocationMsg(msg,url);
	}

	public void onTextMsg(Msg4Text msg, String url) {
		for (HandleMsgAdapter currentListener : this.listeners)
			currentListener.onTextMsg(msg,url);
	}

	public void onVideoMsg(Msg4Video msg, String url) {
		for (HandleMsgAdapter currentListener : this.listeners)
			currentListener.onVideoMsg(msg,url);
	}

	public void onVoiceMsg(Msg4Voice msg, String url) {
		for (HandleMsgAdapter currentListener : this.listeners)
			currentListener.onVoiceMsg(msg,url);
	}
	
	public void onOrderMsg(Msg4Order msg,String url){
		for (HandleMsgAdapter currentListener : this.listeners)
			currentListener.onOrderMsg(msg,url);
	}

	private List<HandleMsgAdapter> listeners = new ArrayList(3);

	public static QiduSessionAction newInstance() {
		return new QiduSessionAction();
	}

	public void addOnHandleMessageListener(HandleMsgAdapter handleMassge) {
		this.listeners.add(handleMassge);
	}

	public void removeOnHandleMsgAdapter(HandleMsgAdapter handleMassge) {
		this.listeners.remove(handleMassge);
	}

	public void process(InputStream is, OutputStream os, String cid, String url) {
		this.os = os;
		try {
			Document document = builder.parse(is);
			Msg4Head head = new Msg4Head();
			head.read(document);
			String type = head.getMsgType();
		    
			if ("text".equals(type)) {
				Msg4Text msg = new Msg4Text(head);
				msg.read(document);
				saveReceiveMsg(msg, cid, type);
				onTextMsg(msg,url);
			} else if ("image".equals(type)) {
				Msg4Image msg = new Msg4Image(head);
				msg.read(document);
				saveReceiveMsg(msg, cid, type);
				onImageMsg(msg);
			} else if ("event".equals(type)) {
			    String eventKey = document.getElementsByTagName("Event").item(0).getTextContent();
			    if(eventKey.equals("merchant_order")||eventKey.equals("MERCHANT_ORDER")){
			    	Msg4Order msg = new Msg4Order(head);
			    	msg.read(document);
			    	saveReceiveMsg(msg, cid, "merchant_order");
			    	onOrderMsg(msg,url);
			    }else{
			    	Msg4EventNew msg = new Msg4EventNewUtil(head);
			    	msg.read(document);
			    	saveReceiveMsg(msg, cid, type);
			    	onEventMsg(msg,url);
			    }
			} else if ("link".equals(type)) {
				Msg4Link msg = new Msg4Link(head);
				msg.read(document);
				saveReceiveMsg(msg, cid, type);
				onLinkMsg(msg);
			} else if ("location".equals(type)) {
				Msg4Location msg = new Msg4Location(head);
				msg.read(document);
				saveReceiveMsg(msg, cid, type);
				onLocationMsg(msg,url);
			} else if ("voice".equals(type)) {
				Msg4Voice msg = new Msg4Voice(head);
				msg.read(document);
				onVoiceMsg(msg);
			} else if ("video".equals(type)) {
				Msg4Video msg = new Msg4Video(head);
				msg.read(document);
				onVideoMsg(msg);
			} else if ("merchant_order".equals(type)) {
				Msg4Order msg = new Msg4Order(head);
				msg.read(document);
				onOrderMsg(msg,url);
			} else {
				onErrorMsg(-1);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveReceiveMsg(Msg msg, String cid, String type) {
		//WxReceiveMsgDao dao = new WxReceiveMsgDao();
		WxReceiveMsgInfo info = new WxReceiveMsgInfo();
		info.setCid(Integer.valueOf(cid));
		info.setFromusername(msg.getFromUserName());
		info.setCreatetime(Long.valueOf(msg.getCreateTime()));
		info.setMsgtype(type);
		if ("text".equals(type)) {
			Msg4Text msgText = (Msg4Text) msg;
			info.setMsgid(msgText.getMsgId());
			info.setContent(msgText.getContent());
		} else if ("image".equals(type)) {
			Msg4Image msgImage = (Msg4Image) msg;
			info.setMsgid(msgImage.getMsgId());
			info.setPicUrl(msgImage.getPicUrl());
		} else if ("event".equals(type)) {
			Msg4EventNew msgEven = (Msg4EventNew) msg;
			info.setEvent(msgEven.getEvent());
			info.setEventKey(msgEven.getEventKey());
			info.setScanValue(msgEven.getScanValue());
		} else if ("link".equals(type)) {
			Msg4Link msgLink = (Msg4Link) msg;
			info.setUrl(msgLink.getUrl());
			info.setTitle(msgLink.getTitle());
			info.setDescription(msgLink.getDescription());
		} else if ("location".equals(type)) {
			Msg4Location msgLocation = (Msg4Location) msg;
			info.setLabel(msgLocation.getLabel());
			info.setLocation_x(msgLocation.getLocation_X());
			info.setLocation_y(msgLocation.getLocation_Y());
			info.setScale(msgLocation.getScale());
		}else if ("merchant_order".equals(type)) {
			Msg4Order msgOrder = (Msg4Order) msg;
			info.setMsgtype(msgOrder.getHead().getMsgType());
			info.setEvent(msgOrder.getEvent());
			info.setOrderId(msgOrder.getOrderId());
			info.setOrderStatus(msgOrder.getOrderStatus());
			info.setProductId(msgOrder.getProductId());
			info.setSkuInfo(msgOrder.getSkuInfo());
		}
		//dao.inserWxReceiveMsg(info);

	}

	public void callback(Msg msg, String cid,WxSendmsgInfo info) {
		Document document = builder.newDocument();
		if(msg instanceof Msg4Voice){
			write(document,(Msg4Voice) msg);
		}else if(msg instanceof Msg4Image){
			write(document, (Msg4Image)msg);
		}else if(msg!=null){
			Msg4Text replyText=new Msg4Text();
			replyText.setContent("欢迎使用");
			replyText.setCreateTime(String.valueOf(new Date().getTime()));
			replyText.setFromUserName(msg.getFromUserName());
			replyText.setToUserName(msg.getToUserName());
			msg=replyText;
			
			msg.write(document);
		}
		try {
			Transformer transformer = tffactory.newTransformer();
			transformer.setOutputProperty("encoding", "utf-8");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			transformer.transform(new DOMSource(document), new StreamResult(
					this.os));
			transformer.transform(new DOMSource(document),
					new StreamResult(bos));
			xmlStr = bos.toString("utf-8");
			System.out.println(xmlStr);
			saveSendMsg(cid, xmlStr,info);
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void write(Document document, Msg4Voice msg) {
		Element root = document.createElement("xml");
		Msg4Head head = msg.getHead();
		head.write(root, document);
	    Element voiceElement = document.createElement("Voice");
	    Element mediaIdElement = document.createElement("MediaId");
	    mediaIdElement.setTextContent(msg.getMediaId());
	    voiceElement.appendChild(mediaIdElement);
	    root.appendChild(voiceElement);
	    document.appendChild(root);
	}
	
	private void write(Document document, Msg4Image msg) {
		Element root = document.createElement("xml");
		Msg4Head head = msg.getHead();
		head.write(root, document);
	    Element voiceElement = document.createElement("Image");
	    Element mediaIdElement = document.createElement("MediaId");
	    mediaIdElement.setTextContent(msg.getMediaId());
	    voiceElement.appendChild(mediaIdElement);
	    root.appendChild(voiceElement);
	    document.appendChild(root);
	}

	private void saveSendMsg(String cid, String xmlStr,WxSendmsgInfo info) {
		info.setSendxml(xmlStr);
		info.setCid(Integer.valueOf(cid));
		info.setCreatetime(Calendar.getInstance().getTime());
		//WxSendMsgDao dao = new WxSendMsgDao();
		//dao.insertWxSendmsgInfo(info);
	}

	static {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTextMsg(Msg4Text arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEventMsg(Msg4Event arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void onEventMsg(Msg4EventNew arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onImageMsg(Msg4Image arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLinkMsg(Msg4Link arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationMsg(Msg4Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVideoMsg(Msg4Video arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVoiceMsg(Msg4Voice arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void onOrderMsg(Msg4Order arg0) {
		// TODO Auto-generated method stub
		
	}
}
