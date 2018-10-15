package com.lingtoo.wechat.controller.backend;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.dao.ManagerDAO;
import com.lingtoo.wechat.dao.RoleDAO;
import com.lingtoo.wechat.pojo.Log;
import com.lingtoo.wechat.pojo.Manager;
import com.lingtoo.wechat.pojo.Role;
import com.lingtoo.wechat.service.LogService;
import com.lingtoo.wechat.service.ManagerService;
import com.lingtoo.wechat.utils.FileOperation;
import com.lingtoo.wechat.utils.MD5;
import com.lingtoo.wechat.utils.StringUtil;
import com.lingtoo.wechat.utils.Strings;
import com.lingtoo.wechat.utils.UploadImgUtil;

/**
 * 后台管理账号控制类
 * 
 * @author wj.chen
 *
 */
@Controller
@RequestMapping("/backend")
public class ManagerController extends BaseController {
	private static Object object = new Object();
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ManagerDAO managerDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private LogService t1TLogService;
	@Value("${system.image.logo.absolutePath}")
	private String imagePath;

	// 管理员列表
	@RequestMapping(value = "manager/list")
	public String list(String account, String locked, String state, Integer platform, Integer pageNo, Integer pageSize,
			HttpServletRequest request, Model model) {
		PageBean pageBean = managerService.findManagerPage(getMerchantIdSession(request), account, locked, state,
				platform, pageNo, pageSize);
		model.addAttribute("pageBean", pageBean);
		return "backend/manager/list";
	}

	// 前往管理员新增
	@RequestMapping(value = "manager/to-add")
	public String toAdd( HttpServletRequest request, Model model,HttpSession session) {
		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		List<Role> oList=new ArrayList<Role>();
		oList.addAll(roleDAO.findRoleList(null,managerSession.getMerchantId()));
		model.addAttribute("roles", oList);
		
		return "backend/manager/add";
	}
	// 管理员新增
	@RequestMapping(value = "manager/add")
	public String add(String act, String account, String realName, String password, String rePassword, String phone,
			String email,Integer roleId, @RequestParam(required = false) MultipartFile bannerPath,HttpSession session, Model model) {
		String msg = "", success = "";
		if ("add".equals(act)) {
			synchronized (object) {
				if (StringUtil.isEmpty(account)) {
					msg = "请输入用户名!";
				} else if (StringUtil.isEmpty(password)) {
					msg = "密码不能为空!";
				} else if (StringUtil.isEmpty(rePassword)) {
					msg = "确认密码不能为空!";
				} else if (!password.equalsIgnoreCase(rePassword)) {
					msg = "两次密码输入不正确!";
				} else if (StringUtil.isEmpty(realName)) {
					msg = "请输入真实名字!";
				} else if (StringUtil.isEmpty(phone)) {
					msg = "请输入手机号!";
				} else {
					MD5 md5 = new MD5(password);
					Manager manager = new Manager();
					manager.setAccount(account.trim().toLowerCase());
					manager.setPassword(md5.compute());
					manager.setLocked(false);
					manager.setCreateTime(new Date());
					manager.setLoginCount(0);
					manager.setDeleteFlag(0);
					manager.setEmail(email);
					manager.setRealName(realName.trim());
					manager.setPhone(phone.trim());
					manager.setState(Manager.STATE_YES);// 审核通过
					manager.setRoleId(roleId);
					if (bannerPath != null && !bannerPath.isEmpty()) {
						String logoPath = "/headImg/manager/";
						String imgName = "";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmSSS");
						imgName = sdf.format(new Date()) + Strings.getRandomNum("1", 5) + "."
									+ FileOperation.getExtension(bannerPath.getOriginalFilename()).toLowerCase();
						logoPath = logoPath + imgName;

						try {
							logger.info("---------------image path=" + (imagePath + logoPath));

							String imgtype = imgName.substring(imgName.indexOf(".") + 1, imgName.length()).toLowerCase();
							if (!imgtype.equals("jpeg") && !imgtype.equals("jpg") && !imgtype.equals("png")) {
								msg = "上传的照片不符合规格，请重新设置";
								model.addAttribute("manager", manager);
								model.addAttribute("msg", msg);
								return "backend/player/edit";
							}

							File file = new File(imagePath + logoPath);
							if (file.exists())
								file.delete();

							UploadImgUtil.suoxiaoImg(bannerPath,
									FileOperation.getExtension(bannerPath.getOriginalFilename()).toLowerCase(),
									imagePath + logoPath, new Float(1.0), 200, null);
						} catch (Exception e) {
							e.printStackTrace();
						}
						manager.setLogoPath(logoPath);
					}
					
					if (managerService.getManagerByAccount(account.trim().toLowerCase()) != null) {
						msg = "账号【" + account.trim() + "】已存在，请重新设置!";
					} else if (managerDAO.getManagerByPhone(phone) != null) {
						msg = "手机【" + phone + "】已存在，请重新设置!";
					} else {
						Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
						manager.setMerchantId(managerSession.getMerchantId());
						//manager.setRoleId(managerSession.getRoleId());
						/* manager.setPlatform(Manager.PLATFORM_CORP); */
						managerDAO.addManager(manager);
						success = "管理用户创建成功！";

						t1TLogService.insertLog(Log.ROLE_MERCHANT,manager.getMerchantId(),  managerSession.getManagerId(), Log.OPERATE_ADD,
								managerSession.getRealName() + "新增管理员["+manager.getRealName()+"]");
					}

				}
			}
		}

		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		List<Role> oList=new ArrayList<Role>();
		oList.addAll(roleDAO.findRoleList(null,managerSession.getMerchantId()));
		model.addAttribute("roles", oList);
		
		model.addAttribute("msg", msg);
		model.addAttribute("success", success);
		return "backend/manager/add";
	}

