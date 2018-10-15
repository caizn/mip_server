<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
</head>
  <body id="channel7-3" class="bgf5f5f5">
    <div class="content">
	  <jsp:include page="../include/left.jsp" flush="true"></jsp:include> 
	  
      <!--右边结束 -->
        <!--开始：右侧内容-->
        <div class="col-main psR">

          <!-- main start-->
          <div class="ofAT main">
	  		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
			<c:if test="${fn:length(pageBean.queryList)!=0 }">
            <div class="main-box mgT20 pdT10">                      
              <div class="flL_ clearfix">
                <form action="list" class="wp100 mgT10_ flL_ mgL20_ psR_" id="mainForm">
					<input type="hidden" name="pageNo" id="pageNo" value="${param.pageNo}" /> 
					<input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize}" />
	                <div class="col_2">
	                  <input type="text" class="form-control" placeholder="请输入管理员名称" name="name" value="${param.name }">
	                </div>
	                <div class="col_2 psR">
	                    <input type="text" class="form-control date_picker" name="beginDate" placeholder="开始时间" value="${param.beginDate }">
	                </div>
	                <div class="flL pdT7">-</div>
	                <div class="col_2 psR">
	                  <input type="text" class="form-control date_picker" name="endDate" placeholder="结束时间" value="${param.endDate }">
	                </div>
	                
	                <input class="btn bg5585c2-7-6" type="submit" value="搜索">  
                </form>
              </div>

              <div class="mgT20">
                  <table class="table txtC td1-150 td2-150 td3-L la1-190">
                      <tr class="bgf2f3f7">
                        <td>管理员ID</td>
                        <td>时间</td>
                        <td>描述</td>
                        <td>操作</td>                        
                      </tr>
	                  <c:forEach items="${pageBean.queryList}" var="item"  varStatus="index">
	                      <tr>          
	                        <td>${item.managerName }</td>
	                        <td><fmt:formatDate value="${item.createTime}" type="both" /></td>
	                        <td><span class="dpIB lh17 lsp1">${item.remark }</span></td>
	                        <td>
	                        	<c:if test="${item.operateType==1 }">添加
	                        	</c:if>
	                        	<c:if test="${item.operateType==2 }">删除
	                        	</c:if>
	                        	<c:if test="${item.operateType==3 }">更新
	                        	</c:if>
	                        	<c:if test="${item.operateType==4 }">审核
	                        	</c:if>
	                        </td>                        
	                      </tr> 
                      </c:forEach>                                        
                  </table>
              </div>
	  		<jsp:include page="../include/page.jsp" flush="true"></jsp:include>

            </div>
			</c:if>


			<c:if test="${fn:length(pageBean.queryList)==0 }">
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
          <!-- main end-->

        </div>
        <!--结束：右侧内容-->
	  
  </div><!--E .content-box-->
  <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
  <jsp:include page="../include/publicjs.jsp" flush="true"></jsp:include>
 <script type="text/javascript">
 $(function(){
	  $('.date_picker').date_input();
	  //弹出框
	$(document).delegate('.delete','click',function(){
		var id=$(this).attr("val");
		  $.confirm({
		    title: false,
		    content: '确定要删除!',
		    confirmButton: '确定',
		    cancelButton: '取消',
		    columnClass: 'col_4 mgAT',
		    confirm: function(){
		    	$.post("delete",{
		    		announcementId:id
		    	},function(data){
		    		layer_success(data);
		    	})
		      //确定删除
		    },
		    cancel: function(){
		        //取消
		    }
		  });
		});
	});

	function goBack(){
		location.reload();
	}

 </script>
</body>
</html>