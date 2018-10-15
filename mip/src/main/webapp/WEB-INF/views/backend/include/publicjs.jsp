<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<script src="${ctx}/style-backend/js/jquery.js?v1.10.2.js"></script>
<script src="${ctx}/style-backend/js/jquery-1.8.3.min.js"></script>
<script src="${ctx}/style-backend/js/bootstrap.min.js"></script>
<script src="${ctx}/style-backend/js/uEditor/ueditor.config.js"></script> <!--编辑器--> 
<script src="${ctx}/style-backend/js/uEditor/ueditor.all.min.js"></script>    <!--编辑器-->
<script src="${ctx}/style-backend/js/common.js?<%=Math.random()%>"></script>  
<script src="${ctx}/style-backend/js/public.js"></script> 
<script src="${ctx}/style-backend/js/layer/layer.js"></script> 
<script src="${ctx}/style-backend/js/validform/js/Validform_v5.2.1_min.js"></script>
<script src="${ctx}/style-backend/js/jquery.date_input.pack/jquery.date_input.pack.js"></script> <!--日历-->
<script src="${ctx}/style-backend/js/DateFormat.js"></script> 
<script src="${ctx}/style-backend/js/jquery-confirm/jquery-confirm.js"></script>
<script src="${ctx}/style-backend/js/responsive.tabs.js"></script>
<script src="${ctx}/style-backend/js/ajaxfileupload.js"></script>
<script src="${ctx}/style-backend/js/jquery.date_input.pack/jquery.date_input.pack.js"></script> <!--日历-->
<script src="${ctx}/style-backend/js/jquery-confirm/jquery-confirm.js"></script>
<link href="${ctx}/style-backend/js/jquery-confirm/jquery-confirm.css" rel="stylesheet"> 
     
<script>
$.extend(DateInput.DEFAULT_OPTS, { month_names: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],  
    short_month_names: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],   
    short_day_names: ["日","一", "二", "三", "四", "五", "六"],  
    dateToString: function(date) {  
        var month = (date.getMonth() + 1).toString();//月份  
        var dom = date.getDate().toString();//日  
        if (month.length == 1) month = "0" + month;//控制月份为10一下的显示为01格式  
        if (dom.length == 1) dom = "0" + dom;//同月份  
        return date.getFullYear() + "-" + month + "-" + dom;  
      }  
    }  
);  
function layer_success(msg,icon){
	var ii=top.layer.msg(msg,{icon: icon,shade: [0.1,'#222']});
	 setTimeout(function(){
        layer.close(ii);
        goBack();
    }, 1800);
}

$(function () {
	var href=location.href;
	/* if(href.indexOf("login")<0){
	    Menu.init();
	} */
});     
</script>