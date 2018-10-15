<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
    <title>我的服务单</title>
</head>
<body>
	
<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx}/style-mobile/images/onload.gif" style="width:25px;">	
</div>	

<div class="wrap">
	<div class="order-ul-div1">
		<div class="order-ul-div2">
			<ul class="order-ul clearfix" style="width:3000000px;">
				<li class="active"><a href="javascript:;" onclick="changeStatus(this,-3);">全部订单</a></li>
				<li><a href="javascript:;" onclick="changeStatus(this,3);">待上门</a></li>
				<li><a href="javascript:;" onclick="changeStatus(this,4);">待付款</a></li>
				<li><a href="javascript:;" onclick="changeStatus(this,5);">待评价</a></li>
				<li><a href="javascript:;" onclick="changeStatus(this,-1);">已取消</a></li>
			</ul>
		</div>
	</div>

	<div class="pdT60 order-sg-box">
		<p class="txtC claaa pdT50" id="noDataShow" style="display:none;">没有数据&nbsp;&nbsp;^_^</p>
		<div id="dataShow">
		</div>
	</div>

	<!-- 提示 -->
	<div class="promp-order-box">
		<div>
			<!-- <img src="images/20.png"> -->
			<p class="p-mark">确定退单？</p>
			<p class="clearfix">
				<a href="javascript:void(0);" class="no">取消</a>
				<a href="javascript:void(0);" class="yes">确认</a>
			</p>
		</div>		
	</div>

</div>
<script type="text/javascript">
$(function(){	
	cancel();
	mySend();
	myOnscroll();
	var liLength=$(".order-ul li").length;
	var liWidth=0;
	for(var i=0;i<liLength;i++){
		liWidth=liWidth+parseInt($($(".order-ul li")[i]).css("width"));
	}
	$(".order-ul").css("width",liWidth+"px");
	getData();
});	


//取消订单
var deleteOrderId=0;
function cancel(){
	$('.wrap').on('click','.receipt',function(event){ 
		deleteOrderId=$(this).attr("data-id");
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
		$.post("set-order-status",{
			"dOrderId":deleteOrderId,
			"status":2
		},function(data){
			if(data=="success"){
	            alert_msg("退单成功",function(){
	        		location.href=location.href+'&time='+((new Date()).getTime());
	            });
			}else{
	            alert_msg("订单状态已改变，请联系*****进行退单",function(){
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


var bool=true; //判断是否可以下拉加载
// 加载
function myOnscroll(){

	window.onscroll=function(){
		var scrollTop = $(window).scrollTop();
		var windowH = $(window).height();
		var bodyH = $('.wrap').height();
		var prompt_obj = $('.prompt_obj');
		if(scrollTop+windowH*2 >= bodyH && bool){
			bool=false;	//此时已经再查询数据，不能再次请求查询			
			if(prompt_obj.length<1){
				prompt_obj = $('<div class="prompt_obj"><img src="${ctx}/style-mobile/images/onload.gif"></div>')
				$('.order-sg-box').append(prompt_obj);
			}		
		}else{
			if(prompt_obj.length>0){
				prompt_obj.remove();
			}
		}
	}
	
}

var changeStatus=function(that,changeStatus){
	status=changeStatus;
	page=1;
	getData();
	
	$(".order-ul li").removeClass("active");
	$(that).parent().addClass("active");
}

var page=1,status=-3;
var getData=function(){
	$.post("get-worker-order-list",{
		"page":page,
		"status":status
	},function(data){
		if(page==1)
			$('#dataShow').html("");
		if(data.indexOf("<")>=0){
			$('#dataShow').append(data);
			$(".prompt_obj").remove();		
			bool=true;
			if(page==1){
				$("#noDataShow").css("display","none");
			}
		}else if(page==1){
			bool=false;
			$("#noDataShow").css("display","");
		}
		page++;
	})
}

var addOrderId=0;
//设置报价
function mySend(){
	// 报价
 $('.wrap').on('click','.send',function(event){ 	
	 addOrderId=$(this).attr("data-id");	
	 location.href="order-detail?dOrderId="+addOrderId;
	    /* $.confirm({
	        title:'师傅报价',
	        columnClass: 'col_8 mgAT',
	        content: '<div id="fah-box" class="clearfix"></div>',
	        confirmButton: '确认完成工程',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        onOpen: function(){
	          $('#fah-box').append('<input type="number" id="addPrice" class="form-control" placeholder="请输入师傅报价" value="0">');
	        },
	        confirm: function () {
	            var addPrice = this.$b.find('input#addPrice');
	            if (addPrice.val() == '') {
	                return false;
	            }else {
	                // 有输入
					$.post("set-order-status",{
						"dOrderId":addOrderId,
						"addPrice":addPrice.val(),
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
	            }
	        }
	    }); */

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