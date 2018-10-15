package com.lingtoo.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.DecorationWorkerDAO;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.pojo.WechatUser;
import com.lingtoo.wechat.utils.SystemConfig;

@Service
public class DecorationWorkerService {
	@Autowired
	private DecorationWorkerDAO dWorkerDao;
	@Autowired
	private UserDAO userDao;
    @Autowired
    private NoticeService noticeService;
	
	public void addDWorker(DecorationWorker dWorker) {
		dWorkerDao.insert(dWorker);
	}
	
	public void updateDWorker(DecorationWorker dWorker) {
		dWorkerDao.update(dWorker);
	}
	
	public void auditDWorker(Integer dWorkerId,Integer auditStatus) {
		DecorationWorker dWorker=getDWorkerById(dWorkerId);
		WechatUser wechatUser=userDao.selectWechatUserByUserId(dWorker.getUserId());
		dWorker.setAuditStatus(auditStatus);
		updateDWorker(dWorker);
		if(auditStatus.equals(0)) {
			String noticeUrl=SystemConfig.getContextPath()+"/mobile/worker/apply-dworker";
			noticeService.noticeWorkerApplyPass(wechatUser.getOpenid(), noticeUrl, dWorker.getName());
		}
	}
	
	public DecorationWorker getDWorkerByUserId(Integer userId) {
		return dWorkerDao.selectDWorkerByUserId(userId);
	}
	
	public DecorationWorker getDWorkerById(Integer dWorkerId) {
		DecorationWorker dWorker=dWorkerDao.selectDWorkerById(dWorkerId);
		dWorker.setUser(userDao.selectUserById(dWorker.getUserId()));
		return dWorker;
	}
	
	public PageBean getDWorkerPage(String name,String phone,Integer auditStatus, Integer pageNo,  Integer pageSize){
        PageBean pageBean=new PageBean();
        pageBean.setRowCount(dWorkerDao.selectDWorkersCount(name,phone, auditStatus));
        pageBean.setPageSize(pageSize==null?10:pageSize);
        pageBean.setPageNo(pageNo==null?1:pageNo);
        List<DecorationWorker> dWorkerList=dWorkerDao.selectDWorkers(name,phone, auditStatus, pageBean.getFirstResult(), pageBean.getPageSize());
        for(DecorationWorker dWorker:dWorkerList){
        	dWorker.setUser(userDao.selectUserById(dWorker.getUserId()));
        }
        pageBean.setQueryList(dWorkerList);
        return pageBean;
	}
}
