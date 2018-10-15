<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate, max-age=0">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 26 Jul 1997 05:00:00 GMT">
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
	<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include> 
	<title>地址管理</title>
</head>

<body class="bgf5f8fa">

<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:40px;">	
</div>	

<div class="wrap">
	<c:if test="${fn:length(addressList)==0 }">
		<p class="txtC claaa pdT50">没有数据&nbsp;&nbsp;^_^</p>
	</c:if>

	<c:if test="${fn:length(addressList)!=0 }">
		<c:forEach items="${addressList}" var="item"  varStatus="index">
			<div class="address-box">
				<p class="clearfix">
					<span class="lf">收件人：${item.name }</span>
					<span class="rig">${item.phone }</span>
				</p>
				<p>${item.provinceValue }&nbsp;|&nbsp;${item.cityValue }&nbsp;|&nbsp;${item.areaValue }</p>
				<p>${item.address }</p>
				<p class="clearfix">
					<a href="javascript:void(0);" class="lf a-checkBg" data-id="${item.userAddressId }">
						<c:if test="${item.status==1 }">
						<span class="checkBg checkBg2"></span>
						</c:if>
						<c:if test="${item.status==0 }">
						<span class="checkBg"></span>
						</c:if>
						<span class="cl6c93f7" data-id="${item.userAddressId }">默认地址</span>	
					</a>
					<span class="rig">
						<a href="address-edit?userAddressId=${item.userAddressId }" class="cl6c93f7">编辑</a>
						<span class="clf73d3d del" data-id="${item.userAddressId }">删除</span>
					</span>
				</p>
			</div>
		</c:forEach>
	</c:if>

	<a href="address-edit?userAddressId=0" class="a3">添加新地址</a>


	<!-- 提示 start -->
	<div class="promp-box">
		<div>
			<p class="promp-box-p">提示</p>
			<div class="content">确定删除此地址？</div>
			<a class="promp-box-a" href="javascript:void(0);">确定</a>
		</div>		
	</div>	
	<!-- 提示 end -->


</div>	
<input type="hidden" id="refreshed" value="<%=new Date().getTime()%>">
<script type="text/javascript">
window.onpageshow = function(event) {
	var e=document.getElementById("refreshed");
	var oldTime=e.value;
	var nowTime=new Date().getTime();
	if(nowTime-parseInt(oldTime)>5000){
		location.href=location.href+'&time='+((new Date()).getTime());
	}
};
</script >
<script type="text/javascript">
$(function(){
	//myOnscroll();
	check();
});	
var deleteAddressId=0;
// 单选
function check(){

	// 
	$('.wrap').on('click','.a-checkBg',function(e){ 
		var obj = $(this).find('.checkBg');
		var clas= $.trim(obj[0].className);	  
	   	if(clas.indexOf('checkBg2')==-1)/* {
	   		$(obj).removeClass('checkBg2');
	   	}else */{
	   		$('.checkBg').removeClass('checkBg2');
	   		$(obj).addClass('checkBg2');
	   	}
	   	setDefault($(e.currentTarget).attr("data-id"));
	});
	$('.wrap').on('click','.del',function(event){ 
		$('body').bind("touchmove",function(e){    
			e.preventDefault();	//禁用    
		});
		$('.promp-box').show();

		$(this).parents('.address-box').addClass('remove');
		
		deleteAddressId=$(event.currentTarget).attr("data-id")
	});
	$(".promp-box").click(function(e){
		$("body").unbind("touchmove");
	 	$('.promp-box').hide();
	 	$('.remove').removeClass('remove');
	});
	$(".promp-box-a").click(function(e){
		$("body").unbind("touchmove");
	 	$('.promp-box').hide();
	 	$('.remove').remove();
	 	deleteAddress(deleteAddressId);
	});


}

var setDefault=function(userAddressId){
	$.post("set-default",{
		"userAddressId":userAddressId
	},function(data){
		if(data=="success"){
		}
	});
}

var deleteAddress=function(userAddressId){
	$.post("delete-address",{
		"userAddressId":userAddressId
	},function(data){
		if(data=="success"){
			alert_msg("删除成功",function(){
				location.href=location.href+'&time='+((new Date()).getTime());
			});
		}
	});
}

// 加载
function myOnscroll(){
	var bool=true; //判断是否可以下拉加载

	window.onscroll=function(){
		var scrollTop = $(window).scrollTop();
		var windowH = $(window).height();
		var bodyH = $('.wrap').height();
		var prompt_obj = $('.prompt_obj');
		if(scrollTop+windowH*2 >= bodyH && bool){
			bool=false;	//此时已经再查询数据，不能再次请求查询			
			if(prompt_obj.length<1){
				prompt_obj = $('<div class="prompt_obj"><img src="images/"></div>')
				$('.wrap').append(prompt_obj);
			}
			//数据查询加载
			$('.wrap').append('<div style="height:200px;text-align: center;width:100%;float:left" >数据</div>');
			prompt_obj.remove();
			bool=true;			
		}else{
			if(prompt_obj.length>0){
				prompt_obj.remove();
			}
		}
	}
	
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