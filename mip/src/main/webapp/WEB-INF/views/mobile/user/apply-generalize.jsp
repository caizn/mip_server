<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
	<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include> 
	<title>推广人入驻</title>
</head>
<body>
	
	
<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:25px;">	
</div>	

<div class="wrap ">	
	<div class="bgfff">
		<c:if test="${session_user_info.auditStatus==-1 }">
			<p class="p11">请等待审核，一般审核时间为3个工作日内。</p>
		</c:if>
		<c:if test="${session_user_info.auditStatus==0 }">
			<p class="p11">您的申请已经审核通过，请在个人中心使用推广二维码进行推广</p>
		</c:if>
		<c:if test="${session_user_info.auditStatus==1 }">
			<p class="p11">您的申请未审核通过，请重新核对您的信息</p>
		</c:if>
		<p class="p2">联系信息</p>
		<p class="p8 clearfix">
			<span class="lf">推广人</span>
			<span class="rig"><input type="text" placeholder="请输入姓名" id="name" value="${session_user_info.name }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">联系电话</span>
			<span class="rig"><input type="text" placeholder="请输入联系电话" id="telephone" value="${session_user_info.telephone }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">联系电话2</span>
			<span class="rig"><input type="text" placeholder="请输入紧急联系电话" id="urgencyTelephone" value="${session_user_info.urgencyTelephone }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">所在区域</span>
			<span class="rig"><input type="text" placeholder="请输入地址" id="myAddrs"  
				data-key="${session_user_info.provinceCode }-${session_user_info.cityCode}-${session_user_info.areaCode}" 
				value="${session_user_info.provinceValue } ${session_user_info.cityValue} ${session_user_info.areaValue}"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">详细地址</span>
			<span class="rig"><input type="text" placeholder="请输入详细地址" id="address" value="${session_user_info.address }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">业务员</span>
			<span class="rig"><input type="text" placeholder="请输入业务员姓名" id="salesman" value="${session_user_info.salesman }"></span>
		</p>
		<p class="p2">备注说明</p>
		<div class="textA-box1">
			<textarea rows="5" placeholder="备注说明" id="remark" >${session_user_info.remark }</textarea>
		</div>		
	</div>
	<p class="p5">客服电话：0592-6688061</p>
	
	<div class="mgT20 ">
		<c:if test="${session_user_info.auditStatus==null||session_user_info.auditStatus==1}">
			<a href="javascript:save();" class="btn_blue btn1">提交申请</a>
		</c:if>
	</div>


</div>

	<jsp:include page="../include/address.jsp" flush="true"></jsp:include> 
<script type="text/javascript">
$(function(){	
	dealW();
	cNAddrArr();
});	

var save=function(){
	if($("#name").val()==""){
		alert_msg("姓名不能为空");
		return;
	}
	if($("#telephone").val()==""){
		alert_msg("电话不能为空");
		return;
	}
	if($("#urgencyTelephone").val()==""){
		alert_msg("紧急电话不能为空");
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
	$.post("save-gengeralize-apply",{
		"remark":$("#remark").val(),
		"salesman":$("#salesman").val(),
		"name":$("#name").val(),
		"telephone":$("#telephone").val(),
		"urgencyTelephone":$("#urgencyTelephone").val(),
		"address":$("#address").val(),
 		 "provinceValue":addressList[0],
  		 "cityValue":addressList[1],
  		 "areaValue":addressList[2],
  		 "provinceCode":addressCodeList[0],
  		 "cityCode":addressCodeList[1],
  		 "areaCode":addressCodeList[2]
	},function(data){
      	 if(data=="success"){
      		 alert_msg("申请成功",function(){
      			location.href=location.href+'&time='+((new Date()).getTime());
      		 });
      		 //layer_success("保存成功");
      	 }
	});
}

function dealW(){
	var w_lf = parseFloat($('.p8 .lf').width());
	var w = parseFloat($('.p8').width());
	var w_rig = w-w_lf;
	$('.p8 .rig').css('width',w_rig+'px');
}

</script>
<script>
var shareSign=true;
var shareTitle = '推广人入驻';
var shareImgUrl='<%=SystemConfig.getContextPath()%>/style-mobile/images/3-29_06_xyx.jpg';
var shareUrl=location.href;
var shareDescription='';
</script>
<jsp:include page="../include/wechat_share.jsp" flush="true"></jsp:include>
</body>
</html>