	// 前往管理员修改
	@RequestMapping(value = "manager/to-edit")
	public String toEdit(Integer managerId, HttpServletRequest request, Model model,HttpSession session) {
		Manager manager = managerDAO.getManagerById(managerId);
		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		List<Role> oList=new ArrayList<Role>();
		oList.addAll(roleDAO.findRoleList(null,managerSession.getMerchantId()));
		model.addAttribute("roles", oList);
		model.addAttribute("manager", manager);
		return "backend/manager/edit";
	}
	
	// 管理员修改
	@RequestMapping(value = "manager/edit")
	public String edit(String act, Integer managerId, String account, String realName, String password,@RequestParam(required = false) MultipartFile bannerPath,
			String rePassword, String phone, String email,Integer roleId, HttpServletRequest request, Model model,HttpSession session) {
		String msg = "", success = "";
		Manager manager = managerDAO.getManagerById(managerId);
		if (manager != null) {
			if ("edit".equals(act)) {
				if (!StringUtil.isEmpty(password) && StringUtil.isEmpty(rePassword)) {
					msg = "确认密码不能为空!";
				} else if (!StringUtil.isEmpty(password) && !password.equalsIgnoreCase(rePassword)) {
					msg = "两次密码输入不正确!";
				} else if (StringUtil.isEmpty(realName)) {
					msg = "请输入真实名字!";
				} else if (StringUtil.isEmpty(phone)) {
					msg = "请输入手机号!";
				} else {
					Manager managerT = new Manager();
					Manager oldMgr=managerDAO.getManagerByPhone(phone);
					if (bannerPath != null && !bannerPath.isEmpty()) {
						String logoPath = "/headImg/manager/";
						String imgName = "";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmSSS");
						imgName = sdf.format(new Date()) + Strings.getRandomNum("1", 5) + "."
									+ FileOperation.getExtension(bannerPath.getOriginalFilename()).toLowerCase();
						logoPath = logoPath + imgName;

						try {
							logger.info("---------------image path=" + (imagePath + logoPath));

							String imgtype = imgName.substring(imgName.indexOf(".") + 1, imgName.length()).toLowerCase();
							if (!imgtype.equals("jpeg") && !imgtype.equals("jpg") && !imgtype.equals("png")) {
								msg = "上传的照片不符合规格，请重新设置";
								model.addAttribute("manager", manager);
								model.addAttribute("msg", msg);
								return "backend/player/edit";
							}

							File file = new File(imagePath + logoPath);
							if (file.exists())
								file.delete();

							UploadImgUtil.suoxiaoImg(bannerPath,
									FileOperation.getExtension(bannerPath.getOriginalFilename()).toLowerCase(),
									imagePath + logoPath, new Float(1.0), 200, null);
						} catch (Exception e) {
							e.printStackTrace();
						}
						managerT.setLogoPath(logoPath);
					}
					boolean sign=false;

					if (oldMgr!=null) {
						if(!oldMgr.getManagerId().equals(managerId)){
							sign=true;
						}
					}
					if(sign){
						msg = "手机【" + phone + "】已存在，请重新设置!";
					} else {
						if (!StringUtil.isEmpty(password)) {
							MD5 md5 = new MD5(password);
							managerT.setPassword(md5.compute());
						}
						managerT.setEmail(email);
						managerT.setRealName(realName.trim());
						managerT.setManagerId(manager.getManagerId());
						managerT.setRoleId(roleId);
						manager.setPhone(phone.trim());
						managerDAO.updateManager(managerT);
						success = "管理用户修改成功！";

						Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
						t1TLogService.insertLog(Log.ROLE_MERCHANT,manager.getMerchantId(),  managerSession.getManagerId(), Log.OPERATE_UPDATE,
								managerSession.getRealName() + "修改管理员["+manager.getManagerId()+":"+manager.getRealName()+"]");

					}
				}
			}
		} else {
			msg = "管理用户信息不存在.";
		}
		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		List<Role> oList=new ArrayList<Role>();
		oList.addAll(roleDAO.findRoleList(null,managerSession.getMerchantId()));
		model.addAttribute("roles", oList);
		
		model.addAttribute("msg", msg);
		model.addAttribute("manager", manager);
		model.addAttribute("success", success);
		return "backend/manager/edit";
	}

