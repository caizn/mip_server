package com.lingtoo.wechat.dao;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.WebStatistics;

@MyBatisRepository
public interface WebStatisticsDAO {
	int insert(WebStatistics ws);
}
