package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.DecorationWorker;

@MyBatisRepository
public interface DecorationWorkerDAO {
	void insert(DecorationWorker dWorker);
	
	void update(DecorationWorker dWorker);
	
	DecorationWorker selectDWorkerByUserId(Integer userId);
	
	DecorationWorker selectDWorkerById(Integer dWorkerId);
	
	Integer selectDWorkersCount(@Param("name") String name,@Param("phone")String phone,@Param("auditStatus")Integer auditStatus);
	
	List<DecorationWorker> selectDWorkers(@Param("name") String name,@Param("phone")String phone,@Param("auditStatus")Integer auditStatus,
			@Param("pageStart") Integer pageStart, @Param("maxResult") Integer maxResult);
	
	List<DecorationWorker> selectDWorkersByArea(
			@Param("province")String province,@Param("city")String city,@Param("area")String area,@Param("itemId")String itemId);
}
