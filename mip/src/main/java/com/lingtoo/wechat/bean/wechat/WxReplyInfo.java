package com.lingtoo.wechat.bean.wechat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author kirby24
 * 回复信息类
 */
public class WxReplyInfo{

	private Integer rid;
	private Integer rcid;
	private String rinfo;
	private String rreplytype;
	private String rreplycontext;
	private String revent;
	private String reventkey;
	private String rreplysummary;
	private String rinfotype;
	private Timestamp createtime; // 创建时间
	private String reventstatus;
	private String rmatch_type;//匹配类型
	private String ismessage;//判断是不是微留言设置的信息
	private String relevance;
	private Integer uploadstatus;
	private String media_id;
	private Date media_id_time;

	
	public Date getMedia_id_time() {
		return media_id_time;
	}

	public void setMedia_id_time(Date media_id_time) {
		this.media_id_time = media_id_time;
		if(media_id_time==null){
			this.media_id_time=new Date();
		}
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getIsmessage() {
		return ismessage;
	}

	public void setIsmessage(String ismessage) {
		this.ismessage = ismessage;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}


	public Integer getRcid() {
		return rcid;
	}

	public void setRcid(Integer rcid) {
		this.rcid = rcid;
	}

	public String getRinfo() {
		return rinfo;
	}

	public void setRinfo(String rinfo) {
		this.rinfo = rinfo;
	}

	public String getRreplytype() {
		return rreplytype;
	}

	public void setRreplytype(String rreplytype) {
		this.rreplytype = rreplytype;
	}

	public String getRreplycontext() {
		return rreplycontext;
	}

	public void setRreplycontext(String rreplycontext) {
		this.rreplycontext = rreplycontext;
	}

	public String getRevent() {
		return revent;
	}

	public void setRevent(String revent) {
		this.revent = revent;
	}

	public String getReventkey() {
		return reventkey;
	}

	public void setReventkey(String reventkey) {
		this.reventkey = reventkey;
	}

	public String getRreplysummary() {
		return rreplysummary;
	}

	public void setRreplysummary(String rreplysummary) {
		this.rreplysummary = rreplysummary;
	}

	public String getRinfotype() {
		return rinfotype;
	}

	public void setRinfotype(String rinfotype) {
		this.rinfotype = rinfotype;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getReventstatus() {
		return reventstatus;
	}

	public void setReventstatus(String reventstatus) {
		this.reventstatus = reventstatus;
	}

	public String getRmatch_type() {
		return rmatch_type;
	}

	public void setRmatch_type(String rmatch_type) {
		this.rmatch_type = rmatch_type;
	}

	public String getRelevance() {
		return relevance;                            
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public Integer getUploadstatus() {
		return uploadstatus;
	}

	public void setUploadstatus(Integer uploadstatus) {
		this.uploadstatus = uploadstatus;
	}
	
}
