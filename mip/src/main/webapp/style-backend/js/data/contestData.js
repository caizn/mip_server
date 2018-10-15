$(function() {

	$('.tabs').respTabs({
		/*切换*/
		responsive: false,
		startIndex: 0
	});

	$('.tabs-n').respTabs({
		/*切换*/
		responsive: false,
		startIndex: 0
	});


	// 排序
	$(document).delegate('.i-School', 'click',
	function() {
		var classN = $.trim($(this)[0].className);

		if (classN.indexOf('rot') != -1) {
			//  有这个字符串
			$(this).removeClass('rot');
		} else {
			$(this).addClass('rot');
			// 
		}
	});
	merchantSecoregoUrl();
	merchantFaulgoUrl();
	playerSecoregoUrl();
	playerFaulgoUrl();
	playerRecordgoUrl();
	liveInfogoUrl();
	$(document).delegate('.i8-9', 'click', function() {
		$('.modal').hide();
	});
});

function merchantSelect(areaCode,obj){
	$.post("getMerchantByAreaCode",{
 		"areaCode":areaCode
 	},function(data){
 		var dataObj=eval("("+data+")");
 		var str='<option value="-1">学校</option>';
 		$(dataObj).each(function(i,item){
 			str+="<option value='"+item.merchantId+"'>"+item.name+"</option>";
 		});
 		obj.html(str);
 	});
}

function merchantSecoregoUrl(pageNo, pageSize) {
	var provinceCode = $("#p1").val();
	var cityCode = $("#c1").val();
	var areaCode = $("#a1").val();
	if ($("#mssort").val() == '') $("#mssort").val("desc");
	$.post("contest-merchant-secore", {
		"provinceCode": $("#p1").val(),
		"cityCode": $("#c1").val(),
		"areaCode": $("#a1").val(),
		"pageNo": pageNo,
		"pageNum": 8,
		"sort": $("#mssort").val()
	},
	function(data) {
		$("#merchantSecoreCount").html(data);
		$("#searchMerchantSecoreCount").on("click",
		function() {
			merchantSecoregoUrl();
		});
		$("#mssortType").on("click",
			function() {
				if ($("#mssort").val() == "asc") {
					$("#mssort").val("desc");
				} else {
					$("#mssort").val("asc");
				}
				merchantSecoregoUrl();
			}
		);
		var obj1 = document.getElementById("p1");
		var obj2 = document.getElementById("c1");
		var obj3 = document.getElementById("a1");
		if (provinceCode != undefined) initValue(provinceCode, cityCode, areaCode, obj1, obj2, obj3); //初始化省市县
		else initValue(a1, a2, a3, obj1, obj2, obj3); //初始化省市县
	});
}

function merchantFaulgoUrl(pageNo, pageSize) {
	var provinceCode = $("#p2").val();
	var cityCode = $("#c2").val();
	var areaCode = $("#a2").val();
	if ($("#mfsort").val() == '') $("#mfsort").val("desc");
	$.post("contest-merchant-faul", {
		"provinceCode": $("#p2").val(),
		"cityCode": $("#c2").val(),
		"areaCode": $("#a2").val(),
		"pageNo": pageNo,
		"pageNum": 8,
		"sort": $("#mfsort").val()
	},
	function(data) {
		$("#merchantFaulCount").html(data);
		$("#searchMerchantFaulCount").on("click",
			function() {
				merchantFaulgoUrl();
			}
		);
		$("#mfsortType").on("click",
			function() {
				if ($("#mfsort").val() == "asc") {
					$("#mfsort").val("desc");
				} else {
					$("#mfsort").val("asc");
				}
				merchantFaulgoUrl();
			}
		);
		var obj1 = document.getElementById("p2");
		var obj2 = document.getElementById("c2");
		var obj3 = document.getElementById("a2");
		if (provinceCode != undefined) initValue(provinceCode, cityCode, areaCode, obj1, obj2, obj3); //初始化省市县
		else initValue(a1, a2, a3, obj1, obj2, obj3); //初始化省市县
	});
}

function playerSecoregoUrl(pageNo, pageSize) {
	var provinceCode = $("#p3").val();
	var cityCode = $("#c3").val();
	var areaCode = $("#a3").val();
	var sex=$("#sex3").val();
	var age=$("#age3").val();
	var merchantId=$("#m3").val();
	if ($("#pssort").val() == '') $("#pssort").val("desc");
	$.post("contest-player-secore", {
		"provinceCode": $("#p3").val(),
		"cityCode": $("#c3").val(),
		"areaCode": $("#a3").val(),
		"sex":sex,
		"age":age,
		"merchantId":merchantId,
		"pageNo": pageNo,
		"pageNum": 8,
		"sort": $("#pssort").val()
	},
	function(data) {
		$("#playerSecoreCount").html(data);
		$("#searchPlayerSecoreCount").on("click",
		function() {
			playerSecoregoUrl();
		});
		$("#pssortType").on("click",
			function() {
				if ($("#pssort").val() == "asc") {
					$("#pssort").val("desc");
				} else {
					$("#pssort").val("asc");
				}
				playerSecoregoUrl();
			}
		);
		$("#sex3").val(sex);
		$("#age3").val(age);
		var obj1 = document.getElementById("p3");
		var obj2 = document.getElementById("c3");
		var obj3 = document.getElementById("a3");
		if (provinceCode != undefined) initValue(provinceCode, cityCode, areaCode, obj1, obj2, obj3); //初始化省市县
		else initValue(a1, a2, a3, obj1, obj2, obj3); //初始化省市县
		$("#a3").on("change",function(){
			merchantSelect($("#a3").val(),$("#m3"));
		});
		$("#a3").change();
		setTimeout(function(){
			$("#m3").val(merchantId);
		}, 300);
	});
}

