<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<link href="${ctx }/style-mobile/css/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx }/style-mobile/js/star-rating/star-rating.css" rel="stylesheet">
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
    <title>订单详情</title>
</head>
<body>

<div class="wrap pdTp40" style="  
    position: fixed;
    z-index: 10000;
    background: rgba(255,255,255,0.9);
    width: 100%;
    height: 100%;
    visibility: hidden;" id="payingshow">

	<p class="txtC" style="margin-top: 60px;"><img src="${ctx }/style-mobile/images/onLoadImgs.gif" style="width: 30px;"></p>
	<p class="txtC pdT10" style="margin-top: 20px;">订单支付确认中，请稍后......</p>

</div>	
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
		<c:if test="${dOrder.evaluatePrice!=null }"><p class="pdLR15 pdTB10">订单费用：￥${dOrder.evaluatePrice }</p></c:if>
		<c:if test="${dOrder.addPrice!=null&&dOrder.addPrice!=0 }"><p class="pdLR15 pdTB10">商讨加价：￥${dOrder.addPrice }</p></c:if>
	</div>
	
	<c:if test="${dOrder.status==6 }">
	<div>
		<p class="p2">订单评价</p>
		<p class="pdLR15 pdTB10" id="tEvaluateShow">技术：</p>
		<p class="pdLR15 pdTB10" id="seEvaluateShow">服务态度：</p>
		<p class="pdLR15 pdTB10" id="spEvaluateShow">流程规范：</p>
	</div>
	</c:if>
	
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

	<div class="txtC pdT50">
		<c:if test="${dOrder.status==-2 }">
			<a href="javascript:$('#buy_btn').click();" class="btn_blue btn1" pay-status="0" data-id="${dOrder.decorationOrderId }">支付上门费</a>
		</c:if>
		<c:if test="${dOrder.status==4 }">
			<a href="javascript:$('#buy_btn').click();" class="btn_blue btn1" pay-status="1" data-id="${dOrder.decorationOrderId }">支付订单费用</a>
		</c:if>
		<c:if test="${dOrder.status==5 }">
			<a href="javascript:evaluate();" class="btn_blue btn1" pay-status="1" data-id="${dOrder.decorationOrderId }">订单评价</a>
		</c:if>
		<c:if test="${dOrder.status==-2||dOrder.status==0||dOrder.status==1||dOrder.status==2 }">
			<a href="javascript:void(0);" class="btn_blue btn1 receipt" data-id="${dOrder.decorationOrderId }" style="margin-top:10px;">取消订单</a>
		</c:if>
	</div>
	
	<div class='promp-box1 dpN'>
		<div class="promp-div" style="margin: 25% auto;">
			<p>技术评分</p>
			<input value="0" type="number" class="rating tEvaluate" min=0 max=5 step=1 data-default-caption="{rating}" data-star-captions="{}">
			<p>服务态度评分</p>
			<input value="0" type="number" class="rating seEvaluate" min=0 max=5 step=1 data-default-caption="{rating}" data-star-captions="{}">
			<p>流程规范评分</p>
			<input value="0" type="number" class="rating spEvaluate" min=0 max=5 step=1 data-default-caption="{rating}" data-star-captions="{}">
			<p class="p0124 clearfix">
				<a href="javascript:void(0);" class="no2">取消</a>
				<a href="javascript:void(0);" class="yes2">确定</a>
			</p>
		</div>
	</div>


	<!-- 提示 -->
	<div class="promp-order-box">
		<div>
			<img src="${ctx }/style-mobile/images/20.png">
			<p>确定取消订单？</p>
			<p class="clearfix">
				<a href="javascript:void(0);" class="no">取消</a>
				<a href="javascript:void(0);" class="yes">确认</a>
			</p>
		</div>		
	</div>


</div>
</div>
	<div style="display:none">
		<%
			String ip=request.getRemoteAddr();
		%>
		<input id="ip" type="hidden" value="<%=ip%>">
		<input id="openid" type="hidden" value="${session_user.openid }">
		<input id="name" type="hidden" value="订单支付">
		<input id="appid" type="hidden" value="${paySet.appid}">
		<input id="appsecret" type="hidden" value="${paySet.appsecret }">
		<input id="partnerkey" type="hidden" value="${paySet.partnerkey }">
		<input id="trade_no" type="hidden" value="">
		<input id="prepay_id" type="hidden" value="">
		<input id="total" type="hidden" value="">
		<input id="mch_id" type="hidden" value="${paySet.partnerid }">
		<c:if test="${dOrder.status==-2 }">
			<input id="totalFee" type="hidden" value="${session_merchant.visitFee }">
			<input id="payType" type="hidden" value="visit">
		</c:if>
		<c:if test="${dOrder.status==4 }">
			<input id="totalFee" type="hidden" value="${dOrder.allPrice-dOrder.visitPrice }">
			<input id="payType" type="hidden" value="work">
		</c:if>
		<input id="orderId" type="hidden" value="${dOrder.decorationOrderId }">
		<button id="buy_btn" style="display:none"></button>
	</div>
