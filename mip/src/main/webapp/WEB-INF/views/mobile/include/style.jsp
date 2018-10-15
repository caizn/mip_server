<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="white">

<script src="${ctx}/style-mobile/js/jquery-2.1.0.min.js"></script>
<link rel="stylesheet" href="${ctx}/style-mobile/css/basic.css">
<link rel="stylesheet" href="${ctx}/style-mobile/css/public.css">

<link rel="stylesheet" href="${ctx}/style-backend/css/font-awesome-4.7.0/css/font-awesome.min.css">


<script type="text/javascript" src="${ctx}/style-mobile/js/responsive.tabs.js"></script>
<link rel="stylesheet" href="${ctx}/style-mobile/js/datePicker/datePicker.css">
<script src="${ctx}/style-mobile/js/datePicker/datePicker.js"></script>

<script src="${ctx }/style-mobile/js/ajaxfileupload.js"></script>
<script src="${ctx }/style-mobile/js/PCASClass.js"></script> 
<style>
.date_btn_box:after{
	height:0px;
}
.date_btn{
	padding:.8em 1em .8em 1em!important;
}
</style>
<script type="text/javascript">
$(function(){
	myOnload();
});

// 处理加载
function myOnload(){ 
	window.onload=function(){ 
		$('.wrap').css('visibility','visible');
		$("#payingshow").css("visibility","hidden");
		$('#onload-X').remove();
	}
}
</script>

<style type="text/css">
select{
  appearance:none;
  -moz-appearance:none;
  -webkit-appearance:none;
}

.cascade_scroll_selects {position: static;}
</style>

