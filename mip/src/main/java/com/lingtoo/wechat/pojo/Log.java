package com.lingtoo.wechat.pojo;

import java.util.Date;

/**
 * Created by shenzh on 2016/8/19.
 */
public class Log {
    private Integer logId;//日志ID

    private Integer merchantId;
    
    private Integer operateRole;//1管理员 2商家

    private Integer operateId;//operate_role=1时为管理员id，=2为商家id

    private Date createTime;//操作时间

    private Integer operateType;//操作类型：1添加 2删除 3更新 4审核

    private String remark;//描述
    
    private String managerName;

    public static final Integer ROLE_MANAGER = 1; //1管理员
    public static final Integer ROLE_MERCHANT = 2; //2商家
    public static final Integer OPERATE_ADD = 1;    //1添加
    public static final Integer OPERATE_DELETE = 2; //2删除
    public static final Integer OPERATE_UPDATE = 3; //3更新
    public static final Integer OPERATE_REVIEW = 4; //4审核

    public Integer getLogId() {
        return logId;
    }

    public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getOperateRole() {
        return operateRole;
    }

    public void setOperateRole(Integer operateRole) {
        this.operateRole = operateRole;
    }

    public Integer getOperateId() {
        return operateId;
    }

    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
}