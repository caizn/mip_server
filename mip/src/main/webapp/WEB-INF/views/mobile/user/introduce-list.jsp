<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
	<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include> 
	<title>我的客户</title>
</head>

<body>

<div id="onload-X" style="text-align: center;padding:30px">
	<img src="images/onload.gif" style="width:25px;">	
</div>	

<div class="wrap">
	<div class="bgfff sg-cust-box">
		<p class="p2">我的客户</p>
		<p class="txtC claaa pdT50" style="display:none;">没有数据&nbsp;&nbsp;^_^</p>
	</div>



</div>
<script type="text/javascript">
var page=1;
var bool=true;
$(function(){	
	getData();
	myOnscroll();
});		

// 加载
function myOnscroll(){
	 //判断是否可以下拉加载

	window.onscroll=function(){
		var scrollTop = $(window).scrollTop();
		var windowH = $(window).height();
		var bodyH = $('.wrap').height();
		var prompt_obj = $('.prompt_obj');
		if(scrollTop+windowH*2 >= bodyH && bool){
			bool=false;	//此时已经再查询数据，不能再次请求查询			
			/* if(prompt_obj.length<1){
				prompt_obj = $('<div class="prompt_obj"><img src="images/onload.gif"></div>')
				$('.sg-cust-box').append(prompt_obj);
			}
			//数据查询加载
			$('.sg-cust-box').append('<div style="height:200px;text-align: center;width:100%;float:left" >数据</div>');
			prompt_obj.remove(); */
			getData();
			//bool=true;			
		}else{
			if(prompt_obj.length>0){
				prompt_obj.remove();
			}
		}
	}
	
}

var getData=function(){
	$.post("get-introduce-list",{
		"page":page
	},function(data){
		if(data.indexOf("<")>=0){
			page++;
			$(".sg-cust-box").append(data);
			bool=true;
		}else{
			//alert_msg("数据加载完毕");
			bool=false;
			if(page==1){
				$(".claaa").css("display","block");
			}
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