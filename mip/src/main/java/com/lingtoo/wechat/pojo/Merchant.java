package com.lingtoo.wechat.pojo;

import java.util.Date;

import com.lingtoo.wechat.service.RegionInfoService;
import com.lingtoo.wechat.utils.DateUtil;
import com.lingtoo.wechat.utils.StringUtil;

public class Merchant {

	private Integer merchantId;//int	11		主键ID				-1
	private String name;//	varchar	64		组织机构	utf8	utf8_general_ci	
	private String provinceCode;//	varchar	6		组织机构	utf8	utf8_general_ci	
	private String cityCode;//	varchar	6		所在城市	utf8	utf8_general_ci	
	private String areaCode;//	varchar	6		所在区县	utf8	utf8_general_ci	
	private String realName;//	varchar	20		手机	utf8	utf8_general_ci	
	private Integer roleId;//	int	11		角色ID（理由）			
	private String logoPath;//	varchar	128	-1		商家LOGO	utf8	utf8_general_ci	
	private Integer state;//	int	1		状态  0 开启  1  禁用			
	private Date createDate;//	datetime		创建时间			
	private String creator;//	varchar	32		创建人	utf8	utf8_general_ci	
	private Date updateTime;//datetime	-1		修改时间			
	private String address;//地址信息
	private Integer type;//商家类型：-1媒体；0校园商家；1区级赛事主办方；2市级赛事主办方；3省级赛事主办方
	
	///merchant_app表中的参数
	private String serviceBeginDate;//	varchar	10		服务开始时间 格式 yyyy-MM-dd	utf8	utf8_general_ci	
	private String serviceEndDate;//	varchar	10		服务结束时间 格式 yyyy-MM-dd	utf8	utf8_general_ci	
	private String appName;//	varchar	32		微信公众号名称	utf8	utf8_general_ci	
	private String appAccount;//	varchar	32		微信公众号名称	utf8	utf8_general_ci	
	private String appId;//	int	11		主键ID		
	private String originalId;//	varchar	32		原始ID	utf8	utf8_general_ci	
	private String appDetail;//	varchar	128	-1		微信公众号描述	utf8	utf8_general_ci	
	private Integer appLevel;//	int	1		级别 1：普通订阅 2：普通服务 3：认证订阅号 4：证服务号/认证媒体/政府订阅号		
	private String appSecret;//	varchar	64		微信appSecret	utf8	utf8_general_ci	
	
	//manager表中参数
	private String phone;
	private String email;
	private String account;//管理员账号
	
	///新增的时候传入的参数
	private String passWord;
	private String repassWord;
	private Integer platform;
	
	private String roleName;//角色名称
	private int managers;//成员数
	private int users;//会员数


    private int players;//球员数
	private int teams;//球队数
	private int games;//对内比赛数
	private int matchs;//球队约战数
	private int contests;//赛事数
    private int lives;//活动数
	private String currUsers;//当前用户数
    private Integer managerId;//管理员ID
	
    
    public static final String STATE_SERVICEING = "1";//服务中
    public static final String STATE_SERVIC_OVER = "2";//过期
    public static final String STATE_SERVIC_NOT = "3";//未判定
	
	
	public final static Integer STATE_ENABLED=0;//开启 
	public final static Integer STATE_DISABLED=1;//禁用	
	
	public Double visitFee;
	
	public Double introduceFee;
	
	public Integer introduceCommission;
	
	public String getProvinceName(){
	
		return RegionInfoService.getRegionInfoMap().get(provinceCode);
	}
	
	public String getCityName(){
		
		return RegionInfoService.getRegionInfoMap().get(cityCode);
	}
	

	public String getAreaName(){
		
		return RegionInfoService.getRegionInfoMap().get(areaCode);
	}
	
	public String getStateName(){
		if(STATE_ENABLED.equals(state))return "开启";
		return "<font color='red'>禁用</font>";
	}
	
	public String getStatus() {
		String status=STATE_SERVIC_NOT;
		if(!StringUtil.isEmpty(serviceBeginDate)&&!StringUtil.isEmpty(serviceEndDate)){
			Long serviceBeginDateL=Long.valueOf(serviceBeginDate.replaceAll("-", ""));
			Long serviceEndDateL=Long.valueOf(serviceEndDate.replaceAll("-", ""));
			Long today=Long.valueOf(DateUtil.getToday("yyyyMMdd"));
			
			if(serviceBeginDateL<=today&&serviceEndDateL>=today)status=STATE_SERVICEING;
			else status=STATE_SERVIC_OVER;
		}

	
		return status;
	}

	public String getStatusName() {
		String statusName="";
		if(STATE_SERVICEING.equals(getStatus().toString()))statusName= " <p class=\"p-going\">服务中("+getStateName()+")</p>";
		else if(STATE_SERVIC_OVER.equals(getStatus().toString()))statusName= "<p class=\"p-over\">不在有效期</p>";
		else if(STATE_SERVIC_NOT.equals(getStatus().toString()))statusName= "<p class=\"p-noOpen\">未绑定</p>";
		
		return statusName;
	}
	    
	
	
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public String getRepassWord() {
		return repassWord;
	}


	public void setRepassWord(String repassWord) {
		this.repassWord = repassWord;
	}


	public Integer getPlatform() {
		return platform;
	}


	public void setPlatform(Integer platform) {
		this.platform = platform;
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


	public String getAppName() {
		return appName;
	}


	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
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

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppAccount() {
		return appAccount;
	}

	public void setAppAccount(String appAccount) {
		this.appAccount = appAccount;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getManagers() {
		return managers;
	}

	public void setManagers(int managers) {
		this.managers = managers;
	}

	public int getUsers() {
		return users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	public int getTeams() {
		return teams;
	}

	public void setTeams(int teams) {
		this.teams = teams;
	}

	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public int getMatchs() {
		return matchs;
	}

	public void setMatchs(int matchs) {
		this.matchs = matchs;
	}

	public int getContests() {
		return contests;
	}

	public void setContests(int contests) {
		this.contests = contests;
	}

	public String getCurrUsers() {
		return currUsers;
	}

	public void setCurrUsers(String currUsers) {
		this.currUsers = currUsers;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

	public Double getVisitFee() {
		return visitFee;
	}

	public void setVisitFee(Double visitFee) {
		this.visitFee = visitFee;
	}

	public Double getIntroduceFee() {
		return introduceFee;
	}

	public void setIntroduceFee(Double introduceFee) {
		this.introduceFee = introduceFee;
	}

	public Integer getIntroduceCommission() {
		return introduceCommission;
	}

	public void setIntroduceCommission(Integer introduceCommission) {
		this.introduceCommission = introduceCommission;
	}
}
