package com.lingtoo.wechat.utils.wxpay;

import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.w3c.dom.Document;

import com.lingtoo.wechat.pojo.WxPaySet;
import com.lingtoo.wechat.utils.StringUtil;

/**
 * 微信支付V3退款接口
 * @author Caizn
 */
public class WeixinPayV3RefundUtil {
	private static Logger log = Logger.getLogger(WeixinPayV3RefundUtil.class);
	public static String refuncd_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	/**
	 * 订单退款请求
	 * 
	 * @param xml
	 *            请求xml信息
	 * @return
	 */
	public static JSONObject refundOrder(String postData,WxPaySet pSet) {
		log.warn("调用微信支付退款接口开始");
		Document returnXml = WeixinHttpUtil.httpKeyRequest(refuncd_url,
				postData.toString(),pSet);
		JSONObject returnObj = new JSONObject();
		if (null != returnXml) {
			String return_code = returnXml.getElementsByTagName("return_code")
					.item(0).getTextContent();
			returnObj.put("return_code", return_code);
			if (return_code.equals("SUCCESS")) {
				returnObj.put("appid",
						returnXml.getElementsByTagName("appid")
								.item(0).getTextContent());
				returnObj.put("mch_id",
						returnXml.getElementsByTagName("mch_id").item(0)
								.getTextContent());
				returnObj.put("nonce_str",
						returnXml.getElementsByTagName("nonce_str").item(0)
								.getTextContent());
				returnObj.put("sign",
						returnXml.getElementsByTagName("sign")
								.item(0).getTextContent());
				returnObj.put("result_code",
						returnXml.getElementsByTagName("result_code").item(0)
								.getTextContent());
				if ("SUCCESS".equals(returnObj.getString("result_code"))) {
					returnObj.put("transaction_id", returnXml
							.getElementsByTagName("transaction_id").item(0)
							.getTextContent());
					returnObj.put("out_trade_no", returnXml
							.getElementsByTagName("out_trade_no").item(0)
							.getTextContent());
					returnObj.put("out_refund_no", returnXml
							.getElementsByTagName("out_refund_no").item(0)
							.getTextContent());
					returnObj.put("refund_id",
							returnXml.getElementsByTagName("refund_id")
									.item(0).getTextContent());
					returnObj.put("refund_fee",
							returnXml.getElementsByTagName("refund_fee")
									.item(0).getTextContent());
					returnObj.put("total_fee",
							returnXml.getElementsByTagName("total_fee")
									.item(0).getTextContent());
					returnObj.put("cash_fee",
							returnXml.getElementsByTagName("cash_fee")
									.item(0).getTextContent());
				} else {
					returnObj.put("err_code",
							returnXml.getElementsByTagName("err_code").item(0)
									.getTextContent());
					returnObj.put("err_code_des", returnXml
							.getElementsByTagName("err_code_des").item(0)
							.getTextContent());
					log.error("调用微信支付退款接口错误：错误代码："
							+ returnXml.getElementsByTagName("err_code").item(0).getTextContent());
					log.error("调用微信支付退款接口错误：错误代码描述："
							+ returnXml.getElementsByTagName("err_code_des").item(0).getTextContent());
				}

			} else {
				returnObj.put("return_msg",
						returnXml.getElementsByTagName("return_msg").item(0)
								.getTextContent());
				log.error("调用微信支付退款接口失败：失败信息："
						+ returnXml.getElementsByTagName("return_msg"));
			}
		}
		return returnObj;
	}

	/**
	 * 构建微信订单退订
	 * 
	 * @param postObj
	 * @param pSet
	 * @return
	 */
	public static JSONObject bulidRefundOrder(JSONObject postObj, WxPaySet pSet) {
		String nonce_str = StringUtil.randomString(32);
		String transaction_id = postObj.getString("transaction_id");
		String out_trade_no = postObj.getString("out_trade_no");
		String out_refund_no = postObj.getString("out_refund_no");
		String total_fee = postObj.getString("total_fee");
		String refund_fee = postObj.getString("refund_fee");
		String op_user_id = postObj.getString("op_user_id");
		String mch_id = pSet.getPartnerid();
		String appid = pSet.getAppid();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("transaction_id", transaction_id);
		packageParams.put("mch_id", mch_id);
		packageParams.put("appid", appid);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", total_fee);
		packageParams.put("refund_fee", refund_fee);
		packageParams.put("op_user_id", op_user_id);
		RequestHandler reqHandler = new RequestHandler();
		reqHandler.setKey(pSet.getPartnerkey());
		String sign = reqHandler.createSign(packageParams);
		String postData = "<xml>" + "<nonce_str>" + nonce_str + "</nonce_str>"
				+ "<transaction_id>" + transaction_id + "</transaction_id>"
				+ "<mch_id>" + mch_id + "</mch_id>" + "<appid>" + appid
				+ "</appid>" + "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<out_refund_no>" + out_refund_no
				+ "</out_refund_no>" + "<total_fee>" + total_fee
				+ "</total_fee>" + "<refund_fee>" + refund_fee
				+ "</refund_fee>" + "<op_user_id>" + op_user_id
				+ "</op_user_id>" + "<sign>" + sign + "</sign>" + "</xml>";
		log.error(postData);
		JSONObject returnObj = refundOrder(postData,pSet);
		return returnObj;
	}
}