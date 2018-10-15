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
	<jsp:include page="include/style.jsp" flush="true"></jsp:include> 

  </head>
  <body id="channel0-1">
    <div class="content"> 
	<jsp:include page="include/left.jsp" flush="true"></jsp:include> 
      <!--右边结束 -->
        <!--开始：右侧内容-->
        <div class="col-main psR">

          <!-- main start-->
          <div class="ofAT main">
			<jsp:include page="include/top.jsp" flush="true"></jsp:include> 
            <p class="p3">动态消息</p>

            <div class="main-box clearfix pdT30 dpB_" style="padding-bottom: 50px;">
              
              <a href="http://localhost/platform-ttshop/backend/order/list?status=2" class="col_3 flL ftS16 txtC">
                <p class="p4 cleb9213">${status3OrderCount }</p>
                <p>
                  <img src="${ctx }/style-backend/images/1.png" class="w28">
                  <span class="vtcAL_m">待发货订单</span>
                </p>
              </a>
              <%
              	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
              	String dateString=sdf.format(new Date());
              %>
              <a href="http://localhost/platform-ttshop/backend/order/list?orderBeginTime=<%=dateString %>&orderEndTime=<%=dateString %>" class="col_3 flL ftS16 txtC">
                <p class="p4 cl1397eb">${yesterDayOrderCount }</p>
                <p>
                  <img src="${ctx }/style-backend/images/1.png" class="w28">
                  <span class="vtcAL_m">昨日订单</span>
                </p>
              </a>
              <div href="javascript:;" class="col_3 flL ftS16 txtC">
                <p class="p4 cl1acc5d">${yesterDayVisitCount }</p>
                <p>
                  <img src="${ctx }/style-backend/images/1.png" class="w28">
                  <span class="vtcAL_m">昨日访客数</span>
                </p>
              </div>
              <div href="javascript:;" class="col_3 flL ftS16 txtC">
                <p class="p4 cleb3c13">${yesterDayOrderMoneySum }</p>
                <p>
                  <img src="${ctx }/style-backend/images/1.png" class="w28">
                  <span class="vtcAL_m">昨日交易额</span>
                </p>
              </div>

            </div>

            <p class="p3">常用功能</p>

            <div class="mgLR20 clearfix txtC"> 
              
              <div class="col_3 flL pdLR10">
                <a href="good/list" class="dpB ftS16 bgfff pdTB35">
                  <img src="${ctx }/style-backend/images/5.png" class="w35">
                  <span class="vtcAL_m">发布商品</span>
                </a>                
              </div>
              <div class="col_3 flL pdLR10">
                <a href="window/list" class="dpB ftS16 bgfff pdTB35">
                  <img src="${ctx }/style-backend/images/6.png" class="w35">
                  <span class="vtcAL_m">店铺管理</span>
                </a>                
              </div>
              <div class="col_3 flL pdLR10">
                <a href="order/list" class="dpB ftS16 bgfff pdTB35">
                  <img src="${ctx }/style-backend/images/7.png" class="w35">
                  <span class="vtcAL_m">订单管理</span>
                </a>                
              </div>
              <div class="col_3 flL pdLR10">
                <a href="user/list?isMember=1" class="dpB ftS16 bgfff pdTB35">
                  <img src="${ctx }/style-backend/images/8.png" class="w35">
                  <span class="vtcAL_m">会员管理</span>
                </a>                
              </div>

            </div>

          </div>  
          <!-- main end-->

        </div>
        <!--结束：右侧内容-->
  </div><!--E .content-box-->

    
<jsp:include page="include/publicjs.jsp" flush="true"></jsp:include>
<jsp:include page="include/jquery.jsp" flush="true"></jsp:include>
 
 <script type="text/javascript">
$(function(){
  
});
 </script>
  </body>
</html>
