package com.lingtoo.wechat.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.DecorationOrderDAO;
import com.lingtoo.wechat.dao.DecorationWorkerDAO;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.pojo.DecorationOrder;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.pojo.WechatUser;
import com.lingtoo.wechat.utils.SystemConfig;

@Service
public class DecorationOrderService {
	@Autowired
	private DecorationOrderDAO dOrderDao;
	@Autowired
	private DecorationWorkerDAO dWorkerDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private UserBillService userBillService;
    @Autowired
    private NoticeService noticeService;
    
    public String setPrice(Integer dOrderId,Double addPrice) {
    	DecorationOrder dOrder = getDOrderById(dOrderId);
    	dOrder.setAddPrice(addPrice);
		dOrder.setAllPrice(dOrder.getAddPrice()+dOrder.getEvaluatePrice());
		dOrder.setWorkPrice(dOrder.getAllPrice()-dOrder.getVisitPrice());
		alert(dOrder);
		return "success";
    }
	/**
	 * 
	 * @param dOrderId				//订单ID
	 * @param evaluatePrice			//订单评估价
	 * @param dispatchPrice			//师傅派单价
	 * @param evaluate				//评分
	 * @param addPrice				//师傅加价
	 * @param workerId				//师傅ID
	 * @param changeWorkerRemark	//更换师傅理由
	 * @param status				//订单状态
	 * @return
	 */
	public String setStatus(
			Integer dOrderId, Double evaluatePrice, Double dispatchPrice,String evaluate,Double addPrice,Integer workerId,String changeWorkerRemark,String type, Integer status) {
		DecorationOrder dOrder = getDOrderById(dOrderId);
		boolean sign=true;
		if(status==-3) {
    		/*换师傅处理，消息模板通知管理员*/
    		if(dOrder.getStatus().equals(3)){
				//List<WechatUser> wechatUserList=userDao.selectManagerUsers();
				dOrder.setChangeWorkerRemark(changeWorkerRemark);
				alert(dOrder);
				String noticeUrl=SystemConfig.getContextPath()+"/mobile/manager/order-detail-show?dOrderId="+dOrder.getDecorationOrderId();
				noticeService.noticeUserApplyChangeWorker(noticeUrl, dOrder, changeWorkerRemark);
    		}else {
    			sign=false;
    		}
    	}else if(status==-1){
			/*用户取消订单*/
    		if(!dOrder.getStatus().equals(-1)){
        		if(type.equals("mobile")&&(dOrder.getStatus().equals(3)||dOrder.getStatus().equals(4)||dOrder.getStatus().equals(5)||dOrder.getStatus().equals(6))) {
        			sign=false;
        		}else {
        			if(!dOrder.getStatus().equals(-2)){
        				/*上门费退款处理*/
        				wxPayService.refund(dOrder.getVisitPayOrderNo(), 2);
        			}
        			if(dOrder.getStatus().equals(5)||dOrder.getStatus().equals(6)) {
        				/*二次支付处理*/
        				wxPayService.refund(dOrder.getWorkPayOrderNo(), 2);
        			}
        			if(dOrder.getStatus().equals(6)) {
        				/*订单取消时，分款回退处理*/
        				userBillService.cancelOrder(dOrder);
        			}
        			dOrder.setStatus(status);
        			alert(dOrder);
        		}
    		}else{
    			sign=false;
    		}
    	}else if (status == 1) {
			/*管理员报价*/
			if (dOrder.getStatus() == 0) {
				dOrder.setEvaluatePrice(evaluatePrice);
				dOrder.setDispatchPrice(dispatchPrice);
				dOrder.setStatus(status);
    			alert(dOrder);
				/* 报价成功时，给相应地区的工人发送消息模板 */
				String noticeUrl = SystemConfig.getContextPath()+"/mobile/worker/send-order-detail?dOrderId="
						+ dOrder.getDecorationOrderId();
    			if(workerId==null) {
    				List<DecorationWorker> dWorkerList = dWorkerDao.selectDWorkersByArea(dOrder.getReceiveProvince(),
    						dOrder.getReceiveCity(), null,dOrder.getDecorationItemId().toString());
    				for (DecorationWorker dWorker : dWorkerList) {
    					WechatUser wUser=userDao.selectWechatUserByUserId(dWorker.getUserId());
    					noticeService.noticeWorkerNewOrder(wUser.getOpenid(), noticeUrl, dOrder);
    				}
    			}else {
    				DecorationWorker dWorker=dWorkerDao.selectDWorkerById(workerId);
					WechatUser wUser=userDao.selectWechatUserByUserId(dWorker.getUserId());
					noticeService.noticeWorkerNewOrder(wUser.getOpenid(), noticeUrl, dOrder);
    			}
			} else {
    			sign=false;
			}
		}else if(status==2){
			/*师傅退单操作*/
    		if(dOrder.getStatus().equals(3)){
    			DecorationWorker oldWorker=dWorkerDao.selectDWorkerById(dOrder.getDecorationWorkerId());
				//WechatUser oldWorkerWechatUser=userDao.selectWechatUserByUserId(oldWorker.getUserId());
    			dOrder.setStatus(status);
        		dOrder.setDecorationWorkerId(null);
    			alert(dOrder);
    			
				/*String noticeUrl = SystemConfig.getContextPath()+"/mobile/worker/order-detail?dOrderId="
						+ dOrder.getDecorationOrderId();*/
//    			noticeService.noticeWorkerExitOrder(oldWorkerWechatUser.getOpenid(), "", dOrder);
    			
				/*师傅退单成功，给管理员发送消息模板*/
				List<WechatUser> wechatUserList=userDao.selectManagerUsers();
		        String noticeUrl=SystemConfig.getContextPath()+"/mobile/manager/order-detail-show?dOrderId="+dOrder.getDecorationOrderId();
				for(WechatUser wechatUser:wechatUserList) {
			        noticeService.noticeWorkerExitOrder(wechatUser.getOpenid(), noticeUrl, dOrder, oldWorker.getName());
                }

				/* 师傅退单成功，给相应地区的工人（除了旧的工人外）重新发送消息模板 */
				noticeUrl = SystemConfig.getContextPath()+"/mobile/worker/send-order-detail?dOrderId="
						+ dOrder.getDecorationOrderId();
				List<DecorationWorker> dWorkerList = dWorkerDao.selectDWorkersByArea(dOrder.getReceiveProvince(),
						dOrder.getReceiveCity(), dOrder.getReceiveArea(),dOrder.getDecorationItemId().toString());
				for (DecorationWorker dWorker : dWorkerList) {
					if(!dWorker.getDecorationWorkerId().equals(oldWorker.getDecorationWorkerId())) {
						WechatUser wUser=userDao.selectWechatUserByUserId(dWorker.getUserId());
						noticeService.noticeWorkerNewOrder(wUser.getOpenid(), noticeUrl, dOrder);
					}
				}
				
    		}else{
    			sign=false;
    		}
    	}else if(status==3){
    		/*师傅抢单操作*/
        	if(dOrder.getStatus()==1||dOrder.getStatus()==2) {
        		dOrder.setStatus(3);
        		dOrder.setDecorationWorkerId(workerId);
    			alert(dOrder);
				/*师傅接单成功，给师傅发送消息模板*/
    			DecorationWorker worker=dWorkerDao.selectDWorkerById(workerId);
				WechatUser wUser=userDao.selectWechatUserByUserId(worker.getUserId());
				String noticeUrl = SystemConfig.getContextPath()+"/mobile/worker/order-detail?dOrderId="
						+ dOrder.getDecorationOrderId();
				noticeService.noticeWorkerGetOrder(wUser.getOpenid(), noticeUrl, dOrder);
				
				/*师傅接单成功，给管理员发送消息模板*/
		        noticeUrl=SystemConfig.getContextPath()+"/mobile/manager/order-detail-show?dOrderId="+dOrder.getDecorationOrderId();
                noticeService.noticeAdminTheWorkerGetOrder(noticeUrl, dOrder);
        	}else {
    			sign=false;
        	}
    	}else  if(status==4){
    		/*师傅上门并加价操作*/
    		if(dOrder.getStatus().equals(3)){
    			dOrder.setStatus(status);
    			if(dOrder.getAddPrice()==null) {
            		dOrder.setAddPrice(0.0);
            		dOrder.setAllPrice(dOrder.getAddPrice()+dOrder.getEvaluatePrice());
            		dOrder.setWorkPrice(dOrder.getAllPrice()-dOrder.getVisitPrice());
    			}
    			alert(dOrder);
		        String noticeUrl=SystemConfig.getContextPath()+"/mobile/manager/order-detail-show?dOrderId="+dOrder.getDecorationOrderId();
				noticeService.noticeWorkerAddPrice( noticeUrl, dOrder);

				/*师傅上门完成工程加价操作，通知用户付费信息*/
				noticeUrl=SystemConfig.getContextPath()+"/mobile/user/order-detail?dOrderId="+dOrder.getDecorationOrderId();
				WechatUser wUser=userDao.selectWechatUserByUserId(dOrder.getUserId());
				noticeService.noticeUserOrderFinish(wUser.getOpenid(), noticeUrl, dOrder);
    		}else{
    			sign=false;
    		}
    	}else if(status==6){
			/*用户评分操作*/
    		if(dOrder.getStatus().equals(5)){
    			if(evaluate!=null){
    				JSONObject evaluateObj=new JSONObject(evaluate);
        			dOrder.setStatus(status);
        			dOrder.setTechnologyEvaluate(evaluateObj.getInt("technologyEvaluate"));
        			dOrder.setServiceEvaluate(evaluateObj.getInt("serviceEvaluate"));
        			dOrder.setSpecificationEvaluate(evaluateObj.getInt("specificationEvaluate"));
        			alert(dOrder);
    				/*订单平评价时，分款处理*/
        			userBillService.dealOrder(dOrder);
    			}
    		}else{
    			sign=false;
    		}
    	}
		if(sign) {
			return "success";
		}else {
			return dOrder.getStatus().toString();
		}
	}