	// 管理员删除
	@RequestMapping(value = "manager/delete")
	public String delete(Integer managerId, String account, String locked, String state, Integer platform,
			Integer pageNo, Integer pageSize, HttpServletRequest request, Model model,HttpSession session) {
		Manager manager = managerDAO.getManagerById(managerId);
		String success = "";
		if (manager != null) {
			managerDAO.deleteManager(managerId);
			success = "管理用户【" + manager.getAccount() + "】删除成功.";
		}
		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		t1TLogService.insertLog(Log.ROLE_MERCHANT,managerSession.getMerchantId(),  managerSession.getManagerId(), Log.OPERATE_DELETE,
				managerSession.getRealName() + "删除管理员["+manager.getManagerId()+":"+manager.getRealName()+"]");
		model.addAttribute("success", success);
		return list(account, locked, state, platform, pageNo, pageSize, request, model);
	}

	/**
	 * 账号锁定
	 **/
	@RequestMapping("manager/lock")
	public String lock(Integer managerId, String account, String locked, String state, Integer platform, Integer pageNo,
			Integer pageSize, HttpServletRequest request, Model model,HttpSession session) {
		String success = "";
		Manager manager = managerDAO.getManagerById(managerId);
		if (manager != null && !manager.isLocked()) {
			managerDAO.updateManagerStatus(managerId, 1);
			success = "管理用户[" + manager.getAccount() + "]已锁定.";
		}
		model.addAttribute("success", success);
		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		t1TLogService.insertLog(Log.ROLE_MERCHANT,managerSession.getMerchantId(),  managerSession.getManagerId(), Log.OPERATE_UPDATE,
				managerSession.getRealName() + "锁定管理员["+manager.getManagerId()+":"+manager.getRealName()+"]");
		return list(account, locked, state, platform, pageNo, pageSize, request, model);
	}

	/**
	 * 账号解锁
	 **/
	@RequestMapping("manager/unlock")
	public String unlock(Integer managerId, String account, String locked, String state, Integer platform,
			Integer pageNo, Integer pageSize, HttpServletRequest request, Model model,HttpSession session) {
		String success = "";
		Manager manager = managerDAO.getManagerById(managerId);
		if (manager != null && manager.isLocked()) {
			managerDAO.updateManagerStatus(managerId, 0);
			success = "管理用户[" + manager.getAccount() + "]已解锁.";
		}
		model.addAttribute("success", success);
		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		t1TLogService.insertLog(Log.ROLE_MERCHANT,managerSession.getMerchantId(),  managerSession.getManagerId(), Log.OPERATE_UPDATE,
				managerSession.getRealName() + "解锁管理员["+manager.getManagerId()+":"+manager.getRealName()+"]");
		return list(account, locked, state, platform, pageNo, pageSize, request, model);
	}

