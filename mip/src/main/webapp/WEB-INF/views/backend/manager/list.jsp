<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
  <head>
   <jsp:include page="../include/style.jsp" flush="true"></jsp:include>
  </head>
  <body id="channel7-2">
    <div class="content">
      <div class="content-box">
        <!---菜单开始 -->
        <jsp:include page="../include/left.jsp" flush="true"></jsp:include>
        
      <!--右边结束 -->
        <!--开始：右侧内容-->
        <div class="col-main psR">

          <!-- main start-->
          <div class="ofAT main">
	  		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>

            <c:if test="${pageBean.queryList.size()>0}">
            <div class="main-box mgT20 pdT10">    
              <div class="flL_ clearfix">
                <form action="list" class="wp100 mgT10_ flL_ mgL20_ psR_"id="mainForm" name="mainForm" method="post" style="display:none;">
      					  <input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize}" />
                          <input type="hidden" name="pageNo" id="pageNo" value="${param.pageNo}" />	
                <div class="col_2">
                  <input type="text" class="form-control" placeholder="请输入姓名" name="name">
                </div>
                <div class="col_2">
                  <input type="text" class="form-control" placeholder="请输入手机号" name="telephone">
                </div>
                
                <input class="btn bg5585c2-7-6" type="submit" value="搜索">  
                </form>
              </div>

              <div class="mgL20 clearfix">
                <a href="to-add" class="cl38a9d1 mgR20 flR">新增管理用户</a>
              </div>

              <div class="pdT15 pdLR20">
                <table class="table txtC td1-50 la1-90">
                  <tr class="bgf2f3f7">
                    <td>账号</td>
                    <td>姓名</td>
                    <td>手机号</td>
                    <td>最后登入时间</td>
                    <td>角色</td>
                    <td>状态</td>
                    <td style="width:140px;">操作</td>
                  </tr> <c:forEach items="${pageBean.queryList}" var="item"  varStatus="index">
                        <tr class="lh50_">
                          <td><a href="to-edit?managerId=${item.managerId}" class="a">${item.account}</a></td>
                          <td>${item.realName}</td>
                          <td >${item.phone}</td>
                          <td ><fmt:formatDate value="${item.lastLoginTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                          <td >${item.roleName}<c:if test="${session_backend_merchant.roleId!=item.roleId }">(子账号)</c:if><c:if  test="${session_backend_merchant.roleId==item.roleId }">管理员</c:if></td>
                          
                          <td ><span class="sp-state <c:if test="${item.locked}">sp-state-no</c:if><c:if test="${!item.locked}">sp-state-yes</c:if>">${item.statusName}</span></td>
                          <%-- <td >${item.stateName}</td> --%>
                          <td class="mgLR10_">
                          <%--  <c:if test="${session_system==null}"> --%>
                            <c:if test="${item.managerId!=session_backend.managerId}">
	                            <c:if test="${!item.locked}">
	                            <a class="a" href="javascript:confirmF('lock?managerId=${item.managerId}','确认要设置禁用吗?')">禁用</a>
	                          	</c:if>
	                          	 <c:if test="${item.locked}">
	                            <a class="a" href="javascript:confirmF('unlock?managerId=${item.managerId}','确认要设置开启吗?')">开启</a>
	                          	</c:if>
                          	</c:if>
                          	<c:if test="${item.managerId!=session_backend.managerId&&item.roleName!='超级管理员'}">
                          		<a class="a" href="javascript:del('delete?managerId=${item.managerId}','您确定要删除吗？')">删除</a>
                          	</c:if>
                          
                          	<c:if test="${item.state==0&&item.managerId!=session_backend.managerId}">
                          		<a class="a" href="javascript:confirmF('check?managerId=${item.managerId}','您确定要审核通过吗')">审核通过</a>
                          	</c:if>
                          <%-- </c:if> --%>
                          </td>
                        </tr>
                        
                        </c:forEach>
                  
                </table>
              </div>
            
        <jsp:include page="../include/page.jsp" flush="true"></jsp:include>

            </div>
</c:if>


            <c:if test="${pageBean.queryList.size()<=0}">
            <div class="main-box">
              <div class="txtC pdT20">
                  <div>
                    <img src="images/12121.png" class="img1">
                  </div>
                  <p class="clc5c5c5 mgT10">没有数据！</p>
              </div>
            </div>
            </c:if>


          </div>  
          <!-- main end-->

        </div>
        <!--结束：右侧内容-->
        
        <!-- 菜单结束 -->
      </div><!--E .content-box-->
    </div>  
 <jsp:include page="../include/publicjs.jsp" flush="true"></jsp:include>
 <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
  </body>
</html>
<script type="text/javascript">

</script>