function playerFaulgoUrl(pageNo, pageSize) {
	var provinceCode = $("#p4").val();
	var cityCode = $("#c4").val();
	var areaCode = $("#a4").val();
	var sex=$("#sex4").val();
	var age=$("#age4").val();
	var merchantId=$("#m4").val();
	if ($("#pfsort").val() == '') $("#pfsort").val("desc");
	$.post("contest-player-faul", {
		"provinceCode": $("#p4").val(),
		"cityCode": $("#c4").val(),
		"areaCode": $("#a4").val(),
		"merchantId":merchantId,
		"sex":sex,
		"age":age,
		"pageNo": pageNo,
		"pageNum": 8,
		"sort": $("#pfsort").val()
	},
	function(data) {
		$("#playerFaulCount").html(data);
		$("#searchPlayerFaulCount").on("click",
			function() {
				playerFaulgoUrl();
			}
		);
		$("#pfsortType").on("click",
			function() {
				if ($("#pfsort").val() == "asc") {
					$("#pfsort").val("desc");
				} else {
					$("#pfsort").val("asc");
				}
				playerFaulgoUrl();
			}
		);
		$("#sex4").val(sex);
		$("#age4").val(age);
		var obj1 = document.getElementById("p4");
		var obj2 = document.getElementById("c4");
		var obj3 = document.getElementById("a4");
		if (provinceCode != undefined) initValue(provinceCode, cityCode, areaCode, obj1, obj2, obj3); //初始化省市县
		else initValue(a1, a2, a3, obj1, obj2, obj3); //初始化省市县
		$("#a4").on("change",function(){
			merchantSelect($("#a4").val(),$("#m4"));
		});
		$("#a4").change();
		setTimeout(function(){
			$("#m4").val(merchantId);
		}, 300);
	});
}

function liveInfogoUrl(pageNo, pageSize) {
	var type=$("#litype").val();
	var sort=$("#lisort").val();
	if ($("#lisort").val() == '') $("#lisort").val("desc");
	$.post("contest-live-info", {
		"pageNo": pageNo,
		"pageNum": 8,
		"sort": $("#lisort").val(),
		"type":$("#litype").val()
	},
	function(data) {
		$("#liveInfoCount").html(data);
		$("#lctdsortType").on("click",
			function() {
				if($("#litype").val()=="lccou"){
					if ($("#lisort").val() == "asc") {
						$("#lisort").val("desc");
					} else {
						$("#lisort").val("asc");
					}
				}else{
					$("#lisort").val("desc");
					$("#litype").val("lccou");
				}
				liveInfogoUrl();
			}
		);
		$("#vrtdsortType").on("click",function(){
			if($("#litype").val()=="vrcou"){
				if ($("#lisort").val() == "asc") {
					$("#lisort").val("desc");
				} else {
					$("#lisort").val("asc");
					$("#litype").val("vrcou");
				}
			}else{
				$("#lisort").val("desc");
				$("#litype").val("vrcou");
			}
			liveInfogoUrl();
		});
		
		$(document).delegate('.playBtn', 'click', function() {
			/* if($(this).attr("playUrl")==""){
				window.open($(this).attr("liveUrl"));
			}else{ */					
				$("#yulantext").html($(this).attr("titleVal"));
				$("#playhtml iframe").attr("src","../live/liveDetail?liveId="+$(this).attr("liveId"));
				var w = $('body').width();
				$('.modal').show();
				var wid = $('.modal-content').width();
				$('.modal-dialog').css('left', (w - wid) / 2 + 'px');
			/* } */
		});
		var sortString="down";
		if(sort=="asc") sortString="up";
		$("#litype").val(type);
		$("#lisort").val(sort);
		if(type=="lccou"){
			$("#lctdsortType").html("<span>评论人数</span>&nbsp;<i class=\"fa fa-caret-"+sortString+" curP ftS18 i-School\"></i>");
		}else if(type=="vrcou"){
			$("#vrtdsortType").html("<span>观看人数</span>&nbsp;<i class=\"fa fa-caret-"+sortString+" curP ftS18 i-School\"></i>");
		}else{
			$("#litype").val("vrcou");
			$("#vrtdsortType").html("<span>观看人数</span>&nbsp;<i class=\"fa fa-caret-"+sortString+" curP ftS18 i-School\"></i>");
		}
	});
}

function playerRecordgoUrl(){
	var provinceCode = $("#p5").val();
	var cityCode = $("#c5").val();
	var areaCode = $("#a5").val();
	var sex=$("#sex5").val();
	var age=$("#age5").val();
	var merchantId=$("#m5").val();
	$.post("get-player-record",{
		"provinceCode": $("#p5").val(),
		"cityCode": $("#c5").val(),
		"areaCode": $("#a5").val(),
		"merchantId":merchantId,
		"sex":sex,
		"age":age,
	},function(data){
		$("#playerRecordCount").html(data);
		 $("#searchPlayerRecordCount").on("click",function(){
			 playerRecordgoUrl();
		 });
		 $("#sex5").val(sex);
			$("#age5").val(age);
			var obj1 = document.getElementById("p5");
			var obj2 = document.getElementById("c5");
			var obj3 = document.getElementById("a5");
			if (provinceCode != undefined) initValue(provinceCode, cityCode, areaCode, obj1, obj2, obj3); //初始化省市县
			else initValue(a1, a2, a3, obj1, obj2, obj3); //初始化省市县
			$("#a5").on("change",function(){
				merchantSelect($("#a5").val(),$("#m5"));
			});
			$("#a5").change();
			setTimeout(function(){
				$("#m5").val(merchantId);
			}, 300);
	});
}