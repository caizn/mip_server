package com.lingtoo.wechat.dao;

import java.util.List;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.RegionInfo;


@MyBatisRepository
public interface RegionInfoDAO {

	List<RegionInfo> findRegionInfoList();
	
}
