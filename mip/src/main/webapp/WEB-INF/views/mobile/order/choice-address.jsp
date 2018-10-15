<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
    <title>下订单</title>
</head>
<body>
	
<div id="onload-X" style="text-align: center;padding:30px">
	<img src="${ctx }/style-mobile/images/onload.gif" style="width:25px;">	
</div>
<div class="wrap pdTp40" style="  
    position: fixed;
    z-index: 10000;
    background: rgba(255,255,255,0.9);
    width: 100%;
    height: 100%;
    visibility: hidden;" id="payingshow">

	<p class="txtC" style="margin-top: 60px;"><img src="${ctx }/style-mobile/images/onLoadImgs.gif" style="width: 30px;"></p>
	<p class="txtC pdT10" style="margin-top: 20px;">订单支付确认中，请稍后......</p>


</div>	
<div class="wrap ">
	<div class="bgfff">
		<p class="p2">联系信息</p>
        <c:if test="${userAddress == null}">
	        <div class="place-oder-top clearfix">
	            <p class="p14"><img src="${ctx }/style-mobile/images/36.png" alt=""><span>添加地址</span></p>
	        </div>
        </c:if>
        <c:if test="${userAddress != null}">
            <div class="place-oder-top clearfix" id="mainAddress" data-id="${userAddress.userAddressId }">
                <div class="lf"><img src="${ctx }/style-mobile/images/dizhi.png"></div>
                <div class="rig">
                    <p class="clearfix">
                        <span class="lf">收件人：${userAddress.name }</span>
                        <span class="rig">${userAddress.phone }</span>
                    </p>
                    <a href="javascript:void(0);">
                        <span>${userAddress.provinceValue }&nbsp;|&nbsp;${userAddress.cityValue }&nbsp;|&nbsp;${userAddress.areaValue }</span>
                        <img src="${ctx }/style-mobile/images/tolf.png">
                    </a>
                    <p>${userAddress.address }</p>
                </div>
            </div>
        </c:if>
	</div>
	<div class="pdLR15 bgfff">
		<p class="p6 clearfix">
			<span>付款金额</span>
			<span id="visitPay">${session_merchant.visitFee }</span>
		</p>
	</div>
	<p class="p7">下单预付款可抵服务费用。师傅没接单之前取消服务，我们将原款退还。电话预报价只能当作参考，最终定价要根据实际情况为准。 客服电话：0592-6688061。</p>

	<div class="txtC pdT50">
		<a href="javascript:payOrder();" class="btn_blue btn1">马上支付</a>
	</div>

	<!-- 弹出框 地址 start -->
	<div class="address-box2" style="padding-top:40px;">
	</div>
	<!-- 弹出框 地址 end -->

	<!-- 删除提示 start -->
	<div class="promp-box">
		<div>
			<p class="promp-box-p">提示</p>
			<div class="content">确定删除此地址？</div>
			<a class="promp-box-a" href="javascript:void(0);">确定</a>
		</div>		
	</div>	
	<!-- 删除提示 end -->

	<!-- 添加地址收货框 start -->
	<div class="address-box3" style="padding-top:40px;">
        <span class="sp4 close-addr3">关闭</span>
        <div class="bgfff">
            <div class="input-box bdBf2f2f2">
                <span>收件人</span>
                <input type="text" id="name" value="${userAddress.name }">
            </div>
            <div class="input-box bdBf2f2f2">
                <span>手机号</span>
                <input type="text" id="phone" value="${userAddress.phone }">
            </div>
            <div class="input-box bdBf2f2f2">
                <span>地址</span>
                <input type="text" readonly placeholder="请选择地址" id="myAddrs"
                       data-key="${userAddress.provinceCode }-${userAddress.cityCode}-${userAddress.areaCode}"
                       value="${userAddress.provinceValue } ${userAddress.cityValue} ${userAddress.areaValue}"/>
            </div>
            <div class="input-box">
                <span>详细地址</span>
                <input type="text" id="address" value="${userAddress.address }">
                <input type="hidden" id="userAddressId" value="">
            </div>
        </div>
        <a href="javascript:saveAddress();" class="a3 confirm-addr" >确定</a>
	</div>
	<!-- 添加地址收货框 end -->


