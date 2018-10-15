package com.lingtoo.wechat.utils.wxpay;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.w3c.dom.Document;

/**
 * 微信支付V2接口
 * @author Caizn
 */
public class WeixinPayV2Util {
	private static Logger log = Logger.getLogger(WeixinPayV2Util.class);
	public static String pay_delivernotify_url = "https://api.weixin.qq.com/pay/delivernotify?access_token=ACCESS_TOKEN";
	public static String pay_orderquery_url = "https://api.weixin.qq.com/pay/orderquery?access_token=ACCESS_TOKEN";
	public static String pay_unifiedorder="https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 统一支付接口
	 * @param accessToken
	 * @param obj
	 * @return
	 */
	public static JSONObject unifiedorder(String xml) {
		Document getXml = WeixinHttpUtil.httpsRequestXml(pay_unifiedorder, "POST", xml);
		JSONObject object=new JSONObject();
		object.put("return_code", getXml.getElementsByTagName("return_code").item(0).getTextContent());
		object.put("return_msg", getXml.getElementsByTagName("return_msg").item(0).getTextContent());
		if(object.getString("return_code").equals("SUCCESS")){
			object.put("appid", getXml.getElementsByTagName("appid").item(0).getTextContent());
			object.put("mch_id", getXml.getElementsByTagName("mch_id").item(0).getTextContent());
			object.put("nonce_str", getXml.getElementsByTagName("nonce_str").item(0).getTextContent());
			object.put("sign", getXml.getElementsByTagName("sign").item(0).getTextContent());
			object.put("result_code", getXml.getElementsByTagName("result_code").item(0).getTextContent());
			object.put("prepay_id", getXml.getElementsByTagName("prepay_id").item(0).getTextContent());
			object.put("trade_type", getXml.getElementsByTagName("trade_type").item(0).getTextContent());
		}
		log.error(object.toString());
		return object;
	}
	/**
	 * 发货通知
	 * @param accessToken
	 * @param obj
	 * @return
	 */
	public static JSONObject delivernotify(String accessToken,JSONObject obj) {
		String url = pay_delivernotify_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = WeixinHttpUtil.httpsRequest(url, "POST", obj.toString());
		if (null != jsonObject) {
			if (jsonObject.get("errcode") != null) {
				if (0 != jsonObject.getInt("errcode")) {
					log.error("发货通知失败 errcode:{"
							+ jsonObject.getInt("errcode") + "} errmsg:{"
							+ jsonObject.getString("errmsg") + "}");
				}
			}
		}
		return jsonObject;
	}
	/**
	 * 订单查询
	 * @param accessToken
	 * @param obj
	 * @return
	 */
	public static JSONObject orderquery(String accessToken,JSONObject obj) {
		String url = pay_orderquery_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = WeixinHttpUtil.httpsRequest(url, "POST", obj.toString());
		if (null != jsonObject) {
			if (jsonObject.get("errcode") != null) {
				if (0 != jsonObject.getInt("errcode")) {
					log.error("订单查询失败 errcode:{"
							+ jsonObject.getInt("errcode") + "} errmsg:{"
							+ jsonObject.getString("errmsg") + "}");
				}
			}
		}
		return jsonObject;
	}
}