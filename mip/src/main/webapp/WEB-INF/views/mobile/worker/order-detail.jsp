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
		<%-- <p class="pdLR15 pdTB10">上门费：${dOrder.visitPrice }</p> --%>
		<c:if test="${dOrder.dispatchPrice!=null }"><p class="pdLR15 pdTB10">派单价：￥${dOrder.dispatchPrice }</p></c:if>
		<c:if test="${dOrder.addPrice!=null&&dOrder.addPrice!=0 }"><p class="pdLR15 pdTB10">商讨加价：￥${dOrder.addPrice }</p></c:if>
		<c:if test="${dOrder.workerPrice!=null }"><p class="pdLR15 pdTB10">师傅工费：￥${dOrder.workerPrice }</p></c:if>
		<c:if test="${dOrder.finishPrice!=null }"><p class="pdLR15 pdTB10">评价工费：￥${dOrder.finishPrice }</p></c:if>
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
		<c:if test="${dOrder.status==3 }">
			<a href="javascript:void(0);" class="btn_blue btn1 add">加价</a>
			<a href="javascript:void(0);" class="btn_blue btn1 send" style="margin-top:10px;">完成订单</a>
			<a href="javascript:void(0);" class="btn_blue btn1 receipt" style="margin-top:10px;">退单</a>
		</c:if>
	</div>


	<!-- 提示 -->
	<div class="promp-order-box">
		<div>
			<img src="${ctx }/style-mobile/images/20.png">
			<p>确定退单？</p>
			<p class="clearfix">
				<a href="javascript:void(0);" class="no">取消</a>
				<a href="javascript:void(0);" class="yes">确认</a>
			</p>
		</div>		
	</div>


	<!-- 提示 -->
	<div class="promp-order-box">
		<div>
			<%-- <img src="${ctx }/style-mobile/images/20.png"> --%>
			<p style="margin-top: 10px;">确定完成该订单?<br>（完成后将不能修改加价）</p>
			<p class="clearfix">
				<a href="javascript:void(0);" class="no2">取消</a>
				<a href="javascript:void(0);" class="yes2">确认</a>
			</p>
		</div>		
	</div>
</div>

<script type="text/javascript">
$(function(){	
	dealW();
	cancel();
	finish();
	mySend();
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

function finish(){
	$('.wrap').on('click','.send',function(event){ 
		$('.promp-order-box').show();
		$('body').bind("touchmove",function(e){    
			e.preventDefault();	//禁用    
		}); 
	}); 

	$('.wrap').on('click','.no2',function(e){ 
		$('.promp-order-box').hide();
		$("body").unbind("touchmove");
	}); 

	$('.wrap').on('click','.yes2',function(e){ 
		$('.promp-order-box').hide();
		$("body").unbind("touchmove");
		$.post("set-order-status",{
			"dOrderId":${dOrder.decorationOrderId},
			"status":4
		},function(data){
			if(data=="success"){
                alert_msg("状态设置成功",function(){
            		location.href=location.href+'&time='+((new Date()).getTime());
                });
			}else{
	            alert_msg("工人已经接单，请联系*****进行退单",function(){
	        		location.href=location.href+'&time='+((new Date()).getTime());
	            });
			}
		});
		/* $.post("set-order-status",{
			"dOrderId":${dOrder.decorationOrderId},
			"status":2
		},function(data){
			if(data=="success"){
	            alert_msg("退单成功",function(){
	        		location.href=location.href+'&time='+((new Date()).getTime());
	            });
			}else{
	            alert_msg("订单状态已经改变，请联系*****进行退单",function(){
	        		location.href=location.href+'&time='+((new Date()).getTime());
	            });
			}
		}); */
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
			"status":2
		},function(data){
			if(data=="success"){
	            alert_msg("退单成功",function(){
	        		location.href=location.href+'&time='+((new Date()).getTime());
	            });
			}else{
	            alert_msg("订单状态已经改变，请联系*****进行退单",function(){
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

//设置报价
function mySend(){
	// 报价
$('.wrap').on('click','.add',function(event){ 	
		addOrderId=$(this).attr("data-id");	
	    $.confirm({
	        title:'师傅加价',
	        columnClass: 'col_8 mgAT',
	        content: '<div id="fah-box" class="clearfix"></div>',
	        confirmButton: '确认加价',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        onOpen: function(){
	          $('#fah-box').append('<input type="number" id="addPrice" class="form-control" placeholder="请输入师傅报价" value="${dOrder.addPrice }">');
	        },
	        confirm: function () {
	            var addPrice = this.$b.find('input#addPrice');
	            if (addPrice.val() == '') {
	                return false;
	            }else {
	                // 有输入
					$.post("set-price",{
						"dOrderId":${dOrder.decorationOrderId},
						"addPrice":addPrice.val()
					},function(data){
						if(data=="success"){
			                alert_msg("加价设置成功",function(){
			            		location.href=location.href+'&time='+((new Date()).getTime());
			                });
						}else{
				            alert_msg("工人已经接单，请联系*****进行退单",function(){
				        		location.href=location.href+'&time='+((new Date()).getTime());
				            });
						}
					});
	            }
	        }
	    });

});
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