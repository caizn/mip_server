<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 
	<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include> 
	<title>师傅入驻</title>
</head>

<body>

<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:25px;">	
</div>

<div class="wrap">
		<c:if test="${dWorker.auditStatus==-1 }">
			<p class="p11">请等待审核，一般审核时间为3个工作日内。</p>
		</c:if>
		<c:if test="${dWorker.auditStatus==0 }">
			<p class="p11">您的申请已经审核通过</p>
		</c:if>
		<c:if test="${dWorker.auditStatus==1 }">
			<p class="p11">您的申请未审核通过，请重新核对您的信息</p>
		</c:if>
	<div class="bgfff">
		<p class="p2">联系信息</p>
		<p class="p8 clearfix">
			<span class="lf">姓名</span>
			<span class="rig"><input type="text" placeholder="请输入姓名" id="name" value="${dWorker.name }"></span>
		</p>
		<p class="p8 clearfix sel">
			<span class="lf">性别</span>
			<span class="rig">
				<select class="wp100 hp100" id="gender">
					<option value="-1">请选择</option>
					<option value="0">男</option>
					<option value="1">女</option>
				</select>
			</span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">手机号</span>
			<span class="rig"><input type="number" placeholder="请输入手机号" id="telephone" value="${dWorker.telephone }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">紧急号码</span>
			<span class="rig"><input type="number" placeholder="请输入紧急联系号码" id="urgencyTelephone" value="${dWorker.urgencyTelephone }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">居住地区</span>
			<span class="rig">
				<input type="text" readonly placeholder="请选择居住地址" id="myAddrs" data-key="${dWorker.provinceCode }-${dWorker.cityCode}-${dWorker.areaCode}" value="${dWorker.provinceValue } ${dWorker.cityValue} ${dWorker.areaValue}"/>
			</span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">详细地址</span>
			<span class="rig"><input type="text" placeholder="请输入详细地址" id="address" value="${dWorker.address }"></span>
		</p>
		<p class="p8 clearfix">
			<span class="lf">接单地区</span>
			<span class="rig">
				<input type="text" readonly placeholder="请选择接单地区" id="myAddrs2" data-key="${dWorker.orderReceiveAreaCode }" value="${dWorker.orderReceiveAreaValue }"/>
			</span>
		</p>
		<p class="p2">证件信息</p>
		<p class="p8 clearfix">
			<span class="lf">身份证号</span>
			<span class="rig"><input type="number" placeholder="请输入身份证号" id="idNumber" value="${dWorker.idNumber }"></span>
		</p>
		<p class="p4 pdLR15 clearfix">
			<span>证件照上传</span>
			<span id="picNum">0/3</span>
		</p>
		<p class="pdLR15 mgB10">身份证正面、身份证反面，手持身份证近照（3张缺一不可）</p>
		<div class="img-box1 clearfix pdLR15">
				<span class="sp-img-box1" onclick="$('#bannerPath').click();"><img src="${ctx }/style-mobile/images/36.png" alt=""></span>
				<div id="fileInputDiv">
					<input type="file"  accept="image/jpg,image/jpeg,image/png" name="pic" id="bannerPath" style="display: none;" onchange="handleFiles(this)"/>
                </div>
			</div>					
	</div>

	<p class="p5">请填写资料完整，否则无法通过审核</p>
	<p class="p9">
		<button class="checkBg5 checkBg6"></button>
		<span>阅读并同意</span>
		<a href="apply-notice" class="a2">《入住须知》</a>
	</p>
	
	<div class="mgT40">
		<c:if test="${dWorker==null||dWorker.auditStatus==1}">
			<a href="javascript:save();" class="btn_blue btn1">申请入驻</a>
		</c:if>
	</div>

</div>
	<jsp:include page="../include/address.jsp" flush="true"></jsp:include> 
	<script src="${ctx }/style-mobile/js/exif.js"></script>
