package com.lingtoo.wechat.controller.mobile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lingtoo.wechat.controller.BaseController;
import com.lingtoo.wechat.dao.DecorationOrderDAO;
import com.lingtoo.wechat.pojo.DecorationOrder;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.pojo.User;
import com.lingtoo.wechat.service.DecorationOrderService;
import com.lingtoo.wechat.service.DecorationWorkerService;
import com.lingtoo.wechat.utils.FileOperation;
import com.lingtoo.wechat.utils.Strings;
import com.lingtoo.wechat.utils.UploadImgUtil;

@Controller("MobileWorkerController")
@RequestMapping("/mobile/worker")
public class WorkerController extends BaseController{
	@Value("${system.image.logo.absolutePath}")
	private String imagePath;
	@Autowired
	private DecorationWorkerService dWorkerService;
    @Autowired
    private DecorationOrderService orderService;

    @RequestMapping("send-order-detail")
    public String sendOrderDetail(
    		Integer dOrderId,
			HttpSession session, Model model, HttpServletRequest request) {
    	DecorationOrder dOrder=orderService.getDOrderById(dOrderId);
		User user=this.getUserSession(request);
		DecorationWorker worker=dWorkerService.getDWorkerByUserId(user.getUserId());
    	model.addAttribute("dWorker", worker);
    	model.addAttribute("dOrder", dOrder);
		model.addAllAttributes(signWechatJSAPI(request));
    	return "/mobile/worker/send-order-detail";
    }

    @RequestMapping("set-price")
    @ResponseBody
    public String setPrice(
    		Integer dOrderId,Double addPrice,
			HttpSession session, Model model, HttpServletRequest request) {
    	return orderService.setPrice(dOrderId, addPrice);
    }
    
    @RequestMapping("set-order-status")
    @ResponseBody
    public String setOrderStatus(
    		Integer dOrderId,Integer status,Double addPrice,
			HttpSession session, Model model, HttpServletRequest request) {
    	return orderService.setStatus(dOrderId, null, null,null,addPrice,null,null,"mobile", status);
    }

    @RequestMapping("rush-order")
    @ResponseBody
    public String rushOrder(
    		Integer dOrderId,
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		DecorationWorker worker=dWorkerService.getDWorkerByUserId(user.getUserId());
    	return orderService.setStatus(dOrderId, null, null,null,null,worker.getDecorationWorkerId(),null,"mobile", 3);
    }
    
    @RequestMapping("worker-order-list")
    public String workerOrderList(
			HttpSession session, Model model, HttpServletRequest request) {
		model.addAllAttributes(signWechatJSAPI(request));
    	return "/mobile/worker/worker-order-list";
    }

    @RequestMapping("get-worker-order-list")
    public String getWorkerOrderList(
    		Integer page,Integer status,
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		DecorationWorker worker=dWorkerService.getDWorkerByUserId(user.getUserId());
		model.addAttribute("orderList", orderService.getDOrderList(1, worker.getDecorationWorkerId(), status, page));
		model.addAllAttributes(signWechatJSAPI(request));
    	return "/mobile/worker/json/worker-order-list";
    }
    
    @RequestMapping("order-detail")
    public String orderDetail(
    		Integer dOrderId,
			HttpSession session, Model model, HttpServletRequest request) {
    	DecorationOrder dOrder=orderService.getDOrderById(dOrderId);
    	model.addAttribute("dOrder", dOrder);
		model.addAllAttributes(signWechatJSAPI(request));
    	return "/mobile/worker/order-detail";
    }
    
	/**
	 * 工人师傅申请页面
	 * @return
	 */
	@RequestMapping("apply-dworker")
	public String applyDWorker(
			HttpSession session, Model model, HttpServletRequest request) {
		User user=this.getUserSession(request);
		DecorationWorker dWorker=dWorkerService.getDWorkerByUserId(user.getUserId());
		model.addAttribute("dWorker", dWorker);
		model.addAllAttributes(signWechatJSAPI(request));
		return "mobile/worker/apply-dworker";
	}
	
	@RequestMapping("save-worker-apply")
	@ResponseBody
	public String savedWorkerApply(
			DecorationWorker dWorker,
			@RequestParam(required = false) List<MultipartFile> picList,
			HttpSession session, Model model, HttpServletRequest request) {
		JSONArray picArray=new JSONArray(dWorker.getIdCardPicUrl());
		/*for(int i=0;i<picList.size();i++) {
			MultipartFile idCardPic=picList.get(i);
			if (idCardPic != null && !idCardPic.isEmpty()) {
				String logoPath = "/images/user/member/";
				String imgName = "";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmSSS");
				imgName = sdf.format(new Date()) + Strings.getRandomNum("1", 5) + "."
							+ FileOperation.getExtension(idCardPic.getOriginalFilename()).toLowerCase();
				logoPath = logoPath + imgName;
	
				try {
					File file = new File(idCardPic + logoPath);
					if (file.exists())
						file.delete();
	
					UploadImgUtil.suoxiaoImg(idCardPic,
							FileOperation.getExtension(idCardPic.getOriginalFilename()).toLowerCase(),
							imagePath + logoPath, null, null, null);
					picArray.put(logoPath);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dWorker.setIdCardPicUrl(logoPath);
			}
		}*/
		if(dWorker.getDecorationWorkerId()!=0) {
			dWorker.setState(0);
			dWorker.setAuditStatus(-1);
			dWorker.setIdCardPicUrl(picArray.toString());
			dWorkerService.updateDWorker(dWorker);
		}else {
			dWorker.setCreateTime(new Date());
			dWorker.setState(0);
			dWorker.setAuditStatus(-1);
			dWorker.setIdCardPicUrl(picArray.toString());
			dWorkerService.addDWorker(dWorker);
		}
		return "success";
	}

    @RequestMapping("apply-notice")
    public String applyNotice(Model model, HttpServletRequest request) {
        return "mobile/worker/apply-notice";
    }
}