	public void add(DecorationOrder dOrder) {
		dOrderDao.insert(dOrder);
	}

	public void alert(DecorationOrder dOrder) {
		dOrderDao.updateByPrimaryKey(dOrder);
	}

	public DecorationOrder getDOrderById(Integer dOrderId) {
		return dOrderDao.selectDOrderById(dOrderId);
	}

	public PageBean getDOrderPage(String orderNo, String telephone, String name, Integer emergencyStatus, Integer type,
			Integer status, Integer pageNo, Integer pageSize) {
		PageBean pageBean = new PageBean();
		pageBean.setRowCount(dOrderDao.selectDOrdersCount(orderNo, telephone, name, emergencyStatus, type, status));
		pageBean.setPageSize(pageSize == null ? 10 : pageSize);
		pageBean.setPageNo(pageNo == null ? 1 : pageNo);
		List<DecorationOrder> dOrderList = dOrderDao.selectDOrders(orderNo, telephone, name, emergencyStatus, type,
				status, pageBean.getFirstResult(), pageBean.getPageSize());
		pageBean.setQueryList(dOrderList);
		return pageBean;
	}

	public List<DecorationOrder> getDOrderList(Integer userType, Integer userId, Integer status, Integer page) {
		List<DecorationOrder> dOrderList = new ArrayList<DecorationOrder>();
		dOrderList = dOrderDao.selectDOrdersByUser(userType, userId, status, (page - 1) * 10, 10);
		return dOrderList;
	}
}
