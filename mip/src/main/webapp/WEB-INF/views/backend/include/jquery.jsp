<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
//显示加载进度
var loading_id;
function loading(){
	loading_id=layer.load(1,{
    shade: [0.1,'#222'] //0.1透明度的白色背景
	});
    
}

var subForm=document.getElementById('mainForm');
function del(url){
 	confirmF(url,"你确定要删除吗？");
}
function confirmF(url,msg){
    layer.confirm(msg, {icon: 3}, function(index){
        layer.close(index);
        subForm.action=url;
        subForm.submit();
    });
}

function layer_success(msg){
	var ii=top.layer.msg(msg,{icon: 1,shade: [0.1,'#222']});
	 setTimeout(function(){
        layer.close(ii);
        goBack();
    }, 800);
}
function layer_alert(msg){
   layer.alert(msg, {icon: 5});
}

var layer_show_id;
function layer_show(w,h,title,url){
	layer_show_id=top.layer.open({
        type: 2,
        title: title,
        maxmin: true,
        shadeClose: false, //点击遮罩关闭层
        area: [w+'px', h +'px'],
        content: url
    });
}
function layer_close(){
   layer.close(layer_show_id);
}
</script>
<c:if test="${msg!=null&&msg!=''}">
<script type="text/javascript">
$(function(){
	layer_alert('${msg}');
});
</script>
</c:if>
<c:if test="${success!=null&&success!=''}">
<script type="text/javascript">
$(function(){
	layer_success('${success}');
});
</script>
</c:if>