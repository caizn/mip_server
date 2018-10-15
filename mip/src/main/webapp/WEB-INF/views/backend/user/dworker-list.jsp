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
  <body id="channe3-1">
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
                  <input type="text" class="form-control" placeholder="请输入姓名" id="name" value="${param.name }">
                </div>

                <div class="col_2">
                  <input type="text" class="form-control" placeholder="请输入手机号" id="phone" value="${param.phone }">
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
                <table class="table txtC td1-L td1-50 td2-L la1-90 td5-wp20">
                  <tr class="bgf2f3f7">
                    <td>头像</td>
                    <td>姓名&nbsp;/&nbsp;昵称</td>
                    <td>手机号</td>
                    <td>身份证号</td>
                    <td>居住地</td>
                    <td>接单地</td>
                    <td>状态</td>
                    <td>操作</td>
                  </tr>
                  <c:forEach items="${pageBean.queryList}" var="item"  varStatus="index">
                  <tr>
                    <td><img class="img1" src="${item.user.headimgurl }" alt=""></td>
                    <td><a href="detail?dWorkerId=${item.decorationWorkerId }" class="a">${item.name }&nbsp;/&nbsp;${item.user.nickname }</a></td>
                    <td>${item.telephone }</td>
                    <td><!-- <span class="a curP carded"> -->${item.idNumber }<!-- </span> --></td>
                    <td><span class="dpIB lh17 lsp1">${item.provinceValue }-${item.cityValue }-${item.areaValue }-${item.address }</span></td>
                    <td>${item.orderReceiveAreaValue }</td>
                    <td>
                    	<c:if test="${item.auditStatus==-1 }">未审核</c:if>
                    	<c:if test="${item.auditStatus==0 }">审核通过</c:if>
                    	<c:if test="${item.auditStatus==1 }">审核未通过</c:if>
                    </td>
                    <td>
                    	<c:if test="${item.auditStatus==-1 }">
                    		<a href="javscript:void(0);" class="a examine" data-id="${item.decorationWorkerId }">审核</a>
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
   carded();  //查看身份证

 });
 //查看身份证
 function carded(){
   $(document).delegate('.carded','click',function(){
     $.confirm({
         title: '身份证信息',
         columnClass: 'col_8 mgAT',
         content: '<div id="sfz-box"></div>',
         onOpen: function(){
           var p1=$('<p>姓名：张三&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：男</p>');
           var p2=$('<p>身份证号：154285844444444444</p>');
           var p3=$('<p class="cl848484">上半身正面照</p>');
           var p4=$('<p class="cl848484">身份证正面照</p>');
           var p5=$('<p class="cl848484">身份证背面照</p>');
           var p6=$('<p>电话号码：1526542512&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;紧急联系号码：00-00000</p>');

           var img1=$('<div class="col_5 mgAT"><img src="images/s/2.png"></div>');
           var img2=$('<div class="col_5 mgAT"><img src="images/s/4-21.png"></div>');
           var img3=$('<div class="col_5 mgAT"><img src="images/s/12.jpg"></div>');
           $('#sfz-box').append(p1).append(p2).append(p6);
           $('#sfz-box').append(p3).append(img1);
           $('#sfz-box').append(p4).append(img2);
           $('#sfz-box').append(p5).append(img3);
         },
         confirmButton: '确定',
         cancelButton: '取消',
         animation: 'top',
         closeAnimation: 'scaleX',
         confirm: function () {
             console.log('confirm');
         }
     });
   });
 }



 // 设置审核状态
 function examine(){

   $(document).delegate('.examine','click',function(){
     var dWorkerId=$(this).attr("data-id");
     // var td = $(this).parents('tr').find('td');
     // var name = $.trim($(td[0]).text());
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

          /*  var input_div = $('<div class="wp100"></div>');
           input_div.append('<input type="text" class="form-control" id="input-name1" placeholder="原因说明">'); */

           $('#fah-box').append(select_div)/* .append(input_div) */;
         },
         confirm: function () {
            $.post("audit",{
            	"dWorkerId":dWorkerId,
            	"auditStatus":$("#status").val()
            },function(data){
            	if(data=="success")layer_success("设置成功");
            })
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
