<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
  <head>
   <jsp:include page="../include/style.jsp" flush="true"></jsp:include>
  </head>
  <body id="channel7-2">
    <div class="content">
      <div class="content-box">
        <!---菜单开始 -->
        <jsp:include page="../include/left.jsp" flush="true"></jsp:include>
        <!-- 菜单结束 -->
        <div class="col-main ofHD psR">
        	<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
          <!-- main start-->
          <div class="main-box mgT20 pdT10 mgTB15_" >
              <div class="mgL20 clearfix">
                <a href="lists" class="cl38a9d1">返回</a>                
              </div>
              <form action="add?act=add" method="post" id="mainForm" name="mainForm"  class="pdB40 mgTB15_ " enctype="multipart/form-data">
                 
	            <div class="clearfix flL_">
	                <span class="col_2 h35 lh35 txtR mgR15">头像</span>
	                <div class="col_5">
	                  <div class="img-box1 curP" onclick="document.getElementById('bannerPath').click();">
	                  	<img src="<d:image srcBak="${ctx }/style-backend/images/16-10-13.jpg" src="${manager.logoPath}"/>">
	                  </div>
	                  <input type="hidden" name="managerId" value="${manager.managerId}" id="managerId" style="display: none;">
	                    <input type="hidden" name="bannerFilePath" value="${manager.logoPath}" id="bannerFilePath" style="display: none;">
				        <input type="file"  accept="image/jpg,image/jpeg,image/png" name="bannerPath" id="bannerPath" style="display: none;" onchange="handleFiles(this)"/>
	                  <p class="cle5e5e5 txtC">头像仅支持JPG, GIF,PNG格式，文件最大5MB</p>
	                </div>
	              </div>
                 <div class="clearfix flL_">
                  <span class="col_2 h35 lh35 txtR mgR15">角色选择</span>
                  <div class="col_5">
                      <select name="roleId" class="form-control">
                        <c:forEach items="${roles}" var="item"  varStatus="index">
                        	<c:if test="${item.roleId==manager.roleId }">
                  				<option value="${item.roleId }" selected>
							</c:if>
                        	<c:if test="${item.roleId!=manager.roleId }">
                  				<option value="${item.roleId }">
							</c:if>
                  				${item.name }
                  			</option>
                      	</c:forEach>
                  	</select>
                  </div>
                </div>
                 <div class="clearfix flL_">
                  <span class="col_2 h35 lh35 txtR mgR15">登录账号</span>
                  <div class="col_5">
                      <input type="text" datatype="s3-20" nullmsg="请输入登录账号！" value="${manager.account }"  errormsg="登录账号不能小于3个字符或大于20个字符" style="ime-mode:disabled;"  maxlength="20"  class="form-control" name="account" id="account" value="${param.account}" placeholder="请输入您的登录账号">
                  </div>
                </div>
                 <div class="clearfix flL_">
                  <span class="col_2 h35 lh35 txtR mgR15">真实姓名</span>
                  <div class="col_5">
                      <input type="text" datatype="*" nullmsg="请输入真实姓名！" value="${manager.realName }" maxlength="20" class="form-control" name="realName" id="realName" value="${param.realName}" placeholder="请输入您的真实姓名">
                  </div>
                </div>
                 <div class="clearfix flL_">
                  <span class="col_2 h35 lh35 txtR mgR15">手机号</span>
                  <div class="col_5">
                      <input type="text" datatype="m" errormsg="手机号码格式错误！" value="${manager.phone }" nullmsg="请输入手机号码！" maxlength="11"  class="form-control" name="phone" id="phone" value="${param.phone}" placeholder="请输入您的手机号">
                  </div>
                </div>
                 <div class="clearfix flL_">
                  <span class="col_2 h35 lh35 txtR mgR15">邮箱</span>
                  <div class="col_5">
                      <input type="text" datatype="e" errormsg="邮箱地址格式错误！" value="${manager.email }" nullmsg="请输入邮箱地址！" maxlength="20"  class="form-control" name="email" id="email" value="${param.email}" placeholder="请输入您的邮箱">
                  </div>
                </div>
                 <div class="clearfix flL_">
                  <span class="col_2 h35 lh35 txtR mgR15">重设密码</span>
                  <div class="col_5">
                      <input type="password" datatype="s6-20" nullmsg="请输入登陆密码！"  errormsg="密码不能小于6个字符或大于20个字符"  maxlength="20" class="form-control" name="password" id="password" value="${param.password}" placeholder="请输入新密码">
                  </div>
                </div>
                 <div class="clearfix flL_">
                  <span class="col_2 h35 lh35 txtR mgR15">确认密码</span>
                  <div class="col_5">
                      <input type="password"  datatype="*"  recheck="password" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！"  style="ime-mode:disabled;" class="form-control" name="rePassword" id="rePassword" value="${param.rePassword}" placeholder="请输入确认密码">
                  </div>
                </div>
               
            <div class="clearfix col_7 flR_ mgLR10_ pdT20">  
                   <div class="col-md-7 clearfix flR_">                    
                    <input type="submit" class="btn btn-default btn4 clfff" value="保存">
                    </div>                  
                </div>
              </form>
           


          </div>
          <!-- main end-->
              </div> 
          </div>  

          <!-- <div class="psA b0 l0 h40 lh40 footer col-md-12 col-sm-12 col-xs-12">1111 </div> -->

        </div>
        <!--结束：右侧内容-->
        
 
 <jsp:include page="../include/publicjs.jsp" flush="true"></jsp:include>
 <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
 <jsp:include page="../include/uploadFile.jsp" flush="true"></jsp:include>
 
   
  </body>
</html>
<script type="text/javascript">
$(function(){
	$("#mainForm").Validform({
		ignoreHidden:false,
		tiptype:function(msg){
			parent.layer_alert(msg);
		},
		tipSweep:true,
		callback:function(){
			$(".wbtn-submit").html("保存中");
			$(".wbtn-submit").attr("disabled","disabled");
		}
	});
})
function dealImg(img){
		$(".img-box1").html(img);
}

function goBack(){
		window.location.href="list"
}
</script>
