<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lingtoo.wechat.bean.PageBean,java.util.List"%>
<%
	PageBean pageBean = (PageBean) request.getAttribute("pageBean");
	if(pageBean!=null){
	List list=pageBean.getQueryList();
%>
	<script type="text/javascript">
	function goUrl(pageNo,pageSize){
		loading();
    	document.getElementById("pageNo").value=pageNo;
    	document.getElementById("pageSize").value=pageSize;
    	document.getElementById("mainForm").submit();
    	
	}
	</script>
		<div class="toolbarBox clearfix">
			<div class="btn-toolbar flR flL_">
				<%if(list!=null && list.size()!=0){%>
					<%if(pageBean.getPageNo()!=1){%>
				<button type="button" onclick="javascript:goUrl('1','${pageSize}');" class="btn btn-sm btn-default">首页</button>
				<button type="button" onclick="javascript:goUrl('<%=pageBean.getPageNo()-1%>','${pageSize}');" class="btn btn-sm btn-default">上一页</button>
					<%}%>
					<%int nPageNoCount=5;
			 	  	int nStartPage = pageBean.getPageNo() != 1 ? pageBean.getPageNo() - 1 : 1;
					if (nStartPage + nPageNoCount > pageBean.getPageCount() && pageBean.getPageCount() >= nPageNoCount)
						nStartPage = (pageBean.getPageCount() - nPageNoCount) + 1;
					if (pageBean.getPageCount() < nPageNoCount)
						nStartPage = 1;
					for (int j = nStartPage; j <= pageBean.getPageCount() && j < nStartPage + nPageNoCount; j++){
						if(j==nStartPage&&j>=2){%>
			 	  		<span class="btn btn-sm curT">...</span>
						<%}
						if (j == pageBean.getPageNo()){%>
			    		<span class="btn btn-sm curT page1"><%=j%></span>            
					<%}else{ %>
			    	<button onclick="javascript:goUrl('<%=j%>','${pageSize}')" type="button" class="btn btn-sm btn-default"><%=j%></button>

			 	  <%} if(j<pageBean.getPageCount()&&j==nStartPage + nPageNoCount-1){%>
			 	  		<span class="btn btn-sm curT">...</span>
					<%}} %>
				
				 <%}%>
			    <%if(!pageBean.isLastPage()){%> 
				<button type="button" onclick="javascript:goUrl('<%=pageBean.getPageNo()+1%>','${pageSize}');" class="btn btn-sm btn-default">下一页</button>
				<button type="button" onclick="javascript:goUrl('<%=pageBean.getPageCount()%>','${pageSize}');" class="btn btn-sm btn-default" >尾页</button>
	            <% } %>
				<%if(list!=null && list.size()!=0){%>
				<span class="btn btn-sm">共&nbsp;<%=pageBean.getRowCount()%>&nbsp;行记录</span>
				<%} %>
			</div> 
		</div>                  
 <%} %>	          
 	             