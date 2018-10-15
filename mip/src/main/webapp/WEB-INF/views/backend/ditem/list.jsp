<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--Company Info-->
	<jsp:include page="../include/style.jsp" flush="true"></jsp:include> 

  </head>
  <body id="channe4-1">
    <div class="content"> 
	<jsp:include page="../include/left.jsp" flush="true"></jsp:include>
        <!--开始：右侧内容-->
        <div class="col-main psR">

          <!-- main start-->
          <div class="ofAT main">
	  		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
            <div class="main-box mgT20 pdT10">
              <div class="div-box14">

                <ul class="tabs-list clearfix">
                  <li class="active" id="install"><a href="javascript:;">安装</a></li>
                  <li class="" id="repair"><a href="javascript:;">维修</a></li> 
                  <li class="" id="clear"><a href="javascript:;">清洗</a></li>                   
                </ul>
              </div>


            <%--   <div class="mgL20 clearfix pdB10">
                  <a href="javascript:void(0);" class="cl38a9d1 mgR20 flR add">添加一级服务</a>
              </div>
 --%>
            <div class="accordion-sort"  id="installBlock">
              
                <div class="sort-box clearfix bgf2f3f7">
                  <div>服务名称</div>       
                  <div>操作</div>
                </div> 
                
                <c:forEach items="${installDItemMap}" var="item"  varStatus="index">
                <div class="accordion-handle-box">
                  <div class="accordion-handle">
                    <div class="sort-box clearfix ">
                      <div><i></i><span class="dpIB lh17 lsp1">${item.key }</span></div>                      
                    </div>                  
                  </div>
                </div>              

                <div class="accordion-content">
                  <c:forEach items="${item.value}" var="itemX"  varStatus="indexX">
                  <div class="sort-box clearfix">
                    <div><span class="dpIB lh17 lsp1">${itemX.title }</span></div>                    
                    <div>
                        <a href="javascript:void(0);" class="a update" data-id="${itemX.decorationItemId }">修改名称</a>                  
                        <a href="javascript:void(0);" class="a del" data-id="${itemX.decorationItemId }">删除子分类</a>         
                    </div>
                  </div>
                  </c:forEach>
                  <p class="pdL50 pdB10"><a href="javascript:void(0);" class="a6 addChildren" data-type="1" data-sub-title="${item.key}" data-sub-type="${item.value[0].subType }">+添加子分类</a></p>                  
                </div> 
                </c:forEach>
              </div>
                   
            <div class="accordion-sort" style="display:none" id="repairBlock">
              
                <div class="sort-box clearfix bgf2f3f7">
                  <div>服务名称</div>       
                  <div>操作</div>
                </div> 
                
                <c:forEach items="${repairDItemMap}" var="item"  varStatus="index">
                <div class="accordion-handle-box">
                  <div class="accordion-handle">
                    <div class="sort-box clearfix ">
                      <div><i></i><span class="dpIB lh17 lsp1">${item.key }</span></div>                      
                    </div>                  
                  </div>
                </div>              

                <div class="accordion-content">
                  <c:forEach items="${item.value}" var="itemX"  varStatus="indexX">
                  <div class="sort-box clearfix">
                    <div><span class="dpIB lh17 lsp1">${itemX.title }</span></div>                    
                    <div>
                        <a href="javascript:void(0);" class="a update" data-id="${itemX.decorationItemId }">修改名称</a>                  
                        <a href="javascript:void(0);" class="a del" data-id="${itemX.decorationItemId }">删除子分类</a>         
                    </div>
                  </div>
                  </c:forEach>
                  <p class="pdL50 pdB10"><a href="javascript:void(0);" class="a6 addChildren" data-type="2" data-sub-title="${item.key}" data-sub-type="${item.value[0].subType }">+添加子分类</a></p>                  
                </div> 
                </c:forEach>
              </div>
                          
            <div class="accordion-sort" style="display:none" id="clearBlock">
              
                <div class="sort-box clearfix bgf2f3f7">
                  <div>服务名称</div>       
                  <div>操作</div>
                </div> 
                
                <c:forEach items="${clearDItemMap}" var="item"  varStatus="index">
                
                <div class="accordion-handle-box">
                  <div class="accordion-handle">
                    <div class="sort-box clearfix ">
                      <div><i></i><span class="dpIB lh17 lsp1"><c:if test="${item.key!='0'}">${item.key }</c:if><c:if test="${item.key=='0'}">无分类</c:if></span></div>                      
                    </div>                  
                  </div>
                </div>              

                <div class="accordion-content">
                  <c:forEach items="${item.value}" var="itemX"  varStatus="indexX">
                  <div class="sort-box clearfix">
                    <div><span class="dpIB lh17 lsp1">${itemX.title }</span></div>                    
                    <div>
                        <a href="javascript:void(0);" class="a update" data-id="${itemX.decorationItemId }">修改名称</a>                  
                        <a href="javascript:void(0);" class="a del" data-id="${itemX.decorationItemId }">删除子分类</a>         
                    </div>
                  </div>
                  </c:forEach>
                  <p class="pdL50 pdB10"><a href="javascript:void(0);" class="a6 addChildren" data-type="3" data-sub-title="${item.key}" data-sub-type="${item.value[0].subType }">+添加子分类</a></p>                  
                </div> 
                </c:forEach>
              </div>

            </div>

            <%-- <div class="main-box">
              <div class="txtC pdT20">
                  <div>
                    <img src="${ctx }/style-backend/images/12121.png" class="img1">
                  </div>
                  <p class="clc5c5c5 mgT10">没有数据！</p>
              </div>
            </div>        --%>     
	  		
	  		
	  	  </div>
	  	</div> 
  </div><!--E .content-box-->

    
