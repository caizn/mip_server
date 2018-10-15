<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include> 
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
	<title>个人中心</title>
</head>

<body>

<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:25px;">	
</div>	

<div class="wrap">
	<div class="pers-box pers-box-yes">
		<div class="pers-box-div"><img src="${session_user.headimgurl }"></div>
		<p class="clfff">
			<a href="javascript:void(0);" class="a4">${session_user.nickname }</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="javascript:void(0);" class="a4">钱包：${session_user_info.money }</a>
		</p>
	</div>

	<c:if test="${workerSign=='1' }">
	<a href="../worker/worker-order-list" class="a1 bdBf2f2f2">我的服务单<img src="${ctx }/style-mobile/images/tolf.png"></a>
	</c:if>
	<a href="user-order-list" class="a1 bdBf2f2f2">我的订单<img src="${ctx }/style-mobile/images/tolf.png"></a>
	<c:if test="${session_user_info.auditStatus==0 }">
	<a href="qr-code-show" class="a1 bdBf2f2f2">推广二维码<img src="${ctx }/style-mobile/images/tolf.png"></a>
	<a href="introduce-list" class="a1 mgT13 bdBf2f2f2">我的客户<img src="${ctx }/style-mobile/images/tolf.png"></a>
	</c:if>
	<c:if test="${session_user_info.introducerId!=null }">
		<a href="javascript:;" class="a1 mgT13 bdBf2f2f2 introducer">我的介绍人<img src="${ctx }/style-mobile/images/tolf.png"></a>
	</c:if>
	<a href="address-list" class="a1 mgT13 bdBf2f2f2">地址管理<img src="${ctx }/style-mobile/images/tolf.png"></a>
	<a href="" class="a1" style="display:none;">我的积分<img src="${ctx }/style-mobile/images/tolf.png"><span class="sp3">${session_user_info.integral }</span></a>

	<a href="withdraw" class="a1 mgTB13">提现<img src="${ctx }/style-mobile/images/tolf.png"></a>

</div>	
<div id="qrcodeShow" style="width:100%;height: 100%;display:none;  position: fixed;   top: 0PX;background-color: rgba(255, 255, 255, 0.7);">
	<div class="wrap txtC btn_blue" id="qrcodeShow" style="visibility: visible;width: 80%;margin-top: 80px;border-radius: 8px;border: 1px solid #cac8c8;">
		<div class="pdTB30">
				<img src="${ctx}/style-mobile/images/3-29_06_xyx.jpg" style="width:40px;    border-radius: 50%;">
			<span class="clfff ftS18 ftWB mgL10">厦门修一修</span>
		</div>
			<div class="bgfff pdTB20 ">
		<img class="wp60" src="${ctx }/style-mobile/images/x1x_qrcode.jpg">
			</div>
		<p class="clfff pdT20">长按二维码关注公众号</p>
		<button  class="btn_red btn1" style="margin-top: 20px;">关闭二维码</button>
	</div>
</div>
<div id="introducerShow" style="width:100%;height: 100%;display:none;  position: fixed;   top: 0PX;background-color: rgba(255, 255, 255, 0.7);">
	<div class="wrap txtC btn_blue"  style="visibility: visible;width: 80%;margin-top: 80px;border-radius: 8px;border: 1px solid #cac8c8;">
		<div class="pdTB30"><%-- 
				<img src="${ctx}/style-mobile/images/3-29_06_xyx.jpg" style="width:40px;    border-radius: 50%;"> --%>
			<span class="clfff ftS18 ftWB mgL10">${introducer.name }（${introducer.wechatUser.nickname }）</span>
		</div>
			<div class="bgfff pdTB20 ">
		<img class="wp40" src="${introducer.wechatUser.headimgurl }" style="  border-radius: 60%;">
			</div>
		<button  class="btn_red btn1" style="margin-top: 20px;">关闭</button>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		if("${param.introduceId}"!=""){
			alert_msg("${msg}");
			if("${msg}"=="绑定成功"){
				//$(".wrap").css("display","none");
				$("#qrcodeShow").css("display","");
			}
		}
		$(".btn_red").on("click",function(){
			//$(".wrap").css("display","");
			$("#qrcodeShow").css("display","none");
			$("#introducerShow").css("display","none");
		})
		
		$(".introducer").on("click",function(){
			//$(".wrap").css("display","none");
			$("#introducerShow").css("display","");
		});
	});
</script>
<script>
var shareSign=true;
var shareTitle = '个人中心';
var shareImgUrl='<%=SystemConfig.getContextPath()%>/style-mobile/images/3-29_06_xyx.jpg';
var shareUrl=location.href;
var shareDescription='';
</script>
<jsp:include page="../include/wechat_share.jsp" flush="true"></jsp:include>
</body>
</html>