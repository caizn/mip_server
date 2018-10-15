package com.lingtoo.wechat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.UserAddress;
import com.lingtoo.wechat.pojo.WechatUser;

@Service
public class UserService {
	@Autowired
	private UserDAO userDao;
	
	public boolean introduce(String openid,Integer introduceId) {
		WechatUser wechatUser=userDao.selectWechatUserByOpenId(openid);
		User user=userDao.selectUserByOpenId(openid);
		if(wechatUser==null) {
			wechatUser=new WechatUser();
			wechatUser.setOpenid(openid);
			wechatUser.setMerchantId(2);
			wechatUser.setAppid(T1TConstants.APPID_DECORATION);
			wechatUser.setCreateTime(new Date());
    		wechatUser=userDao.selectWechatUserByOpenId(openid);
		}
		if(user==null) {
			user=new User();
			user.setCreateTime(new Date());
			user.setState(0);
			user.setMoney(0.0);
			user.setIntegral(0);
			user.setIntroduceCount(0);
			user.setManagerSign(0);
			user.setWechatUserId(wechatUser.getWechatUserId());
			userDao.insertUser(user);
			user=userDao.selectUserByOpenId(openid);
		}
		if(user.getIntroducerId()==null) {
			user.setIntroducerId(introduceId);
			userDao.updateUser(user);
			
			User introducer=userDao.selectUserById(introduceId);
			introducer.setIntroduceCount(introducer.getIntroduceCount()+1);
			userDao.updateUser(introducer);
			return true;
		}
		return false;
	}

	/**
	 * 设置用户管理员权限
	 * 
	 * @param userId
	 * @param managerSign
	 */
	public void setManagerSign(Integer userId, Integer managerSign) {
		User user = getUser(userId);
		user.setManagerSign(managerSign);
		userDao.updateUser(user);
	}

	public PageBean getUserByIntroduce(Integer introducerId,Integer pageNo,Integer pageSize){
		PageBean pageBean=new PageBean();
		pageBean.setRowCount(userDao.selectUserCountByIntroduce(introducerId));
		pageBean.setPageSize(pageSize == null ? 10 : (pageSize > 100 ? 100 : pageSize));
		pageBean.setPageNo(pageNo == null ? 1 : pageNo);
		List<User> uList = userDao.selectUserByIntroduce(introducerId,pageBean.getFirstResult(), pageBean.getPageSize());
		pageBean.setQueryList(uList);
		return pageBean;
	}
	
	public PageBean getUserPageBean(Integer introducerId, String phone, String nickname,String name, Integer auditStatus, Integer gengeralizeStatus,Integer managerSign,
			Integer pageNo, Integer pageSize) {
		PageBean pageBean = new PageBean();
		pageBean.setRowCount(userDao.selectUserCount(introducerId,phone,nickname, name, auditStatus, gengeralizeStatus,managerSign));
		pageBean.setPageSize(pageSize == null ? 10 : (pageSize > 100 ? 100 : pageSize));
		pageBean.setPageNo(pageNo == null ? 1 : pageNo);
		List<User> uList = userDao.selectUserPage(introducerId,phone,nickname, name, auditStatus, gengeralizeStatus,managerSign,
				pageBean.getFirstResult(), pageBean.getPageSize());

		pageBean.setQueryList(uList);
		return pageBean;
	}

	public User getUser(Integer userId) {
		User user = userDao.selectUserById(userId);
		user.setUserAddressList(userDao.selectUserAddressByUserId(userId));
		user.setWechatUser(userDao.selectWechatUserByUserId(userId));
		return user;
	}

	public User getUserByRand(String rand) {
		return userDao.findUserByRand(rand);
	}

	public void updateUserSubscribe(Integer wechatUserId) {
		userDao.updateUserSubscribe(wechatUserId);
	}

	public UserAddress selectUserDefaultAddressById(Integer userId) {
		return userDao.selectUserDefaultAddressById(userId);
	}

	public UserAddress selectUserAddressById(Integer userAddressId) {
		return userDao.selectUserAddressById(userAddressId);
	}

}