<jsp:include page="../include/paySet.jsp" flush="true"></jsp:include>
<script src="${ctx }/style-mobile/js/star-rating/star-rating.js"></script>

<script type="text/javascript">
$(function(){	
	dealW();
	cancel();

	$(".rating-kv").rating({});	
	$('.rating-gly-star').css('visibility','visible');

	$(".no2").on("click",function(){
		$('.promp-box1').hide();
		$("body").unbind("touchmove");
	});
	
	$(".yes2").on("click",function(){
		var tEvaluate=$(".tEvaluate").val();
		var seEvaluate=$(".seEvaluate").val();
		var spEvaluate=$(".spEvaluate").val();
		var evaluate='{"technologyEvaluate":'+tEvaluate+',"serviceEvaluate":'+seEvaluate+',"specificationEvaluate":'+spEvaluate+'}';
		$.post("set-order-status",{
			"dOrderId":${dOrder.decorationOrderId},
			"status":6,
			"evaluate":evaluate
		},function(data){
			if(data=="success"){
	            alert_msg("订单评价成功",function(){
	            });
			}else{
	            alert_msg("评价出现问题，请与管理员联系",function(){});
			}
			location.href=location.href+'&time='+((new Date()).getTime());
		})
	});
	
  	var picListString='${dOrder.picUrl}';
  	if(picListString!=""){
  	  	var picListArray=eval("("+picListString+")");
  	  	picListArray.forEach(function(item){
  	  		$("#picShow").append('<div class="col_10 mgB15 mgL20"><img src="${ctxImageServer}'+item+'"></div>');
  	  	});
  	}else{
  		$("#picShow").css("display","none");
  	}
  	
  	if("${dOrder.technologyEvaluate}"!=""){
  	  	var evaluateShowHtml="";
  	  	var evaluate=parseInt("${dOrder.technologyEvaluate}");
  	  	for(var i=0;i<evaluate;i++){
  	  		evaluateShowHtml+='<i class="fa fa-star i4"></i>'
  	  	}
  	  	$("#tEvaluateShow").append(evaluateShowHtml);
  	}
  	if("${dOrder.serviceEvaluate}"!=""){
  	  	var evaluateShowHtml="";
  	  	var evaluate=parseInt("${dOrder.serviceEvaluate}");
  	  	for(var i=0;i<evaluate;i++){
  	  		evaluateShowHtml+='<i class="fa fa-star i4"></i>'
  	  	}
  	  	$("#seEvaluateShow").append(evaluateShowHtml);
  	}
  	if("${dOrder.specificationEvaluate}"!=""){
  	  	var evaluateShowHtml="";
  	  	var evaluate=parseInt("${dOrder.specificationEvaluate}");
  	  	for(var i=0;i<evaluate;i++){
  	  		evaluateShowHtml+='<i class="fa fa-star i4"></i>'
  	  	}
  	  	$("#spEvaluateShow").append(evaluateShowHtml);
  	}
});	

function evaluate(){
	
	$('.promp-box1').show();
	$('body').bind("touchmove",function(e){    
		e.preventDefault();	//禁用    
	});

}

//取消订单
function cancel(){
	$('.wrap').on('click','.receipt',function(event){ 
		$('.promp-order-box').show();
		$('body').bind("touchmove",function(e){    
			e.preventDefault();	//禁用    
		}); 
	}); 

	$('.wrap').on('click','.no',function(e){ 
		$('.promp-order-box').hide();
		$("body").unbind("touchmove");
	}); 

	$('.wrap').on('click','.yes',function(e){ 
		$('.promp-order-box').hide();
		$("body").unbind("touchmove");
		$.post("set-order-status",{
			"dOrderId":${dOrder.decorationOrderId},
			"status":-1
		},function(data){
			if(data=="success"){
	            alert_msg("订单取消成功",function(){
	        		location.href=location.href+'&time='+((new Date()).getTime());
	            });
			}else{
	            alert_msg("工人已经接单，请联系*****进行取消订单",function(){
	        		location.href=location.href+'&time='+((new Date()).getTime());
	            });
			}
		});
	});	

	// 点击空白关闭
  	$(".promp-order-box").click(function(e){
	   var obj = $(e.target).parents('.promp-order-box div');
	   if(obj.length<=0){
	   		$('.promp-order-box').hide();
	   		$("body").unbind("touchmove");
	   }
	});	
}
function dealW(){
	var w_lf = parseFloat($('.p8 .lf').width());
	var w = parseFloat($('.p8').width());
	var w_rig = w-w_lf;
	$('.p8 .rig').css('width',w_rig+'px');
}

function checkPay(tradeNo){
	setInterval(function(){
		$.post("../pay/check-pay-order",{
			"tradeNo":tradeNo
		},function(data){
			if(data=="success"){
				location.href=location.href+'&time='+((new Date()).getTime());
			}
		});
	}, 1000);
	setTimeout(function(){
		location.href=location.href+'&time='+((new Date()).getTime());
	}, 2500);
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