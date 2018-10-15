package com.lingtoo.wechat.controller.mobile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.dao.DecorationOrderDAO;
import com.lingtoo.wechat.dao.DecorationWorkerDAO;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.dao.WxPayDAO;
import com.lingtoo.wechat.pojo.DecorationOrder;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.pojo.Merchant;
import com.lingtoo.wechat.pojo.MerchantApp;
import com.lingtoo.wechat.pojo.WechatUser;
import com.lingtoo.wechat.pojo.WxPayProductorder;
import com.lingtoo.wechat.pojo.WxPayReceivemsgpost;
import com.lingtoo.wechat.pojo.WxPaySet;
import com.lingtoo.wechat.service.DecorationOrderService;
import com.lingtoo.wechat.service.MerchantService;
import com.lingtoo.wechat.service.NoticeService;
import com.lingtoo.wechat.service.UserBillService;
import com.lingtoo.wechat.utils.StringUtil;
import com.lingtoo.wechat.utils.SystemConfig;
import com.lingtoo.wechat.utils.wxpay.RequestHandler;
import com.lingtoo.wechat.utils.wxpay.WeixinPayV2Util;

@Controller("MobilePayController")
@RequestMapping("/mobile/pay")
public class PayController extends BaseController {
	@Autowired
	private WxPayDAO wxPayDao;
	@Autowired
	private MerchantService merhcnatService;
	@Autowired
	private DecorationOrderService dOrderService;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private DecorationOrderDAO dOrderDao;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private DecorationWorkerDAO dWorkerDao;
	@Autowired
	private UserBillService userBillService;

	@RequestMapping("pay-page")
	public String payPage(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
		Merchant merchant = this.getMerchantMobileSession(request);
		WxPaySet paySet = wxPayDao.selectWxPaySet(merchant.getMerchantId());
		model.addAttribute("paySet", paySet);
		return "/mobile/pay/pay";
	}

