<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<%@ page isELIgnored="false"%>
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="defaultGameTime" value="${fns:defaultGameTime()}"/>
<c:set var="formatDefaultGameTime" value="${fns:formatDatetime(defaultGameTime)}"/>
<c:set var="defaultDate" value="${fns:formatDate(now)}"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="ctxImageServer" value="${fns:getImageServer()}"/>
<c:set var="backend" value="${pageContext.request.contextPath}/backend"/>
<c:set var="state" value="state=${session_appid }"/>


<c:set var="user" value="${session_user_info }"/>
<c:set var="wechat_user" value="${session_user }"/>
<%@ page language="java" import="com.lingtoo.wechat.utils.SystemConfig" %>