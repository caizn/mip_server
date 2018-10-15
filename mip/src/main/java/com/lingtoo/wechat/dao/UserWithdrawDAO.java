package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.UserWithdraw;
@MyBatisRepository
public interface UserWithdrawDAO {
	void insert(UserWithdraw uWithdraw);
	
	void update(UserWithdraw uWithdraw);
	
	UserWithdraw selectLastUserWithdrawByUserId(Integer userId);
	
	UserWithdraw selectUserWithdrawById(Integer uWithdrawId);
	
	int selectUserWithdrawCount(@Param("name")String name,@Param("uWithdrawNo")String uWithdrawNo,
			@Param("userId")Integer userId,@Param("status")Integer status);
	
	List<UserWithdraw> selectUserWithdrawPage(
			@Param("name")String name,@Param("uWithdrawNo")String uWithdrawNo,
			@Param("userId")Integer userId,@Param("status")Integer status,
			@Param("pageStart") Integer pageStart, @Param("maxResult") Integer maxResult);
}
