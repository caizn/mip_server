package com.lingtoo.wechat.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.ManagerDAO;
import com.lingtoo.wechat.dao.MerchantDAO;
import com.lingtoo.wechat.dao.RoleDAO;
import com.lingtoo.wechat.pojo.Manager;
import com.lingtoo.wechat.pojo.Merchant;
import com.lingtoo.wechat.pojo.MerchantApp;
import com.lingtoo.wechat.pojo.Role;

@Service
public class MerchantService {

    @Autowired
    private MerchantDAO merchantDAO;
    @Autowired
    private ManagerDAO managerDAO;
    @Autowired
    private RoleDAO roleDAO;

    public PageBean findMerchantPage(String merchantName,String provinceCode,String cityCode,String areaCode,String status,Integer state, Integer pageNo, Integer pageSize){
    	PageBean pageBean=new PageBean();
		pageBean.setRowCount(merchantDAO.findMerchantCount(merchantName, provinceCode, cityCode,areaCode,status,state));
		pageBean.setPageSize(pageSize==null?10:(pageSize>100?100:pageSize));
		pageBean.setPageNo(pageNo==null?1:pageNo);
		List<Merchant> mList=merchantDAO.findMerchantList(merchantName,provinceCode,cityCode,areaCode,status,state,pageBean.getFirstResult(), pageBean.getPageSize());
		for(Merchant m:mList){
			MerchantApp app=merchantDAO.getMerchantAppByMerchantId(m.getMerchantId());
			m.setAppId(app.getAppId());
		}
		pageBean.setQueryList(mList);
    	return pageBean;
    }
    

    public PageBean findMerchantPageBySchool(String merchantName,String provinceCode,String cityCode,String areaCode, Integer pageNo, Integer pageSize){
    	PageBean pageBean=new PageBean();
		pageBean.setRowCount(merchantDAO.findMerchantCountBySchool(merchantName, provinceCode, cityCode,areaCode));
		pageBean.setPageSize(pageSize==null?10:(pageSize>100?100:pageSize));
		pageBean.setPageNo(pageNo==null?1:pageNo);
		List<Merchant> mList=merchantDAO.findMerchantListBySchool(merchantName,provinceCode,cityCode,areaCode,pageBean.getFirstResult(), pageBean.getPageSize());
		for(Merchant m:mList){
			MerchantApp app=merchantDAO.getMerchantAppByMerchantId(m.getMerchantId());
			m.setAppId(app.getAppId());
		}
		pageBean.setQueryList(mList);
    	return pageBean;
    }
    
    public PageBean findMerchantTongJiPage(Integer pageNo, Integer pageSize){
    	PageBean pageBean=new PageBean();
		pageBean.setRowCount(merchantDAO.findMerchantTongJiCount());
		pageBean.setPageSize(pageSize==null?10:(pageSize>100?100:pageSize));
		pageBean.setPageNo(pageNo==null?1:pageNo);
		List<Merchant> merchants=merchantDAO.findMerchantTongJiList(pageBean.getFirstResult(), pageBean.getPageSize());
		//merchants.forEach(merchant -> merchant.setCurrUsers(WechatClient.getusercumulate(merchant.getAppId())));
		pageBean.setQueryList(merchants);
    	return pageBean;
    }
    
