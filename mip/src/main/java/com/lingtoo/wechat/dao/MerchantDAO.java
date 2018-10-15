package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.Merchant;
import com.lingtoo.wechat.pojo.MerchantApp;
import com.lingtoo.wechat.pojo.MerchantSms;
import com.lingtoo.wechat.pojo.MerchantTemplate;
@MyBatisRepository
public interface MerchantDAO {
	//通过商家ID获取商家信息
	Merchant getMerchantById(@Param("merchantId") Integer merchantId);
	//通过APPID获取公众号信息
	MerchantApp getMerchantAppById(@Param("appId") String appId);
	//通过APPID获取公众号信息
	MerchantApp getMerchantAppByMerchantId(@Param("merchantId") Integer merchantId);
    //所有商家公众号信息
    List<MerchantApp> findMerchantApp();
	
	//通过APPID获取商家推送模板信息
	//MerchantTemplate getMerchantTemplateByAppId(@Param("appId") String appId);
	//通过APPID获取商家短信配置信息
	MerchantSms getMerchantSmsByAppId(@Param("appId") String appId);

	void deleteMerchant(@Param("merchantId") Integer merchantId);
	
	void deleteManagerByMerchartId(@Param("merchantId") Integer merchantId);
	
	void addMerchant(Merchant merchant);
	//void addMerchantTemplate(MerchantTemplate merchantTemplate);
	void addMerchantSms(MerchantSms merchantSms);
	void addMerchantApp(MerchantApp merchantApp);
	
	void updateMerchant(Merchant merchant);
	//void updateMerchantTemplate(MerchantTemplate merchantTemplate);
	void updateMerchantSms(MerchantSms merchantSms);
	void updateMerchantApp(MerchantApp merchantApp);
	
	//修改商家管理员角色
	void updateManagerRoleId(@Param("merchantId") Integer merchantId,@Param("roleId") Integer roleId);
	
	int getMerchantsByRoleId(@Param("roleId") Integer roleId);
	
	int findMerchantCount(@Param("merchantName") String merchantName,
			@Param("provinceCode") String provinceCode,
			@Param("cityCode") String cityCode,
			@Param("areaCode") String areaCode,
			@Param("status") String status,
			@Param("state") Integer state);
	int findMerchantCountBySchool(@Param("merchantName") String merchantName,
			@Param("provinceCode") String provinceCode,
			@Param("cityCode") String cityCode,
			@Param("areaCode") String areaCode);
	List<Merchant> findMerchantList(@Param("merchantName") String merchantName,
			@Param("provinceCode") String provinceCode,
			@Param("cityCode") String cityCode,
			@Param("areaCode") String areaCode,
			@Param("status") String status,
			@Param("state") Integer state,
			@Param("pageStart") Integer pageStart,
			@Param("maxResult") Integer maxResult);
	List<Merchant> findMerchantListBySchool(@Param("merchantName") String merchantName,
			@Param("provinceCode") String provinceCode,
			@Param("cityCode") String cityCode,
			@Param("areaCode") String areaCode,
			@Param("pageStart") Integer pageStart,
			@Param("maxResult") Integer maxResult);
	List<Integer> findMerchantIdList(@Param("merchantName") String merchantName,
			@Param("provinceCode") String provinceCode,
			@Param("cityCode") String cityCode,
			@Param("areaCode") String areaCode,
			@Param("status") String status,
			@Param("state") Integer state,
			@Param("pageStart") Integer pageStart,
			@Param("maxResult") Integer maxResult);
	
	int findMerchantTongJiCount();
	List<Merchant> findMerchantTongJiList(@Param("pageStart") int pageStart, @Param("maxResult") int maxResult);

	
	List<Merchant> findMerchantByRoleId(@Param("roleId") Integer roleId);

    //列出区/市/省一级的教育局主办方下面的所有学校
    int findMerchantCountByAreaCode(@Param("type") Integer type, @Param("areaCode") String areaCode);
    List<Merchant> findMerchantByAreaCode(@Param("type") Integer type, @Param("areaCode") String areaCode,@Param("pageStart") int pageStart, @Param("maxResult") int maxResult);

	//通过APPID获取商家推送模板信息
	MerchantTemplate getMerchantTemplateByAppId(@Param("appId") String appId);
}
