<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
    <title>订单详情</title>
</head>
<body>

<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx}/style-mobile/images/onload.gif" style="width:25px;">	
</div>	

<div class="wrap pdB20">
	<div class="bgfff mgB15">
		<p class="p2">联系信息</p>
		<div>
			<p class="p8 clearfix">
				<span class="lf">姓名</span>
				<span class="rig">${dOrder.receiveName }</span>
			</p>
			<p class="p8 clearfix">
				<span class="lf">手机号</span>
				<span class="rig">${dOrder.receiveMobile }</span>
			</p>
			<p class="p8 clearfix">
				<span class="lf">所在城市</span>
				<span class="rig">${dOrder.receiveCity }</span>
			</p>
			<p class="p8 clearfix">
				<span class="lf">所在区域</span>
				<span class="rig">${dOrder.receiveArea }</span>
			</p>
			<p class="p8 clearfix">
				<span class="lf">详细地址</span>
				<span class="rig">${dOrder.receiveAddress }</span>
			</p>
		</div>
		<p class="p2">服务内容</p>
		<div>
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
		</div>
		<p class="p2">上门时间</p>
		<p class="p8 clearfix">
			<span class="lf">预约时间</span>
			<span class="rig">
				<c:if test="${dOrder.emergencyStatus==0 }"><fmt:formatDate value="${dOrder.bookTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></c:if>
				<c:if test="${dOrder.emergencyStatus==1 }">紧急订单，请您即刻联系</c:if>
			</span>
		</p>
	</div>

	<div class="bgfff pdLR15 pdB15 mgB15" id="picShow">
		<p class="lh38">现场图片</p>
	</div>
	<c:if test="${dOrder.remark!=null&&dOrder.remark!='' }">
	<div class="bgfff">
		<p class="p2">情况描述</p>
		<p class="pdLR15 pdTB10">${dOrder.remark }</p>
	</div>
	</c:if>	

	<div class="bgfff">
		<p class="p2">费用</p>
		<p class="pdLR15 pdTB10">上门费：${dOrder.visitPrice }</p>
		<c:if test="${dOrder.evaluatePrice!=null }"><p class="pdLR15 pdTB10">预估费：￥${dOrder.evaluatePrice }</p></c:if>
		<c:if test="${dOrder.dispatchPrice!=null }"><p class="pdLR15 pdTB10">派单价：￥${dOrder.dispatchPrice }</p></c:if>
		<c:if test="${dOrder.addPrice!=null&&dOrder.addPrice!=0 }"><p class="pdLR15 pdTB10">商讨加价：￥${dOrder.addPrice }</p></c:if>
		<c:if test="${dOrder.workerPrice!=null }"><p class="pdLR15 pdTB10">师傅工费：￥${dOrder.workerPrice }</p></c:if>
		<c:if test="${dOrder.finishPrice!=null }"><p class="pdLR15 pdTB10">评价工费：￥${dOrder.finishPrice }</p></c:if>
	</div>
	
	<div class="bgfff">
		<p class="p2">订单状态</p>
		<p class="pdLR15 pdTB10">
						<c:if test="${dOrder.status==-2 }">未支付</c:if>
						<c:if test="${dOrder.status==-1 }">已取消</c:if>
						<c:if test="${dOrder.status==0 }">已缴下单费</c:if>
						<c:if test="${dOrder.status==1 }">已审核</c:if>
						<c:if test="${dOrder.status==2 }">师傅退单，等待接单</c:if>
						<c:if test="${dOrder.status==3 }">已接单</c:if>
						<c:if test="${dOrder.status==4 }">师傅上门完成订单</c:if>
						<c:if test="${dOrder.status==5 }">支付完成</c:if>
						<c:if test="${dOrder.status==6 }">已评分</c:if>
		</p>
	</div>
</div>

<script type="text/javascript">
$(function(){	
	dealW();
  	var picListString='${dOrder.picUrl}';
  	if(picListString!=""){
  	  	var picListArray=eval("("+picListString+")");
  	  	picListArray.forEach(function(item){
  	  		$("#picShow").append('<div class="col_10 mgB15 mgL20"><img src="${ctxImageServer}'+item+'"></div>');
  	  	});
  	}else{
  		$("#picShow").css("display","none");
  	}
});	

function dealW(){
	var w_lf = parseFloat($('.p8 .lf').width());
	var w = parseFloat($('.p8').width());
	var w_rig = w-w_lf;
	$('.p8 .rig').css('width',w_rig+'px');
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