<jsp:include page="../include/publicjs.jsp" flush="true"></jsp:include>
<jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>
 
 <script type="text/javascript">
	$(function(){
		$(".tabs-list li").on("click",function(){
			var id=$(this).attr("id");
			$(".tabs-list li").removeClass("active");
			$(this).addClass("active");
			$("#installBlock").css("display","none");
			$("#repairBlock").css("display","none");
			$("#clearBlock").css("display","none");
			$("#"+id+"Block").css("display","");
		});
	});
	function goBack(){
		location.reload();
	}
 $(function(){

	  $('.accordion-sort').respTabs({
	      model   : 'accordions',
	      toggles : true ,
	      hidenContent : true
	  });

	  // 添加子分类
	  $(document).delegate('.addChildren','click',function(){
		  var type=$(this).attr("data-type");
		  var subType=$(this).attr("data-sub-type");
		  var subTitle=$(this).attr("data-sub-title");
	    var prev = $(this).parents('.accordion-content').prev();
	    var str = $(prev).find('span').text();

	    var input = '<input type="text" class="form-control" id="input-name1" placeholder="请输入名称">';
	    
	     $.confirm({
	        title: '添加 "'+str+'" 的子分类',
	        columnClass: 'col_4 mgAT',
	        content: input,
	        confirmButton: '确定',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        confirm: function () {
	            var input = this.$b.find('input#input-name1');
	            if (input.val() == '') {
	                return false;
	            } else {
	                // 有输入
					$.post("save",{
	            		"dItemId":0,
	            		"title":$("#input-name1").val(),
	            		"type":type,
	            		"subTitle":subTitle,
	            		"subType":subType
					},function(){
						layer_success("保存成功");
					})
	            }
	        }
	    });

	  });

	  // 添加分类
	  $(document).delegate('.add','click',function(){
	      var input = '<input type="text" class="form-control" id="input-name1" placeholder="请输入名称">';
	     $.confirm({
	        title: '添加分类',
	        columnClass: 'col_4 mgAT',
	        content:input ,
	        confirmButton: '确定',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        confirm: function () {
	            var input = this.$b.find('input#input-name1');
	            if (input.val() == '') {
	                return false;
	            } else {
	                // 有输入

	            }
	        }
	    });

	  });

	  // 修改名称-子类
	  $(document).delegate('.update','click',function(){
	     var str = $(this).parents('.sort-box').find('span').text();
	     var dItemId=$(this).attr("data-id");
	     $.confirm({
	        title: '将 " '+str+' " 修改为',
	        columnClass: 'col_4 mgAT',
	        content: '<input type="text" class="form-control" value="'+str+'" id="input-name1" placeholder="请输入名称" id="title">',
	        confirmButton: '确定',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        confirm: function () {
	            var input = this.$b.find('input#input-name1');
	            if (input.val() == '') {
	                return false;
	            } else {
	            	$.post("save",{
	            		"dItemId":dItemId,
	            		"title":$("#input-name1").val()
	            	},function(data){
	            		layer_success("名称保存成功");
	            	});
	            }
	        }
	    });

	  });

	  // 删除-子类
	  $(document).delegate('.del','click',function(){
	    var str = $(this).parents('.sort-box').find('span').text();
		var dItemId=$(this).attr("data-id");
		 $.confirm({
		      title: false,
		      content: '确定要删除 " '+str+' " ？',
		      confirmButton: '确定',
		      cancelButton: '取消',
		      columnClass: 'col_4 mgAT',
		      animation: 'top',
		      closeAnimation: 'scaleX',
		      confirm: function(){
		        //确定删除
		        $.post("delete",{
		        	"dItemId":dItemId,
		        },function(data){
		        	if(data=="success"){
	            		layer_success("删除成功");
		        	}
		        });
		      },
		      cancel: function(){
		          //取消
		      }
		    });

	  });


	  // 修改名称-父类
	  $(document).delegate('.update-par','click',function(){
	     var str = $(this).parents('.accordion-handle-box').find('.sort-box').find('span').text();
	     $.confirm({
	        title: '将 " '+str+' " 修改为',
	        columnClass: 'col_4 mgAT',
	        content: '<input type="text" class="form-control" id="input-name1" placeholder="请输入名称">',
	        confirmButton: '确定',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        confirm: function () {
	            var input = this.$b.find('input#input-name1');
	            if (input.val() == '') {
	                return false;
	            } else {
	                // 有输入

	            }
	        }
	    });

	  });

	  // 删除-父类
	  $(document).delegate('.del-par','click',function(){
	    var str = $(this).parents('.accordion-handle-box').find('.sort-box').find('span').text();
	    $.confirm({
	        title: '确定要删除 " '+str+' " ？',
	        columnClass: 'col_4 mgAT',
	        content: '<input type="text" class="form-control" id="input-name1" placeholder="删除原因">',
	        confirmButton: '确定',
	        cancelButton: '取消',
	        animation: 'top',
	        closeAnimation: 'scaleX',
	        confirm: function () {
	            var input = this.$b.find('input#input-name1');
	            if (input.val() == '') {
	                return false;
	            } else {
	                // 有输入

	            }
	        }
	    });
	  });

	});

 </script>
  </body>
</html>
