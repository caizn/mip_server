$(function(){
	//var obj1 = document.getElementById("p1");
	//var obj2 = document.getElementById("c1");
	//var obj3 = document.getElementById("a1");
	//initValue('','','',obj1,obj2,obj3); //初始化省市县
	var obj2_1 = document.getElementById("p2");
	var obj2_2 = document.getElementById("c2");
	var obj2_3 = document.getElementById("a2");
	initValue(a1,a2,a3,obj2_1,obj2_2,obj2_3); //初始化省市县
	var obj3_1 = document.getElementById("p3");
	var obj3_2 = document.getElementById("c3");
	var obj3_3 = document.getElementById("a3");
	initValue(a1,a2,a3,obj3_1,obj3_2,obj3_3); //初始化省市县
	
	$("#a2").on("change",function(){
		$.post("getMerchantByAreaCode",{
	 		"areaCode":$("#a2").val()
	 	},function(data){
	 		var obj=eval("("+data+")");
	 		var str='<option value="-1">学校</option>';
	 		$(obj).each(function(i,item){
	 			str+="<option value='"+item.merchantId+"'>"+item.name+"</option>";
	 		});
	 		$("#m2").html(str);
	 	});
	});
	$("#a2").change();
	
	playerCountgoUrl();
	
	$("#a3").on("change",function(){
		$.post("getMerchantByAreaCode",{
	 		"areaCode":$("#a3").val()
	 	},function(data){
	 		var obj=eval("("+data+")");
	 		var str='<option value="-1">学校</option>';
	 		$(obj).each(function(i,item){
	 			str+="<option value='"+item.merchantId+"'>"+item.name+"</option>";
	 		});
	 		$("#m3").html(str);
	 	});
	});
	$("#a3").change();
	
	playerCountgoUrl();
		  

		  $('.tabs').respTabs({ /*切换*/
		      responsive : false,
		      startIndex:0,
		      getIndex: function(index){
		        if(index==2){
		          BarF1();
		        }
		        if(index==1){

		        }
		      }
		  });


		  // 排序
		  $(document).delegate('.i-School','click',function(){
		    var classN = $.trim($(this)[0].className);

		    if(classN.indexOf('rot')!=-1){
		      //  有这个字符串
		      $(this).removeClass('rot');
		    }else{
		      $(this).addClass('rot');
		      // 
		    }
		    
		  }) 

		  $("#playerSexBtn").on("click",function(){
			  PieF();
		  });
		  $("#playerAgeBtn").on("click",function(){
			  BarF();
		  });
		  PieF(); //饼图-基本信息
		  BarF(); //柱状图-基本信息

		  a();    // 技术考核-条形图
		});

		
		

		// 健康信息 - 
		function BarF1(){

		  var BarData1 = {
		    labels : ["运动距离","平均心率","平均速度","体能消耗","爆发力","平均负荷","速度","耐力","体能"],
		    datasets : [
		      {
		        fillColor : "rgba(155, 197, 126, 0.7)",
		        strokeColor : "rgba(155, 197, 126, 0.5)",
		        data : [62,81,68,82,65,36,43,45,53]
		      }
		    ]
		  }
		  var BarData3 = {
		    labels : ["运动距离","平均心率","平均速度","体能消耗","爆发力","平均负荷","速度","耐力","体能"],
		    datasets : [
		      {
		        fillColor : "rgba(140, 185, 226, 0.7)",
		        strokeColor : "rgba(140, 185, 226, 0.5)",
		        data : [62,81,68,82,65,36,43,45,53]
		      }
		    ]
		  }

		  var options ={
		    responsive: true,
		    barValueSpacing :20,//影响柱状块大小
		    legendTemplate : '<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
		    onAnimationComplete: function () {
		            var ctx = this.chart.ctx;
		            ctx.font = this.scale.font;
		            ctx.fillStyle = this.scale.textColor;
		            ctx.textAlign = 'center';
		            ctx.textBaseline = 'bottom';
		            this.datasets.forEach(function (dataset){
		                dataset.bars.forEach(function (bar) {
		                    ctx.fillText(bar.value, bar.x, bar.y);
		                });
		            });
		        },

		    showTooltips: false,
		    scaleShowVerticalLines: false,

		    scaleOverride:true,
		    scaleSteps     :10,
		    scaleStepWidth : 10   //y轴每个刻度的宽度
		  }; 
		    // 处理高宽
		  var w = $('.bar-box1').width();
		  var h = $('.bar-box1').height();
		  $('.bar-canvas1').attr('width',w);
		  $('.bar-canvas1').attr('height',h);

		  // 注意--数据要换
		  window.ctxBar1 = document.getElementById("Bar1").getContext("2d");
		  new Chart(ctxBar1).Bar(BarData1, options);

		  window.ctxBar2 = document.getElementById("Bar2").getContext("2d");
		  new Chart(ctxBar2).Bar(BarData1, options);

		  window.ctxBar3 = document.getElementById("Bar3").getContext("2d");
		  new Chart(ctxBar3).Bar(BarData3, options);

		  window.ctxBar4 = document.getElementById("Bar4").getContext("2d");
		  new Chart(ctxBar4).Bar(BarData3, options);
		}

		// 技术信息
		function a(){

			$.post("playerScore",{
			},function(dataString){
		  var data = eval("("+dataString+")");		        
		      var chart = new iChart.Pie3D({
		        render : 'canvasDiv',
		        data: data,
		        title : {
		          text : mname+'足球运动技能等级评定占比情况',
		          height:40,
		          fontsize : 30,
		          color : '#282828'
		        },
		        sub_option : {
		          mini_label_threshold_angle : 40,//迷你label的阀值,单位:角度
		          mini_label:{//迷你label配置项
		            fontsize:20,
		            fontweight:600,
		            color : '#ffffff'
		          },
		          label : {
		            background_color:null,
		            sign:false,//设置禁用label的小图标
		            padding:'0 4',
		            border:{
		              enable:false,
		              color:'#666666'
		            },
		            fontsize:11,
		            fontweight:600,
		            color : '#4572a7'
		          },
		          border : {
		            width : 2,
		            color : '#ffffff'
		          },
		          listeners:{
		            parseText:function(d, t){
		              return d.get('value')+"";//自定义label文本
		            }
		          } 
		        },
		        legend:{
		          enable:true,
		          padding:0,
		          offsetx:120,
		          offsety:50,
		          color:'#3e576f',
		          fontsize:20,//文本大小
		          sign_size:20,//小图标大小
		          line_height:28,//设置行高
		          sign_space:10,//小图标与文本间距
		          border:false,
		          align:'left',
		          background_color : null//透明背景
		        }, 
		        shadow : true,
		        shadow_blur : 6,
		        shadow_color : '#aaaaaa',
		        shadow_offsetx : 0,
		        shadow_offsety : 0,
		        // background_color:'#f1f1f1',
		        align:'right',//右对齐
		        offsetx:-100,//设置向x轴负方向偏移位置60px
		        offset_angle:-90,//逆时针偏移120度
		        width : 800,
		        height : 400,
		        radius:150
		      });
		chart.draw();
			});
		}
		
		

		function playerCountgoUrl(pageNo,pageSize){
			var provinceCode=$("#p1").val();
			var cityCode=$("#c1").val();
			var areaCode=$("#a1").val();
			if($("#sort").val()=='') $("#sort").val("desc");
			$.post("getPlayerCount",{
		 		"provinceCode":$("#p1").val(),
		 		"cityCode":$("#c1").val(),
		 		"areaCode":$("#a1").val(),
		 		"pageNo":pageNo,
		 		"pageNum":8,
		 		"sort":$("#sort").val()
		 	},function(data){
		 		$("#merchantCount").html(data);
		 		$("#searchMerchantPlayer").on("click",function(){
		 			playerCountgoUrl();
		 		});
		 		$("#sortType").on("click",function(){
		 			if($("#sort").val()=="asc"){
		 				$("#sort").val("desc");
		 			}else{
		 				$("#sort").val("asc");
		 			}
		 			playerCountgoUrl();
		 		});
		 		var obj1 = document.getElementById("p1");
		 		var obj2 = document.getElementById("c1");
		 		var obj3 = document.getElementById("a1");
		 		if(provinceCode!=undefined)
		 			initValue(provinceCode,cityCode,areaCode,obj1,obj2,obj3); //初始化省市县
		 		else
		 			initValue(a1,a2,a3,obj1,obj2,obj3); //初始化省市县
		 	});
		}
		
		function playerSexData(){
			$.post("playerSex",{
		 		"provinceCode":$("#p2").val(),
		 		"cityCode":$("#c2").val(),
		 		"areaCode":$("#a2").val(),
		 		"merchantId":$("#m2").val()
		 	},function(data){
		 		
		 	});
		}
		// 基本信息-饼图
		function PieF(){
			$.post("playerSex",{
		 		"provinceCode":$("#p2").val(),
		 		"cityCode":$("#c2").val(),
		 		"areaCode":$("#a2").val(),
		 		"merchantId":$("#m2").val()
			},function(data){
				 var PieData = eval("("+data+")");
				 var sex0String="",sex1String="",sexNullString="";
				 var sex0Value=0,sex1Value=0,sexNullValue=0;
				 $(PieData).each(function(i,item){
					 if(item.sex==0)
						 sex0Value=item.value;
					 else if(item.sex==1)
						 sex1Value=item.value;
					 else 
						 sexNullValue=item.value;
				 });
				 var all=sex0Value+sex1Value+sexNullValue;
				 var fixed0=0,fixed1=0,fixedNull=0;
				 if(all!=0){
					 fixed0=(sex0Value*100/all).toFixed(2);
					 fixed1=(sex1Value*100/all).toFixed(2);
					 fixedNull=(sexNullValue*100/all).toFixed(2);
				 }
				 $("#sex0").html(sex0Value+"人（"+fixed0+"%）");
				 $("#sex1").html(sex1Value+"人（"+fixed1+"%）");
				 $("#sexNull").html(sexNullValue+"人（"+fixedNull+"%）");
					 var options ={
						responsive: true,
						tooltipTemplate: "<%= label %>：<%= value %>",
						segmentShowStroke  : false
					}; 
					 // 处理高宽
					var w = $('.pie-box').width();
					var h = $('.pie-box').height();
					$('.pie-canvas').attr('width',w);
					$('.pie-canvas').attr('height',h);
					if(window.chartPie!=undefined)
						window.chartPie.destroy();
					window.ctxPie = document.getElementById("Pie").getContext("2d");
					window.chartPie = new Chart(ctxPie).Pie(PieData, options);
					piecount=1;
				  
			});
		 

		}
		// 基本信息 - 柱状图
		function BarF(){
			$.post("playerAge",{
		 		"provinceCode":$("#p3").val(),
		 		"cityCode":$("#c3").val(),
		 		"areaCode":$("#a3").val(),
		 		"merchantId":$("#m3").val()
			},function(data){
			  var BarData =  eval("("+data+")");

			  var options ={
			    responsive: true,
			    barValueSpacing :20,//柱状块大小
			    legendTemplate : '<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
			    onAnimationComplete: function () {
			            var ctx = this.chart.ctx;
			            ctx.font = this.scale.font;
			            ctx.fillStyle = this.scale.textColor;
			            ctx.textAlign = 'center';
			            ctx.textBaseline = 'bottom';
			            this.datasets.forEach(function (dataset){
			                dataset.bars.forEach(function (bar) {
			                    ctx.fillText(bar.value, bar.x, bar.y);
			                });
			            });
			        },

			    showTooltips: false,
			    scaleShowVerticalLines: false
			  }; 
			    // 处理高宽
			  var w = $('.bar-box').width();
			  var h = $('.bar-box').height();
			  $('.bar-canvas').attr('width',w);
			  $('.bar-canvas').attr('height',h);

				if(window.chartBar!=undefined)
					window.chartBar.destroy();
			  window.ctxBar = document.getElementById("Bar").getContext("2d");
			  window.chartBar = new Chart(ctxBar).Bar(BarData, options);
			});
			}