package com.lingtoo.wechat.bean;

public class ContestBean {

	
	private Integer contestId;//主键
	private String contestName;//赛事名称  *	限制30个汉字
	private String sponsor;//主办方   文本输入
	private String province;//省份
	private String city;//赛事地区	省份、城市
	private String contestAddress;//赛事场地	文本输入
	private String contestStartDate;//赛事时间	点选时间控件 (开始及结束时间)
	private String contestEndDate;
	private String applyStartDate;//报名时间	点选时间控件 (开始及结束时间)
	private String applyEndDate;
	private Integer type;//赛程赛制	1:杯赛 2:联赛 
	private Integer playersFormat;//赛事赛制	5人制、7人制、9人制、11人制（单选、非必填）
	private Integer contestScale;//	赛事规模	几支球队（非必填）
	private String contestFee;//赛事费用	以分为单位 文本输入（指报名费：100元）
	private String contestAward;//	赛事奖励	文本输入
	private String linkMan;//	负责人	文本输入
	private String linkPhone;//	联系电话	数字位
	private String contestRemark;//详细章程	填写相关比赛规则、参与方式（参赛要求）、联系方式等信息，限制2000个字
	public Integer getContestId() {
		return contestId;
	}
	public void setContestId(Integer contestId) {
		this.contestId = contestId;
	}
	public String getContestName() {
		return contestName;
	}
	public void setContestName(String contestName) {
		this.contestName = contestName;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContestAddress() {
		return contestAddress;
	}
	public void setContestAddress(String contestAddress) {
		this.contestAddress = contestAddress;
	}
	public String getContestStartDate() {
		return contestStartDate;
	}
	public void setContestStartDate(String contestStartDate) {
		this.contestStartDate = contestStartDate;
	}
	public String getContestEndDate() {
		return contestEndDate;
	}
	public void setContestEndDate(String contestEndDate) {
		this.contestEndDate = contestEndDate;
	}
	public String getApplyStartDate() {
		return applyStartDate;
	}
	public void setApplyStartDate(String applyStartDate) {
		this.applyStartDate = applyStartDate;
	}
	public String getApplyEndDate() {
		return applyEndDate;
	}
	public void setApplyEndDate(String applyEndDate) {
		this.applyEndDate = applyEndDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPlayersFormat() {
		return playersFormat;
	}
	public void setPlayersFormat(Integer playersFormat) {
		this.playersFormat = playersFormat;
	}
	public Integer getContestScale() {
		return contestScale;
	}
	public void setContestScale(Integer contestScale) {
		this.contestScale = contestScale;
	}
	public String getContestFee() {
		return contestFee;
	}
	public void setContestFee(String contestFee) {
		this.contestFee = contestFee;
	}
	public String getContestAward() {
		return contestAward;
	}
	public void setContestAward(String contestAward) {
		this.contestAward = contestAward;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getContestRemark() {
		return contestRemark;
	}
	public void setContestRemark(String contestRemark) {
		this.contestRemark = contestRemark;
	}

	
}
