<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
	<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include> 
	<title>提现</title>
</head>

<body>

<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:25px;">	
</div>


<div class="wrap">
	
	<div class="bgfff">
		<p class="p2">银行卡信息</p>
		<p class="p8 clearfix">
			<span class="lf">银行姓名</span>
			<span class="rig"><input type="text" placeholder="请输入银行姓名" id="bankName" value="${session_user_info.bankName }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">开户行</span>
			<span class="rig"><input type="text" placeholder="请输入开户行" id="bankPlace" value="${session_user_info.bankPlace }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">开户名</span>
			<span class="rig"><input type="text" placeholder="请输入开户名" id="bankCardName" value="${session_user_info.bankCardName }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">卡号</span>
			<span class="rig"><input type="text" placeholder="请输入卡号" id="bankCardCode" value="${session_user_info.bankCardCode }"></span>
		</p>

		<p class="p2">提现</p>
		<p class="p8 clearfix">
			<span class="lf">提现金额</span>
			<span class="rig"><input type="number" placeholder="请输入提现金额" id="money"></span>
		</p>
		<p class="ftS12 pdL15 lh30 claaa">您的余额为：<span>${session_user_info.money }</span></p>

	</div>
	<p class="p5">提现金额不能小于100，也不能有小数</p>

	<div class="mgT40">
		<a href="javascript:save();" class="btn_blue btn1">提交申请</a>
	</div>
</div>

</body>
<script type="text/javascript">
var save=function(){
	if($("#bankName").val()==""){
		alert_msg("请输入银行名称");
		return;
	}
	if($("#bankPlace").val()==""){
		alert_msg("请输入开户行");
		return;
	}
	if($("#bankCardName").val()==""){
		alert_msg("请输入开户名");
		return;
	}
	if($("#bankCardCode").val()==""){
		alert_msg("请输入卡名");
		return;
	}
	if($("#bankName").val()==""){
		alert_msg("请输入银行名称");
		return;
	}
	var moneyWithdraw=$("#money").val();
	if(moneyWithdraw==""){
		alert_msg("请输入金额");
		return;
	}
	if(moneyWithdraw.indexOf(".")>=0){
		alert_msg("金额不能有小数点");
		return;
	}
	if(parseInt(moneyWithdraw)<100){
		alert_msg("提现金额不能小于100");
		return;
	}
	var moneyAll=parseInt("${session_user_info.money }");
	if(moneyAll<parseInt(moneyWithdraw)){
		alert_msg("提现金额不能大于钱包余额");
		return;
	}
	loading_show();
	$.post("save-withdraw",{
		"bankName":$("#bankName").val(),
		"bankPlace":$("#bankPlace").val(),
		"bankCardName":$("#bankCardName").val(),
		"bankCardCode":$("#bankCardCode").val(),
		"moneyWithdraw":parseInt(moneyWithdraw)
	},function(data){
		if(data=="success"){
			alert_msg("提现申请成功",function(){
				location.href=location.href+'&time='+((new Date()).getTime());
			});
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
</html>