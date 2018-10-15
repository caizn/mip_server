package com.lingtoo.wechat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lingtoo.common.utils.StringUtils;
import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.dao.RoleDAO;
import com.lingtoo.wechat.pojo.Menu;
import com.lingtoo.wechat.pojo.Role;
import com.lingtoo.wechat.pojo.RoleMenu;
import com.lingtoo.wechat.utils.StringUtil;

@Service
public class RoleService {
    @Autowired
    private RoleDAO roleDAO;

    private static Map<String,String> menuMap=new HashMap<String,String>();
	public  Map<String, String> getMenuMap() {
		return menuMap;
	}
	
	@PostConstruct 
	public void init(){
		Map<String,String> tmpMap=new HashMap<String,String>();
		List<Menu> menus=roleDAO.findMenuList();
		for(Menu menu:menus){
			if(!StringUtils.isEmpty(menu.getHref()))tmpMap.put( menu.getHref(), menu.getHref());
		}
		menuMap.clear();
		menuMap.putAll(tmpMap);
	}

	public 	List<Menu> findMenuListByRoleId(Integer roleId,HttpSession session){
		
		List<Menu> mList=new ArrayList<Menu>();
		if(roleId==-1)mList=this.roleDAO.findMenuList();
		else mList=this.roleDAO.findMenuListByRoleId(roleId);
		List<Menu> menuList=new ArrayList<Menu>();
		List<Menu> subMenuList=new ArrayList<Menu>();
		 Map<String,String> myMenuMap=new HashMap<String,String>();
		if(mList!=null && mList.size()>0){
			for(Menu m:mList){
				if("0".equals(m.getParentMenuId())){
					menuList.add(m);
				}else{
					subMenuList.add(m);
				}
				
				 if(!StringUtil.isEmpty(m.getHref()))myMenuMap.put(m.getHref(),m.getHref());
			}
			for(Menu m:subMenuList){
				for(Menu m1:menuList){
					if(m1.getMenuId().equals(m.getParentMenuId())){
						List<Menu> subMenus=m1.getSubMenuList();
						subMenus.add(m);
						m1.setSubMenuList(subMenus);

					}
				}
			}
		}
		session.setAttribute(T1TConstants.SESSION_MY_MENU_MAP,myMenuMap);
		return menuList;
	}
	
	/**
	 * 添加权限
	 * @param roleId
	 * @param rights
	 */
    @Transactional
	public void updateRight(Integer roleId,String[] rights){
		roleDAO.deleteRoleMenu(roleId);
		int menus=0;
		if(rights!=null && rights.length>0){
			for (int i=0;i<rights.length;i++){
				if(!StringUtils.isEmpty(rights[i])){
					RoleMenu roleMenu=new RoleMenu();
					roleMenu.setRoleId(roleId);
					roleMenu.setMenuId(Integer.valueOf(rights[i].trim()));
					if(rights[i].trim().length()==6)menus++;
					roleDAO.addRoleMenu(roleMenu);			
				}
			}
		}
		Role roleUpdate=new Role();
		roleUpdate.setRoleId(roleId);
		roleUpdate.setMenus(menus);
		roleDAO.updateRole(roleUpdate);
	}
	/**
	 * 删除角色
	 * @param role
	 */
	@Transactional
	public String deleteRole(Role role){
		if(roleDAO.getManagerCountByRoleId(role.getRoleId())>0){
			return "角色["+role.getName()+"]已绑定商家管理员，不能删除！";
		}
		roleDAO.deleteRole(role.getRoleId());
		roleDAO.deleteRoleMenu(role.getRoleId());
		return "0";
	}
	/**
	 * 通过角色ID获取 角色菜单关联表
	 * @param roleId
	 * @return
	 */
	public Map<String,String> findRoleMenu(Integer roleId){
		List<RoleMenu> roleMenuList=roleDAO.findRoleMenuList(roleId);
		Map<String,String>  roleMenuMap=new HashMap<String,String>();
		for(RoleMenu roleMenu:roleMenuList){
			roleMenuMap.put(String.valueOf(roleMenu.getMenuId()), String.valueOf(roleMenu.getMenuId()));
		}
		return roleMenuMap;
	}
}