<script type="text/javascript">
$(function(){
	var idCardPicUrl='${dWorker.idCardPicUrl}';
	if("${dWorker}"==""){
        //$("#gender").val("0");
	}else if("${dWorker.gender}".length == 0){
        $("#gender").val("0");
    }else{
        $("#gender").val("${dWorker.gender}");
    }
    //$("#gender").val("${dWorker.gender}");

	if(idCardPicUrl!=""){
		var idCardPicArray=eval("("+idCardPicUrl+")");
		
		idCardPicArray.forEach(function(item){
			var div=$('<span class="sp-img-box2" val="'+item+'">'+'</span>');
			var img=$('<img class="imgShow" src="${ctxImageServer}'+item+'">');
			$(img).addClass("ig1");
			div.append(img);
			div.append('<img class="del-ig2" src="${ctx }/style-mobile/images/20.png" alt="">');
			$(".sp-img-box1").before(div);
		});
		$(".sp-img-box1").css("display","none");
	}
})
function save(){
	if($("#name").val()==""){
		alert_msg("姓名不能为空");
		return;
	}
	if($("#gender").val()=="-1"){
		alert_msg("请选择性别");
		return;
	}
	if($("#telephone").val()==""){
		alert_msg("电话不能为空");
		return;
	}
	if($("#urgencyTelephone").val()==""){
		alert_msg("紧急电话不能为空");
		return;
	}
	if($("#myAddrs").val()==""){
		alert_msg("居住地区不能为空");
		return;
	}
	if($("#myAddrs2").val()==""){
		alert_msg("接单地区不能为空");
		return;
	}
	if($("#idNumber").val()==""){
		alert_msg("身份证号不能为空");
		return;
	}
	var picCount=$(".img-box1 span").length-1;
	if(picCount<3){
		alert_msg("请上传满3张证件照");
		return;
	}
	var idCardPicUrl="[";
	for(var i=0;i<3;i++){
		var value=$($(".img-box1 span")[i]).attr("val");
		if(value!=undefined){
			idCardPicUrl+="'"+value+"',"
		}
	}
	if(idCardPicUrl!="[")
		idCardPicUrl=idCardPicUrl.substring(0,idCardPicUrl.length-1);
	idCardPicUrl+="]";
	var newPicLength=$("#fileInputDiv input").length;
	var array=[];
	var decorationWorkerId="${dWorker.decorationWorkerId}";
	if(decorationWorkerId==""){
		decorationWorkerId=0
	}
	loading_show();
	var addressCodeList=$("#myAddrs").attr("data-key").split("-");
	var addressList=$("#myAddrs").val().split(" ");
	var orderReceiveAreaValue=$("#myAddrs2").val();
	var orderReceiveAreaCode=$("#myAddrs2").attr("data-key");
	$.ajaxFileUpload({
      	 url: "save-worker-apply",   // 提交的页面
      	 data: {
      		 "decorationWorkerId":decorationWorkerId,
      		 "name":$("#name").val(),
      		 "userId":${session_user_info.userId},
      		 "gender":$("#gender").val(),
      		 "telephone":$("#telephone").val(),
      		 "urgencyTelephone":$("#urgencyTelephone").val(),
      		 "address":$("#address").val(),
      		 "idNumber":$("#idNumber").val(),
      		 "idCardPicUrl":idCardPicUrl,
      		 "provinceValue":addressList[0],
      		 "cityValue":addressList[1],
      		 "areaValue":addressList[2],
      		 "provinceCode":addressCodeList[0],
      		 "cityCode":addressCodeList[1],
      		 "areaCode":addressCodeList[2],
      		 "orderReceiveAreaValue":orderReceiveAreaValue,
      		 "orderReceiveAreaCode":orderReceiveAreaCode
        },
        dataType: 'text',
     		 // 从表单中获取数据
      	 type: "POST",                   // 设置请求类型为"POST"，默认为"GET"
     	 secureuri:false,
		 fileElementId:array,
        success: function(data) {
        	loading_hide();
       	 if(data=="success"){
       		 alert_msg("申请成功",function(){
       			location.href=location.href+'&time='+((new Date()).getTime());
       		 });
       		 //layer_success("保存成功");
       	 }
       }
      });
}

