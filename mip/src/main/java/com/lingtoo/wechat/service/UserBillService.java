package com.lingtoo.wechat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.dao.UserBillDAO;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.pojo.DecorationOrder;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.pojo.Merchant;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.UserIntegralBill;
import com.lingtoo.wechat.pojo.UserMoneyBill;

@Service
public class UserBillService {
	@Autowired
	private UserBillDAO userBillDao;
	@Autowired
	private DecorationWorkerService dWorkerService;
	@Autowired
	private DecorationOrderService dOrderService;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private MerchantService merchantService;
	
	public void cancelOrder(DecorationOrder dOrder) {
		DecorationWorker dWorker=dWorkerService.getDWorkerById(dOrder.getDecorationWorkerId());
		User dWorkerUser=userDao.selectUserById(dWorker.getUserId());
		User introducer=null;
		if(dWorkerUser.getIntroducerId()!=null&&dWorkerUser.getIntroducerId()!=0) {
			introducer=userDao.selectUserById(dWorkerUser.getIntroducerId());
		}
		
		Double workerPrice=dOrder.getWorkerPrice();
		Double spreadPrice=dOrder.getSpreadPrice();
		if(introducer!=null) {
			if(!spreadPrice.equals(0.0)) {
				Double moneyFinal=introducer.getMoney()-spreadPrice;
				UserMoneyBill introducerMoneyBill=new UserMoneyBill();
				introducerMoneyBill.setCreateTime(new Date());
				introducerMoneyBill.setMoneyFinal(moneyFinal);
				introducerMoneyBill.setMoneyFloat(-spreadPrice);
				introducerMoneyBill.setState(0);
				introducerMoneyBill.setType(UserMoneyBill.TYPE_COMMISSION_CANCEL);
				introducerMoneyBill.setUserId(introducer.getUserId());
				introducerMoneyBill.setDecorationOrderId(dOrder.getDecorationOrderId());
				addUMoneyBill(introducerMoneyBill);
				
				introducer.setMoney(moneyFinal);
				userDao.updateUser(introducer);
			}
		}
		Double moneyFinal=dWorkerUser.getMoney()-workerPrice;
		UserMoneyBill workerMoneyBill=new UserMoneyBill();
		workerMoneyBill.setCreateTime(new Date());
		workerMoneyBill.setMoneyFinal(moneyFinal);
		workerMoneyBill.setMoneyFloat(-workerPrice);
		workerMoneyBill.setState(0);
		workerMoneyBill.setType(UserMoneyBill.TYPE_ORDER_CANCEL);
		workerMoneyBill.setUserId(dWorkerUser.getUserId());
		workerMoneyBill.setDecorationOrderId(dOrder.getDecorationOrderId());
		addUMoneyBill(workerMoneyBill);
		
		dWorkerUser.setMoney(moneyFinal);
		userDao.updateUser(dWorkerUser);
	}