</div>
	<div style="display:none">
		<%
			String ip=request.getRemoteAddr();
		%>
		<input id="ip" type="hidden" value="<%=ip%>">
		<input id="openid" type="hidden" value="${session_user.openid }">
		<input id="name" type="hidden" value="订单支付">
		<input id="appid" type="hidden" value="${paySet.appid}">
		<input id="appsecret" type="hidden" value="${paySet.appsecret }">
		<input id="partnerkey" type="hidden" value="${paySet.partnerkey }">
		<input id="trade_no" type="hidden" value="">
		<input id="prepay_id" type="hidden" value="">
		<input id="total" type="hidden" value="">
		<input id="mch_id" type="hidden" value="${paySet.partnerid }">
		
		<input id="totalFee" type="hidden">
		<input id="orderId" type="hidden">
		<input id="payType" type="hidden" value="visit">
		<button id="buy_btn" style="display:none"></button>
	</div>
<jsp:include page="../include/address.jsp" flush="true"></jsp:include>
<jsp:include page="../include/paySet.jsp" flush="true"></jsp:include>
<script type="text/javascript">
$(function(){	
	address();
    //check();
    cNAddrArr();
});


// 地址
function address(){

    reloadAddr();
	//  打开地址选择框
	$('.wrap').on('click','.place-oder-top',function(e){ 
		$('.address-box2').show();
		$(".address-box2").animate({
	        	top: "0"
	    }, 300);
	});	

	// 关闭地址
	$('.wrap').on('click','.close-addr',function(e){

        var boxSize = $(this).parents('.address-box2').find('.address-box').length;
        if(boxSize == 1){
            var obj=$(this).parents('.address-box2').find('.address-box');
            var name=obj.attr("data-name");
            var phone=obj.attr("data-phone");
            var province=obj.attr("data-province");
            var city=obj.attr("data-city");
            var area=obj.attr("data-area");
            var address=obj.attr("data-address");
            $(".place-oder-top").attr("data-id",obj.attr("data-id"));
            var html=
                    '<div class="lf">'+
                    '<img src="${ctx }/style-mobile/images/dizhi.png">'+
                    '</div>'+
                    '<div class="rig">'+
                    '<p class="clearfix">'+
                    '<span class="lf">收件人：'+name+'</span>'+
                    '<span class="rig">'+phone+'</span>'+
                    '</p>'+
                    '<a href="javascript:void(0);">'+
                    '<span>'+province+'&nbsp;|&nbsp;'+city+'&nbsp;|&nbsp;'+area+'</span>'+
                    '<img src="${ctx }/style-mobile/images/tolf.png">'+
                    '</a>'+
                    '<p>'+address+'</p>'+
                    '</div>';
            $(".place-oder-top").html(html);
        }

		$(this).parents('.address-box2').hide();
		$(this).parents('.address-box2').css('top','100%');
	});

    $('.wrap').on('click','.close-addr3',function(e){
        $(this).parents('.address-box3').hide();
    });

	// 选择地址
	$('.wrap').on('click','.cho-addr',function(e){

        $(".checkBg2").siblings('.cl6c93f7').remove();
		$(".checkBg").removeClass("checkBg2");
		$(this).find(".checkBg").addClass("checkBg2");
        $(this).append('<span class="cl6c93f7" data-id="${item.userAddressId }">默认地址</span>');
		
		var userAddressId=$(this).attr("data-id");
		var obj=$(".address-box[data-id='"+userAddressId+"']");
		var name=obj.attr("data-name");
		var phone=obj.attr("data-phone");
		var province=obj.attr("data-province");
		var city=obj.attr("data-city");
		var area=obj.attr("data-area");
		var address=obj.attr("data-address");

		var html=
				'<div class="lf">'+
					'<img src="${ctx }/style-mobile/images/dizhi.png">'+
				'</div>'+
		        '<div class="rig">'+
		        '<p class="clearfix">'+
		            '<span class="lf">收件人：'+name+'</span>'+
		            '<span class="rig">'+phone+'</span>'+
		        '</p>'+
		        '<a href="javascript:void(0);">'+
		            '<span>'+province+'&nbsp;|&nbsp;'+city+'&nbsp;|&nbsp;'+area+'</span>'+
		            '<img src="${ctx }/style-mobile/images/tolf.png">'+
		        '</a>'+
		        '<p>'+address+'</p>'+
		    '</div>';
        $("#mainAddress").attr("data-id",userAddressId);
		$("#mainAddress").html(html);

        $(this).parents('.address-box2').hide();
        $(this).parents('.address-box2').css('top','100%');

        setDefault(userAddressId);
	});

	// 删除
	$('.wrap').on('click','.del',function(event){ 
		$('body').bind("touchmove",function(e){    
			e.preventDefault();	//禁用    
		});
		$('.promp-box').show();
	});

	$(".promp-box").click(function(e){
		$("body").unbind("touchmove");
	 	$('.promp-box').hide();
	});

	// 打开添加地址按钮
	$('.wrap').on('click','.add-addr',function(event){ 
		var dataId=$(event.target).attr("data-id");
		$("#userAddressId").val(dataId);
		if(dataId!="0"){
			var obj=$(".address-box[data-id='"+dataId+"']");
			var name=obj.attr("data-name");
			var phone=obj.attr("data-phone");
			var provinceCode=obj.attr("data-province-code");
			var cityCode=obj.attr("data-city-code");
			var areaCode=obj.attr("data-area-code");
			
			var area=obj.attr("data-area");
			var province=obj.attr("data-province");
			var city=obj.attr("data-city");
			
			var address=obj.attr("data-address");
			$("#name").val(name);
			$("#phone").val(phone);
			$("#address").val(address);
			$("#myAddrs").attr("data-key",provinceCode+"-"+cityCode+"-"+areaCode);
			$("#myAddrs").val(province+" "+city+" "+area);
		}else{
			$("#name").val("");
			$("#telephone").val("");
			$("#address").val("");
			$("#myAddrs").attr("data-key","");
			$("#myAddrs").val("");
		}
		$('body').bind("touchmove",function(e){    
			e.preventDefault();	//禁用    
		});
		$('.address-box3').show();
	});

    $('.wrap').on('click','.return-addr',function(event){
        $('.address-box3').hide();
    });

	// 确认添加地址按钮 
	/*$('.wrap').on('click','.confirm-addr',function(event){
		$("body").unbind("touchmove");

		$('.address-box3').hide();
	});*/

	// 编辑地址
	$('.wrap').on('click','.edit-addr',function(event){ 
		$('body').bind("touchmove",function(e){    
			e.preventDefault();	//禁用    
		});
		$('.address-box3').show();
		
	});
}

