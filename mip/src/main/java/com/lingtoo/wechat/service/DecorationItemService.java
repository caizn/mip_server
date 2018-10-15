package com.lingtoo.wechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.DecorationItemDAO;
import com.lingtoo.wechat.pojo.DecorationItem;

@Service
public class DecorationItemService {
	@Autowired
	private DecorationItemDAO dItemDao;
	
	public void addDItem(DecorationItem dItem) {
		dItemDao.insert(dItem);
	}
	
	public void updateDItem(DecorationItem dItem) {
		dItemDao.update(dItem);
	}
	
	public void deleteDItem(Integer dItemId) {
		DecorationItem dItem=selectDItemById(dItemId);
		dItem.setState(1);
		updateDItem(dItem);
	}
	
	public DecorationItem selectDItemById(Integer dItemId) {
		return dItemDao.selectDecorationItemById(dItemId);
	}
	
	public PageBean selectDItemPage(Integer type, Integer pageNo,  Integer pageSize){
        PageBean pageBean=new PageBean();
        pageBean.setRowCount(dItemDao.selectDecorationItemsCount(type));
        pageBean.setPageSize(pageSize==null?10:pageSize);
        pageBean.setPageNo(pageNo==null?1:pageNo);
        pageBean.setQueryList(dItemDao.selectDecorationItems(type, pageBean.getFirstResult(), pageBean.getPageSize()));
        return pageBean;
	}
	
	public List<DecorationItem> selectAllDItem(){
		return dItemDao.selectDecorationItems(null, null, null);
	}
}
