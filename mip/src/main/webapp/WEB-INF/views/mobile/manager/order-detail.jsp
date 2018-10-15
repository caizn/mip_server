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
						<c:if test="${dOrder.status==0 }">待审核</c:if>
						<c:if test="${dOrder.status==1 }">已审核</c:if>
						<c:if test="${dOrder.status==2 }">师傅退单，等待接单</c:if>
						<c:if test="${dOrder.status==3 }">已接单</c:if>
						<c:if test="${dOrder.status==4 }">师傅上门完成订单</c:if>
						<c:if test="${dOrder.status==5 }">支付完成</c:if>
						<c:if test="${dOrder.status==6 }">已评分</c:if></i></p>
        <p class="p13">手机：<a style="color: #6c93f7" href="tel:${dOrder.receiveMobile}">${dOrder.receiveMobile}</a></p>
		<p class="p13">项目：<c:if test="${dOrder.type==1 }">安装</c:if>
	                    <c:if test="${dOrder.type==2 }">维修</c:if>
	                    <c:if test="${dOrder.type==3 }">清洗</c:if>/${dOrder.decorationItemShow }</p>
		<p class="p13">地址：${dOrder.receiveProvince }${dOrder.receiveCity }${dOrder.receiveArea }${dOrder.receiveAddress }</p>
		<c:if test="${dOrder.remark!=null&&dOrder.remark!=''}">
		<p class="p13">备注说明：${dOrder.remark }</p>
		</c:if>
		<c:if test="${dOrder.emergencyStatus==0 }">
		<p class="p13">预约时间：<fmt:formatDate value="${dOrder.bookTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></p>
		</c:if>
		<c:if test="${dOrder.emergencyStatus==1 }">
		<p class="p13">紧急订单</p>
		</c:if>
		<div id="picShow">
		</div>
	</div>
	
		<p class="p13 pdB15">
			<a class="cl6c93f7 pdR10  choice">选择师傅：</a>
			<span class="choice_name"></span>
		</p>
	<div class="txtC pdT40">
		<c:if test="${dOrder.status==0 }">
			<a href="javascript:void(0);" class="btn_blue btn2 send">我要报价</a>
		</c:if>
		<!-- <a href="javascript:void(0);" class="btn_red btn2 examine">确认审核</a> -->
	</div>	


	<!-- 提示 -->
	<div class="promp-order-box">
		<div>
			<!-- <img src="images/20.png"> -->
			<p >确认审核？</p>
			<p class="clearfix">
				<a href="javascript:void(0);" class="no">取消</a>
				<a href="javascript:void(0);" class="yes">确认</a>
			</p>
		</div>		
	</div>

<!-- 选择师傅 -->
	<div class="distri-box" >
		<div >
			<p>选择师傅</p>
			<ul class="distri-ul">
				<c:forEach items="${dWorkerList}" var="item"  varStatus="index">
					<li>
						${item.name }<span class="checkBg" data-val="${item.decorationWorkerId }"></span>
					</li>
				</c:forEach>
			</ul>
			<a href="javascript:void(0);" class="a3 distriClose">确定</a>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	examine();	
	mySend();
	choice()
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
});		

//选择师傅
function choice(){
	// 打开
	$('.wrap').on('click','.choice',function(e){ 
		$('.distri-box').show();
		$(".distri-box div").animate({
     	bottom: "0"
 	}, 500);
 	$('body').bind("touchmove",function(e){    
			e.preventDefault();	//禁用    
		});

	});
	// 关闭
	$('.wrap').on('click','.distriClose',function(e){ 
		$('.distri-box').hide();
	   	$(".distri-box div").animate({bottom: "-800px"}, 500,function(){});
	   	var str = $('.checkBg2').parent().text();
	   	$('.choice_name').text(str);

	   	$("body").unbind("touchmove");
	});

	// 选择
	$('.wrap').on('click','.distri-ul li',function(e){ 
		var obj_span = $(this).find('span');
		var clas= $.trim(obj_span[0].className);	  
	   	if(clas.indexOf('checkBg2')!=-1){
	   		$(obj_span).removeClass('checkBg2');
	   	}else{
	   		$('.checkBg2').removeClass('checkBg2');
	   		$(obj_span).addClass('checkBg2');
	   	}
	});

	// 点击空白关闭
	$(".distri-box").click(function(e){
	   var obj = $(e.target).parents('.distri-box div');
	   if(obj.length<=0){
	   		$('.distri-box').hide();
	   		$(".distri-box div").animate({bottom: "-800px"}, 500,function(){});
	   		$("body").unbind("touchmove");
	   }
	});
}

//弹出提示框
function examine(){
	$('.wrap').on('click','.examine',function(event){ 
		alert();
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

// 设置报价
function mySend(){
	// 报价
    $('.wrap').on('click','.send',function(event){ 		
	    $.confirm({
	        title:'报价',
	        columnClass: 'col_8 mgAT',
	        content: '<div id="fah-box" class="clearfix"></div>',
	        confirmButton: '确认发布',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        onOpen: function(){
	          $('#fah-box').append('<input type="number" id="evaluatePrice" class="form-control" placeholder="请输入订单评估价">');
	          $('#fah-box').append('<input type="number" id="dispatchPrice" class="form-control" placeholder="请输入师傅派单价" style="margin-top: 10px;">');
	        },
	        confirm: function () {
	            var evaluatePrice = this.$b.find('input#evaluatePrice');
	            var dispatchPrice = this.$b.find('input#dispatchPrice');
	            var dWorkerId=null;
	            if($(".checkBg2").length!=0){
	            	dWorkerId=$(".checkBg2").attr("data-val");
	            }
	            if (dispatchPrice.val() == ''||evaluatePrice.val()=='') {
	                return false;
	            } else if(parseFloat($("#evaluatePrice").val())<parseFloat($("#dispatchPrice").val())){
	                alert_msg("订单评估价不能低于师傅派单价",function(){
	                });
	                return false;
	            }else {
	                // 有输入
					$.post("set-status",{
						"dOrderId":${dOrder.decorationOrderId},
						"evaluatePrice":evaluatePrice.val(),
						"dispatchPrice":dispatchPrice.val(),
						"status":1,
						"dWorkerId":dWorkerId
					},function(data){
						if(data=="success"){
			                alert_msg("报价成功",function(){
			            		location.href=location.href+'&time='+((new Date()).getTime());
			                });
						}else if(data=="1"){
			                alert_msg("其他管理员已经报价",function(){
			            		location.href=location.href+'&time='+((new Date()).getTime());
			                });
						}else if(data!="1"){
			                alert_msg("订单状态已经改变",function(){
			            		location.href=location.href+'&time='+((new Date()).getTime());
			                });
						}
					});
	            }
	        }
	    });

    });
}

//弹出提示框
function cancel(){
	$('.wrap').on('click','.receipt',function(event){ 
		var val = $.trim($(this).attr('val'));
		$('.p-mark').text('确定'+val+'？');

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