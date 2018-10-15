<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
    <title>呼叫师傅</title>
</head>
<body>

<form id="mainForm" action="choice-address" method="post" onsubmit="return ValidateValue()" enctype="multipart/form-data">
    <input type="text" id="dItemId" name="decorationItemId" hidden="hidden">
    <input type="text" id="emergencyStatus" name="emergencyStatus" hidden="hidden" value="0">

<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:25px;">	
</div>

<div class="wrap">
	<div class="call-tabs">
		<ul class="tabs-list">
			<li class="active">维修</li>
			<li>清洗</li>
			<li>安装</li>
		</ul>
		<div class="tabs-container">
			<div class="tab-content">
                <c:forEach items="${repairMap}" var="item"  varStatus="index">
                    <p class="p1 mgT15">${item.key}</p>
                    <div class="sg-good-box clearfix">
                        <c:forEach items="${item.value}" var="subItem"  varStatus="subIndex">
                            <div class="sg-good">
                                <button type="button" class="checkBg"></button>
                                <span val="${subItem.decorationItemId}">${subItem.title}</span>
                            </div>
                        </c:forEach>
                    </div>
				</c:forEach>
			</div>
			<div class="tab-content">
                <c:forEach items="${clearMap}" var="item"  varStatus="index">
                    <%--<p class="p1 mgT15">${item.key}</p>--%>
                    <div class="sg-good-box clearfix">
                        <c:forEach items="${item.value}" var="subItem"  varStatus="subIndex">
                            <div class="sg-good">
                                <button type="button" class="checkBg"></button>
                                <span val="${subItem.decorationItemId}">${subItem.title}</span>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
			</div>
			<div class="tab-content">
                <c:forEach items="${installMap}" var="item"  varStatus="index">
                    <p class="p1 mgT15">${item.key}</p>
                    <div class="sg-good-box clearfix">
                        <c:forEach items="${item.value}" var="subItem"  varStatus="subIndex">
                            <div class="sg-good">
                                <button type="button" class="checkBg"></button>
                                <span val="${subItem.decorationItemId}">${subItem.title}</span>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
			</div>
		</div>
		

	</div>

	<div class="bgfff">
		<p class="p2">情况描述</p>
		<div class="pdLR15 clearfix">
			<div class="sg-good clearfix checkBgDiv" style="width:100%;float:none">
				<button type="button" class="checkBg3"></button>
				<span>紧急叫修（立即上门）</span>
			</div>
			<div class="time-urgent">
				<%--<p class="p3">预约上门时间</p>--%>
				<input type="text" id="demo1" class="p3" name="bookTimeStr" style="width:100%;" readonly="readonly" placeholder="请选择预约上门时间">
			</div>
			<p class="p4 clearfix">
				<span>图片上传</span>
				<span id="picNum">0/3</span>
			</p>
            <div class="img-box1 clearfix pdLR15">
                <span class="sp-img-box1" onclick="$('#bannerPath').click();"><img src="${ctx }/style-mobile/images/36.png" alt=""></span>
                <div id="fileInputDiv">
                    <input type="file"  accept="image/jpg,image/jpeg,image/png" name="pic" id="bannerPath" style="display: none;" onchange="handleFiles(this)"/>
                </div>
            </div>
        </div>
		<p class="p2">简要说明</p>
		<div class="textA-box1">
			<textarea rows="5" placeholder="具体说明" id="orderRemark" name="remark"></textarea>
		</div>
	</div>
	<p class="p5">客服电话：0592-6688061</p>
	<p class="p9">
		<button type="button" class="checkBg5 checkBg6"></button>
		<span>阅读并同意</span>
		<a href="price-desc" class="a2">《收费标准》</a>
	</p>
	
	<div class="mgT40">
		<%--<a href="javascript:save();" class="btn_blue btn1">下一步</a>--%>
            <%--<a href="javascript:document:mainForm.submit();" class="btn_blue btn1">下一步</a>--%>
            <input type="submit" class="btn_blue btn1" value="下一步" />
	</div>

</div>
</form>
	<script src="${ctx }/style-mobile/js/exif.js"></script>
