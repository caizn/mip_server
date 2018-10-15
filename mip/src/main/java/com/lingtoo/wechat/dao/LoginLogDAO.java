package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.LoginLog;


@MyBatisRepository
public interface LoginLogDAO {

	
	void addLoginLog(LoginLog loginLog);
	
	int findLoginLogCount(@Param("merchantId")Integer merchantId,@Param("account") String account,@Param("realName") String realName,@Param("accountType") String accountType);
	List<LoginLog> findLoginLogList(@Param("merchantId")Integer merchantId,@Param("account") String account,@Param("realName") String realName,@Param("accountType") String accountType,@Param("pageStart") int pageStart, @Param("maxResult") int maxResult);
}
