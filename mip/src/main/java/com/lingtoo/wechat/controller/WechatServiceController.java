package com.lingtoo.wechat.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.service.HandleMsgAdapter;
import com.lingtoo.wechat.service.QiduSessionAction;
import com.lingtoo.wechat.service.UserService;
import com.lingtoo.wechat.utils.StringUtil;

@Controller("WechatServiceController")
@RequestMapping("/wechat/service")
public class WechatServiceController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDAO userDao;
	@RequestMapping("get-info")
	@ResponseBody
	public String getInfo(
			HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getRequestURL().toString();
		String token="abcdefghijklmnopqrstuvwxyz123456";
		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();
		final QiduSessionAction qdsession = QiduSessionAction.newInstance();

		qdsession.addOnHandleMessageListener(new HandleMsgAdapter(userService,userDao,qdsession, "1","1"));

		// 必须调用这两个方法
		// 如果不调用close方法，将会出现响应数据串到其它Servlet中。
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[1024];
		int n = 0;
		while ((n = is.read(b)) != -1) {
			out.append(new String(b, 0, n, "utf-8"));
		}
		String str = out.toString();
		if (str.indexOf("?") == 0)
			str = str.substring(1);
		System.out.println(str);
		/*if(StringUtil.isEmpty(str))
			str="<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";*/
		if(!StringUtil.isEmpty(str)) {
			InputStream input = new ByteArrayInputStream(str.getBytes("utf-8"));
			qdsession.process(input, os, "1",url);// 处理微信消息
			qdsession.close();// 关闭Session
			return "SUCCESS";
		}else {
			System.out.println(request.getParameter("echostr"));
			return request.getParameter("echostr");
		}
	}
}
