package com.lingtoo.wechat.pojo;

import java.util.Date;

/**
 * 商家公众号信息
 * @author Administrator
 *
 */
public class MerchantApp {

	private String appId;//	int	11		主键ID				-1
	private Integer merchantId;//	int	11		商家ID			
	private String serviceBeginDate;//	varchar	10		服务开始时间 格式 yyyy-MM-dd	utf8	utf8_general_ci	
	private String serviceEndDate;//	varchar	10		服务结束时间 格式 yyyy-MM-dd	utf8	utf8_general_ci	
	private String originalId;//	varchar	32		原始ID	utf8	utf8_general_ci	
	private String appName;//	varchar	32		微信公众号名称	utf8	utf8_general_ci	
	private String appAccount;//	varchar	32		微信公众号名称	utf8	utf8_general_ci	
	private String appDetail;//	varchar	128	-1		微信公众号描述	utf8	utf8_general_ci	
	private Integer appLevel;//	int	1		级别 1：普通订阅 2：普通服务 3：认证订阅号 4：证服务号/认证媒体/政府订阅号		
	private String appSecret;//	varchar	64		微信appSecret	utf8	utf8_general_ci	
	private String qrCodePath;//	varchar	128	-1		二维码	utf8	utf8_general_ci	
	private Date createDate;//	datetime		创建时间			
	private String creator;//	varchar	32		创建人	utf8	utf8_general_ci	
	private Date updateTime;//	datetime	-1		修改时间		
	
	public final static Integer LEVEL_PTDY=1;//普通订阅 
	public final static Integer LEVEL_PYFF=2;//普通服务
	public final static Integer LEVEL_RZDYH=3;//认证订阅号
	public final static Integer LEVEL_RZFFH=4;//证服务号/认证媒体/政府订阅号	
	
	
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getServiceBeginDate() {
		return serviceBeginDate;
	}
	public void setServiceBeginDate(String serviceBeginDate) {
		this.serviceBeginDate = serviceBeginDate;
	}
	public String getServiceEndDate() {
		return serviceEndDate;
	}
	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}
	public String getOriginalId() {
		return originalId;
	}
	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getQrCodePath() {
		return qrCodePath;
	}
	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDetail() {
		return appDetail;
	}
	public void setAppDetail(String appDetail) {
		this.appDetail = appDetail;
	}
	public Integer getAppLevel() {
		return appLevel;
	}
	public void setAppLevel(Integer appLevel) {
		this.appLevel = appLevel;
	}
	public String getAppAccount() {
		return appAccount;
	}
	public void setAppAccount(String appAccount) {
		this.appAccount = appAccount;
	}
	
	

}
