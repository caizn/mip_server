package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.Manager;

@MyBatisRepository
public interface ManagerDAO {
	/**
	 * 通过账号获取管理员信息
	 * @param account
	 * @return
	 */
	Manager getManagerByAccount(@Param("account") String account);

	Manager getManagerByPhone(@Param("phone") String phone);
	
	Manager getManagerByEmail(@Param("email") String email);
	
	Manager getManagerById(@Param("managerId") Integer managerId);
	
	Manager getManagerByMerchantId(@Param("merchantId") Integer merchantId);
	
	void updateManagerPwd(@Param("managerId")Integer managerId,@Param("password") String password);
	
	void updateManagerStatus(@Param("managerId")Integer managerId,@Param("locked") Integer locked);
	
	void updateManager(Manager manager);
	
	void addManager(Manager manager);
	
	void deleteManager(@Param("managerId") Integer managerId);

	int findManagerCount(@Param("merchantId")Integer merchantId,@Param("account") String account,
			@Param("locked") String locked,@Param("state") String state);
	List<Manager> findManagerList(@Param("merchantId")Integer merchantId,@Param("account") String account,
			@Param("locked") String locked,@Param("state") String stateform,@Param("pageStart") int pageStart, @Param("maxResult") int maxResult);
}