	@RequestMapping("deal-result")
	public String dealResult(String status, String tradeNo, String error, String orderIdArray, HttpSession session,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		Merchant merchant = this.getMerchantMobileSession(request);
		WxPayProductorder order = new WxPayProductorder();
		order = wxPayDao.selectProductorOrderByTradeNo(tradeNo);
		boolean sign = false;
		if (status.equals("success")) {

			WxPayReceivemsgpost post = new WxPayReceivemsgpost();
			post.setMerchantId(merchant.getMerchantId());
			post.setOutTradeNo(order.getOutTradeNo());
			post = wxPayDao.selectReceivemsgpostByTradeNo(merchant.getMerchantId(), order.getOutTradeNo());
			DecorationOrder dOrder=dOrderDao.selectDOrderByOrderNo(order.getOutTradeNo());
			if(dOrder.getVisitPayOrderNo().equals(order.getOutTradeNo())) {
				dOrder.setStatus(0);
			}
			if(dOrder.getWorkPayOrderNo().equals(order.getOutTradeNo())) {
				dOrder.setStatus(5);
			}
			dOrderDao.updateByPrimaryKey(dOrder);
		} else {
			order.setStatus(0);
			order.setDealtime(new Date());
			wxPayDao.updateProductOrder(order);
			request.setAttribute("order", order);
			System.out.println(error);
			// return "/mobile/pay/payResult";
			sign = true;
		}
		String url = "";
		JSONArray array = new JSONArray(orderIdArray);
		if (array.length() == 1) {
			Integer orderId = array.getInt(0);
			url = "/mobile/order/detail?shopOrderId=" + orderId + "&state=" + this.getAppidMobileSession(request);
		} else {
			url = "/mobile/order/list?state=" + this.getAppidMobileSession(request);
		}
		if (sign) {
			return "redirect:" + url;
		} else {
			try {
				url = "&url=" + URLEncoder.encode(url, UTF8);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/mobile/pay/wait?state=" + this.getAppidMobileSession(request) + url + "&tradeNo="
					+ tradeNo;
		}
	}

	@RequestMapping("check-pay-order")
	@ResponseBody
	public String checkPayOrder(String url, String tradeNo, HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println(tradeNo);
		WxPayProductorder order = new WxPayProductorder();
		order = wxPayDao.selectProductorOrderByTradeNo(tradeNo);

		WxPayReceivemsgpost post = new WxPayReceivemsgpost();
		boolean sign=false;
		if(order!=null) {
			if(order.getStatus().equals(1)) {
				sign=true;
			}
		}
		if(sign){
			return "success";
		}else {
			return "failure";
		}
	}

	@RequestMapping("wait")
	public String wait(String url, String tradeNo, HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		url = URLDecoder.decode(url);
		model.addAttribute("url", url);
		model.addAttribute("tradeNo", tradeNo);
		return "/mobile/pay/wait";
	}

	@RequestMapping("unified-order")
	@ResponseBody
	public String unifiedOrder(String ip, Double totalFee, Integer dOrderId,String payType, HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Merchant merchant = this.getMerchantMobileSession(request);
		MerchantApp merchantApp = merhcnatService.findMerchantApp(merchant.getMerchantId());
		WechatUser wechatUser = this.getWechatUserSession(request);
		WxPaySet paySet = wxPayDao.selectWxPaySet(merchant.getMerchantId());
		String openid = wechatUser.getOpenid();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currTime = sdf.format(new Date());
		String strTime = currTime.substring(8, currTime.length());
		String ran = String.valueOf(Math.random() * 10000);
		ran = ran.substring(0, ran.indexOf("."));
		String strRandom = ran + "";
		String nonce_str = strTime + strRandom;
		/* 构建随机字符串nonce_str */

		WxPayProductorder order = new WxPayProductorder();
		order.setMerchantId(merchant.getMerchantId());
		String trade_no = merchantApp.getAppId() + sdf.format(new Date());
		order.setOutTradeNo(trade_no);
		order.setDealtime(new Date());
		order.setTotalFee(totalFee);
		order.setBody("订单支付");
		order.setStatus(WxPayProductorder.UNCONFIRM);
		order.setPaytype("wechat");
		order.setOpenid(wechatUser.getOpenid());

		String url = SystemConfig.getContextPath();
		url += "/mobile/pay/" + merchant.getMerchantId() + "/notify-api";
		order.setNotifyUrl(url);

		wxPayDao.insertProductOrder(order);
		
		DecorationOrder dOrder=dOrderService.getDOrderById(dOrderId);
		if(payType.equals("visit")) {
			dOrder.setVisitPayOrderNo(trade_no);
		}else if(payType.equals("work")) {
			dOrder.setWorkPayOrderNo(trade_no);
		}
		dOrderService.alert(dOrder);

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", paySet.getAppid());
		packageParams.put("mch_id", paySet.getPartnerid());
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", order.getBody());
		packageParams.put("out_trade_no", order.getOutTradeNo());
		String new_total_fee= String.valueOf(totalFee*100);
		String totalFeeString=new_total_fee.substring(0,new_total_fee.indexOf("."));
		/*totalFeeString = totalFeeString.replace(".","");
		if (totalFeeString.indexOf(".") >= 0) {
			totalFeeString = totalFeeString.substring(0, totalFeeString.indexOf("."));
		}*/
		packageParams.put("total_fee", totalFeeString);
		packageParams.put("spbill_create_ip", ip);
		packageParams.put("notify_url", order.getNotifyUrl());
		packageParams.put("trade_type", "JSAPI");
		packageParams.put("openid", openid);
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(paySet.getAppid(), paySet.getAppsecret(), paySet.getPartnerkey());
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + paySet.getAppid() + "</appid>" + "<mch_id>" + paySet.getPartnerid()
				+ "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + order.getBody() + "]]></body>" + "<out_trade_no>" + trade_no + "</out_trade_no>"
				+ "<total_fee>" + Integer.valueOf(totalFeeString) + "</total_fee>" + "<spbill_create_ip>" + ip
				+ "</spbill_create_ip>" + "<notify_url>" + order.getNotifyUrl() + "</notify_url>" + "<trade_type>"
				+ "JSAPI" + "</trade_type>" + "<openid>" + openid + "</openid>" + "</xml>";
		System.out.println(xml);
		JSONObject obj = WeixinPayV2Util.unifiedorder(xml);
		obj.put("out_trade_no", trade_no);
		return obj.toString();
	}

	@RequestMapping("{merchantId}/notify-api")
	@ResponseBody
	public String notifyApi(@PathVariable Integer merchantId, HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("!!!!!!!!!!!");
		DocumentBuilder builder = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		InputStream is = null;
		try {
			is = request.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 必须调用这两个方法
		// 如果不调用close方法，将会出现响应数据串到其它Servlet中。
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[1024];
		int n = 0;
		try {
			while ((n = is.read(b)) != -1) {
				out.append(new String(b, 0, n, "utf-8"));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String str = out.toString();
		if (str.indexOf("?") == 0)
			str = str.substring(1);
		if(StringUtil.isEmpty(str))
			str="<xml><appid><![CDATA[wxa467b05e3bc5bd09]]></appid><bank_type><![CDATA[CMB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1496583032]]></mch_id><nonce_str><![CDATA[1439138566]]></nonce_str><openid><![CDATA[o9GSAwyWHxBpBA31JNfAV3Iy8rS8]]></openid><out_trade_no><![CDATA[wxa467b05e3bc5bd0920180309143913]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[825F81BB96EBF341CA48C92882CFC32C]]></sign><time_end><![CDATA[20180309143924]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[4200000065201803095487892338]]></transaction_id></xml>";
		System.out.println(str);
		InputStream input = null;
		try {
			input = new ByteArrayInputStream(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document document = null;
		try {
			document = builder.parse(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Merchant merchant = merhcnatService.getMerchantById(merchantId);
		WxPayReceivemsgpost post = new WxPayReceivemsgpost();
		String return_code = document.getElementsByTagName("return_code").item(0).getTextContent();
		post.setReturnCode(return_code);
		if (return_code.equals("SUCCESS")) {
			String appid = document.getElementsByTagName("appid").item(0).getTextContent();
			String mch_id = document.getElementsByTagName("mch_id").item(0).getTextContent();
			String sign = document.getElementsByTagName("sign").item(0).getTextContent();
			if (document.getElementsByTagName("device_info").getLength() != 0) {
				String device_info = document.getElementsByTagName("device_info").item(0).getTextContent();
				post.setDeviceInfo(device_info);
				System.out.println(device_info);
			}
			if (document.getElementsByTagName("err_code").getLength() != 0) {
				String err_code = document.getElementsByTagName("err_code").item(0).getTextContent();
				post.setErrCode(err_code);
				System.out.println(err_code);
			}
			if (document.getElementsByTagName("err_code_des").getLength() != 0) {
				String err_code_des = document.getElementsByTagName("err_code_des").item(0).getTextContent();
				post.setErrCode(err_code_des);
				System.out.println(err_code_des);
			}
			String nonce_str = document.getElementsByTagName("nonce_str").item(0).getTextContent();
			String result_code = document.getElementsByTagName("result_code").item(0).getTextContent();
			post.setSign(sign);
			System.out.println(sign);
			post.setAppid(appid);
			System.out.println(appid);
			post.setMchId(mch_id);
			System.out.println(mch_id);
			post.setResultCode(result_code);
			System.out.println(result_code);
			post.setNonceStr(nonce_str);
			System.out.println(nonce_str);
			if (result_code.equals("SUCCESS")) {
				String openid = document.getElementsByTagName("openid").item(0).getTextContent();
				String out_trade_no = document.getElementsByTagName("out_trade_no").item(0).getTextContent();
				String time_end = document.getElementsByTagName("time_end").item(0).getTextContent();
				if (document.getElementsByTagName("coupon_fee").getLength() != 0) {
					String total_fee = document.getElementsByTagName("total_fee").item(0).getTextContent();
					post.setTotalFee(Integer.valueOf(total_fee));
					System.out.println(total_fee);
				}
				String trade_type = document.getElementsByTagName("trade_type").item(0).getTextContent();
				String transaction_id = document.getElementsByTagName("transaction_id").item(0).getTextContent();
				String bank_type = document.getElementsByTagName("bank_type").item(0).getTextContent();
				String is_subscribe = document.getElementsByTagName("is_subscribe").item(0).getTextContent();
				if (document.getElementsByTagName("coupon_fee").getLength() != 0) {
					String coupon_fee = document.getElementsByTagName("coupon_fee").item(0).getTextContent();
					post.setCouponFee(Integer.parseInt(coupon_fee));
					System.out.println(coupon_fee);
				}
				if (document.getElementsByTagName("attach").getLength() != 0) {
					String attach = document.getElementsByTagName("attach").item(0).getTextContent();
					post.setAttach(attach);
					System.out.println(attach);
				}

				if (document.getElementsByTagName("coupon_fee").getLength() != 0) {
					String fee_type = document.getElementsByTagName("fee_type").item(0).getTextContent();
					//post.setFeeType(Integer.valueOf(fee_type));
					System.out.println(fee_type);
				}
				post.setOpenid(openid);
				System.out.println(openid);
				post.setIsSubscribe(is_subscribe);
				System.out.println(is_subscribe);
				post.setTradeType(trade_type);
				System.out.println(trade_type);
				post.setBankType(bank_type);
				System.out.println(bank_type);
				post.setOutTradeNo(out_trade_no);
				System.out.println(out_trade_no);
				post.setTimeEnd(time_end);
				System.out.println(time_end);
				post.setTransactionId(transaction_id);
				System.out.println(transaction_id);
				OrderDeal(post, merchant);
			}
		} else {
			if (document.getElementsByTagName("return_msg").getLength() != 0) {
				String return_msg = document.getElementsByTagName("return_msg").item(0).getTextContent();
				post.setReturnMsg(return_msg);
				System.out.println(return_msg);
			}
		}
		post.setMerchantId(merchant.getMerchantId());
		if ("SUCCESS".equals(post.getResultCode())) {
			wxPayDao.insertReceivemsgpost(post);
		}

		WxPayProductorder order = new WxPayProductorder();
		order = wxPayDao.selectProductorOrderByTradeNo(post.getOutTradeNo());
		order.setTradeNo(post.getTransactionId());
		order.setDealtime(new Date());
		order.setStatus(WxPayProductorder.CONFIRM);
		order.setTradeNo(post.getTransactionId());
		wxPayDao.updateProductOrder(order);

		String returnXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
				+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
		JSONObject obj = new JSONObject();
		obj.put("return_code", "SUCCESS");
		System.out.println(obj.toString());
		return returnXml;
	}

	public void OrderDeal(WxPayReceivemsgpost post, Merchant merchant) {
		WxPayProductorder po = wxPayDao.selectProductorOrderByTradeNo(post.getOutTradeNo());
		

		DecorationOrder dOrder=dOrderDao.selectDOrderByOrderNo(po.getOutTradeNo());
		if(dOrder.getVisitPayOrderNo()!=null) {
			if(dOrder.getVisitPayOrderNo().equals(po.getOutTradeNo())&&dOrder.getStatus().equals(-2)) {
				dOrder.setStatus(0);
				dOrder.setVisitPrice(po.getTotalFee());
				dOrderDao.updateByPrimaryKey(dOrder);
				/*支付成功时，给管理员发送消息模板*/
		        String noticeUrl=SystemConfig.getContextPath()+"/mobile/manager/order-detail?dOrderId="+dOrder.getDecorationOrderId();
			    noticeService.noticeAdminNewOrder(noticeUrl, dOrder);
			}
		}
		if(dOrder.getWorkPayOrderNo()!=null) {
			if(dOrder.getWorkPayOrderNo().equals(po.getOutTradeNo())&&dOrder.getStatus().equals(4)) {
				dOrder.setStatus(5);
				dOrderDao.updateByPrimaryKey(dOrder);
				userBillService.payOrder(dOrder);
				/*二次支付成功时，给管理员发送消息模板*/
		        String noticeUrl=SystemConfig.getContextPath()+"/mobile/manager/order-detail-show?dOrderId="+dOrder.getDecorationOrderId();
		        noticeService.noticeAdminOrderFinish( noticeUrl, dOrder);
		        /*for(WechatUser wUser:wechatUserList) {
			        noticeService.noticeAdminOrderFinish( noticeUrl, dOrder);
				}*/
				/*二次支付成功时，给管理员发送消息模板*/
				noticeUrl=SystemConfig.getContextPath()+"/mobile/worker/order-detail?dOrderId="+dOrder.getDecorationOrderId();
				DecorationWorker worker=dWorkerDao.selectDWorkerById(dOrder.getDecorationWorkerId());
				WechatUser wUser=userDao.selectWechatUserByUserId(worker.getUserId());
		        noticeService.noticeWorkerOrderFinish(wUser.getOpenid(), noticeUrl, dOrder);
			}
		}
		
	}
	public static void main(String[] args) {
		Double fee=33.28;
		fee=fee*100;
		String feeString=String.valueOf(fee);
		feeString=feeString.substring(0,feeString.indexOf("."));
		System.out.println(feeString);
	}
}