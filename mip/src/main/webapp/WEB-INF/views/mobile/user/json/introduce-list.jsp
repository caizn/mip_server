<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<c:forEach items="${pageBean.queryList}" var="item"  varStatus="index">
		<a href="javascript:;" class="sg-cust clearfix">
			<img class="sg-cust-img1" src="${item.headimgurl }" >
			<span class="sg-cust-sp1">
				<span>${item.nickname }</span>
				<span>${item.provinceValue}&nbsp;${item.cityValue}&nbsp;${item.areaValue }</span>									
			</span>
			<span class="sg-cust-sp2">
				<fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</span>
		</a>
</c:forEach>