package com.lingtoo.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.dao.WebStatisticsDAO;
import com.lingtoo.wechat.pojo.WebStatistics;

@Service
public class WebStatisticsService {
	@Autowired
	private WebStatisticsDAO wsDao;
	
	public void add(WebStatistics ws){
		wsDao.insert(ws);
	}
}
