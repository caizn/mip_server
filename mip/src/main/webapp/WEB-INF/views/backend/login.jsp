<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录页</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<jsp:include page="include/style.jsp" flush="true"></jsp:include> 
	<style type="text/css">
	@media screen and (max-width: 750px)  { 
		body{min-width: 100%;}
	}
	</style>
</head>
<body>

<div class="login1 wrap">
	<div class="con">
		<div class="txtC"><img src="${ctx}/style-mobile/images/3-29_06_xyx.jpg" style="
    width: 30%;
    border-radius: 50%;
		"></div>
		<div class="title1">同城修一修后台</div>
		<form action="${ctx}/backend/login?act=login" method="post" name="mainForm" id="mainForm">
			<div class="box">
				<input type="text" placeholder="手机号或邮箱" datatype="*" nullmsg="账号不能为空" id="_account" name="_account">
			</div>
			<div class="box">
				<input type="password" placeholder="密码" datatype="*" nullmsg="密码不能为空"  id="_pwd" name="_pwd" >
			</div>
			<c:if test="${cookie._account.value!=null}">
				<input type="hidden" value="1" name="checkAccount" id="checkAccount">
			</c:if>
			<c:if test="${cookie._account.value==null}">
				<input type="hidden" value="0" name="checkAccount" id="checkAccount">
			</c:if>
			<input type="submit" id="submitBtn" style="display:none;">
		</form>	
		
		<div class="div-bot">
			<a href="javascript:$('#submitBtn').click();" class="btn btn-default btnActive btn6">登录</a>
			
			<div class="clearfix mgT10">
				<div class="flL">
				
					<c:if test="${cookie._account.value!=null}">
						<input type="checkbox" value="1" name="checkAccount" checked style="  margin-top: 0px;"/> 记住账号
					</c:if>
					<c:if test="${cookie._account.value==null}">
						<input type="checkbox" value="1" name="checkAccount" style="  margin-top: 0px;"/> 记住账号
					</c:if>
				</div>
				<a href="javascript:$("#");" class="a1">忘记密码？</a>
			</div>	
		</div>

	</div>


</div>
<jsp:include page="include/publicjs.jsp" flush="true"></jsp:include>
<jsp:include page="include/jquery.jsp" flush="true"></jsp:include>

<script>
$(function(){
	$("input[type='checkbox']").on("click",function(){
		if($(this).attr("checked")==undefined){
			$("#checkAccount").val(0);
		}else{
			$("#checkAccount").val(1);
		}
	});
	$("#mainForm").Validform({
		ignoreHidden:true,
		tiptype:function(msg){
			layer.alert(msg);
		},
		tipSweep:true,
		callback:function(){
		}
	});
})
</script>
</body>
</html>