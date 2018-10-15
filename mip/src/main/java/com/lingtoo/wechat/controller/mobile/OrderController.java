package com.lingtoo.wechat.controller.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.T1TConstants;
import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.dao.DecorationOrderDAO;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.dao.WxPayDAO;
import com.lingtoo.wechat.pojo.DecorationItem;
import com.lingtoo.wechat.pojo.DecorationOrder;
import com.lingtoo.wechat.pojo.Merchant;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.pojo.UserAddress;
import com.lingtoo.wechat.pojo.WechatUser;
import com.lingtoo.wechat.pojo.WxPaySet;
import com.lingtoo.wechat.service.DecorationItemService;
import com.lingtoo.wechat.service.DecorationOrderService;
import com.lingtoo.wechat.service.NoticeService;
import com.lingtoo.wechat.utils.DateUtil;
import com.lingtoo.wechat.utils.StringUtil;
import com.lingtoo.wechat.utils.SystemConfig;

@Controller("MobileOrderController")
@RequestMapping("/mobile/order")
public class OrderController extends BaseController{
	@Autowired
	private DecorationItemService dItemService;
	@Autowired
	private WxPayDAO wxPayDao;
	@Autowired
	private UserDAO userDao;
	@Value("${system.image.logo.absolutePath}")
	private String imagePath;
    @Autowired
    private DecorationOrderService orderService;
    @Autowired
    private DecorationOrderDAO orderDao;
    @Autowired
    private NoticeService noticeService;
	
    @RequestMapping("user-order-list")
    public String userOrderList(
			HttpSession session, Model model, HttpServletRequest request) {
		model.addAllAttributes(signWechatJSAPI(request));
		return "/mobile/order/user-order-list";
    }

    @RequestMapping("get-user-order-list")
    public String getUserOrderList(
    		Integer page,Integer status,
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		model.addAttribute("orderList", orderService.getDOrderList(0, user.getUserId(), status, page));
    	return "/mobile/order/json/user-order-list";
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
    	return "/mobile/order/order-detail";
    }
    
	@RequestMapping("add-order")
	public String addOrder(
			HttpSession session, Model model, HttpServletRequest request) {
		List<DecorationItem> dItemList=dItemService.selectAllDItem();
		Map<String,List<DecorationItem>> repairDItemMap=new LinkedHashMap<>();
        Map<String,List<DecorationItem>> clearDItemMap=new LinkedHashMap<>();
        Map<String,List<DecorationItem>> installDItemMap=new LinkedHashMap<>();
		for(DecorationItem dItem:dItemList) {
			if(dItem.getType().equals(DecorationItem.TYPE_INSTALL)) {
                List<DecorationItem> list = installDItemMap.get(dItem.getSubTitle());
                if(list != null){
                    list.add(dItem);
                }else {
                    List<DecorationItem> listNew = new ArrayList<>();
                    listNew.add(dItem);
                    installDItemMap.put(dItem.getSubTitle(), listNew);
                }
			}
			if(dItem.getType().equals(DecorationItem.TYPE_REPAIR)) {
                List<DecorationItem> list = repairDItemMap.get(dItem.getSubTitle());
                if(list != null){
                    list.add(dItem);
                }else {
                    List<DecorationItem> listNew = new ArrayList<>();
                    listNew.add(dItem);
                    repairDItemMap.put(dItem.getSubTitle(), listNew);
                }
			}
			if(dItem.getType().equals(DecorationItem.TYPE_CLEAR)) {
                List<DecorationItem> list = clearDItemMap.get(dItem.getSubTitle());
                if(list != null){
                    list.add(dItem);
                }else {
                    List<DecorationItem> listNew = new ArrayList<>();
                    listNew.add(dItem);
                    clearDItemMap.put(dItem.getSubTitle(), listNew);
                }
			}
		}
		model.addAttribute("installMap", installDItemMap);
        model.addAttribute("repairMap", repairDItemMap);
        model.addAttribute("clearMap", clearDItemMap);
		model.addAllAttributes(signWechatJSAPI(request));
		return "/mobile/order/add-order";
	}

