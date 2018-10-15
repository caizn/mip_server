<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>踢踢青训</title>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
	<link rel="stylesheet" href="${ctx}/style-backend/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
  <body id="channel7-1">
    <div class="content">
	  <jsp:include page="../include/left.jsp" flush="true"></jsp:include>
	  

      <!--右边结束 -->
        <!--开始：右侧内容-->
        <div class="col-main psR">

          <!-- main start-->
          <div class="ofAT main">
	  		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>

              <form  action="role-add?act=add" method="post" id="mainForm" name="mainForm"   class="main-box mgT20 pdT10 mgTB15_">                      

              <div class="mgL20 clearfix">
                <a href="role-list" class="cl38a9d1">返回</a>                
              </div>
              <div class="clearfix flL_">
                <span class="col_2 h35 lh35 txtR mgR15">角色名称</span>
                <div class="col_5">
                  <input type="text" class="form-control" name="name" id="name" datatype="*" placeholder="请输入角色名称" value="${role.name}">
                </div>
              </div>
            
              <div class="clearfix flL_">
                  <span class="col_2 h35 lh35 txtR mgR15">角色描述</span>
                  <div class="col_5">
                      <textarea class="form-control resizeN"  name="remark"  rows="3" cols="20" placeholder="请输入角色描述">${role.remark}</textarea>
                  </div>
              </div>
                <div class="clearfix flL_">
                  <span class="col_2 h35 lh35 mgR15 txtR">权限配置</span>
                  <div class="col_5">
                      <div class="zTreeDemoBackground left">
							<ul id="rightTree" class="ztree"></ul>
					</div>
                  </div>
                </div>
              <div class="col_7 txtR">
                <input type="hidden" name="rights" id="rights" value="">
              	<input type="hidden" name="roleId" id="roleId" value="${role.roleId}">
                <input type="submit" class="btn btn-default btn1" value="确认"/>
              </div>

            </form>


          </div>  
          <!-- main end-->

        </div>
        <!--结束：右侧内容-->
  </div><!--E .content-box-->
	  
	</div>
  </body>
</html> 
  <jsp:include page="../include/publicjs.jsp" flush="true"></jsp:include>
  <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
<script src="${ctx}/style-system/js/initParam.js"></script>
<script src="${ctx}/style-system/js/region.js"></script>
	<script type="text/javascript" src="${ctx}/style-backend/js/zTree/js/jquery.ztree.core-3.5.js"></script>
 	<script type="text/javascript" src="${ctx}/style-backend/js/zTree/js/jquery.ztree.excheck-3.5.js"></script>

<script type="text/javascript">
function goBack(){
		window.location.href="role-list";
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
          
		}
		
$(function(){
	$("#mainForm").Validform({
		ignoreHidden:false,
		tiptype:function(msg){
			layer_alert(msg);
		},
		tipSweep:true,
		callback:function(){
			submitF();
			$(".wbtn-submit").html("保存中");
			$(".wbtn-submit").attr("disabled","disabled");
		}
	});
})		
		
</script>