	public void payOrder(DecorationOrder dOrder) {
		Merchant merchant=merchantService.getMerchantById(2);
		DecorationWorker dWorker=dWorkerService.getDWorkerById(dOrder.getDecorationWorkerId());
		User dWorkerUser=userDao.selectUserById(dWorker.getUserId());
		User introducer=null;
		if(dWorkerUser.getIntroducerId()!=null&&dWorkerUser.getIntroducerId()!=0) {
			introducer=userDao.selectUserById(dWorkerUser.getIntroducerId());
		}
		Double workerPrice=(dOrder.getDispatchPrice()+dOrder.getAddPrice())*90/100;
		
		Double spreadPrice=0.0;
		if(introducer!=null) {
			spreadPrice=(dOrder.getDispatchPrice()+dOrder.getAddPrice())*merchant.getIntroduceCommission()/100;
			//workerPrice=workerPrice-spreadPrice;
			
			Double moneyFinal=introducer.getMoney()+spreadPrice;
			UserMoneyBill introducerMoneyBill=new UserMoneyBill();
			introducerMoneyBill.setCreateTime(new Date());
			introducerMoneyBill.setMoneyFinal(moneyFinal);
			introducerMoneyBill.setMoneyFloat(spreadPrice);
			introducerMoneyBill.setState(0);
			introducerMoneyBill.setType(UserMoneyBill.TYPE_COMMISSION);
			introducerMoneyBill.setUserId(introducer.getUserId());
			introducerMoneyBill.setDecorationOrderId(dOrder.getDecorationOrderId());
			addUMoneyBill(introducerMoneyBill);
			
			introducer.setMoney(moneyFinal);
			userDao.updateUser(introducer);
		}
		dOrder.setWorkerPrice(workerPrice);
		dOrder.setSpreadPrice(spreadPrice);
		dOrder.setRemainPrice(dOrder.getAllPrice()-workerPrice-spreadPrice);
		
		dOrderService.alert(dOrder);
		Double moneyFinal=dWorkerUser.getMoney()+workerPrice;
		UserMoneyBill workerMoneyBill=new UserMoneyBill();
		workerMoneyBill.setCreateTime(new Date());
		workerMoneyBill.setMoneyFinal(moneyFinal);
		workerMoneyBill.setMoneyFloat(workerPrice);
		workerMoneyBill.setState(0);
		workerMoneyBill.setType(UserMoneyBill.TYPE_ORDER);
		workerMoneyBill.setUserId(dWorkerUser.getUserId());
		workerMoneyBill.setDecorationOrderId(dOrder.getDecorationOrderId());
		addUMoneyBill(workerMoneyBill);
		
		dWorkerUser.setMoney(moneyFinal);
		userDao.updateUser(dWorkerUser);
	}
	
	public void dealOrder(DecorationOrder dOrder) {
		Merchant merchant=merchantService.getMerchantById(2);
		DecorationWorker dWorker=dWorkerService.getDWorkerById(dOrder.getDecorationWorkerId());
		User dWorkerUser=userDao.selectUserById(dWorker.getUserId());
		User introducer=null;
		if(dWorkerUser.getIntroducerId()!=null&&dWorkerUser.getIntroducerId()!=0) {
			introducer=userDao.selectUserById(dWorkerUser.getIntroducerId());
		}
		float tEvaluate=dOrder.getTechnologyEvaluate();
		float seEvaluate=dOrder.getServiceEvaluate();
		float spEvaluate=dOrder.getSpecificationEvaluate();
		float evaluate=(tEvaluate/5+seEvaluate/5+spEvaluate/5)/3;
		Double finishPrice=(dOrder.getDispatchPrice()+dOrder.getAddPrice())*10/100*evaluate;

		dOrder.setRemainPrice(dOrder.getRemainPrice()-finishPrice);
		dOrder.setFinishPrice(finishPrice);
		dOrderService.alert(dOrder);
		
		Double moneyFinal=dWorkerUser.getMoney()+finishPrice;
		UserMoneyBill workerMoneyBill=new UserMoneyBill();
		workerMoneyBill.setCreateTime(new Date());
		workerMoneyBill.setMoneyFinal(moneyFinal);
		workerMoneyBill.setMoneyFloat(finishPrice);
		workerMoneyBill.setState(0);
		workerMoneyBill.setType(UserMoneyBill.TYPE_ORDER_EVALUATE);
		workerMoneyBill.setUserId(dWorkerUser.getUserId());
		workerMoneyBill.setDecorationOrderId(dOrder.getDecorationOrderId());
		addUMoneyBill(workerMoneyBill);
		
		dWorkerUser.setMoney(moneyFinal);
		userDao.updateUser(dWorkerUser);
	}
	
	public void addUMoneyBill(UserMoneyBill userMoneyBill) {
		userBillDao.insertUserMoneyBill(userMoneyBill);
	}
	
	public void addUIntegralBill(UserIntegralBill userIntegralBill) {
		userBillDao.insertUserIntegralBill(userIntegralBill);
	}
}
