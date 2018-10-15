package com.lingtoo.wechat.pojo;

import java.util.Date;

/**
 * 后台管理员类
 * @author wj.chen
 *
 */
public class Manager {

	private Integer managerId;//管理员ID
	private String account;//管理员账号
	private String realName;//真实名字
	private String password;//密码
	private String phone;//手机号码
	private String email;//邮箱
    private boolean locked;//是否锁定 1锁定 0未锁定
    private Date lastLoginTime;//最后登录时间
    private Integer loginCount;//登录次数
	private Date createTime;
	private Integer roleId;
	private Integer deleteFlag=0;
	private Integer state;//状态 0 审核中 1 同意  
	private Integer merchantId;
	private Integer platform;//系统管理平台 =可以查询所有APPID的数据   商家管理平台
	private Integer subadminFlag;//是否超级管理员  1是 0否
	
	private String logoPath;
	private String level;//赛事主办方级别 area区级 city市级 province省级
	
	private Integer type;//商户类型 0校园1赛事主办方
	
	public final static Integer STATE_CHECKING=0;//审核中
	public final static Integer STATE_YES=1;//同意
	
	
	public final static Integer SUBADMIN_FLAG_YES=1;//是超级管理员
	public final static Integer SUBADMIN_FLAG_NO=0;//不是超级管理员
	
	
    public static final Integer PLATFORM_SYS=1;//系统管理平台 
    public static final Integer PLATFORM_CORP=2;//商家管理平台
    
    
    private String appId="0";
	
	private String roleName;
	public String getStatusName(){
		if(locked)return "禁止登陆";
		return "允许登录";
	}
	
    public String getStateName(){
    	if(STATE_CHECKING.equals(state))return "<font color='orange'>审核中</font>";
    	if(STATE_YES.equals(state))return "审核通过";
    	return "";
    }
    public String getPlatformName(){
    	if(PLATFORM_SYS.equals(platform))return "<font color='orange'>系统管理平台 </font>";
    	if(PLATFORM_CORP.equals(platform))return "商家管理平台";
    	return "";
    }
	
	
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public static Integer getStateChecking() {
		return STATE_CHECKING;
	}

	public static Integer getStateYes() {
		return STATE_YES;
	}

	public static Integer getSubadminFlagYes() {
		return SUBADMIN_FLAG_YES;
	}

	public static Integer getSubadminFlagNo() {
		return SUBADMIN_FLAG_NO;
	}

	public static Integer getPlatformSys() {
		return PLATFORM_SYS;
	}

	public static Integer getPlatformCorp() {
		return PLATFORM_CORP;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getSubadminFlag() {
		return subadminFlag;
	}

	public void setSubadminFlag(Integer subadminFlag) {
		this.subadminFlag = subadminFlag;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

}
