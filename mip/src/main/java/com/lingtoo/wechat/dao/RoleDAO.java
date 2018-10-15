package com.lingtoo.wechat.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.Menu;
import com.lingtoo.wechat.pojo.Role;
import com.lingtoo.wechat.pojo.RoleMenu;


@MyBatisRepository
public interface RoleDAO {


	void addRole(Role role);
	
	List<Role> findRoleList(@Param("type")Integer type,@Param("merchantId")Integer merchantId);
	
	Role getRole(@Param("roleId") Integer roleId);
	
	void updateRole(Role role);
	
	void updateRoleMerchants(Role role);
	
	void deleteRole(@Param("roleId") Integer roleId);
	
	int getManagerCountByRoleId(@Param("roleId") Integer roleId);
	
	List<Menu> findMenuList();
	List<Menu> findMenuListByRoleId(@Param("roleId") Integer roleId);
	List<RoleMenu> findRoleMenuList(@Param("roleId") Integer roleId);
	
	void deleteRoleMenu(@Param("roleId") Integer roleId);
	
	void addRoleMenu(RoleMenu roleMenu);

}
