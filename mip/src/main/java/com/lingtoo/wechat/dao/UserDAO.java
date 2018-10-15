package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.UserAddress;
import com.lingtoo.wechat.pojo.WechatUser;

@MyBatisRepository
public interface UserDAO {
	void insertUser(User user);
	
	void insertUserAddress(UserAddress userAddress);
	
	void insertWechatUser(WechatUser wechatUser);
	
	void updateUser(User user);
	
	void updateUserAddress(UserAddress userAddress);
	
	void updateWechatUser(WechatUser wechatUser);
	
	List<Integer> selectUserVisitCount(
			@Param("merchantId")Integer merchantId,			@Param("dayString")String dayString);
	
	Integer selectUserCount(
			@Param("introducerId")Integer introducerId,
			@Param("phone")String phone,				
			@Param("nickname")String nickname,				@Param("name")String name,
			@Param("auditStatus")Integer auditStatus,		@Param("gengeralizeStatus")Integer gengeralize,
			@Param("managerSign")Integer managerSign);
	
	Integer selectUserCountByIntroduce(@Param("introducerId")Integer introducerId);
	
	List<User> selectUserByIntroduce(@Param("introducerId")Integer introducerId,
			@Param("pageStart") int pageStart, 				@Param("maxResult") int maxResult);
	
	List<User> selectUserPage(
			@Param("introducerId")Integer introducerId,
			@Param("phone")String phone,					
			@Param("nickname")String nickname,				@Param("name")String name,
			@Param("auditStatus")Integer auditStatus,		@Param("gengeralizeStatus")Integer gengeralize,
			@Param("managerSign")Integer managerSign,
			@Param("pageStart") int pageStart, 				@Param("maxResult") int maxResult);
	
	User selectUserById(Integer userId);
	
	User selectUserByOpenId(@Param("openId")String openId);
	
	WechatUser selectWechatUserByUserId(Integer userId);
	
	WechatUser selectWechatUserByOpenId(@Param("openId")String openId);
	
	List<UserAddress> selectUserAddressByUserId(Integer userId);

    UserAddress selectUserDefaultAddressById(Integer userId);
	
	UserAddress selectUserAddressById(Integer userAddressId);
	
	UserAddress selectLastUserAddressByUserId(Integer userId);

    void updateUserSubscribe(@Param("userId") Integer wechatUserId);
    
    User findUserByRand(@Param("rand")String rand);
    
    List<WechatUser> selectManagerUsers();
}