	@RequestMapping("choice-address")
	public String choiceAddress(
            DecorationOrder order,@RequestParam(required = false) String bookTimeStr,
			/*@RequestParam(required = false) List<MultipartFile> picList,*/
            String picList,
			HttpSession session, Model model, HttpServletRequest request) {
        JSONArray picArray=new JSONArray();
        if(!StringUtil.isEmpty(picList)) {
        	if(picList.indexOf(",")>=0) {
        		String[] picUrls=picList.split(",");
        		for(String picUrl:picUrls) {
        			picArray.put(picUrl);
        		}
        	}else{
        		picArray.put(picList);
        	}
        }
        order.setPicUrl(picArray.toString());
        if(bookTimeStr != null && bookTimeStr.length() > 0){
            order.setBookTime(DateUtil.convertStringToDate(bookTimeStr + ":00", "yyyy-MM-dd HH:mm:ss"));
        }

        User user=this.getUserSession(request);
        UserAddress userAddress=userDao.selectUserDefaultAddressById(user.getUserId());
        model.addAttribute("userAddress", userAddress);
        session.setAttribute(T1TConstants.SESSION_DECORATION_ORDER, order);
        //model.addAttribute("order",order);
		model.addAllAttributes(signWechatJSAPI(request));

		Merchant merchant = this.getMerchantMobileSession(request);
		WxPaySet paySet = wxPayDao.selectWxPaySet(merchant.getMerchantId());
		model.addAttribute("paySet", paySet);
		return "/mobile/order/choice-address";
	}
	
	@RequestMapping("save-order")
    @ResponseBody
	public String saveOrder(Integer userAddressId,
			HttpSession session, Model model, HttpServletRequest request) {
        User user=this.getUserSession(request);
        WechatUser wechatUser=this.getWechatUserSession(request);
        DecorationOrder order=(DecorationOrder) session.getAttribute(T1TConstants.SESSION_DECORATION_ORDER);
        UserAddress userAddress=userDao.selectUserAddressById(userAddressId);
        order.setReceiveAddress(userAddress.getAddress());
        order.setReceiveArea(userAddress.getAreaValue());
        order.setReceiveCity(userAddress.getCityValue());
        order.setReceiveMobile(userAddress.getPhone());
        order.setReceiveName(userAddress.getName());
        order.setReceiveProvince(userAddress.getProvinceValue());
        order.setCreateTime(new Date());
        order.setStatus(-2);
        order.setUserId(user.getUserId());
        Double ran=10000*Math.random();
		String ranString=ran.toString().substring(0, ran.toString().indexOf("."));
		for(int i=0;i<5-ranString.length();i++){
			ranString="0"+ranString;
		}
		if(order.getEmergencyStatus().equals(1))
			order.setOrderNo("EX"+new Date().getTime()+ranString);
		else
			order.setOrderNo("CM"+new Date().getTime()+ranString);
        orderService.add(order);
        order=orderDao.selectLastDOrder();
        JSONObject object=new JSONObject();
        object.put("status", "success");
        object.put("dOrderId", order.getDecorationOrderId());
        
        String noticeUrl=SystemConfig.getContextPath()+"/mobile/user/order-detail?dOrderId="+order.getDecorationOrderId();
//        noticeService.noticeUserNewOrder(wechatUser.getOpenid(), noticeUrl, order);
        noticeService.noticeUserNewOrder(wechatUser.getOpenid(),noticeUrl, order);
        
        session.removeAttribute(T1TConstants.SESSION_DECORATION_ORDER);
        return object.toString();
	}

    @RequestMapping("user-address")
    public String userAddressList(Model model, HttpServletRequest request) {
        User user=this.getUserSession(request);
        List<UserAddress> userAddressList=userDao.selectUserAddressByUserId(user.getUserId());
        model.addAttribute("addressList", userAddressList);
		model.addAllAttributes(signWechatJSAPI(request));
        return "mobile/order/json/user-address";
    }

    @RequestMapping("price-desc")
    public String priceDesc(Model model, HttpServletRequest request) {
        return "mobile/order/price-desc";
    }
}
