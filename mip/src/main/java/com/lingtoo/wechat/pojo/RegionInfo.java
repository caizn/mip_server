package com.lingtoo.wechat.pojo;

/**
 * 地区编码
 * @author Administrator
 *
 */
public class RegionInfo {

	private String regionCode;//	varchar	6			gbk	gbk_chinese_ci		-1
	private String regionName;//	varchar	30			gbk	gbk_chinese_ci	
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	

}
