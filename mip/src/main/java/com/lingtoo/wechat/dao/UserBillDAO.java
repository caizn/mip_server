package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.UserIntegralBill;
import com.lingtoo.wechat.pojo.UserMoneyBill;

@MyBatisRepository
public interface UserBillDAO {
	void insertUserMoneyBill(UserMoneyBill userMoneyBill) ;
	
	void updateUserMoneyBill(UserMoneyBill userMoneyBill);
	
	void insertUserIntegralBill(UserIntegralBill userIntegralBill);
	
	void updateUserIntegralBill(UserIntegralBill userIntegralBill);
	
	UserMoneyBill selectUserMoneyBillById(Integer userMoneyBillId);
	
	UserIntegralBill selectUserIntegralBillById(Integer userIntegralBillId);
	
	Integer selectUserMoneyBillCount(
			@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	Integer selectUserIntegralBillCount(
			@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	List<UserMoneyBill> selectUserMoneyBills(
			@Param("beginTime")String beginTime,@Param("endTime")String endTime,
			@Param("pageStart") Integer pageStart, @Param("maxResult") Integer maxResult);
	
	List<UserIntegralBill> selectUserIntegralBills(
			@Param("beginTime")String beginTime,@Param("endTime")String endTime,
			@Param("pageStart") Integer pageStart, @Param("maxResult") Integer maxResult);
}
