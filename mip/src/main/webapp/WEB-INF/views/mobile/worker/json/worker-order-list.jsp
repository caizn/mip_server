<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>

<c:if test="${fn:length(orderList)!=0 }">
    <c:forEach items="${orderList}" var="item"  varStatus="index">
		<div class="order-sg">
			<a class="order-sg-a" href="order-detail?dOrderId=${item.decorationOrderId }">
				<p class="order-sg-p1">
					<span>订单号：</span>
					<span class="cl232323">${item.orderNo }</span>
					<c:if test="${item.emergencyStatus==1 }"><img src="${ctx }/style-mobile/images/001.png" class="img1"></c:if>
					<c:if test="${item.status==-1 }"><img src="${ctx }/style-mobile/images/002.png" class="img1"></c:if>
				</p>
				<p class="pLR clearfix">
					<span class="lf">订单金额</span>
					<span class="rig span">￥${item.evaluatePrice+item.addPrice }</span>
				</p>
				<p class="pLR clearfix">
					<span class="lf">服务内容</span>
					<span class="rig">
						<c:if test="${item.type==1 }">安装</c:if>
	                    <c:if test="${item.type==2 }">清洗</c:if>
	                    <c:if test="${item.type==3 }">维修</c:if>&nbsp;&nbsp;${item.decorationItemShow }</span>
				</p>
				<p class="pLR clearfix">
					<span class="lf">师傅信息</span>
					<span class="rig">${item.workerName }（${item.workerPhone }）</span>
				</p>
				<p class="pLR clearfix">
					<span class="lf">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</span>
					<span class="rig">
					<!-- (-1：师傅未接单时，用户取消订单；0:下单费已缴，等待管理员审核  1:管理员审核通过，等待师傅接单
   						 2:师傅退单,等待其他师傅接单  3:师傅已经接单；4：师傅上门完成订单；5：用户二次支付完成；6：用户评分完成) -->
						<c:if test="${item.status==-1 }">已取消</c:if>
						<c:if test="${item.status==0 }">已缴下单费</c:if>
						<c:if test="${item.status==1 }">已审核</c:if>
						<c:if test="${item.status==2 }">师傅退单，等待接单</c:if>
						<c:if test="${item.status==3 }">已接单</c:if>
						<c:if test="${item.status==4 }">师傅上门完成订单</c:if>
						<c:if test="${item.status==5 }">支付完成</c:if>
						<c:if test="${item.status==6 }">已评分</c:if>
					</span>
					<!-- <i class="i1">已申请退款</i> -->
				</p>
			</a>
			<div class="order-sg-bot clearfix">
				<c:if test="${item.status==3 }">
					<a href="javascript:void(0);" class="send" val="已预约" data-id="${item.decorationOrderId }">上门设置</a>
					<a href="javascript:void(0);" class="receipt" val="退单" data-id="${item.decorationOrderId }">我要退单</a>
				</c:if>
			</div>			
		</div>
	</c:forEach>
</c:if>