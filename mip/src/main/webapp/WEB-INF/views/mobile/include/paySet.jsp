、<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>

<script src="${ctx}/style-mobile/js/wxpay/md5.js"></script>
<script src="${ctx}/style-mobile/js/wxpay/sha1.js"></script> 
<script src="${ctx}/style-mobile/js/wxpay/payV3.js"></script> 
<script>
$.ajaxSetup({async: false});
function loadPayShow(){
	if($("#paytype").val()=="wechat"){
		var ua = window.navigator.userAgent;
		var begin = ua.lastIndexOf("MicroMessenger/");
		ua=ua.substring(begin+15,begin+18);
		if(parseFloat(ua)<=5.0||!check_validate(ua)){
			$.remind("提示","对不起，您的浏览器不符合微信支付要求！",2000,function(){
				history.back();
			});
		}
	}else if($("#paytype").val()=="alipay"){
	}
}

function loadAlipayShow(){
	$('#buy_btn').on("click",
			function(e) {
				var tradeno;
				$.get(
					"pay/WxPayV2OrderServlet",{},
					{"type":"save"},
					function(data){
						tradeno=data;
						$("#trade_no").val(tradeno);
					}
				);
				location.href="AliPayJumpServlet";
	});
}

function loadPayResult(){
	$('#finish_btn').on("click",
		function(e){
				location.href=$("#data").val();
		}
	);
	var out_trade_no = $("#out_trade_no").text();
	var paytype = $("#paytype").val();
	var status = $("#status").val();
	var total_fee = $("#total_fee").val();
	var url="pay/PayFinishServlet";
	if($("#paytype").val()=="wechat")
		url="../"+url;
	$.post(
			url,
			{
				"out_trade_no" : out_trade_no,
				"paytype" : paytype,
				"status" : status,
				"total_fee" : total_fee
			},
			function(data) {
				$("#data").val(data);
			}
	);
}

document.addEventListener('WeixinJSBridgeReady',
		function onBridgeReady() {
			//公众号支付
			$('#buy_btn').on("click",
			function(e) {
				var tradeno;
				$.post(
					"../pay/unified-order",
					{"openid":$("#openid").val(),
						"ip":$("#ip").val(),
						"totalFee":$("#totalFee").val(),
						"dOrderId":$("#orderId").val(),
						"payType":$("#payType").val()},
					function(data){
						//	alert(data);
						var obj=eval("("+data+")");
						if(obj.return_code=="SUCCESS"){
							$("#trade_no").val(obj.out_trade_no);
							$("#prepay_id").val(obj.prepay_id);
						}else{
							alert("下订单错误，请与管理员联系");
							location.href="WxPayV3ResultServlet?type=deal&status=failure&tradeno="+$("#trade_no").val()+"&error="+res.err_msg;
						}
					}
				);
				WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId": getAppId(),
					//公众号名称，由商户传入
					"timeStamp": getTimeStamp(),
					//时间戳
					"nonceStr": getNonceStr(),
					//随机串
					"package": getPackage(),
					//扩展包
					"signType": getSignType(),
					//微信签名方式:1.md5
					"paySign": getSign() //微信签名
				},
				function(res) {
					//alert(JSON.stringify(res)+getAppId());
					if (res.err_msg == "get_brand_wcpay_request:ok") {
						$("#payingshow").css("visibility","visible");
						checkPay($("#trade_no").val());
					}else {
		            	location.href="order-detail?dOrderId="+$("#orderId").val();
					}
					// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
					//因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面。
				});

			});
			WeixinJSBridge.log('yo~ ready.');
		},false);


function check_validate(value){
    //定义正则表达式部分
    var reg = /^\d+(\.\d+)?$/;
    if(!reg.test(value)) return false;
    else return true;
}
</script>