package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.DecorationOrder;

@MyBatisRepository
public interface DecorationOrderDAO {
    int deleteByPrimaryKey(Integer decorationOrderId);

    int insert(DecorationOrder record);

    DecorationOrder selectByPrimaryKey(Integer decorationOrderId);

    int updateByPrimaryKeySelective(DecorationOrder record);

    int updateByPrimaryKey(DecorationOrder record);

    DecorationOrder selectDOrderById(Integer dOrderId);
    
    DecorationOrder selectLastDOrder();
	
	DecorationOrder selectDOrderByOrderNo(@Param("orderNo")String orderNo);
	
	Integer selectDOrdersCount(
			@Param("orderNo")String orderNo,		@Param("telephone")String telephone,		@Param("name")String name,
			@Param("emergencyStatus")Integer emergencyStatus,										
			@Param("type")Integer type,				@Param("status")Integer status);
	
	List<DecorationOrder> selectDOrders(
			@Param("orderNo")String orderNo,		@Param("telephone")String telephone,		@Param("name")String name,
			@Param("emergencyStatus")Integer emergencyStatus,										
			@Param("type")Integer type,				@Param("status")Integer status,
			@Param("pageStart") Integer pageStart, 	@Param("maxResult") Integer maxResult);
	
	List<DecorationOrder> selectDOrdersByUser(
			@Param("userType")Integer userType,		@Param("userId")Integer userId,				@Param("status")Integer status,
			@Param("pageStart") Integer pageStart, 	@Param("maxResult") Integer maxResult);
}