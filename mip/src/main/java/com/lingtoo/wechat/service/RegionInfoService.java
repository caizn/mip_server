package com.lingtoo.wechat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.dao.RegionInfoDAO;
import com.lingtoo.wechat.pojo.RegionInfo;

@Service
public class RegionInfoService {

	private static Map<String,String> regionInfoMap;
	
    @Autowired
    private RegionInfoDAO regionInfoDAO;
    
	@PostConstruct 
	public void init(){
		regionInfoMap=new HashMap<String,String>();
		List<RegionInfo> regionInfoList=regionInfoDAO.findRegionInfoList();
		if(regionInfoList!=null){
			for(RegionInfo regionInfo:regionInfoList){
				regionInfoMap.put(regionInfo.getRegionCode(), regionInfo.getRegionName());
			}
		}
	}

	public static Map<String, String> getRegionInfoMap() {
		return regionInfoMap;
	}

	
}
