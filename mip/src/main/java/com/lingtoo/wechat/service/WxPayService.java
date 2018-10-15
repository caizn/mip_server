package com.lingtoo.wechat.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.dao.WxPayDAO;
import com.lingtoo.wechat.pojo.WxPayProductorder;
import com.lingtoo.wechat.pojo.WxPayRefund;
import com.lingtoo.wechat.pojo.WxPaySet;
import com.lingtoo.wechat.utils.wxpay.WeixinPayV3RefundUtil;

@Service
public class WxPayService {
	@Autowired
	private WxPayDAO wxPayDao;
	
	public Integer refund(String payOrderNo,Integer merchantId) {
		WxPayProductorder wpp = wxPayDao.selectProductorOrderByTradeNo(payOrderNo);
		WxPaySet wps = wxPayDao.selectWxPaySet(merchantId);
		if (wpp != null) {
			JSONObject object = new JSONObject();
			object.put("transaction_id", wpp.getTradeNo());
			object.put("out_trade_no", wpp.getOutTradeNo());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String refund = "refund" + sdf.format(new Date());
			object.put("out_refund_no", refund);
			Integer totalFee = Double.valueOf(wpp.getTotalFee() * 100).intValue();
			Integer refundFee = Double.valueOf(wpp.getTotalFee() * 100).intValue();
			List<WxPayRefund> wprList = wxPayDao.selectRefundListByTradeNo(wpp.getOutTradeNo());
			Integer allRefundFee = refundFee;
			for (WxPayRefund wpr : wprList) {
				Double fee=Double.valueOf(wpr.getRefundFee())*100;
				allRefundFee += fee.intValue();
			}
			if (allRefundFee <= totalFee) {
				object.put("total_fee", String.valueOf(totalFee));
				object.put("refund_fee", String.valueOf(refundFee));
				object.put("op_user_id", wps.getPartnerid());
				object = WeixinPayV3RefundUtil.bulidRefundOrder(object, wps);
				WxPayRefund wprf = new WxPayRefund(wps, wpp);
				wxPayDao.insertPayRefund(wprf);
				if (object.get("result_code") != null) {
					if ("SUCCESS".equals(object.getString("result_code")) && allRefundFee == totalFee) {
						if (allRefundFee <= totalFee) {
							wpp.setStatus(WxPayProductorder.REFUND);
							wxPayDao.updateProductOrder(wpp);
						}
						return 0;
					}else {
						return -2;
					}
				}else {
					return -3;
				}
			}else {
				return -4;
			}
		} else {
			return -1;
		}
	}
}
