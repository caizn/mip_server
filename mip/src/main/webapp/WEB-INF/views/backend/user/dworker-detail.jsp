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

            <div class="main-box lh35 ftS16">
              <p class="clearfix">
                <a href="师傅管理-师傅列表.html" class="cl38a9d1">返回</a>
                <c:if test="${dWorker.auditStatus==-1 }">
                <a href="javascript:void(0);" class="cl38a9d1 flR examine">设置审核状态</a>
                </c:if>
              </p>
              <p class="p3">基本信息</p>
              <div class="pdL30 bdBddd pdB12">
                <p>姓名：${dWorker.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;昵称：${dWorker.user.nickname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：<c:if test="${dWorker.gender==0 }">男</c:if><c:if test="${dWorker.gender==1 }">女</c:if></p>
                <p>手机号：${dWorker.telephone }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态：
                    	<c:if test="${dWorker.auditStatus==-1 }">未审核</c:if>
                    	<c:if test="${dWorker.auditStatus==0 }">审核通过</c:if>
                    	<c:if test="${dWorker.auditStatus==1 }">审核未通过</c:if>
                </p>
                <p>地区：${dWorker.provinceValue }-${dWorker.cityValue }-${dWorker.areaValue }-${dWorker.address }</p>                
                <p>身份证号：${dWorker.idNumber}</p>
                <div class="clearfix flL_" id="picList">
                    <div class="col_3 mgR20"><img class="wp100" src=""></div>
                    <div class="col_3 mgR20"><img class="wp100" src=""></div>
                    <div class="col_3"><img class="wp100" src=""></div>
                </div>
                
              </div>
              <p class="p3 ">其他信息</p>
              <div class="pdL30">
                <p>购次：1</p>
                <p>奖金：11000</p>
                <div class="clearfix flL_">
                  <a href="javascript:void(0);">添加服务:</a>
                  <div class="mgL20" style="width:300px;border:1px solid #ccc;">
                    <%-- <img src="${ctx }/style-backend/images/s/1.png" alt=""> --%>
                    
                      <div class="zTreeDemoBackground left">
							<ul id="rightTree" class="ztree"></ul>
					  </div>
                  </div>
                </div>
                
              </div>
            </div>
              <div class="col_7 txtR pdB20">
                <input type="hidden" name="rights" id="rights" value="">
                <a href="javascript:submitF();" class="btn btn-default btn1">确定</a>
              </div> 
	  		</div>
	  	</div>
  </div><!--E .content-box-->

    
<jsp:include page="../include/publicjs.jsp" flush="true"></jsp:include>
<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
 
	<link rel="stylesheet" href="${ctx}/style-backend/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/style-backend/js/zTree/js/jquery.ztree.core-3.5.js"></script>
 	<script type="text/javascript" src="${ctx}/style-backend/js/zTree/js/jquery.ztree.excheck-3.5.js"></script>

 <script type="text/javascript">
$(function(){
	var picArrayString='${dWorker.idCardPicUrl}';
	var picArray=eval("("+picArrayString+")");
	$($("#picList img")[0]).attr("src","${ctxImageServer}"+picArray[0]);
	$($("#picList img")[2]).attr("src","${ctxImageServer}"+picArray[1]);
	$($("#picList img")[1]).attr("src","${ctxImageServer}"+picArray[2]);
  examine();

});

 

// 设置审核状态
function examine(){

  $(document).delegate('.examine','click',function(){
    
    $.confirm({
        title:'设置审核状态',
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

          /* var input_div = $('<div class="wp100"></div>');
          input_div.append('<input type="text" class="form-control" id="input-name1" placeholder="原因说明">'); */

          $('#fah-box').append(select_div)/* .append(input_div) */;
        },
        confirm: function () {
        	$.post("audit",{
        		"auditStatus":$("#status").val(),
        		"dWorkerId":"${param.dWorkerId}"
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

var setting = {
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};

var zNodes =${jsonTree};
$(document).ready(function(){
	$.fn.zTree.init($("#rightTree"), setting, zNodes);
});
function submitF(){
	var rights="";
	var treeObj = $.fn.zTree.getZTreeObj("rightTree");
	var nodes = treeObj.getCheckedNodes(true);
    for (var i = 0; i < nodes.length; i++) {
  		rights=rights+nodes[i].id+",";
    }
    $("#rights").val(rights);
  	
    $.post("save",{
    	dWorkerId:"${param.dWorkerId}",
    	dWorkerItemIdsString:$("#rights").val()
    },function(data){
    	layer_success("保存成功");
    });
}

 </script>
  </body>
</html>
