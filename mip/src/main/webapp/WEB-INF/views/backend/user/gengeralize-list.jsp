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
  <body id="channe2-3">
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
                  <input type="text" class="form-control" placeholder="请输入推广人姓名" name="name" value="${param.name }">
                </div>

                <div class="col_2">
                  <input type="text" class="form-control" placeholder="请输入手机号" name="phone" value="${param.phone }">
                </div>
                
                <div class="col_2">
                  <select class="form-control" name="auditStatus" id="auditStatus">
                    <option value="-2">状态</option>
                    <option value="-1">未审核</option>
                    <option value="0">审核通过</option>
                    <option value="1">审核未通过</option>
                  </select>
                </div>
              
                
      					  <input type="hidden" name="pageSize" id="pageSize" value="${param.pageSize}" />
                          <input type="hidden" name="pageNo" id="pageNo" value="${param.pageNo}" />	
                <input class="btn bg5585c2-7-6" type="submit" value="搜索">  
                </form>
              </div>

              

              <div class="pdT30 pdLR20">
                <table class="table txtC td1-L la1-90 td5-wp20">
                  <tr class="bgf2f3f7">
                    <td>推广人</td>
                    <td>联系电话</td>
                    <td>业务员</td>     
                    <td>地址</td>                    
                    <td>备注</td>
                    <td>状态</td>
                    <td>操作</td>
                  </tr>
                  <c:forEach items="${pageBean.queryList}" var="item"  varStatus="index">
                  <tr>
                    <td>${item.name }</td>
                    <td>${item.telephone }</td>
                    <td>${item.salesman }</td>
                    <td><span class="dpIB lh17 lsp1">${item.provinceValue }-${item.cityValue }-${item.areaValue }-${item.address }</span></td>
                    <td><span class="dpIB lh17 lsp1">${item.remark }</span></td>
                    <td>
                    	<c:if test="${item.auditStatus==-1 }">未审核</c:if>
                    	<c:if test="${item.auditStatus==0 }">审核通过</c:if>
                    	<c:if test="${item.auditStatus==1 }">审核未通过</c:if>
                    </td>
                    <td>
                    	<c:if test="${item.auditStatus==-1 }">
                    		<a href="javscript:void(0);" class="a examine" data-id="${item.userId }">审核</a>
                    	</c:if>
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
	$("#auditStatus").val("${param.auditStatus}");
	  examine(); //审核


	  $(document).delegate('#examine-sel','change',function(){
	    var val = $.trim($(this).val());
	    if(val=='2'){
	      // 不通过
	      $('#input-name1').show()
	    }else{
	      $('#input-name1').hide()
	    }
	  });



	});


	// 设置审核状态
	function examine(){

	  $(document).delegate('.examine','click',function(){
	    var userId=$(this).attr("data-id");
	    var td = $(this).parents('tr').find('td');
	    var name = $.trim($(td[0]).text());
	    $.confirm({
	        title:'审核&nbsp;&nbsp;'+name,
	        columnClass: 'col_5 mgAT',
	        content: '<div id="fah-box" class="clearfix"></div>',
	        confirmButton: '确定',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        onOpen: function(){
	          var select_div = $('<div class="wp100 mgB10"></div>');
	          var select = $('<select class="form-control" id="status"></select>');
	          select.append('<option value="0">通过</option>');
	          select.append('<option value="1">不通过</option>');
	          select_div.append(select);

	          var input_div = $('<div class="wp100"></div>');
	          input_div.append('<input type="text" class="form-control dpN" id="input-name1" placeholder="原因说明">');

	          $('#fah-box').append(select_div).append(input_div);
	        },
	        confirm: function () {
	        	$.post("audit-user",{
	        		"auditStatus":$("#status").val(),
	        		"userId":userId
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
