<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
    <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
	<title>换师傅</title>
</head>

<body>

<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:25px;">	
</div>	

<div class="wrap">
	<div class="bgfff">
		<p class="p8 clearfix">
			<span class="lf">订单号</span>
			<span class="rig">${dOrder.orderNo}</span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">服务类型</span>
			<span class="rig"><c:if test="${dOrder.type==1 }">安装</c:if>
	                    <c:if test="${dOrder.type==2 }">维修</c:if>
	                    <c:if test="${dOrder.type==3 }">清洗</c:if></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">服务对象</span>
			<span class="rig">${dOrder.decorationItemShow }</span>
		</p>
		<div class="textA-box1">
			<textarea rows="5" placeholder="原因说明" id="changeWorkerRemark"></textarea>
		</div>
	</div>

	<div class="mgT40 mgB30">
		<a href="javascript:save();" class="btn_blue btn1">确定</a>
	</div>

</div>

<script type="text/javascript">
$(function(){	
	dealW();
});
function dealW(){
	var w_lf = parseFloat($('.p8 .lf').width());
	var w = parseFloat($('.p8').width());
	var w_rig = w-w_lf;
	$('.p8 .rig').css('width',w_rig+'px');
}	
function save(){
	if($("#changeWorkerRemark").val()!=""){
        alert_msg("原因不能为空");
	}
	$.post("set-order-status",{
		dOrderId:"${dOrder.decorationOrderId}",
		status:-3,
		changeWorkerRemark:$("#changeWorkerRemark").val()
	},function(data){
		if(data=="success"){
            alert_msg("更改工人的请求已经通知管理员，请耐心等待",function(){
            	history.go(-1);
            });
		}else{
            alert_msg("订单状态已经改变，请联系*****进行取消订单",function(){
        		location.href=location.href+'&time='+((new Date()).getTime());
            });
		}
	})
}
</script>
<script>
var shareSign=false;
var shareTitle = '';
var shareImgUrl='';
var shareUrl='';
var shareDescription='';
</script>
<jsp:include page="../include/wechat_share.jsp" flush="true"></jsp:include>
</body>
</html>