    /**
     * 创建商家
     */
    @Transactional
    public String addMerchant(Merchant merchant,Manager manager){
    	merchant.setCreateDate(new Date());
    	merchantDAO.addMerchant(merchant);
		manager.setLocked(false);
		manager.setCreateTime(new Date());
		manager.setLoginCount(0);
		manager.setDeleteFlag(0);
		manager.setRoleId(merchant.getRoleId());
		manager.setRealName(merchant.getRealName().trim());
		manager.setState(Manager.STATE_YES);//审核通过
		manager.setMerchantId(merchant.getMerchantId());
		manager.setAccount(merchant.getAccount());
		manager.setEmail(merchant.getEmail());
		manager.setPhone(merchant.getPhone());
		manager.setSubadminFlag(0);
		//manager.setPlatform(Integer.valueOf(merchant.getPlatform()));
		manager.setSubadminFlag(Manager.SUBADMIN_FLAG_YES);
		managerDAO.addManager(manager);
		
		MerchantApp app=new MerchantApp();

		String rand="";
		if(merchant.getAppId()!=null){
			rand=merchant.getAppId();
		}else{			
	        while(true){
	            rand=getRandomString(20);
	            app=merchantDAO.getMerchantAppById(rand);
	        	if(app==null){
	        		app=new MerchantApp();
	        		break;
	        	}
	        }
		}
		
		app.setAppId(rand);
		app.setAppSecret("");
		app.setMerchantId(merchant.getMerchantId());
		app.setOriginalId("");
		app.setAppName(merchant.getName());
		app.setAppAccount("");
		app.setAppDetail(merchant.getAppDetail());
		app.setAppLevel(1);
		app.setQrCodePath("");
		app.setCreateDate(new Date());
		app.setCreator(merchant.getCreator());
		app.setUpdateTime(new Date());
		app.setServiceBeginDate(merchant.getServiceBeginDate());
		app.setServiceEndDate(merchant.getServiceEndDate());
		merchantDAO.addMerchantApp(app);
		
		//修改角色商家数
		int merchants=merchantDAO.getMerchantsByRoleId(merchant.getRoleId());
		Role role=new Role();
		role.setRoleId(merchant.getRoleId());
		role.setMerchants(merchants);
		roleDAO.updateRoleMerchants(role);

		manager.setPassword(merchant.getPassWord());
    	return "0";
    }
    /**
     * 修改商家
     */
    @Transactional
    public String updateMerchant(Merchant merchant){
    	
    	Merchant merchantOld=merchantDAO.getMerchantById(merchant.getMerchantId());
    	merchant.setUpdateTime(new Date());
    	merchantDAO.updateMerchant(merchant);
    	Manager manager=managerDAO.getManagerByMerchantId(merchant.getMerchantId());
    	manager.setRealName(merchant.getRealName());
    	managerDAO.updateManager(manager);
    	if(!merchantOld.getRoleId().equals(merchant.getRoleId())){
    		//修改新角色商家数
    		int merchants=merchantDAO.getMerchantsByRoleId(merchant.getRoleId());
    		Role role=new Role();
    		role.setRoleId(merchant.getRoleId());
    		role.setMerchants(merchants);
    		roleDAO.updateRoleMerchants(role);
    		
    		//修改旧角色商家数
    		merchants=merchantDAO.getMerchantsByRoleId(merchantOld.getRoleId());
    		role=new Role();
    		role.setRoleId(merchantOld.getRoleId());
    		role.setMerchants(merchants);
    		roleDAO.updateRoleMerchants(role);
    		
    		//修改商家用户角色
    		merchantDAO.updateManagerRoleId(merchant.getMerchantId(),merchant.getRoleId());
    	}
    	
    	

    	return "0";
    }

    
    /**
     * 判定商家微信号
     */
    @Transactional
    public String addMerchantApp(MerchantApp merchantApp){
    	if(merchantDAO.getMerchantAppById(merchantApp.getAppId().trim())!=null){
			return "appId【"+merchantApp.getAppId().trim()+"】已存在，请重新设置";
		}
    	merchantApp.setCreateDate(new Date());
    	merchantDAO.addMerchantApp(merchantApp);
    	return "0";
    }
    
    /**
     * 删除商家
     */
    @Transactional
    public String deleteMerchant(Merchant merchant){
    	if(merchantDAO.getMerchantAppByMerchantId(merchant.getMerchantId())!=null){
    		return "商家已判断公众号，不能删除";
    	}
    	merchantDAO.deleteMerchant(merchant.getMerchantId());
    	merchantDAO.deleteManagerByMerchartId(merchant.getMerchantId());
		//修改角色商家数
		int merchants=merchantDAO.getMerchantsByRoleId(merchant.getRoleId());
		Role role=new Role();
		role.setRoleId(merchant.getRoleId());
		role.setMerchants(merchants);
		roleDAO.updateRoleMerchants(role);
		
    	return "0";
    }

    @Transactional
    public MerchantApp findMerchantApp(Integer merchantID){
        return merchantDAO.getMerchantAppByMerchantId(merchantID);
    }
    
    @Transactional
    public MerchantApp findMerchantAppByAppId(String appid){
    	return merchantDAO.getMerchantAppById(appid);
    }
    
    
    public List<MerchantApp> findMerchantApp(){
        return merchantDAO.findMerchantApp();
    }

    public PageBean findMerchantByAreaCode(Integer type, String areaCode,Integer pageNo, Integer pageSize){
        PageBean pageBean=new PageBean();
        pageBean.setRowCount(merchantDAO.findMerchantCountByAreaCode(type, areaCode));
        pageBean.setPageSize(pageSize==null?10:pageSize);
        pageBean.setPageNo(pageNo==null?1:pageNo);
        pageBean.setQueryList(merchantDAO.findMerchantByAreaCode(type, areaCode, pageBean.getFirstResult(), pageBean.getPageSize()));
        return pageBean;
    }

    public Merchant getMerchantById(Integer merchantId){
        return merchantDAO.getMerchantById(merchantId);
    }

    private String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < length; i++) {   
            int number = random.nextInt(base.length());   
            sb.append(base.charAt(number));   
        }   
        return sb.toString();   
     }  
    
}
