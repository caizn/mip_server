<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include> 
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
	<title>个人推广码</title>
</head>

<body class="btn_blue">


<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:25px;">	
</div>	

<div class="wrap txtC">
	
	<div class="pdTB30">
		<img src="${ctx}/style-mobile/images/3-29_06_xyx.jpg" style="width:40px;    border-radius: 50%;">
		<span class="clfff ftS18 ftWB mgL10">厦门修一修</span>
	</div>
	<c:if test="${session_user_info.auditStatus==0 }">
		<div class="bgfff pdTB20 ">
			<img src="" class="wp60" id="picShow">
		</div>
		<p class="clfff pdT20">长按二维码识别图中二维码</p>
		<p class="clfff pdT10">点击右上角可以分享给朋友们</p>
	</c:if>
	<c:if test="${session_user_info.auditStatus!=0 }">
		<p class="cl000 bgfff pdTB20">对不起，您还不具备推广资格</p>
		<p class="cl000 bgfff pdTB20"><a href="apply-generalize" class="btn_blue btn1">申请推广资格</a></p>
	</c:if>
	

</div>


</body>
<script type="text/javascript">
$(function(){
	if("${session_user_info.auditStatus}"=="0"){
		var url="<%=SystemConfig.getContextPath()%>/mobile/user/index?introduceId=${session_user_info.userId }"
		var src="https://www.kuaizhan.com/common/encode-png?large=true&data="+url;
		$("#picShow").attr("src",src);
	}else{
		
	}
});
</script>
<script>
var shareSign=true;
var shareTitle = '欢迎加入';
var shareImgUrl='<%=SystemConfig.getContextPath()%>/style-mobile/images/3-29_06_xyx.jpg';
var shareUrl="<%=SystemConfig.getContextPath()%>/mobile/user/index?introduceId=${session_user_info.userId }";
var shareDescription='';
if("${session_user_info.auditStatus}"=="0"){
	
}else{
	shareSign=false;
}
</script>
<jsp:include page="../include/wechat_share.jsp" flush="true"></jsp:include>
</html>