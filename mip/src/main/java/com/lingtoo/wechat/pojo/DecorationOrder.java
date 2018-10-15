package com.lingtoo.wechat.pojo;

import java.util.Date;
import java.util.List;

public class DecorationOrder {
    private Integer decorationOrderId;

    private String orderNo;

    private Integer userId;

    private Integer decorationItemId;

    private Integer emergencyStatus;//紧急状态（0：非紧急；1：紧急）

    private Date bookTime;

    private String picUrl;//预约时间

    private String remark;

    private Integer decorationWorkerId;

    private Double visitPrice;//下单费用

    private Double workPrice;//二次支付费用

    private Double allPrice;//总支付费用

    private Double evaluatePrice;//订单评估价

    private Double dispatchPrice;//师傅派单价

    private Double addPrice;//师傅另行加价

    private Double workerPrice;//师傅抽成(派单价+另行加价)×用户评价的比例

    private Double spreadPrice;//推广人抽成(评估价*比例如5%)

    private Double remainPrice;//平台所得(总支付费用-师傅抽成-推广人抽成)
    
    private Double finishPrice;//评价所得

    private String visitPayOrderNo;

    private String workPayOrderNo;

    private String receiveName;

    private String receiveProvince;

    private String receiveCity;

    private String receiveArea;

    private String receiveAddress;

    private String receiveMobile;

    private Date createTime;
    
    private String changeWorkerRemark;	//	更换师傅理由

    private Integer status;//订单状态(-2：下单支付出现问题，用户未支付上门费；-1：师傅未接单时，用户取消订单；0:下单费已缴，等待管理员审核  1:管理员审核通过，等待师傅接单
    // 2:师傅退单,等待其他师傅接单  3:师傅已经接单；4：师傅上门完成订单；5：用户二次支付完成；6：用户评分完成)

    public static final Integer STATE_USER_NO_PAY = -2;//下单支付出现问题，用户未支付上门费
    public static final Integer STATE_USER_CANCEL = -1;//师傅未接单时，用户取消订单
    public static final Integer STATE_WAIT_ADMIN = 0;//下单费已缴，等待管理员审核
    public static final Integer STATE_WAIT_WORKER = 1;//管理员审核通过，等待师傅接单
    public static final Integer STATE_WORKER_CANCEL = 2;//师傅退单,等待其他师傅接单
    public static final Integer STATE_WORKER_CONFIRM = 3;//师傅已经接单
    public static final Integer STATE_WORKER_COMPLETE = 4;//师傅上门完成订单
    public static final Integer STATE_USER_PAYED = 5;//用户二次支付完成
    public static final Integer STATE_USER_EVALUATE = 6;//用户评分完成
    
    

	
	private String subTitle;			//项目子类型标题
	
	private Integer type;				//项目类型
	
	private String workerName;			//师傅姓名
	
	private String workerPhone;			//师傅电话
	
	private String decorationItemShow;	//项目标题

    private Integer technologyEvaluate;	//技术评价1到5星，默认为4星

    private Integer serviceEvaluate;	//服务态度评价1到5星，默认为4星

    private Integer specificationEvaluate;	//流程规范评价1到5星，默认为4星
	
	private List<String> picUrlList;

    public Integer getDecorationOrderId() {
        return decorationOrderId;
    }