function dealImg(img,rotate){
	var loadingPic=$('<img src="${ctx }/style-mobile/images/onLoadImgs.gif" style="height: 62px;">');
    var div=$('<span id="loadingShow" class="sp-img-box2">'+'</span>');
    loadingPic.addClass("ig1");
	div.append(loadingPic);
    $(".sp-img-box1").before(div);
    
	$.ajaxFileUpload({
    	 url: "../public/upload-pictrue",   // 提交的页面
    	 data: {
    		 "rotate":rotate
      },
      dataType: 'text',
   		 // 从表单中获取数据
    	 type: "POST",                   // 设置请求类型为"POST"，默认为"GET"
   	 secureuri:false,
		 fileElementId:["bannerPath"],
      success: function(data) {
			$("#loadingShow").remove();
    		var picCount=$(".img-box1 span").length-1;
    		
    		var div=$('<span class="sp-img-box2" data-pic-id="new'+picCount+'" val="'+data+'">'+'</span>');
    		$(img).addClass("ig1");
    		div.append("<img class='imgShow' src='${ctxImageServer}"+data+"' style='height: 62px;'>");
    		div.append('<img class="del-ig2" src="${ctx }/style-mobile/images/20.png" alt="">');


    		var index=$("#fileInputDiv").find("input").length-1;
    		$(".sp-img-box1").before(div);
    		wH();
    		picCount++;
    	    $('#picNum').text(picCount+"/3");
    		if(picCount==3){
    			$(".sp-img-box1").css("display","none");
    		}
     }
    });
}


function handleFiles(obj,type) {
    var files = obj.files,img = new Image();
    if(files.length>0){
        img.className ="bannerPic";
        /* if(files[0].size>204800){
        	layer_alert('图片不能大于200K');
        	return;
        } */
        if (window.URL) {
            //File API
            img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
            img.onload = function (e) {
                window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
            }
            
            var rotate=0;
            var Orientation = null;
            EXIF.getData(files[0], function() {  
                // alert(EXIF.pretty(this));  
                 EXIF.getAllTags(this);   
                 //alert(EXIF.getTag(this, 'Orientation'));   
                 Orientation = EXIF.getTag(this, 'Orientation');  
            	if (navigator.userAgent.match(/iphone/i)) {  
                    console.log('iphone');
                    if(Orientation != "" && Orientation != 1){  
                        //alert('旋转处理');  
                        switch(Orientation){  
                            case 6://需要顺时针（向左）90度旋转  
                            	rotate=90;
                                break;  
                            case 8://需要逆时针（向右）90度旋转  
                            	rotate=270;
                                break;  
                            case 3://需要180度旋转  
                            	rotate=180;
                                break;  
                        }         
                    }  
                }
                dealImg(img,rotate);
            });
        } else if (window.FileReader) {
            //opera不支持createObjectURL/revokeObjectURL方法。我们用FileReader对象来处理
            var reader = new FileReader();
            reader.readAsDataURL(files[0]);
            reader.onload = function (e) {
                img.src = this.result;
                dealImg(img);
            }
            dealImg(img);
        } else {
            obj.select();
            obj.blur();
            var nfile = document.selection.createRange().text;
            document.selection.empty();
            img.src = nfile;
            img.onload = function () {
            }

            dealImg(img);
            //fileList.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src='"+nfile+"')";
        }
    }
}

$(function(){	
	dealW();
	wH();
	delImg();
	checkB();
	cNAddrArr();
});	

function checkB(){
	// 同意入住
	$('.wrap').on('click','.checkBg5',function(e){ 
		var obj = $(this);
		var clas= $.trim(obj[0].className);	  
	   	if(clas.indexOf('checkBg6')!=-1){
	   		$(obj).removeClass('checkBg6');
	   	}else{
	   		$(obj).addClass('checkBg6');
	   	}
	});
}

// 删除图片
function delImg(){
	$('.wrap').on('click','.del-ig2',function(e){ 
		$(this).parents('.sp-img-box2').remove();
        var picCount=$(".img-box1 span").length-1;
        //$($("#fileInputDiv").find("input")[picCount]).remove();
        $('#picNum').text(picCount+"/3");
		$(".sp-img-box1").css("display","");
	});
}


function dealW(){
	var w_lf = parseFloat($('.p8 .lf').width());
	var w = parseFloat($('.p8').width());
	var w_rig = w-w_lf;
	$('.p8 .rig').css('width',w_rig+'px');
}

// 上传身份证 等高宽
function wH(){
	var w = $('.sp-img-box1').width();
	$('.sp-img-box1').css({'height':w+'px','line-height':w+'px'});
	//$('.sp-img-box2 .imgShow').css({'height':w+'px','line-height':w+'px'});
	$('.sp-img-box2').css({'height':w+'px','line-height':w+'px'});
}

</script>	
<script>
var shareSign=false;
var shareTitle = '';
var shareImgUrl='';
var shareUrl='';
var shareDescription='';
</script>
<jsp:include page="../include/wechat_share.jsp" flush="true"></jsp:include>
</body>
</html>