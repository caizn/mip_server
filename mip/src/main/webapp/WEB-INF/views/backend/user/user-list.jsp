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
	<jsp:include page="../include/left.jsp" flush="true"></jsp:include>  <!--右边结束 -->
        <!--开始：右侧内容-->
        <div class="col-main psR">

          <!-- main start-->
          <div class="ofAT main">
	  		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
            <c:if test="${pageBean.queryList.size()>0}">
            <div class="main-box mgT20 pdT10">
		  		<c:if test="${introducer!=null }">
			  		<div class="pdL30 bdBddd pdB12" style="    border-color: #fff;">
			  			${introducer.name }的客户
			  		</div>
		  		</c:if>                 
              <div class="flL_ clearfix">
                <form action="list" class="wp100 mgT10_ flL_ mgL20_ psR_" id="mainForm">

                <%-- <div class="col_2">
                  <input type="text" class="form-control" placeholder="请输入姓名">
                </div> --%>
                <div class="col_2">
                  <input type="text" class="form-control" placeholder="请输入昵称" name="nickname" value="${param.nickname }">
                </div>
                
                <div class="col_2">
                  <select class="form-control" name="managerSign" id="managerSign">
                    <option value="-1">角色属性</option>
                    <option value="0">普通用户</option>
                    <option value="1">管理员</option>
                  </select>
                </div>
              
      					  <input type="hidden" name="introducerId" id="introducerId" value="${param.introducerId}" />
      					  <input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize}" />
                          <input type="hidden" name="pageNo" id="pageNo" value="${param.pageNo}" />	
                <input class="btn bg5585c2-7-6" type="submit" value="搜索">  
                </form>
              </div>

              <div class="pdT30 pdLR20">
                <table class="table txtC td1-L td1-50 td2-L la1-90 la2-240">
                  <tr class="bgf2f3f7">
                    <td>头像</td>
                    <td>姓名&nbsp;/&nbsp;昵称</td>
                    <td>钱包</td>
                    <td>积分</td>
                    <td>角色</td>
                    <td>其他</td>
                    <td>操作</td>
                  </tr>
                  <c:forEach items="${pageBean.queryList}" var="item"  varStatus="index">
                  <tr>
                    <td><img class="img1" src="${item.headimgurl }" alt=""></td>
                    <td>${item.name }&nbsp;/&nbsp;${item.nickname }</td>
                    <td>${item.money }</td>
                    <td>${item.integral }</td>
                    <td>
                    <c:if test="${item.managerSign==0||item.managerSign==null }">普通用户</c:if>
                    <c:if test="${item.managerSign==1 }">管理员</c:if>
                    </td>
                    <td>
                      <a href="订单管理-订单列表.html" class="a">订单</a>/
                      <a href="list?introducerId=${item.userId }" class="a">客户</a>/
                      <a href="user-address?userId=${item.userId }" class="a">地址</a>/
                      <a href="../withdraw/list?userId=${item.userId }" class="a">提现</a>
                    </td>
                    <td><a href="javscript:void(0);" class="a mySet" data-id="${item.userId }">设置</a></td>
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
	$("#managerSign").val("${param.managerSign}");
	  mySet();
	});



	// 设置审核状态
	function mySet(){

	  $(document).delegate('.mySet','click',function(){
	    var userId=$(this).attr("data-id");
	    $.confirm({
	        title:'设置角色属性',
	        columnClass: 'col_5 mgAT',
	        content: '<div id="fah-box" class="clearfix"></div>',
	        confirmButton: '确定',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        onOpen: function(){
	          var select = $('<select class="form-control" id="managerSign1"></select>');
	          select.append('<option value="0">普通用户</option>');      
	          select.append('<option value="1">管理员</option>');

	          $('#fah-box').append(select);
	        },
	        confirm: function () {
	            var managerSign=$("#managerSign1").val();
	            $.post("set-manager-sign",{
	            	"userId":userId,
	            	"managerSign":$("#managerSign1").val()
	            },function(data){
	            	if(data=="success"){
	            		layer_success("设置成功");
	            	}
	            });
	        }
	    });

	  });
	}

	var goBack=function(){
		location.reload();
	}

 </script>
  </body>
</html>
