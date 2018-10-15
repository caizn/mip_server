<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include>
	<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include> 
	<title>地址编辑</title> 
</head>
<body class="bgf5f8fa">

<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:40px;">	
</div>	

<div class="wrap">
	<div class="bgfff">
		<div class="input-box bdBf2f2f2">
			<span>收件人</span>
			<input type="text" id="name" value="${userAddress.name }">
		</div>
		<div class="input-box bdBf2f2f2">
			<span>手机号</span>
			<input type="text" id="phone" value="${userAddress.phone }">
		</div>
		<div class="input-box bdBf2f2f2">
			<span>地址</span>
			<input type="text" readonly placeholder="请选择地址" id="myAddrs" 
				data-key="${userAddress.provinceCode }-${userAddress.cityCode}-${userAddress.areaCode}" 
				value="${userAddress.provinceValue } ${userAddress.cityValue} ${userAddress.areaValue}"/>
		</div>
		<div class="input-box">
			<span>详细地址</span>
			<input type="text" id="address" value="${userAddress.address }">
		</div>
	</div>
	<a href="javascript:save();" class="a3" >确定</a>
</div>	

	<jsp:include page="../include/address.jsp" flush="true"></jsp:include>
<script type="text/javascript">
$(function(){
  cNAddrArr();

});

var save=function(){
	if($("#name").val()==""){
		alert_msg("收件人不能为空");
		return;
	}
	if($("#phone").val()==""){
		alert_msg("手机号不能为空");
		return;
	}
	if($("#myAddrs").val()==""){
		alert_msg("所在地区不能为空");
		return;
	}
	if($("#address").val()==""){
		alert_msg("地址不能为空");
		return;
	}
	var addressCodeList=$("#myAddrs").attr("data-key").split("-");
	var addressList=$("#myAddrs").val().split(" ");
	$.post("save-address",{
		"userAddressId":"${param.userAddressId}",
		"name":$("#name").val(),
		"telephone":$("#phone").val(),
		
		"address":$("#address").val(),
 		 "provinceValue":addressList[0],
  		 "cityValue":addressList[1],
  		 "areaValue":addressList[2],
  		 "provinceCode":addressCodeList[0],
  		 "cityCode":addressCodeList[1],
  		 "areaCode":addressCodeList[2]
	},function(data){
		var dataObj=eval("("+data+")");
      	 if(dataObj.status=="success"){
      		 alert_msg("保存成功",function(){
          		history.go(-1);
      		 });
      		 //layer_success("保存成功");
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