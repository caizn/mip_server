<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--Company Info-->
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 

  </head>
  <body id="channe5-1">
    <div class="content"> 
	<jsp:include page="../include/left.jsp" flush="true"></jsp:include> 
        <!--开始：右侧内容-->
        <div class="col-main psR">

          <!-- main start-->
          <div class="ofAT main">
	  		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
	  		

            <c:if test="${pageBean.queryList.size()>0}">
            <div class="main-box mgT20 pdT10">                      
              <div class="flL_ clearfix">
                <form action="list" class="wp100 mgT10_ flL_ mgL20_ psR_" id="mainForm">

                <div class="col_2">
                  <input type="text" class="form-control" placeholder="请输入订单号" name="orderNo" value="${param.orderNo }">
                </div>
                <div class="col_2">
                  <input type="text" class="form-control" placeholder="请输入手机号" name="telephone" value="${param.telephone }">
                </div>
                <div class="col_2">
                  <input type="text" class="form-control" placeholder="请输入姓名" name="name" value="${param.name }">
                </div>
                
                <div class="col_1">
                  <select class="form-control" name="emergencyStatus" id="emergencyStatus">
                    <option value="-1">紧急状态</option>
                    <option value="0">不紧急</option>
                    <option value="1">紧急</option>
                  </select>
                </div>
              <div class="col_1">
                  <select class="form-control" name="type" id="type">
                    <option value="-1">项目</option>
                    <option value="1">安装</option>
                    <option value="2">清洗</option>
                    <option value="3">维修</option>
                  </select>
                </div>
                <div class="col_2">
                  <select class="form-control" name="status" id="status">
                    <option value="-3">订单状态</option>
                    <option value="-1">用户取消订单</option>
                    <option value="0">等待接单</option>
                    <option value="3">师傅已经接单</option>
                    <option value="4">完成订单</option>
                    <option value="5">用户二次支付完成</option>
                    <option value="6">用户评分完成</option>
                  </select>
                </div>
                
      			  <input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize}" />
                  <input type="hidden" name="pageNo" id="pageNo" value="${param.pageNo}" />	
                <input class="btn bg5585c2-7-6" type="submit" value="搜索">  
                </form>
              </div>

              <div class="pdT30 pdLR20">
                <table class="table txtC td1-L la_Auto">
                  <tr class="bgf2f3f7">
                    <td>订单号</td>
                    <td>项目</td>
                    <td>紧急状态</td>                                    
                    <td>订单状态</td>
                    <td>预约时间</td>
                    <td>师傅</td>
                    <!-- <td>用户评价</td>
                    <td>支付费用</td>
                    <td>师傅抽成</td>
                    <td>推广人抽成</td> -->
                    <!-- <td>平台所得</td> -->
                  </tr>
                  <c:forEach items="${pageBean.queryList}" var="item"  varStatus="index">
                  <tr>
                    <td><a href="detail?dOrderId=${item.decorationOrderId }" class="a">${item.orderNo }</a></td>
                    <td>
                    	<span class="sp14">
	                    	<c:if test="${item.type==1 }">安装</c:if>
	                    	<c:if test="${item.type==2 }">清洗</c:if>
	                    	<c:if test="${item.type==3 }">维修</c:if>
	                    	&nbsp;/&nbsp;${item.subTitle }&nbsp;/&nbsp;
	                    	${item.decorationItemShow }
                    	</span>
                    </td>
                    <td><span class="sp14"><c:if test="${item.emergencyStatus==0 }">不紧急</c:if><c:if test="${item.emergencyStatus==1 }">紧急</c:if></span></td>                    
                    <td><span class="sp14">
                    	<c:if test="${item.status==-2 }">未付费</c:if>
                    	<c:if test="${item.status==-1 }">已取消</c:if>
						<c:if test="${item.status==0 }">已缴下单费</c:if>
						<c:if test="${item.status==1 }">已审核</c:if>
						<c:if test="${item.status==2 }">师傅退单，等待接单</c:if>
						<c:if test="${item.status==3 }">已接单</c:if>
						<c:if test="${item.status==4 }">师傅上门完成订单</c:if>
						<c:if test="${item.status==5 }">支付完成</c:if>
						<c:if test="${item.status==6 }">已评分</c:if></span></td>
                    <td><span class="sp14"><fmt:formatDate value="${item.bookTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></span></td>
                    <td><span class="sp14">${item.receiveName }</span></td>
                   <%--  <td>${item.evaluate }星</td>
                    <td>${item.workPrice }</td>
                    <td>${item.workerPrice }</td>
                    <td>${item.spreadPrice }</td> --%>
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
                    <img src="${ctx }/style-backend/images/12121.png" class="img1">
                  </div>
                  <p class="clc5c5c5 mgT10">没有数据！</p>
              </div>
            </div>
	  		</c:if>
	  	  </div>
	  	</div>
  </div><!--E .content-box-->

    
<jsp:include page="../include/publicjs.jsp" flush="true"></jsp:include>
<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
 
 <script type="text/javascript">
$(function(){
	$("#status").val("${param.status}");
  	$("#emergencyStatus").val("${param.exigencyStatus}");
  	$("#type").val("${param.type}");
});
 </script>
  </body>
</html>
