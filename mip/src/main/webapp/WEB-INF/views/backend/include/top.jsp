<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>

            <p class="clearfix p2">
              <a href="${ctx}/backend/logout" class="flR cleb3c13 mgR15">退出登录</a>
              <a href="${ctx }/backend/index" class="flR mgR30">
                <img src="<d:image srcBak="${ctx}/style-backend/images/s/logo.png" src="${session_backend.logoPath}"/>" class="img3 mgR3">
                <span>欢迎您，${session_backend.realName}</span>
              </a>              
            </p>