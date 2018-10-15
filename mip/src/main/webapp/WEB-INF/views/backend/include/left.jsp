<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>

      <div class="col-sub">
        <div class="col-sub-auto">
            
          <div class="w222">
            <div class="img7-6"><img style="  border-radius: 100%;  border: 2px solid #9a9999;" src="<d:image srcBak="${ctx}/style-mobile/images/3-29_06_xyx.jpg" src="${session_backend_merchant.logoPath}"/>"></div>
            <a href="#" class="a7-15-1">同城修一修后台</a>
            <a href="${ctx}/backend/logout" class="a7-15-2">退出</a>
          </div>
          
          <ul class="nav nav-list" id="J_NavList">
            <%-- <li data-id='0'>
              <a href="javascript:void(0);">
                <i class="fa fa-user"></i>首页
                <b class="arrow fa fa-angle-right"></b>
              </a>
              <ul class="sub-nav" id='sub0'>
                <li data-id='1'>
                  <a href="${ctx}/backend/index"> <i class="fa fa-angle-double-right"></i>首页</a>
                </li>                 
              </ul> 
            </li> --%>
            <c:forEach items="${session_my_menu}" var="menu">
	            <li data-id="${menu.dataId}">
	              <a href="javascript:void(0);">
	                 <i class="fa ${menu.icon}"></i>${menu.name}
	                <b class="arrow fa fa-angle-right"></b>
	              </a>
	              <ul class="sub-nav" id="sub${menu.dataId}">
	             
	              <c:forEach items="${menu.subMenuList}" var="subMenu">
	                <li data-id="${subMenu.dataId}">
	                  <a href="${ctx}/backend/${subMenu.href}"><i class="fa fa-angle-double-right"></i>${subMenu.name}</a>
	                </li>       
	                </c:forEach>                   
	              </ul><!--E .sub-nav-->
	            </li>
	        </c:forEach>
          </ul>

        </div>          
      </div>