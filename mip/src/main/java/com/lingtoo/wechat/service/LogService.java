package com.lingtoo.wechat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.LogDAO;
import com.lingtoo.wechat.pojo.Log;

/**
 * Created by shenzh on 2016/8/22.
 */
@Service
public class LogService {

    @Autowired
    private LogDAO t1TLogDAO;

    //插入日志记录
    @Async
    public void insertLog(Integer operateRole,Integer merchantId, Integer operateId, Integer operateType, String remark){
        Log t1TLog = new Log();
        t1TLog.setOperateRole(operateRole);
        t1TLog.setOperateId(operateId);
        t1TLog.setMerchantId(merchantId);
        t1TLog.setCreateTime(new Date());
        t1TLog.setOperateType(operateType);
        t1TLog.setRemark(remark);
        t1TLogDAO.insert(t1TLog);
    }

    // 分页查询
    public PageBean findLogList(String name,Integer operateId,String beginDate,String endDate, Integer pageNo,  Integer pageSize){
        PageBean pageBean=new PageBean();
        pageBean.setRowCount(t1TLogDAO.findLogCount(name,operateId, beginDate, endDate));
        pageBean.setPageSize(pageSize==null?10:pageSize);
        pageBean.setPageNo(pageNo==null?1:pageNo);
        pageBean.setQueryList(t1TLogDAO.findLogList(name,operateId, beginDate, endDate, pageBean.getFirstResult(), pageBean.getPageSize()));
        return pageBean;
    }

    // 分页查询
    public PageBean findLogListByMerchant(String name,Integer merchantId,String beginDate,String endDate, Integer pageNo,  Integer pageSize){
        PageBean pageBean=new PageBean();
        pageBean.setRowCount(t1TLogDAO.findLogCountByMerchant(name,merchantId, beginDate, endDate));
        pageBean.setPageSize(pageSize==null?10:pageSize);
        pageBean.setPageNo(pageNo==null?1:pageNo);
        pageBean.setQueryList(t1TLogDAO.findLogListByMerchant(name,merchantId, beginDate, endDate, pageBean.getFirstResult(), pageBean.getPageSize()));
        return pageBean;
    }
}
