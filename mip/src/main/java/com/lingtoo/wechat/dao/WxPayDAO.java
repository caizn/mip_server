package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.WxPayProductorder;
import com.lingtoo.wechat.pojo.WxPayReceivemsgpost;
import com.lingtoo.wechat.pojo.WxPayRefund;
import com.lingtoo.wechat.pojo.WxPaySet;

@MyBatisRepository
public interface WxPayDAO {
	void insertProductOrder(WxPayProductorder order);
	
	void updateProductOrder(WxPayProductorder order);
	
	void insertReceivemsgpost(WxPayReceivemsgpost post);
	
	void insertPayRefund(WxPayRefund refund);
	
	List<WxPayRefund> selectRefundListByTradeNo(@Param("tradeNo")String tradeNo);
	
	WxPaySet selectWxPaySet(Integer merchantId);
	
	WxPayProductorder selectProductorOrderByTradeNo(@Param("tradeNo")String tradeNo);
	
	WxPayReceivemsgpost selectReceivemsgpostByTradeNo(
			@Param("merchantId")Integer merchantId,@Param("tradeNo")String tradeNo);
}
