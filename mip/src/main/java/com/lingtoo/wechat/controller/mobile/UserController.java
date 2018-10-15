package com.lingtoo.wechat.controller.mobile;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.bean.PageBean;
import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.dao.DecorationWorkerDAO;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.dao.WxPayDAO;
import com.lingtoo.wechat.pojo.DecorationOrder;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.pojo.Merchant;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.UserAddress;
import com.lingtoo.wechat.pojo.WechatUser;
import com.lingtoo.wechat.pojo.WxPaySet;
import com.lingtoo.wechat.service.DecorationOrderService;
import com.lingtoo.wechat.service.UserService;
import com.lingtoo.wechat.service.UserWithdrawService;

@Controller("MobileUserController")
@RequestMapping("/mobile/user")
public class UserController extends BaseController{
	@Autowired
	private UserDAO userDao;
	@Autowired
	private UserService userService;
	@Value("${system.image.logo.absolutePath}")
	private String imagePath;
	@Autowired
	private UserWithdrawService uWithdrawService;
    @Autowired
    private DecorationOrderService orderService;
    @Autowired
    private DecorationWorkerDAO workerDao;
	@Autowired
	private WxPayDAO wxPayDao;
    @RequestMapping("user-order-list")
    public String userOrderList(
			HttpSession session, Model model, HttpServletRequest request) {
		model.addAllAttributes(signWechatJSAPI(request));

		Merchant merchant = this.getMerchantMobileSession(request);
		WxPaySet paySet = wxPayDao.selectWxPaySet(merchant.getMerchantId());
		model.addAttribute("paySet", paySet);
		return "/mobile/user/user-order-list";
    }

    @RequestMapping("get-user-order-list")
    public String getUserOrderList(
    		Integer page,Integer status,
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		model.addAttribute("orderList", orderService.getDOrderList(0, user.getUserId(), status, page));
    	return "/mobile/user/json/user-order-list";
    }

    @RequestMapping("change-worker")
    public String changeWorker(
    		Integer dOrderId,
			HttpSession session, Model model, HttpServletRequest request) {
    	DecorationOrder dOrder=orderService.getDOrderById(dOrderId);
    	model.addAttribute("dOrder", dOrder);
		model.addAllAttributes(signWechatJSAPI(request));
    	return "/mobile/user/change-worker";
    }
    
    @RequestMapping("set-order-status")
    @ResponseBody
    public String setOrderStatus(
    		Integer dOrderId,Integer status,String evaluate,String changeWorkerRemark,
			HttpSession session, Model model, HttpServletRequest request) {
    	return orderService.setStatus(dOrderId, null, null,evaluate,null,null,changeWorkerRemark,"mobile", status);
    }
    
    @RequestMapping("order-detail")
    public String orderDetail(
    		Integer dOrderId,
			HttpSession session, Model model, HttpServletRequest request) {
    	DecorationOrder dOrder=orderService.getDOrderById(dOrderId);
    	model.addAttribute("dOrder", dOrder);
		model.addAllAttributes(signWechatJSAPI(request));

		Merchant merchant = this.getMerchantMobileSession(request);
		WxPaySet paySet = wxPayDao.selectWxPaySet(merchant.getMerchantId());
		model.addAttribute("paySet", paySet);
    	return "/mobile/user/order-detail";
    }
	
	@RequestMapping("index")
	public String index(
			Integer introduceId,
			HttpSession session, Model model, HttpServletRequest request) {
		if(introduceId!=null) {
			WechatUser wechatUser=(WechatUser) session.getAttribute(T1TConstants.SESSION_USER);
			User user=(User)session.getAttribute(T1TConstants.SESSION_USER_INFO);
			if(user.getUserId()!=introduceId){
				boolean sign=userService.introduce(wechatUser.getOpenid(), introduceId);
				if(sign) {
					model.addAttribute("msg", "绑定成功");
				}else {
					model.addAttribute("msg", "您之前已经有推广人了，无法绑定多个推广人");
				}
			}else{
				model.addAttribute("msg", "您不能扫您自己的二维码");
			}
		}
		User user=this.getUserSession(request);
		if(user.getIntroducerId()!=null) {
			User introducer=userService.getUser(user.getIntroducerId());
			model.addAttribute("introducer", introducer);
		}
		
		DecorationWorker worker=workerDao.selectDWorkerByUserId(user.getUserId());
		if(worker==null) {
			model.addAttribute("workerSign", "0");
		}else if(worker.getAuditStatus().equals(0)) {
			model.addAttribute("workerSign", "1");
		}else 
			model.addAttribute("workerSign", "0");
		model.addAllAttributes(signWechatJSAPI(request));
		return "mobile/user/index";
	}
	
	@RequestMapping("qr-code-show")
	public String qrCodeShow(
			HttpSession session, Model model, HttpServletRequest request) {
		model.addAllAttributes(signWechatJSAPI(request));
		return "mobile/user/qr-code-show";
	}
	
	/**
	 * 客户列表
	 * @return
	 */
	@RequestMapping("introduce-list")
	public String introduceList(
			HttpSession session, Model model, HttpServletRequest request) {
		model.addAllAttributes(signWechatJSAPI(request));
		return "mobile/user/introduce-list";
	}

	@RequestMapping("get-introduce-list")
	public String getIntroduceList(
			Integer page,
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		PageBean pageBean=userService.getUserByIntroduce(user.getUserId(), page, 20);
		model.addAttribute("pageBean", pageBean);
		return "mobile/user/json/introduce-list";
	}
	
	/**
	 * 提现页面
	 * @return
	 */
	@RequestMapping("withdraw")
	public String withdraw(
			HttpSession session, Model model, HttpServletRequest request) {
		model.addAllAttributes(signWechatJSAPI(request));
		return "mobile/user/withdraw";
	}
	
