<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>踢踢青训</title>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
</head>
  <body id="channel7-1">
    <div class="content">
	  <jsp:include page="../include/left.jsp" flush="true"></jsp:include>
	  

      <!--右边结束 -->
        <!--开始：右侧内容-->
        <div class="col-main psR">

          <!-- main start-->
          <div class="ofAT main">
	  		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>

            <div class="main-box mgT20 pdT10">                      
              <div class="flL_ clearfix" style="display:none">
                <form action="role-list" class="wp100 mgT10_ flL_ mgL20_ psR_">
                <div class="col_2">
					<input type="hidden" name="pageNo" id="pageNo" value="${param.pageNo}" /> 
					<input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize}" />
                </div>
                <input class="btn bg5585c2-7-6" type="submit" value="搜索">  
                </form>
              </div>

              <div class="mgL20 clearfix">
                <a href="role-add" class="cl38a9d1 mgR20 flR">新增角色</a>
              </div>

              <div class="pdT15 pdLR20">
                <table class="table txtC td2-L td1-150 la1-90">
                  <tr class="bgf2f3f7">
                    <td>角色名称</td>
                    <td>角色描述</td>
                    <td>操作</td>
                  </tr>
                  <c:forEach items="${roles}" var="item"  varStatus="index">
	                  <tr>
	                    <td>${item.name }</td>
	                    <td><span class="dpIB lh17 lsp1">${item.remark }</span></td>
	                    <c:if test="${item.merchantId!=0 }">
	                    <td><a href="role-edit?roleId=${item.roleId }" class="a">修改</a></td>
	                    </c:if>
	                    <c:if test="${item.merchantId==0 }">
	                    <td></td>
	                    </c:if>
	                  </tr>
                  </c:forEach>                  
                </table>
              </div>

            </div>


          </div>  
          <!-- main end-->

        </div>
        <!--结束：右侧内容-->
  </div><!--E .content-box-->
	  
	</div>
  </body>
</html> 
  <jsp:include page="../include/publicjs.jsp" flush="true"></jsp:include>
  <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
<script src="${ctx}/style-system/js/initParam.js"></script>
<script src="${ctx}/style-system/js/region.js"></script>
<script>
</script>