<script type="text/javascript">
$(function(){	

	$('.call-tabs').respTabs({
		responsive : false
	});	

	check();
	wH();
	myDatePicker();
	delImg();



});

// 删除图片
function delImg(){
	$('.wrap').on('click','.del-ig2',function(e){ 
		$(this).parents('.sp-img-box2').remove();
        var picCount=$(".img-box1 span").length-1;
        //$($("#fileInputDiv").find("input")[picCount]).remove();
        $('#picNum').text(picCount+"/3");
        if(picCount < 3){
            $(".sp-img-box1").css("display","block");
        }
	});
}

// 时间
function myDatePicker(){
	var calendar = new datePicker();
calendar.init({
	'trigger': '#demo1', /*按钮选择器，用于触发弹出插件*/
	'type': 'datetime',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
	// 'minDate':'1900-1-1',/*最小日期*/
	'minDate':new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+new Date().getDate(),/*最小日期*/
	// 'maxDate':'2100-12-31',/*最大日期*/
	'onSubmit':function(){/*确认时触发事件*/
		var theSelectData=calendar.value;
	},
	'onClose':function(){/*取消时触发事件*/
	}
});
}

// 上传图片 等高宽
function wH(){
	var w = $('.sp-img-box1').width();
	$('.sp-img-box1').css({'height':w+'px','line-height':w+'px'});
	$('.sp-img-box2').css({'height':w+'px','line-height':w+'px'});
}

// 单选
function check(){

	// 
	$('.wrap').on('click','.sg-good-box .sg-good',function(e){ 
		var obj = $(this).find('.checkBg');
		var clas= $.trim(obj[0].className);	  
	   	if(clas.indexOf('checkBg2')!=-1){
	   		$(obj).removeClass('checkBg2');
            $('#dItemId').attr("value","");
            $('#dItemId').value = "";
	   	}else{
	   		$(this).parents('.tab-content').find('.checkBg').removeClass('checkBg2');
            $(this).parents('.tab-content').siblings('.tab-content').find('.checkBg').removeClass('checkBg2');
	   		$(obj).addClass('checkBg2');
            $('#dItemId').attr("value",$(this).find("span").attr("val"));
	   	}
	});

	// 情况描述
	$('.wrap').on('click','.checkBgDiv',function(e){
		var obj = $(this).find('.checkBg3');
		var clas= $.trim(obj[0].className);	  
	   	if(clas.indexOf('checkBg4')!=-1){
	   		$(obj).removeClass('checkBg4');
	   		$('.time-urgent').show();
            $('#emergencyStatus').attr("value",0);
	   	}else{
	   		$(obj).addClass('checkBg4');
	   		$('.time-urgent').hide();
            $('#emergencyStatus').attr("value",1);
	   	}
	});

	// 同意收费标准
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

    	    var div=$('<span class="sp-img-box2" data-pic-id="new'+picCount+'">'+'</span>');
    	    $(img).addClass("ig1");
    		div.append("<img src='${ctxImageServer}"+data+"' style='height: 58px;'>");
    		div.append("<input type='hidden' name='picList' value='"+data+"'>");
    	    div.append('<img class="del-ig2" src="${ctx }/style-mobile/images/20.png" alt="">');


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

function ValidateValue()  {
    var dItemId = $("#dItemId").attr("value");
    if(!dItemId || dItemId==""){
        alert_msg("请选择服务项目");
        return false;
    }
    var obj = $('.checkBg3');
    var clas= $.trim(obj[0].className);
    var exigencyStatus = 0;
    if(clas.indexOf('checkBg4')!=-1){
        exigencyStatus = 1;
    }

    var bookTime = $('#demo1').val();
    if(!bookTime && !exigencyStatus){
        alert_msg("请选择紧急叫修或预约时间");
        return false;
    }

    var obj = $('.checkBg5');
    var clas= $.trim(obj[0].className);
    var exigencyStatus = 0;
    if(clas.indexOf('checkBg6')==-1){
        alert_msg("请须知收费标准");
        return false;
    }

//    document.mainForm.decorationItemId.value = '99';
    return true;
};
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