	@RequestMapping("save-withdraw")
	@ResponseBody
	public String saveWithdraw(
			String bankName,		String bankPlace,		String bankCardName,
			String bankCardCode,	Integer moneyWithdraw,
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		user.setBankCardCode(bankCardCode);
		user.setBankCardName(bankCardName);
		user.setBankName(bankName);
		user.setBankPlace(bankPlace);
		user=uWithdrawService.withdraw(user, Double.valueOf(String.valueOf(moneyWithdraw)));
		userDao.updateUser(user);
		session.setAttribute(T1TConstants.SESSION_USER_INFO, user);
		
		return "success";
	}
	
	/**
	 * 推广人申请页面
	 * @return
	 */
	@RequestMapping("apply-generalize")
	public String applyGeneralize(
			HttpSession session, Model model, HttpServletRequest request) {
		model.addAllAttributes(signWechatJSAPI(request));
		return "mobile/user/apply-generalize";
	}
	
	@RequestMapping("save-gengeralize-apply")
	@ResponseBody
	public String saveGeneralizeApply(
			 String remark,					String name,		 	String telephone,
			 String urgencyTelephone,		String address,		 	String provinceValue,
			 String cityValue,				String areaValue,		String provinceCode,
			 String cityCode,				String areaCode,		String salesman,
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		user.setAddress(address);
		user.setAreaCode(areaCode);
		user.setAreaValue(areaValue);
		user.setAuditStatus(-1);
		user.setCityCode(cityCode);
		user.setCityValue(cityValue);
		user.setProvinceCode(provinceCode);
		user.setProvinceValue(provinceValue);
		user.setRemark(remark);
		user.setTelephone(urgencyTelephone);
		user.setTelephone(urgencyTelephone);
		user.setSalesman(salesman);
		user.setName(name);
		userDao.updateUser(user);
		session.setAttribute(T1TConstants.SESSION_USER_INFO, user);
		return "success";
	}
	
	
	
	/**
	 * 地址列表页面
	 * @return
	 */
	@RequestMapping("address-list")
	public String addressList(
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		List<UserAddress> userAddressList=userDao.selectUserAddressByUserId(user.getUserId());
		model.addAttribute("addressList", userAddressList);
		model.addAllAttributes(signWechatJSAPI(request));
		return "mobile/user/address-list";
	}
	
	/**
	 * 地址编辑页面
	 * @param userAddressId
	 * @return
	 */
	@RequestMapping("address-edit")
	public String addressEdit(
			Integer userAddressId,
			HttpSession session, Model model, HttpServletRequest request) {
		if(userAddressId!=0) {
			UserAddress uAddress=userDao.selectUserAddressById(userAddressId);
			model.addAttribute("userAddress", uAddress);
		}
		model.addAllAttributes(signWechatJSAPI(request));
		return "mobile/user/address-edit";
	}

	@RequestMapping("delete-address")
	@ResponseBody
	public String deleteAddress(Integer userAddressId,
			HttpSession session, Model model, HttpServletRequest request) {
		UserAddress userAddress=new UserAddress();
		userAddress=userDao.selectUserAddressById(userAddressId);
		userAddress.setState(1);
		userDao.updateUserAddress(userAddress);
		return "success";
	}
	
	@RequestMapping("set-default")
	@ResponseBody
	public String setDefault(
			Integer userAddressId,
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		List<UserAddress> userAddressList=userDao.selectUserAddressByUserId(user.getUserId());
		for(UserAddress uAddress:userAddressList) {
			uAddress.setStatus(0);
			userDao.updateUserAddress(uAddress);
		}
		UserAddress uAddress=userDao.selectUserAddressById(userAddressId);
		uAddress.setStatus(1);

		userDao.updateUserAddress(uAddress);
		return "success";
	}
	
	@RequestMapping("save-address")
	@ResponseBody
	public String saveAddress(
			Integer userAddressId,		String name,			String telephone,
			String provinceValue,		String cityValue,		String areaValue,
			String provinceCode,		String cityCode,		String areaCode,
			String address,
			HttpSession session, Model model, HttpServletRequest request) {
		UserAddress userAddress=new UserAddress();
		if(userAddressId != null && userAddressId!=0) {
			userAddress=userDao.selectUserAddressById(userAddressId);
		}
		userAddress.setAddress(address);
		userAddress.setAreaCode(areaCode);
		userAddress.setAreaValue(areaValue);
		userAddress.setCityCode(cityCode);
		userAddress.setCityValue(cityValue);
		userAddress.setName(name);
		userAddress.setPhone(telephone);
		userAddress.setProvinceCode(provinceCode);
		userAddress.setProvinceValue(provinceValue);

		if(userAddressId!=0) {
			userDao.updateUserAddress(userAddress);
			JSONObject obj=new JSONObject();
			obj.put("status", "success");
			obj.put("userAddressId", userAddress.getUserAddressId());
			return obj.toString();
		}else {
			User user=this.getUserSession(request);
			userAddress.setUserId(user.getUserId());
			userAddress.setCreateTime(new Date());
			userAddress.setState(0);

			List<UserAddress> userAddressList=userDao.selectUserAddressByUserId(user.getUserId());
			if(userAddressList.size()==0) {
				userAddress.setStatus(1);
			}else {
				userAddress.setStatus(0);
			}
			
			userDao.insertUserAddress(userAddress);
			UserAddress ua=userDao.selectLastUserAddressByUserId(user.getUserId());
			JSONObject obj=new JSONObject();
			obj.put("status", "success");
			obj.put("userAddressId", ua.getUserAddressId());
			return obj.toString();
		}
	}
}
