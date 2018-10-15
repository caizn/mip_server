<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>

<link href="${ctx}/style-mobile/js/jquery-confirm/jquery-confirm.css" rel="stylesheet"> 
<script src="${ctx}/style-mobile/js/jquery-confirm/jquery-confirm.js"></script>

<script>
	var loading_show=function(){
		var loadingBlock=
			$('<div class="loading-box" id="loadingBlock">'+
				'<img src="${ctx}/style-mobile/images/onload.gif" alt="">'+
			'</div>');
		$("body").append(loadingBlock);
	}
	
	var loading_hide=function(){
		$("#loadingBlock").remove();
	}
	
	var alert_msg=function(msg,fun){
		var msgBlock=$('<p class="p10" style="z-index: 100000000;""><span>'+msg+'</span></p>');
		$("body").append(msgBlock);
		setTimeout(function(){
			$(".p10").remove();
			if(fun!=undefined&&typeof fun=='function'){
				fun.call();
			}
		},1000);
	}
</script>

<style>
.btn-default{
    background-color: #fff!important;
    border: 1px solid #848484!important;
    padding: 2px 5px!important;
    border-radius: 2px!important;
}
</style>