package com.lingtoo.wechat.controller.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lingtoo.common.utils.StringUtils;
import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.bean.TreeBean;
import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.dao.MerchantDAO;
import com.lingtoo.wechat.dao.RoleDAO;
import com.lingtoo.wechat.pojo.Log;
import com.lingtoo.wechat.pojo.Manager;
import com.lingtoo.wechat.pojo.Menu;
import com.lingtoo.wechat.pojo.Role;
import com.lingtoo.wechat.service.LogService;
import com.lingtoo.wechat.service.RoleService;
import com.lingtoo.wechat.utils.JsonUtil;

/**
 * 角色管理
 * @author caizn
 *
 */
@Controller("backendRoleController")
@RequestMapping("/backend/role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private MerchantDAO merchantDAO;
    @Autowired
    private LogService LogService;

    //角色列表
    @RequestMapping(value = "role-list")
    public String list(HttpServletRequest request, HttpSession session, Model model) {
		Integer merchantId = this.getMerchantIdSession(request);
    	List<Role> roles=roleDAO.findRoleList(null,merchantId);
    	model.addAttribute("roles", roles);
    	return "backend/merchant/role-list";
    }
    //角色明细
    @RequestMapping(value = "role-detail")
    public String detail(Integer roleId,
    		HttpServletRequest request, HttpSession session, Model model) {
    	Role role=roleDAO.getRole(roleId);
    	
		List<Menu> mList=new ArrayList<Menu>();
		mList=this.roleDAO.findMenuListByRoleId(roleId);
		List<Menu> menuList=new ArrayList<Menu>();
		List<Menu> subMenuList=new ArrayList<Menu>();
	
		if(mList!=null && mList.size()>0){
			for(Menu m:mList){
				if("0".equals(m.getParentMenuId())){
					menuList.add(m);
				}else{
					subMenuList.add(m);
				}
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
		model.addAttribute("menuList", menuList);
    	model.addAttribute("role", role);
    	model.addAttribute("merchants", merchantDAO.findMerchantByRoleId(roleId));
    	
    	return "backend/merchant/role-detail";
    }
    //新增角色
    @RequestMapping(value = "role-add")
    public String add(String act,String name,String remark,Integer type,String[] rights,
    		HttpServletRequest request,Model model, HttpSession session) {
		Integer merchantId = this.getMerchantIdSession(request);
    	String msg="",success="";
		if("add".equals(act)){
			if(StringUtils.isEmpty(name)){
				msg="请输入商家角色名称！";
			}else{
				Role role=new Role();
				role.setCreateTime(new Date());
				role.setName(name);
				role.setRemark(remark);
				role.setMerchantId(merchantId);
				roleDAO.addRole(role);
				roleService.updateRight(role.getRoleId(),rights);
				success="商家角色添加成功！";
				Manager manager=(Manager)session.getAttribute(T1TConstants.SESSION_BACKEND);
		        LogService.insertLog(Log.ROLE_MANAGER,manager.getMerchantId(), manager.getManagerId(), Log.OPERATE_ADD, manager.getRealName() + "新增商家角色["+name+"]");

			    model.addAttribute("role",role);
			}
		}
		model.addAttribute("msg",msg);
		model.addAttribute("success", success);
		List<Menu> menus=roleDAO.findMenuList();
		List<TreeBean> treeList=new ArrayList<TreeBean>();
		Map<String,String>  roleMenuMap=roleService.findRoleMenu(0);
		if(menus!=null){
			for(Menu menu:menus){
				treeList.add(new TreeBean(menu,roleMenuMap.get(menu.getMenuId().toString())!=null));
			}
		}
		model.addAttribute("jsonTree",JsonUtil.getJsonString(treeList));
		return "backend/merchant/role-add";
    }
    
	
	/**
	 * 角色修改
	 **/
	@RequestMapping("/role-edit")
	public String edit(String act,Integer roleId,String name,String remark,String[] rights,
    		HttpServletRequest request,Model model, HttpSession session)throws Exception {

		
		String msg="",success="";
		Role role=roleDAO.getRole(roleId);
		if(role!=null){
			if("edit".equals(act)){
				if(StringUtils.isEmpty(name)){
					msg="请输入商家角色名称！";
				}else{
					role.setName(name);
					role.setRemark(remark);
					roleDAO.updateRole(role);
					roleService.updateRight(role.getRoleId(),rights);
					success="商家角色修改成功！";
					Manager manager=(Manager)session.getAttribute(T1TConstants.SESSION_BACKEND);
				    LogService.insertLog(Log.ROLE_MANAGER,manager.getMerchantId(),  manager.getManagerId(), Log.OPERATE_UPDATE, manager.getRealName() + "修改商家角色["+name+"]");
						
				}
			}
		}else{
			msg="商家角色信息不存在！";
		}
     model.addAttribute("role",role);
		model.addAttribute("msg",msg);
		model.addAttribute("success", success);
		List<Menu> menus=roleDAO.findMenuList();
		List<TreeBean> treeList=new ArrayList<TreeBean>();
		Map<String,String>  roleMenuMap=roleService.findRoleMenu(roleId);
		if(menus!=null){
			for(Menu menu:menus){
				treeList.add(new TreeBean(menu,roleMenuMap.get(menu.getMenuId().toString())!=null));
			}
		}
		model.addAttribute("jsonTree",JsonUtil.getJsonString(treeList));
		return "backend/merchant/role-edit";
	}
	/**
	 * 角色删除
	 **/
	@RequestMapping("/role-delete")
	public String delete(Integer roleId,String name,
    		HttpServletRequest request,Model model, HttpSession session)throws Exception {
		Role role=roleDAO.getRole(roleId);
		String msg="",success="";
		if(role!=null){
			String result=roleService.deleteRole(role);
			if("0".equals(result)){
				success=String.format("商家角色名称[%s]删除成功！",role.getName());
				Manager manager=(Manager)session.getAttribute(T1TConstants.SESSION_BACKEND);
			    LogService.insertLog(Log.ROLE_MANAGER,manager.getMerchantId(), manager.getManagerId(), Log.OPERATE_DELETE, manager.getRealName() + "删除商家角色["+name+"]");
					
			}else{
				msg=result;
			}
		}
		model.addAttribute("msg",msg);
		model.addAttribute("success", success);
		return list(request, session, model);
	}
}
