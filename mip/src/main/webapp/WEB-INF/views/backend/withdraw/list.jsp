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
  <body id="channe2-2">
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
                  <input type="text" class="form-control" placeholder="请输入昵称" name="nickname" value="${param.nickname }">
                </div>
                
                <div class="col_2">
                  <select class="form-control" name="status" id="status">
                    <option value="-2">是否已经转账</option>
                    <option value="0">是</option>
                    <option value="-1">否</option>
                  </select>
                </div>
              
      					  <input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize}" />
                          <input type="hidden" name="pageNo" id="pageNo" value="${param.pageNo}" />	
                
                <input class="btn bg5585c2-7-6" type="submit" value="搜索">  
                </form>
              </div>

              <div class="pdT30 pdLR20">
                <table class="table txtC td1-50 td2-L td1-L la1-120">
                  <tr class="bgf2f3f7">
                    <td>头像</td>
                    <td>姓名&nbsp;/&nbsp;昵称</td>
                    <td>银行</td>
                    <td>开户行</td>
                    <td>开户名</td>
                    <td>卡号</td>                    
                    <td>提现金额</td>
                    <td>余额</td>
                    <td>申请时间</td>
                    <td>是否已经转账</td>
                    <td>操作</td>
                  </tr>
                  <c:forEach items="${pageBean.queryList}" var="item"  varStatus="index">
                  <tr>
                    <td><img class="img1" src="${item.user.headimgurl }" alt=""></td>
                    <td>${item.user.name}&nbsp;/&nbsp;${item.user.nickname }</td>
                    <td>${item.bankName }</td>
                    <td>${item.bankPlace }</td>
                    <td>${item.bankCardName }</td>
                    <td>${item.bankCardCode }</td>
                    <td>${item.moneyWithdraw }</td>
                    <td>${item.moneyFinal }</td>
                    <td><fmt:formatDate value="${item.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                    	<c:if test="${item.status==-1 }">否</c:if>
                    	<c:if test="${item.status==0 }">是</c:if>
                    </td>
                    <td><c:if test="${item.status==-1 }"><a href="javscript:void(0);" class="a mySet" data-id="${item.userWithdrawId }">设置已转账</a></c:if></td>
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
	  mySet();
	});



	// 设置审核状态
	function mySet(){
	  $(document).delegate('.mySet','click',function(){
			var userWithdrawId=$(this).attr("data-id");
	    $.confirm({
	      title: false,
	      content: '确定已经转账了？',
	      confirmButton: '确定',
	      cancelButton: '取消',
	      columnClass: 'col_4 mgAT',
	      animation: 'top',
	      closeAnimation: 'scaleX',
	      confirm: function(){
	        //确定删除
	        $.post("set-status",{
	        	"uWithdrawId":userWithdrawId,
	        	"status":0
	        },function(data){
	        	if(data=="success"){
            		layer_success("设置成功");
	        	}
	        });
	      },
	      cancel: function(){
	          //取消
	      }
	    });
	  });

	}

function goBack(){
	location.reload();
}
 </script>
  </body>
</html>
