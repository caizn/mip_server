package com.lingtoo.wechat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.LoginLogDAO;


@Service
public class LoginLogService {
    @Autowired
    private LoginLogDAO loginLogDAO;

	
    public PageBean findLoginLogPage(Integer merchantId,String account,String realName,String accountType,Integer pageNo, Integer pageSize){
    	PageBean pageBean=new PageBean();
		pageBean.setRowCount(loginLogDAO.findLoginLogCount(merchantId,account, realName, accountType));
		pageBean.setPageSize(pageSize==null?10:pageSize);
		pageBean.setPageNo(pageNo==null?1:pageNo);
		pageBean.setQueryList(loginLogDAO.findLoginLogList(merchantId,account, realName, accountType,pageBean.getFirstResult(), pageBean.getPageSize()));
    	return pageBean;
    }
}
