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
<div class="wrap">
	<div class="bgfff">
		<p class="p12">订单号：${dOrder.orderNo }</p>
		<p class="p13 pdT5">客户：${dOrder.receiveName }<i class="i1"><c:if test="${dOrder.status==-2 }">未支付</c:if>
						<c:if test="${dOrder.status==-1 }">已取消</c:if>
						<c:if test="${dOrder.status==0 }">已缴下单费</c:if>
						<c:if test="${dOrder.status==1 }">等待接单</c:if>
						<c:if test="${dOrder.status==2 }">师傅退单，等待接单</c:if>
						<c:if test="${dOrder.status==3 }">已接单</c:if>
						<c:if test="${dOrder.status==4 }">师傅上门完成订单</c:if>
						<c:if test="${dOrder.status==5 }">支付完成</c:if>
						<c:if test="${dOrder.status==6 }">已评分</c:if></i></p>
		<p class="p13">地址：${dOrder.receiveProvince }${dOrder.receiveCity }${dOrder.receiveArea }${dOrder.receiveAddress }</p>
		<p class="p13">服务类型：<c:if test="${dOrder.type==1 }">安装</c:if>
	                    	<c:if test="${dOrder.type==2 }">维修</c:if>
	                    	<c:if test="${dOrder.type==3 }">清洗</c:if>
	                    	&nbsp;/&nbsp;${dOrder.subTitle }&nbsp;/&nbsp;
	                    	${dOrder.decorationItemShow }</p>
		<c:if test="${dOrder.remark!=null&&dOrder.remark!=''}">
			<p class="p13">备注说明：${dOrder.remark }</p>
		</c:if>
		<c:if test="${dOrder.dispatchPrice!=null }">
			<p class="p13">派单价：￥${dOrder.dispatchPrice}</p>
		</c:if>
		<c:if test="${dOrder.emergencyStatus==0 }">
		<p class="p5">预约时间：<fmt:formatDate value="${dOrder.bookTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></p>
		</c:if>
		<c:if test="${dOrder.emergencyStatus==1 }">
		<p class="p5">紧急订单</p>
		</c:if>
		<div id="picShow">
		</div>

	</div>
	<c:if test="${dOrder.status==1||dOrder.status==2 }">
		<span class="btn_blue sp1" onclick="rushOrder();">抢单</span>
	</c:if>	
</div>
</body>
</html>

<script type="text/javascript">
$(function(){
	var picListString='${dOrder.picUrl}';
  	if(picListString!=""){
  	  	var picListArray=eval("("+picListString+")");
  	  	picListArray.forEach(function(item){
  	  		$("#picShow").append('<p class="p13 pdT10">'+
  					'<img src="${ctxImageServer}'+item+'" alt="">'+
  					'</p>');
  	  	});
  	}else{
  		$("#picShow").css("display","none");
  	}
  	
  	if("${dOrder.decorationWorkerId}"=="${dWorker.decorationWorkerId}"&&${dOrder.status}>2){
  		$(".bgfff").on("click",function(){
  			location.href="order-detail?dOrderId=${dOrder.decorationOrderId }";
  		});
  	}
});		
var rushOrder=function(){
	$.post("rush-order",{
		dOrderId:${dOrder.decorationOrderId}
	},function(data){
		if(data=="success"){
            alert_msg("抢单成功",function(){});
		}else if(data=="2"){
            alert_msg("已被别人抢单",function(){});
		}else if(data!="1"){
            alert_msg("订单状态已经改变",function(){});
		}
		location.href=location.href+'&time='+((new Date()).getTime());
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