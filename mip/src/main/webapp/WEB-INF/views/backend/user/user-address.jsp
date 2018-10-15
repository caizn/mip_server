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
  <body id="channe2-1">
    <div class="content"> 
	<jsp:include page="../include/left.jsp" flush="true"></jsp:include> 
        <!--开始：右侧内容-->
        <div class="col-main psR">

          <!-- main start-->
          <div class="ofAT main">
	  		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
	  		

            <c:if test="${userAddressList.size()>0}">
            <div class="main-box mgT20 pdT10">                                    
              <p class="clearfix">
                <a href="javascript:history.go(-1);" class="cl38a9d1">返回</a>
    
              </p>
              <div class="pdT30 pdLR20">
                <table class="table txtC td1-L td2-L td3-L td1-150 la_Auto">
                  <tr class="bgf2f3f7">
                    <td>接受人</td>
                    <td>联系电话</td>
                    <td>地址</td>   
                  </tr>
                  <c:forEach items="${userAddressList}" var="item"  varStatus="index">
	                  <tr>
	                    <td>${item.name }</td>
	                    <td>${item.phone }</td>
	                    <td>${item.provinceValue }-${item.cityValue }-${item.areaValue }-${item.address }</td>
	                  </tr>
                  </c:forEach>           
                </table>
              </div>

            </div>
			</c:if>


            <c:if test="${userAddressList.size()<=0}">
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
  
});
 </script>
  </body>
</html>