var setDefault=function(userAddressId){
    $.post("../user/set-default",{
        "userAddressId":userAddressId
    },function(data){
        if(data=="success"){
        }
    });
}

var saveAddress=function(){
    if($("#name").val()==""){
        alert_msg("收件人不能为空");
        return;
    }
    if($("#phone").val()==""){
        alert_msg("手机号不能为空");
        return;
    }
    if($("#myAddrs").val()==""){
        alert_msg("所在地区不能为空");
        return;
    }
    if($("#address").val()==""){
        alert_msg("地址不能为空");
        return;
    }
    var addressCodeList=$("#myAddrs").attr("data-key").split("-");
    var addressList=$("#myAddrs").val().split(" ");
    $.post("../user/save-address",{
        "userAddressId":$("#userAddressId").val(),
        "name":$("#name").val(),
        "telephone":$("#phone").val(),

        "address":$("#address").val(),
        "provinceValue":addressList[0],
        "cityValue":addressList[1],
        "areaValue":addressList[2],
        "provinceCode":addressCodeList[0],
        "cityCode":addressCodeList[1],
        "areaCode":addressCodeList[2]
    },function(data){
        var dataObj=eval("("+data+")");
        if(dataObj.status=="success"){
//            alert_msg("保存成功",function(){
//                history.go(-1);
//            });
            alert_msg("保存成功");
            reloadAddr();
            $("body").unbind("touchmove");
            $('.address-box3').hide();
        }
    });
}

var reloadAddr=function(){
    $.post("user-address",{
    },function(data){
        $('.address-box2').html(data);
    });
}

var count=0;
var payOrder=function(){
	if(count!=1){
		var userAddressId=$(".place-oder-top").attr("data-id");
		if(userAddressId==undefined){
	        alert_msg("请选择地址");
			return;
		}
		count=1;
	    $.ajax({
	        type:'POST',
	        url:'save-order',
	        data:{
	        	"userAddressId":userAddressId
	        },
	        dataType:'json',
	        async:true,
	        success:function(data){
	            if(data.status=="success"){
	                alert_msg("提交成功",function(){
	                    $("#orderId").val(data.dOrderId);
	                	$("#totalFee").val(${session_merchant.visitFee });
	                    $("#buy_btn").click();
	                });
	            }
	        }
	    });
	}
}


function checkPay(tradeNo){
	setInterval(function(){
		$.post("../pay/check-pay-order",{
			"tradeNo":tradeNo
		},function(data){
			if(data=="success"){
            	location.href="order-detail?dOrderId="+$("#orderId").val();
			}
		});
		setTimeout(function(){
        	location.href="order-detail?dOrderId="+$("#orderId").val();
		}, 2500);
	}, 1000);
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