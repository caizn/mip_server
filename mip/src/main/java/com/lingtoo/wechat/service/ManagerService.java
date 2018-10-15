package com.lingtoo.wechat.service;


import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.bean.ResponseInfo;
import com.lingtoo.wechat.dao.LoginLogDAO;
import com.lingtoo.wechat.dao.ManagerDAO;
import com.lingtoo.wechat.pojo.LoginLog;
import com.lingtoo.wechat.pojo.Manager;
import com.lingtoo.wechat.utils.MD5;

@Service
public class ManagerService {
    @Autowired
    private ManagerDAO managerDAO;
    @Autowired
    private LoginLogDAO loginLogDAO;
	/**
	 * 通过账号获取管理员信息
	 * @param account
	 * @return
	 */
	public Manager getManagerByAccount(String account){
		return managerDAO.getManagerByAccount(account);
	}
	
	public Manager getManagerByMerchantId(Integer merchantId){
		return managerDAO.getManagerByMerchantId(merchantId);
	}
	
    public PageBean findManagerPage(Integer merchantId,String account,String locked,String state,Integer platform,Integer pageNo, Integer pageSize){
    	PageBean pageBean=new PageBean();
		pageBean.setRowCount(managerDAO.findManagerCount(merchantId,account,locked,state));
		pageBean.setPageSize(pageSize==null?10:pageSize);
		pageBean.setPageNo(pageNo==null?1:pageNo);
		pageBean.setQueryList(managerDAO.findManagerList(merchantId,account,locked,state,pageBean.getFirstResult(), pageBean.getPageSize()));
    	return pageBean;
    }
    /**
     * 管理员登录 
     * @param _account
     * @param _pwd
     * @param ip
     * @return ResponseInfo 其中 respCode为0 表示登录成功，-1错误  （成功后manager的信息在result属性里取）
     */
    @Transactional
    public ResponseInfo managerLogin(String _account,String _pwd,String ip){
    	  ResponseInfo responseInfo=new ResponseInfo();
    	  responseInfo.setRespCode("-1");
    	  String msg="";
		  if(StringUtils.isEmpty(_account)){
			 msg="请输入账号!";
		   }else if(StringUtils.isEmpty(_pwd)){
			 msg="请输入密码!";
		   }else{
			   _account=_account.trim();
			   _pwd=_pwd.trim();
			   Manager manager=this.getManagerByAccount(_account.toLowerCase().trim());
			   MD5 md5 = new MD5(_pwd);	 
			   LoginLog loginLog=new LoginLog();
			   loginLog.setLogIp(ip);
			   loginLog.setAccount(_account);
			   loginLog.setRealName(manager==null?"":manager.getRealName());
			   loginLog.setLogTime(new Date());
			   loginLog.setAccountType(LoginLog.ACCOUNT_TYPE_MERCHANT);
			   loginLog.setMerchantId(manager==null?null:manager.getMerchantId());
			   if(manager==null){
				  msg="账号不存在！";
				  loginLog.setResult(LoginLog.REUSLT_NOT_ACCOUNT);
			   }else if(md5.compute().equalsIgnoreCase(manager.getPassword())){
				   if(manager.getState().equals(Manager.STATE_CHECKING)){
					   msg="您的账号还未审核，请联系管理员进行审核，即可使用！";
					   loginLog.setResult(LoginLog.REUSLT_STATE_NOTCHECK);
				   }else if(!manager.isLocked()){
					   loginLog.setResult(LoginLog.REUSLT_SUCCESS);
					   loginLogDAO.addLoginLog(loginLog);
					   Manager updateManager=new Manager();
					   updateManager.setManagerId(manager.getManagerId());
					   updateManager.setLastLoginTime(new Date());
					   updateManager.setLoginCount(manager.getLoginCount()+1);
					   managerDAO.updateManager(updateManager);
                       responseInfo.setResult(manager);
					   responseInfo.setRespCode("0");
					   responseInfo.setResult(manager);
				   }else{
					   msg="您的账号已被锁定，请联系管理员进行解锁，即可使用！";
					   loginLog.setResult(LoginLog.REUSLT_ACCOUNT_EXCEPTION);
				   }
				
			   }else{
					 msg="账号密码错误!";
					 loginLog.setResult(LoginLog.REUSLT_PWD_ERROR);
			   }
			   loginLogDAO.addLoginLog(loginLog);
		  }	  
		  responseInfo.setRespDesc(msg);
    	return responseInfo;
    }
    
}
