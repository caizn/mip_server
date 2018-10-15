var count=0;
  // 自定义规格
  function custom(){

    // 选择规格 下拉菜单
    $(".format").change(function(){
      var val = $.trim($(this).val());
      var txt = $.trim($(this).find("option:selected").text());
      var thi = $(this);
      var dataVal=$(".format option[value='"+val+"']").attr("data-val");
      if(val==110){
        // 选择了自定义

        $.confirm({
          title: '自定义规格',
          columnClass: 'col_4 mgAT',
          content: '<input type="text" class="form-control" id="input-name1" placeholder="请输入">',
          confirmButton: '确定',
          cancelButton: '取消',
          animation: 'top',
          closeAnimation: 'scaleX',
          confirm: function(){
              var input = $.trim(this.$b.find('input#input-name1').val());
              if (input == '') {
                  return false;
              } else {
                  // 有输入
            	  $.post("../property/add-property",{
            		  name:input
            	  },function(data){
                      // 改变原来的
                      $(thi).find("option:selected").text(input);
                      $(thi).find("option:selected").val(data);
                      $(thi).find("option:selected").attr("data-val","[]");
                      // 添加
                      $(thi).append('<option value="110">自定义</option>');

                      // 存到后台之后的处理
                      var obj = $('.template2 .mrk2').clone(true);
                      obj.attr("data-val","[]");
                      obj.attr("data-id",data);
                      obj.addClass('skuMultipls-box');

                      obj.find('.color-sp').text(input);
                      obj.attr('mark',3);

                      $('.standard-box').append(obj);
            	  });
              }

          },
          cancel: function(){
              //取消
          }
        });

      }else{
        // 判断是否已经存在
        var items = $('.skuMultipls-box');
        var mark ;
        var ifTru = true;
        for(var i=0; i<items.length;i++){
          mark = $.trim($(items[i]).attr('data-id'));
          if(val == mark){
            $('.sp2').show();
            setInterval(function() { 
              $('.sp2').fadeOut();
            }, 1000); 
            ifTru=false;
            break;
          }
        }

        if(ifTru){
          // 新的属性
          var obj = $('.template2 .mrk2').clone(true);
          obj.attr("data-val",dataVal);
          obj.attr("data-id",val);
          obj.addClass('skuMultipls-box');

          obj.find('.color-sp').text(txt);
          obj.attr('mark',val);

          $('.standard-box').append(obj);
        }
      }

    });

  }
  // 添加属性弹出框-相关事件 
  function attrPopup(){


    $(document).delegate('.add-pop-btn','click',function(){

      var mark = $.trim($(this).parents('.skuMultipls-box').attr('mark')); //标记属于那个
      var that=this;
      var skuMultiplsBox=$(that).parent().parent().parent().parent();
      $.confirm({
          title: '添加',
          columnClass: 'col_8 mgAT',
          content: '<div class="add-boxi" mark="'+mark+'"></div>',
          onOpen: function(){
        	var dataStringVal=skuMultiplsBox.attr("data-val");
        	var dataIdVal=skuMultiplsBox.attr("data-id");
        	
        	var choiceVal=skuMultiplsBox.find(".end-attr-box").children();
        	var choiceArray=new Array;
        	choiceVal.each(function(i,item){
        		choiceArray.push($(item).find("a").attr("data-id"));
        	});
        	
        	var dataObjVal=eval("("+dataStringVal+")");
        	
            var obj = $('.template .mrk1').clone(true);
            var attr_box = $(obj).find('.attr-box');
            dataObjVal.forEach(function(item){
            	var css="";
            	choiceArray.forEach(function(itemX){
            		if(item.id==itemX)
            			css="a8-1";
            	});
            	 $(attr_box).append(
            			 '<a href="javascript:void(0);" class="a8 add-attr txtHide noi '+css+'" data-id="'+item.id+'" data-property-id="'+dataIdVal+'">'
            			 	+item.name+'<img style="display:none;" src="'+ctxString+'/style-backend/images/no-1.png" class="del-attr">'
            			 +'</a>');
            });

            this.$b.find('.add-boxi').append(obj);
            this.$b.find('.add-boxi').attr("data-property-id",dataIdVal);
          },
          confirmButton: '确定',
          cancelButton: '取消',
          animation: 'top',
          closeAnimation: 'scaleX',
          confirm: function () {
        	  	
          	var choiceVal=skuMultiplsBox.find(".end-attr-box").children();
          	var choiceArray=new Array;
          	choiceVal.each(function(i,item){
          		choiceArray.push($(item).find("a").attr("data-id"));
          	});
          	
              var items = this.$b.find('.a8-1');
              if(items.length<1){return false;}
              var end_attr_box = skuMultiplsBox.filter('[mark='+mark+']').find('.end-attr-box');
              $(items).each(function(i,item){
            	  var sign=true;
            	  choiceArray.forEach(function(itemX){
            		  if(itemX==$(item).attr("data-id")){
            			  sign=false;
            		  }
            	  });
            	  if(sign){
                      end_attr_box.append(
                    		  '<span class="noi">'
                    		  +'<a href="javascript:void(0);" class="a8 add-attr txtHide" data-id="'+$(item).attr("data-id")+'">'
                    		  	+$(item).text()+'<img src="'+ctxString+'/style-backend/images/no-1.png" class="del-attr">'
                    		  +'</a>'
                    		  +'<span class="sp1">'
                    		  +'<img src="'+ctxString+'/style-backend/images/add1027-2.png">'
                    		  +'</span>'
                    		  +'</span>');
            	  }
              });
          }
      });
    });

    //删除属性
    $(document).delegate('.del-attr','click',function(){
      $(this).parents('.noi').remove();
    });

    // 选中属性  a8-1
    $(document).delegate('.add-boxi .add-attr','click',function(e){
      var clas= $.trim(e.target.className);
       if(clas.indexOf('a8-1')!=-1){
          $(this).removeClass('a8-1');
       }else{
          $(this).addClass('a8-1');
       }
    });
    // 弹出框 添加属性
    $(document).delegate('.add-attr-btn','click',function(e){
    	var propertyId=$(this).parents().find(".add-boxi").attr("data-property-id");
    	
        var val = $.trim($('.add-boxi').find('.add-input').val());
        if(val!=""){
            var attr_box = $('.add-boxi').find('.attr-box');
            $(attr_box).append(
            		'<a href="javascript:void(0);" class="a8 add-attr txtHide noi">'
            			+val+'<img src="'+ctxString+'/style-backend/images/no-1.png" class="del-attr">'
            		+'</a>');
            $('.add-boxi').find('.add-input').val('');
            $.post("../property/add-property-value",{
            	"shopPropertyId":propertyId,
            	"name":val
            },function(data){
				var obj={
					"id":data,
					"name":val
				}
                var dataVal=eval("("+$(".format option[value='"+propertyId+"']").attr("data-val")+")");
				dataVal.push(obj);
				$(".format option[value='"+propertyId+"']").attr("data-val",JSON.stringify(dataVal));
				$(".mrk2[data-id='"+propertyId+"']").attr("data-val",JSON.stringify(dataVal));
            });
        }
    });

    // 复选框选中功能
    $(document).delegate('.checki','click',function(){
      /* var parent = $(this).parents('.skuMultipls-box');
      if($(this).is(':checked')){
        $(parent).find('.sp1').css('display','block');
      }else{
        $(parent).find('.sp1').css('display','none');
      } */
    });

    //删除 skuMultipls-box
    $(document).delegate('.del-skuMultipls','click',function(){
      $(this).parents('.skuMultipls-box').remove();
    });

  }
  
  function clkFile(that){
	  $(that).parent().find("input").click();
  }

  function handlePropertyFiles(obj){
	  var files = obj.files,img = new Image();
	  if(files.length!=0){
		    img.className ="bannerPic";
		    if($(obj).parent().find("button").length!=0){
			    $(obj).parent().find("button").remove();
		    }else{
			    $(obj).parent().find("img").remove();
		    }
		    if (window.URL) {
		        img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
		        img.onload = function (e) {
		            window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
		        }
		    } else if (window.FileReader) {
		        var reader = new FileReader();
		        reader.readAsDataURL(files[0]);
		        reader.onload = function (e) {
		            img.src = this.result;
		        }
		    } else {
		        obj.select();
		        obj.blur();
		        var nfile = document.selection.createRange().text;
		        document.selection.empty();
		        img.src = nfile;
		        img.onload = function () {
		        }
		    }
		    $(img).css("width","70px");
		    $(img).css("cursor","pointer");
		    $(img).css("border","1px solid #ddd");
		    $(img).on("click",function(){
		    	clkFile(this);
		    });
		    var id=$(obj).attr("id");
		    id=id.substring(id.indexOf("Pic")+3,id.length);
		    id=parseInt(id);
	        $(obj).parent().append(img);
	        //var rowCount=$(obj).parent().parent().attr("rowCount");
			  propertyPicLocation.forEach(function(item){
				  if(id==item.rowCount){
					  item.picStatus=1;
				  }
			  });
	  }
  }
  
  var colCount=0,rowCount=1;
  function buildTable(sign){
	  if(sign!=0) saveProperty();
	  var property=$(".standard-box").children();
	  var array=new Array();
	  var th=$("<th></th>");
	  var td=$("<td></td>");
	  var tr=$("<tr></tr>");
	  var input=$('<input class="sku_list_stock" type="number">');
	  var inputL=$('<input class="sku_list_code" type="text">');
	  var inputFile=$('<button class="btn btn-default" style=" padding: 3px 6px;margin-left: 3px;" onclick="clkFile(this);">上传图片</button>'
			  +'<input name="propertyPic" class="sku_list_stock" type="file" style="display:none;" onchange="handlePropertyFiles(this)";>');
	  var thead=$("<thead></thead>");
	  var thtr=tr.clone(true);
	  colCount=0,rowCount=1;
	  var firstPropertys=new Array();
	  var lastPropertys=new Array();
	  var picCount=0;
	  property.each(function(i,item){
		  var status=$(item).find(".checki").attr("checked");
		  if(status=="checked"){
			  firstPropertys.push(item);
		  }else{
			  lastPropertys.push(item);
		  }
	  });
	  $(firstPropertys).each(function(i,item){
		  buildThead(item,thtr,array,1);
		  count++;
	  });
	  $(lastPropertys).each(function(i,item){
		  buildThead(item,thtr,array,0);
		  count++;
	  });
	  thead.append("");
	  if(rowCount!=1&&colCount!=0) $("#skuShow").css("display","none");
	  else $("#skuShow").css("display","");
	  if(thtr.html()==""){
		  $(".sku-multiple-table").html("");
	  }else  if(thtr.html()!=""){
		  thtr.append(th.clone(true).append("价格"));
		  thtr.append(th.clone(true).append("库存"));
		  thtr.append(th.clone(true).append("商品编号"));
		  thead.append(thtr);
		  $(".sku-multiple-table").html(thead);

		  propertyPicLocation=new Array();
		  var indexRC=0;
		  for(var i=0;i<rowCount;i++){
			  var trNew=tr.clone(true);
			  var colsign=colCount;
			  var x=1;
			  var propertyArray=new Array();
			  trNew.attr("rowCount",i);
			  $(lastPropertys).each(function(ix,item){
				  var item=$(lastPropertys)[$(lastPropertys).length-1-ix];
				  var size=$(item).find(".add-attr").size();
				  if(size!=0){
					  var num=0
					  var sign=true;
					  num=parseInt(i/x);
					  if(num>=size){
						  num=parseInt((i%(size*x))/x);
						  if((i%(size*x))%x!=0)
							  sign=false;
					  }else{
						  if(i%x!=0)
							  sign=false;
					  }
					  if(sign){
						  var text=$($(item).find(".add-attr")[parseInt(num)]).html();
						  var tdNew=td.clone(true);
						  tdNew.attr("rowSpan",x);
						  tdNew.html(text.substring(0,text.indexOf("<img")))
						  trNew.prepend(tdNew);
					  }
					  colsign--;
					  x=x*size;
					  var obj={
							id:parseInt($(item).attr("data-id")),
							vid:$($(item).find(".add-attr")[parseInt(num)]).attr("data-id")
					  }
					  propertyArray.push(obj);
				  }
			  });
			  $(firstPropertys).each(function(ix,item){
				  var item=$(firstPropertys)[$(firstPropertys).length-1-ix];
				  var size=$(item).find(".add-attr").size();
				  if(size!=0){
					  var num=0
					  var sign=true;
					  num=parseInt(i/x);
					  if(num>=size){
						  num=parseInt((i%(size*x))/x);
						  if((i%(size*x))%x!=0)
							  sign=false;
					  }else{
						  if(i%x!=0)
							  sign=false;
					  }
					  if(sign){
						  var text=$($(item).find(".add-attr")[parseInt(num)]).html();
						  var tdNew=td.clone(true);
						  tdNew.attr("rowSpan",x);
						  tdNew.html(text.substring(0,text.indexOf("<img")));
						  if(ix==0){
							  var ifile=inputFile.clone(true);
							  $(ifile[1]).attr("id","propertyPic"+picCount);
							  picCount++;
							  tdNew.append(ifile);
							  var obj={
									  "rowCount":indexRC,
									  "picStatus":0
							  }
							  indexRC++;
							  propertyPicLocation.push(obj);
						  }
						  trNew.prepend(tdNew);
					  }
					  colsign--;
					  x=x*size;
					  var obj={
								id:parseInt($(item).attr("data-id")),
								vid:$($(item).find(".add-attr")[parseInt(num)]).attr("data-id")
						  }
					  propertyArray.push(obj);
				  }
			  });
			  for(var ix=0;ix<3;ix++){
				  if(ix==2){
					  var inputLNew=inputL.clone(true);
					  inputLNew.attr("data-property",JSON.stringify(propertyArray));
					  inputLNew.attr("name","code");
					  trNew.append(td.clone(true).append(inputLNew));
				  }else{
					  var inputNew=input.clone(true);
					  inputNew.attr("data-property",JSON.stringify(propertyArray));
					  if(ix==0){
						  inputNew.attr("name","price");
					  }else{
						  inputNew.attr("name","sku");
					  }
					  trNew.append(td.clone(true).append(inputNew));
				  }
			  }
			  $(trNew).find("input[type='file']").attr("data-property",JSON.stringify(propertyArray));
			  $(".sku-multiple-table").append(trNew);
		  }		  
		  
		  var trPilian=tr.clone(true);
		  var tdFirst=td.clone(true).attr("colspan",colCount).append("批量修改");
		  trPilian.append(tdFirst);
		  for(var i=0;i<3;i++){
			  var inputNew=input.clone(true);
			  if(i==2){
				  inputNew.attr("name","code");
				  inputNew.attr("id","allCode");
				  inputNew.attr("type","text");
				  var tdNew=td.clone(true);
				  tdNew.append(inputNew);
				  trPilian.append(tdNew.append('<button class="btn btn-default" style=" padding: 3px 6px;margin-left: 3px;" onclick="piliang();">确定</button>'));
			  }else if(i==0){
				  inputNew.attr("name","price");
				  inputNew.attr("id","allPrice");
				  trPilian.append(td.clone(true).append(inputNew));
			  }else{
				  inputNew.attr("name","sku");
				  inputNew.attr("id","allSku");
				  trPilian.append(td.clone(true).append(inputNew));
			  }
		  }
		  $(".sku-multiple-table").append(trPilian);
	  }

	  if(sign!=0) buildData(shopGoodsList);
  };
  function piliang(){
	    $.confirm({
		      title: false,
		      content: '确定执行批量操作？',
		      confirmButton: '确定',
		      cancelButton: '取消',
		      columnClass: 'col_4 mgAT',
		      animation: 'top',
		      closeAnimation: 'scaleX',
		      confirm: function(){
		        //确定删除
		    	  var price=$("#allPrice").val();
		    	  var code=$("#allCode").val();
		    	  var sku=$("#allSku").val();
		    	  if(price!=""){
		    		  $(".sku-multiple-table").find("input[name='price']").val(price);
		    	  }
		    	  if(code!=""){
		    		  $(".sku-multiple-table").find("input[name='code']").val(code);
		    	  }
		    	  if(sku!=""){
		    		  $(".sku-multiple-table").find("input[name='sku']").val(sku);
		    	  }
		      },
		      cancel: function(){
		          //取消
		      }
		    });
  }
  function buildThead(item,thtr,array,picSign){
	  var th=$("<th></th>");
	  var obj=new Object();
	  obj.valSize=$(item).find(".add-attr").size();
	  array.push(obj);
	  if(obj.valSize!=0){
		  var thc=th.clone(true).append($(item).find(".color-sp").text());
		  thc.attr("data-id",$(item).attr("data-id"));
		  thc.attr("pic-sign",picSign);
		  thtr.append(thc);
		  colCount++;
		  rowCount=rowCount*obj.valSize;
	  }
  }
  
  function saveProperty(){
	  var inputsPrice=$(".sku-multiple-table").find("input[name='price']");
	  var array=new Array();
	  inputsPrice.each(function(i,itemX){
		  var obj=new Object();
		  var dataProperty=$(itemX).attr("data-property");
		  if(dataProperty!=undefined){
			  var price=$(itemX).val();
			  var sku=$(".sku-multiple-table").find("input[name='sku'][data-property='"+dataProperty+"']").val();
			  var code=$(".sku-multiple-table").find("input[name='code'][data-property='"+dataProperty+"']").val();
			  var file=$(".sku-multiple-table").find("input[type='file'][data-property='"+dataProperty+"']");
			  var imgSrc=file.parent().find("img").attr("src");
			  if(imgSrc!=undefined){
				  if(imgSrc.indexOf("http://")>=0){
					  obj.picUrl=imgSrc.substring(basicUrl.length,imgSrc.length);
				  }
			  }
			  
			  dataPropertyObj=eval("("+dataProperty+")");
			  obj.propertyValue=dataPropertyObj;
			  obj.sku=sku;
			  obj.code=code;
			  obj.price=price;
			  array.push(obj);
		  }
	  });
	  shopGoodsList=array;
  }
  
  /*填值*/
  function buildData(array){
	  var inputsPrice=$(".sku-multiple-table").find("input[name='price']");
	  var inputsCode=$(".sku-multiple-table").find("input[name='code']");
	  var inputsSku=$(".sku-multiple-table").find("input[name='sku']");
	  var inputsFile=$(".sku-multiple-table").find("input[type='file']");
	  array.forEach(function(item){
		  var pipeiArray=item.propertyValue;
		  inputsPrice.each(function(i,itemX){
			  var sign=dealData(itemX,pipeiArray,item);
			  if(sign) $(itemX).val(item.price);
		  });
		  inputsCode.each(function(i,itemX){
			  var sign=dealData(itemX,pipeiArray,item);
			  if(sign) $(itemX).val(item.code);
		  });
		  inputsSku.each(function(i,itemX){
			  var sign=dealData(itemX,pipeiArray,item);
			  if(sign) $(itemX).val(item.sku);
		  });
		  inputsFile.each(function(i,itemX){
			  var sign=dealData(itemX,pipeiArray,item);
			  if(sign) {
				  if(item.picUrl!=undefined){
					  img = new Image();
					  var url=basicUrl+item.picUrl;
					    img.className ="bannerPic";
					        img.src = url //创建一个object URL，并不是你的本地路径
					        img.onload = function (e) {
					            window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
					        }

						    $(img).css("width","70px");
						    $(img).css("cursor","pointer");
						    $(img).css("border","1px solid #ddd");
						    $(img).on("click",function(){
						    	clkFile(this);
						    });
					        $(itemX).parent().find("button").remove();
					        $(itemX).parent().append(img);
				  }
			  }
		  });
	  });
  }
  
  function dealData(itemX,pipeiArray,item){
	  var dataProperty=$(itemX).attr("data-property");
	  if(dataProperty!=undefined){
		  var dpArray=eval("("+dataProperty+")");
		  var sign=true;
		  dpArray.forEach(function(dpItem){
			  var dpid=dpItem.id;
			  var dpvalue=dpItem.vid;
			  var nowSign=false;
			  pipeiArray.forEach(function(ppItem){
				  var ppid=ppItem.id;
				  var ppvalue=ppItem.vid;
				  if(dpid==ppid+""){
					  if(dpvalue!=ppvalue){
						  return;
					  }else{
						  nowSign=true;
						  return;
					  }
				  }
			  });
			  if(!nowSign){
				  sign=false;
				  return;
			  }
		  });
		  return sign;
	  }
	  return false;
  }