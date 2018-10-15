package com.lingtoo.wechat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.dao.UserWithdrawDAO;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.UserMoneyBill;
import com.lingtoo.wechat.pojo.UserWithdraw;

@Service
public class UserWithdrawService {
	@Autowired
	private UserWithdrawDAO uWithdrawDao;
	@Autowired
	private UserBillService uBillService;
	@Autowired
	private UserDAO uDao;
	
	public User withdraw(User user,Double moneyWidthdraw){
		Double moneyFinal=user.getMoney()-moneyWidthdraw;
		
		UserWithdraw uWithdraw=new UserWithdraw();
		uWithdraw.setCreateTime(new Date());
		uWithdraw.setMoneyFinal(moneyFinal);
		uWithdraw.setMoneyWidthdraw(moneyWidthdraw);
		uWithdraw.setState(0);
		uWithdraw.setStatus(-1);
		uWithdraw.setUserId(user.getUserId());
		uWithdraw.setBankCardCode(user.getBankCardCode());
		uWithdraw.setBankCardName(user.getBankCardName());
		uWithdraw.setBankName(user.getBankName());
		uWithdraw.setBankPlace(user.getBankPlace());
		
		Double ran=10000*Math.random();
		String ranString=ran.toString().substring(0, ran.toString().indexOf("."));
		for(int i=0;i<5-ranString.length();i++){
			ranString="0"+ranString;
		}
		uWithdraw.setWithdrawNo("TX"+new Date().getTime()+ranString);
		uWithdraw=add(uWithdraw);
		
		UserMoneyBill uMoneyBill=new UserMoneyBill();
		uMoneyBill.setCreateTime(new Date());
		uMoneyBill.setMoneyFinal(moneyFinal);
		uMoneyBill.setMoneyFloat(-moneyWidthdraw);
		uMoneyBill.setState(0);
		uMoneyBill.setType(UserMoneyBill.TYPE_WITHDROW);
		uMoneyBill.setUserId(user.getUserId());
		uMoneyBill.setUserWithdrawId(uWithdraw.getUserWithdrawId());
		uBillService.addUMoneyBill(uMoneyBill);
		
		user.setMoney(moneyFinal);
		uDao.updateUser(user);
		
		return user;
	}
	
	public UserWithdraw add(UserWithdraw uWithdraw) {
		uWithdrawDao.insert(uWithdraw);
		return uWithdrawDao.selectLastUserWithdrawByUserId(uWithdraw.getUserId());
	}
	
	public void alert(UserWithdraw uWithdraw) {
		uWithdrawDao.update(uWithdraw);
	}
	
	public void audit(Integer uWithdrawId,Integer status) {
		UserWithdraw uWithdraw=getUWithdrawById(uWithdrawId);
		uWithdraw.setStatus(status);
		alert(uWithdraw);
	}
	
	public UserWithdraw getUWithdrawById(Integer uWithdrawId) {
		return uWithdrawDao.selectUserWithdrawById(uWithdrawId);
	}
	
	
	public PageBean getUWithdrawByPage(String name,Integer userId,Integer status,String uWithdrawNo, Integer pageNo,  Integer pageSize){
        PageBean pageBean=new PageBean();
        pageBean.setRowCount(uWithdrawDao.selectUserWithdrawCount(name, uWithdrawNo, userId, status));
        pageBean.setPageSize(pageSize==null?10:pageSize);
        pageBean.setPageNo(pageNo==null?1:pageNo);
        List<UserWithdraw> uWithdrawList=uWithdrawDao.selectUserWithdrawPage(name, uWithdrawNo, userId, status, pageBean.getFirstResult(), pageBean.getPageSize());
        for(UserWithdraw uWithdraw:uWithdrawList) {
        	uWithdraw.setUser(uDao.selectUserById(uWithdraw.getUserId()));
        }
        pageBean.setQueryList(uWithdrawList);
        return pageBean;
	}
}
