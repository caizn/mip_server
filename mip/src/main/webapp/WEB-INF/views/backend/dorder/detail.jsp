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
	  		

            <div class="main-box lh35 ftS16">
              <p class="clearfix">
                <a href="javscript:history.go(-1);" class="cl38a9d1">返回</a>
                
              </p>
              <p class="p3">订单信息</p>

              <div class="pdL30 pdB12">
                <p>订单号：${dOrder.orderNo }</p>
                <p>姓名：${dOrder.receiveName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   手机号：${dOrder.receiveMobile }
                </p>
                <p>项目：
	                    	<c:if test="${dOrder.type==1 }">安装</c:if>
	                    	<c:if test="${dOrder.type==2 }">维修</c:if>
	                    	<c:if test="${dOrder.type==3 }">清洗</c:if>
	                    	&nbsp;/&nbsp;${dOrder.subTitle }&nbsp;/&nbsp;
	                    	${dOrder.decorationItemShow }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    紧急状态：<c:if test="${dOrder.emergencyStatus==0 }">不紧急</c:if><c:if test="${dOrder.emergencyStatus==1 }">紧急</c:if></p>
                <p><c:if test="${dOrder.emergencyStatus==0 }">预约时间：<fmt:formatDate value="${dOrder.bookTime}" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                </p>
                <p>订单状态：<c:if test="${dOrder.status==-2 }">未付费</c:if>
                		<c:if test="${dOrder.status==-1 }">已取消</c:if>
						<c:if test="${dOrder.status==0 }">已缴下单费</c:if>
						<c:if test="${dOrder.status==1 }">已审核</c:if>
						<c:if test="${dOrder.status==2 }">师傅退单，等待接单</c:if>
						<c:if test="${dOrder.status==3 }">已接单</c:if>
						<c:if test="${dOrder.status==4 }">师傅上门完成订单</c:if>
						<c:if test="${dOrder.status==5 }">支付完成</c:if>
						<c:if test="${dOrder.status==6 }">已评分</c:if></p>
                <p><c:if test="${dOrder.evaluatePrice!=null }">报价：${dOrder.evaluatePrice }元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                	<c:if test="${dOrder.addPrice!=null }">师傅加价：${dOrder.addPrice }元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                	<c:if test="${dOrder.evaluatePrice!=null&&dOrder.addPrice!=null }">实付（上门费+工作费用）：${dOrder.evaluatePrice+dOrder.addPrice  }元</c:if></p>
                <p><c:if test="${dOrder.workerPrice!=null }">师傅抽成：${dOrder.workerPrice }元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                	<c:if test="${dOrder.spreadPrice!=null }">推广抽成：${dOrder.spreadPrice }元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                	<c:if test="${dOrder.remainPrice!=null }">平台抽成：${dOrder.remainPrice  }元</c:if></p>
                <p>地区：${dOrder.receiveProvince }-${dOrder.receiveCity}-${dOrder.receiveArea}-${dOrder.receiveAddress}</p>  
				<c:if test="${dOrder.remark!=null&&dOrder.remark!='' }">
                	<p>备注：${dOrder.remark }</p>
                </c:if>
                <c:if test="${dOrder.technologyEvaluate!=null }">
                <p id="tEvaluateShow">技术评分：</p>
                </c:if>
                <c:if test="${dOrder.serviceEvaluate!=null }">
                <p id="seEvaluateShow">服务态度评分：</p>
                </c:if>
                <c:if test="${dOrder.specificationEvaluate!=null }">
                <p id="spEvaluateShow">流程规范评分：</p>
                </c:if>
                <div class="clearfix " id="picShow">
                </div>
                
              </div>
                
            </div>
              <div class="col_7 txtR pdB50">
		<c:if test="${dOrder.status==0 }">
                <a href="javascript:void(0);" class="btn btn-default btn1 mgR15 offer">设置报价</a>
       </c:if>
		<c:if test="${dOrder.status!=-1 }">
                <a href="javascript:void(0);" class="btn btn-default btn1 mgR15 cancel">取消订单</a>
        </c:if>
              </div> 
	  		
	  	 </div>
	 	</div>
  </div><!--E .content-box-->

    
<jsp:include page="../include/publicjs.jsp" flush="true"></jsp:include>
<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
 
 <script type="text/javascript">
$(function(){
	offer();
	$(".cancel").on("click",function(){
		 $.confirm({
		     title: false,
		     content: '确定取消订单',
		     columnClass: 'col_4 mgAT',
		     /* content: '<input type="text" class="form-control" id="evaluatePrice" placeholder="请输入订单评估价">'+
		     		'<input type="text" class="form-control" id="dispatchPrice" placeholder="请输入师傅派单价" style="margin-top:10px;">', */
		     confirmButton: '确定',
		     cancelButton: '取消',
		     animation: 'top',
		     closeAnimation: 'scaleX',
		     confirm: function () {
		    	 $.post("set-status",{
		    		 "dOrderId":${dOrder.decorationOrderId},
		    		 "status":-1
		    	 },function(data){
	            	layer_success("取消成功");
		    	 });
		     }
		 });
	});
  	var picListString='${dOrder.picUrl}';
  	if(picListString!=""){
  	  	var picListArray=eval("("+picListString+")");
  	  	picListArray.forEach(function(item){
  	  		$("#picShow").append('<div class="col_10 mgB15 mgL20"><img src="${ctxImageServer}'+item+'"></div>');
  	  	});
  	}
  	if("${dOrder.technologyEvaluate}"!=""){
  	  	var evaluateShowHtml="";
  	  	var evaluate=parseInt("${dOrder.technologyEvaluate}");
  	  	for(var i=0;i<evaluate;i++){
  	  		evaluateShowHtml+='<i class="fa fa-star i4"></i>'
  	  	}
  	  	$("#tEvaluateShow").append(evaluateShowHtml);
  	}
  	if("${dOrder.serviceEvaluate}"!=""){
  	  	var evaluateShowHtml="";
  	  	var evaluate=parseInt("${dOrder.serviceEvaluate}");
  	  	for(var i=0;i<evaluate;i++){
  	  		evaluateShowHtml+='<i class="fa fa-star i4"></i>'
  	  	}
  	  	$("#seEvaluateShow").append(evaluateShowHtml);
  	}
  	if("${dOrder.specificationEvaluate}"!=""){
  	  	var evaluateShowHtml="";
  	  	var evaluate=parseInt("${dOrder.specificationEvaluate}");
  	  	for(var i=0;i<evaluate;i++){
  	  		evaluateShowHtml+='<i class="fa fa-star i4"></i>'
  	  	}
  	  	$("#spEvaluateShow").append(evaluateShowHtml);
  	}
});

//设置报价
function offer(){

$(document).delegate('.offer','click',function(){
  $.confirm({
     title: '设置报价',
     columnClass: 'col_4 mgAT',
     content: '<input type="text" class="form-control" id="evaluatePrice" placeholder="请输入订单评估价">'+
     		'<input type="text" class="form-control" id="dispatchPrice" placeholder="请输入师傅派单价" style="margin-top:10px;">'+
     		'<select id="workeid" class="form-control" style="margin-top:10px;">'+
				<c:forEach items="${dWorkerList}" var="item"  varStatus="index">
					'<option value="0">请选择工人</option>'+
					'<option value="${item.decorationWorkerId}">${item.name}</option>'+
				</c:forEach>
     		'</select>',
     confirmButton: '确定',
     cancelButton: '取消',
     animation: 'top',
     closeAnimation: 'scaleX',
     confirm: function () {
         var evaluatePrice = this.$b.find('input#evaluatePrice');
         var dispatchPrice = this.$b.find('input#dispatchPrice');
         if (dispatchPrice.val() == ''||evaluatePrice.val()=='') {
             return false;
         } else if(parseFloat($("#evaluatePrice").val())<parseFloat($("#dispatchPrice").val())){
        	 layer_alert("订单评估价不能低于师傅派单价");
             return false;
         }else {
        	 var workerId=null;
        	 if($("#workeid").val()!="0"){
        		 workerId=$("#workeid").val();
        	 }
             // 有输入
				$.post("set-status",{
					"dOrderId":${dOrder.decorationOrderId},
					"evaluatePrice":evaluatePrice.val(),
					"dispatchPrice":dispatchPrice.val(),
					"status":1,
					"dWorkerId":workerId
				},function(data){
					if(data=="success"){
	            		layer_success("报价成功");
					}else if(data=="1"){
						layer_success("其他管理员已经报价");
					}else if(data!="1"){
						layer_success("订单状态已经改变");
					}
				});
         }
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
