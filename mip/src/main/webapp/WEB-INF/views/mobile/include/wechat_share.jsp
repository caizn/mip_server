<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<script type="text/javascript"
	src="${ctx}/style-mobile/js/jweixin-1.1.0.js"></script>
<script>
	wx.config({
		debug : false,
		appId : '${appId}',
		timestamp : '${timestamp}',
		nonceStr : '${nonceStr}',
		signature : '${signature}',
		jsApiList : [ 'checkJsApi', 'showOptionMenu', 'onMenuShareTimeline',
				'onMenuShareAppMessage' ]
	});
	if (shareSign) {
		wx.ready(function() {
			wx.showOptionMenu();
	
			wx.onMenuShareTimeline({
				title : shareTitle, // 分享标题
				link : shareUrl, // 分享链接
				imgUrl : shareImgUrl,
				desc : shareDescription, // 分享描述
				success : function() {
					//alert(shareTitle+" "+shareUrl);
				}
			});
	
			wx.onMenuShareAppMessage({
				title : shareTitle, // 分享标题
				link : shareUrl, // 分享链接
				imgUrl : shareImgUrl,
				desc : shareDescription, // 分享描述
				success : function() {
					//alert(shareTitle+" "+shareUrl);
				}
			});
		});
	} else {
		wx.ready(function() {
			wx.checkJsApi({
				jsApiList : [ 'onMenuShareTimeline' ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
				success : function(res) {
					// 以键值对的形式返回，可用的api值true，不可用为false
					// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
				}
			});
			wx.hideOptionMenu();
		});
	}
</script>