	/**
	 * 账号审核
	 **/
	@RequestMapping("manager/check")
	public String check(Integer managerId, String account, String locked, String state, Integer platform,
			Integer pageNo, Integer pageSize, HttpServletRequest request, Model model,HttpSession session) {
		String success = "";
		Manager manager = managerDAO.getManagerById(managerId);
		if (manager != null && manager.getState().equals(Manager.STATE_CHECKING)) {
			manager.setState(Manager.STATE_YES);
			managerDAO.updateManager(manager);
			success = "商家管理用户["+manager.getManagerId()+":" + manager.getAccount() + "]审核通过.";
			t1TLogService.insertLog(Log.ROLE_MERCHANT,manager.getMerchantId(),  manager.getManagerId(), Log.OPERATE_REVIEW,
					manager.getRealName() + success);
		}
		model.addAttribute("success", success);
		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		t1TLogService.insertLog(Log.ROLE_MERCHANT,managerSession.getMerchantId(),  managerSession.getManagerId(), Log.OPERATE_UPDATE,
				managerSession.getRealName() + "审核管理员["+manager.getRealName()+"]");
		return list(account, locked, state, platform, pageNo, pageSize, request, model);
	}

	// 修改密码
	@RequestMapping(value = "update-pwd")
	public String updatePwd(String act, String _oldpassword, String _password, String _repassword, HttpSession session,
			HttpServletRequest request, Model model) {

		String msg = "", success = "";
		if ("edit".equals(act)) {
			if (StringUtils.isEmpty(_password)) {
				msg = "新密码不能为空!";
			} else if (StringUtils.isEmpty(_repassword)) {
				msg = "确认密码不能为空!";
			} else if (!_password.equalsIgnoreCase(_repassword)) {
				msg = "两次密码输入不正确!";
			} else {
				Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
				Manager manager = managerDAO.getManagerById(managerSession.getManagerId());
				MD5 md5 = new MD5(_oldpassword.trim());
				if (!md5.compute().equalsIgnoreCase(manager.getPassword())) {
					msg = "原密码输入错误!";
				} else {
					managerDAO.updateManagerPwd(managerSession.getManagerId(), new MD5(_password).compute());
					success = "密码修改成功!";
					t1TLogService.insertLog(Log.ROLE_MERCHANT, manager.getMerchantId(), managerSession.getManagerId(), Log.OPERATE_DELETE,
							"["+manager.getManagerId()+":"+managerSession.getRealName() + "]修改密码");
				}
			}
		}

		model.addAttribute("success", success);
		model.addAttribute("msg", msg);
		return "backend/update-pwd";
	}

	// 修改个人信息
	@RequestMapping(value = "update-info")
	public String updateInfo(String act, String realName, String phone, String email, HttpSession session,
			Model model) {

		String msg = "", success = "";
		Manager managerSession = (Manager) session.getAttribute(T1TConstants.SESSION_BACKEND);
		if ("edit".equals(act)) {
			if (StringUtils.isEmpty(realName)) {
				msg = "真实名字不能为空!";
			} else if (StringUtils.isEmpty(phone)) {
				msg = "手机号码不能为空!";
			} else if (StringUtils.isEmpty(email)) {
				msg = "邮箱不能为空!";
			} else {
				Manager manager = new Manager();
				manager.setRealName(realName);
				manager.setPhone(phone);
				manager.setEmail(email);
				manager.setManagerId(managerSession.getManagerId());
				managerDAO.updateManager(manager);
				success = "信息修改成功!";
				t1TLogService.insertLog(Log.ROLE_MERCHANT, manager.getMerchantId(), managerSession.getManagerId(), Log.OPERATE_UPDATE,
						"["+manager.getManagerId()+":"+managerSession.getRealName() + "修改个人信息");
			}
		}

		model.addAttribute("manager", managerDAO.getManagerById(managerSession.getManagerId()));
		model.addAttribute("success", success);
		model.addAttribute("msg", msg);
		return "backend/update-info";
	}

}