    public void setDecorationOrderId(Integer decorationOrderId) {
        this.decorationOrderId = decorationOrderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDecorationItemId() {
        return decorationItemId;
    }

    public void setDecorationItemId(Integer decorationItemId) {
        this.decorationItemId = decorationItemId;
    }

    public Integer getEmergencyStatus() {
        return emergencyStatus;
    }

    public void setEmergencyStatus(Integer emergencyStatus) {
        this.emergencyStatus = emergencyStatus;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDecorationWorkerId() {
        return decorationWorkerId;
    }

    public void setDecorationWorkerId(Integer decorationWorkerId) {
        this.decorationWorkerId = decorationWorkerId;
    }

    public Double getVisitPrice() {
		return visitPrice;
	}

	public void setVisitPrice(Double visitPrice) {
		this.visitPrice = visitPrice;
	}

	public Double getWorkPrice() {
		return workPrice;
	}

	public void setWorkPrice(Double workPrice) {
		this.workPrice = workPrice;
	}

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	public Double getEvaluatePrice() {
		return evaluatePrice;
	}

	public void setEvaluatePrice(Double evaluatePrice) {
		this.evaluatePrice = evaluatePrice;
	}

	public Double getDispatchPrice() {
		return dispatchPrice;
	}

	public void setDispatchPrice(Double dispatchPrice) {
		this.dispatchPrice = dispatchPrice;
	}

	public Double getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(Double addPrice) {
		this.addPrice = addPrice;
	}

	public Double getWorkerPrice() {
		return workerPrice;
	}

	public void setWorkerPrice(Double workerPrice) {
		this.workerPrice = workerPrice;
	}

	public Double getSpreadPrice() {
		return spreadPrice;
	}

	public void setSpreadPrice(Double spreadPrice) {
		this.spreadPrice = spreadPrice;
	}

	public Double getRemainPrice() {
		return remainPrice;
	}

	public void setRemainPrice(Double remainPrice) {
		this.remainPrice = remainPrice;
	}

	public String getVisitPayOrderNo() {
        return visitPayOrderNo;
    }

    public void setVisitPayOrderNo(String visitPayOrderNo) {
        this.visitPayOrderNo = visitPayOrderNo == null ? null : visitPayOrderNo.trim();
    }

    public String getWorkPayOrderNo() {
        return workPayOrderNo;
    }

    public void setWorkPayOrderNo(String workPayOrderNo) {
        this.workPayOrderNo = workPayOrderNo == null ? null : workPayOrderNo.trim();
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceiveProvince() {
        return receiveProvince;
    }

    public void setReceiveProvince(String receiveProvince) {
        this.receiveProvince = receiveProvince == null ? null : receiveProvince.trim();
    }

    public String getReceiveCity() {
        return receiveCity;
    }

    public void setReceiveCity(String receiveCity) {
        this.receiveCity = receiveCity == null ? null : receiveCity.trim();
    }

    public String getReceiveArea() {
        return receiveArea;
    }

    public void setReceiveArea(String receiveArea) {
        this.receiveArea = receiveArea == null ? null : receiveArea.trim();
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress == null ? null : receiveAddress.trim();
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile == null ? null : receiveMobile.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getDecorationItemShow() {
		return decorationItemShow;
	}

	public void setDecorationItemShow(String decorationItemShow) {
		this.decorationItemShow = decorationItemShow;
	}

	public List<String> getPicUrlList() {
		return picUrlList;
	}

	public void setPicUrlList(List<String> picUrlList) {
		this.picUrlList = picUrlList;
	}

    public String getWorkerPhone() {
		return workerPhone;
	}

	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}

	public String getChangeWorkerRemark() {
		return changeWorkerRemark;
	}

	public void setChangeWorkerRemark(String changeWorkerRemark) {
		this.changeWorkerRemark = changeWorkerRemark;
	}

	public Integer getTechnologyEvaluate() {
		return technologyEvaluate;
	}

	public void setTechnologyEvaluate(Integer technologyEvaluate) {
		this.technologyEvaluate = technologyEvaluate;
	}

	public Integer getServiceEvaluate() {
		return serviceEvaluate;
	}

	public void setServiceEvaluate(Integer serviceEvaluate) {
		this.serviceEvaluate = serviceEvaluate;
	}

	public Integer getSpecificationEvaluate() {
		return specificationEvaluate;
	}

	public void setSpecificationEvaluate(Integer specificationEvaluate) {
		this.specificationEvaluate = specificationEvaluate;
	}

	public Double getFinishPrice() {
		return finishPrice;
	}

	public void setFinishPrice(Double finishPrice) {
		this.finishPrice = finishPrice;
	}

	@Override
    public String toString() {
        return "DecorationOrder{" +
                "decorationOrderId=" + decorationOrderId +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", decorationItemId=" + decorationItemId +
                ", emergencyStatus=" + emergencyStatus +
                ", bookTime=" + bookTime +
                ", picUrl='" + picUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", decorationWorkerId=" + decorationWorkerId +
                ", visitPrice=" + visitPrice +
                ", workPrice=" + workPrice +
                ", allPrice=" + allPrice +
                ", evaluatePrice=" + evaluatePrice +
                ", dispatchPrice=" + dispatchPrice +
                ", addPrice=" + addPrice +
                ", workerPrice=" + workerPrice +
                ", spreadPrice=" + spreadPrice +
                ", remainPrice=" + remainPrice +
                ", visitPayOrderNo='" + visitPayOrderNo + '\'' +
                ", workPayOrderNo='" + workPayOrderNo + '\'' +
                ", technologyEvaluate=" + technologyEvaluate +
                ", serviceEvaluate=" + serviceEvaluate +
                ", specificationEvaluate=" + specificationEvaluate +
                ", receiveName='" + receiveName + '\'' +
                ", receiveProvince='" + receiveProvince + '\'' +
                ", receiveCity='" + receiveCity + '\'' +
                ", receiveArea='" + receiveArea + '\'' +
                ", receiveAddress='" + receiveAddress + '\'' +
                ", receiveMobile='" + receiveMobile + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}