<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<span class="sp4 close-addr">关闭</span>
<c:if test="${fn:length(addressList)!=0 }">
            <c:forEach items="${addressList}" var="item"  varStatus="index">
                <div class="address-box" data-id="${item.userAddressId }" data-id="${item.userAddressId }"
                        	data-province="${item.provinceValue }" data-city="${item.cityValue }" data-area="${item.areaValue }"
                        	data-name="${item.name }" data-phone="${item.phone }" data-address="${item.address }"
                        	data-province-code="${item.provinceCode }" data-city-code="${item.cityCode }" data-area-code="${item.areaCode }">
                    <p class="clearfix">
                        <span class="lf">收件人：${item.name }</span>
                        <span class="rig">${item.phone }</span>
                    </p>
                    <p>${item.provinceValue }&nbsp;|&nbsp;${item.cityValue }&nbsp;|&nbsp;${item.areaValue }</p>
                    <p>${item.address }</p>
                    <p class="clearfix">
                        <a href="javascript:void(0);" class="lf cho-addr" data-id="${item.userAddressId }">
                            <c:if test="${item.status==1 }">
                                <span class="checkBg checkBg2"></span>
                            	<span class="cl6c93f7" data-id="${item.userAddressId }">默认地址</span>
                            </c:if>
                            <c:if test="${item.status==0 }">
                                <span class="checkBg"></span>
                            </c:if>
                        </a>
					<span class="rig">
						<a href="javascript:;" class="cl6c93f7  add-addr" data-id="${item.userAddressId }">编辑</a>
						<%-- <span class="clf73d3d del" data-id="${item.userAddressId }">删除</span> --%>
					</span>
                    </p>
                </div>
            </c:forEach>
        </c:if>

<a href="javascript:void(0);" class="a3 add-addr" data-id="0">添加新地址</a>