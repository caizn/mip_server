
  // 商品详情操作 
function handleDetailFiles(obj){

	  var files = obj.files,img = new Image();
	  if(files.length!=0){
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
			  
		      var object = $('.bdccc-das');
		      object.attr("data-id","0");
		      var subDetailImgCount=detailImgCount-1
		      object.attr("data-context",subDetailImgCount);
		      object.attr("data-type","image");
		      object.append(img);
		      var count=detailImgCount-1;
		      var p5 = $('<p class="p5"></p>');
		      var detls_del = $('<a href="javascript:void(0);" class="detls-del">删除</a>');
		      var detls_next = $('<a href="javascript:void(0);" class="detls-next">下移</a> ');
		      var detls_pre = $('<a href="javascript:void(0);" class="detls-pre">上移</a>');
		      var detls_add = $('<a href="javascript:void(0);" class="detls-add">添加</a>');

		      p5.append(detls_del).append(detls_next).append(detls_pre).append(detls_add);
		      object.append(p5);
		      object.removeClass('bdccc-das');
		      $('.add-modular2').hide();
		      $('.main').css('overflow','auto');
		      $('.detls-modular').css('overflow','auto');
	  }
}

  function detlsDo(){
    // 点击添加视频
    $(document).delegate('.add-video','click',function(){
      var obj = $('.bdccc-das');

      $.confirm({
          title: '添加视频',
          columnClass: 'col_7 mgAT',
          content: '<input type="text" class="form-control" id="input-name1" placeholder="请输入视频链接">',
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
        	      obj.attr("data-id","0");
        	      obj.attr("data-context",input.val());
        	      obj.attr("data-type","video");
                  var p5 = $('<p class="p5"></p>');
                  var detls_del = $('<a href="javascript:void(0);" class="detls-del">删除</a>');
                  var detls_next = $('<a href="javascript:void(0);" class="detls-next">下移</a> ');
                  var detls_pre = $('<a href="javascript:void(0);" class="detls-pre">上移</a>');
                  var detls_add = $('<a href="javascript:void(0);" class="detls-add">添加</a>');

                  p5.append(detls_del).append(detls_next).append(detls_pre).append(detls_add);
                  obj.append(p5); 
                  obj.append('<video src="'+input.val()+'" controls="controls" style="width:100%;"></video>');
                  obj.removeClass('bdccc-das');
                  $('.add-modular2').hide();
                  $('.main').css('overflow','auto');
                  $('.detls-modular').css('overflow','auto');

              }
          }
      });

    });

    // 点击点击图片
    $(document).delegate('.add-pic','click',function(){
	  var inputFile=$('<input name="detailPic" accept="image/jpg,image/jpeg,image/png" type="file" style="display:none;" onchange="handleDetailFiles(this)";>');
	  inputFile.attr("id","detailPic"+detailImgCount);
	  $(".detls-modular").append(inputFile);
	  inputFile.click();
	  detailImgCount++;
      
    });

    // 点击添加文字  
    $(document).delegate('.add-txt','click',function(){
      var obj = $('.bdccc-das');
      obj.attr("data-id","0");
      obj.attr("data-context","");
      obj.attr("data-type","text");
      var txtA = $('<textarea class="form-control resizeN txtAR" rows="5" placeholder="请输入文字"></textarea>');
      var p5 = $('<p class="p5"></p>');
      var detls_del = $('<a href="javascript:void(0);" class="detls-del">删除</a>');
      var detls_next = $('<a href="javascript:void(0);" class="detls-next">下移</a> ');
      var detls_pre = $('<a href="javascript:void(0);" class="detls-pre">上移</a>');
      var detls_add = $('<a href="javascript:void(0);" class="detls-add">添加</a>');

      p5.append(detls_del).append(detls_next).append(detls_pre).append(detls_add);
      obj.append(txtA).append(p5);
      obj.removeClass('bdccc-das');
      $('.add-modular2').hide();
      txtA.focus();
      // 禁止页面滑动
      $('.main').css('overflow','auto');
      $('.detls-modular').css('overflow','auto');
    });

    // 确定添加文字
    /*$(document).delegate('.txtAR','blur',function(){
      alert('确定添加文字');
    });*/
  }
  // 商品详情框
  function detls(){

	    if($('.add-modular2').is(':visible')){
	      return false;
	    }
    // 点击添加模块
    $(document).delegate('.detls-add-a3','click',function(){
      var detls_modular = $('.detls-modular');
      var new_obj = $('<div class="detls-sg  bdccc-das"></div>');
      detls_modular.append(new_obj);
      detls_modular.scrollTop(2000000);
      var mgT = new_obj.position().top;
      $('.add-modular2').css('top',mgT+'px');
      $('.add-modular2').show();
      
      // 禁止页面滑动
      $('.main').css('overflow','hidden');
      $('.detls-modular').css('overflow','hidden');

    });

    // 添加 
    $(document).delegate('.detls-add','click',function(){
      var detls_sg = $(this).parents('.detls-sg');
      var new_obj = $('<div class="detls-sg  bdccc-das"></div>');
      detls_sg.after(new_obj);
      detls_sg.scrollTop(100);
      var mgT = new_obj.position().top;
      $('.add-modular2').css('top',mgT+'px');
      $('.add-modular2').show();
      
      // 禁止页面滑动
      $('.main').css('overflow','hidden');
      $('.detls-modular').css('overflow','hidden');

    });


    // 删除
    $(document).delegate('.detls-del','click',function(){
      var thi = $(this);
      $.confirm({
        title: false,
        content: '确定删除模块？',
        confirmButton: '确定',
        cancelButton: '取消',
        columnClass: 'col_4 mgAT',
        animation: 'top',
        closeAnimation: 'scaleX',
        confirm: function(){
          //确定删除
          thi.parents('.detls-sg').remove();
        },
        cancel: function(){
            //取消
        }
      });
    });

    // 上移
    $(document).delegate('.detls-pre','click',function(){
      var detls_sg = $(this).parents('.detls-sg');
      var detls_sg_pre = detls_sg.prev();

      var index = detls_sg.index();
      if(index){
        detls_sg_pre.before(detls_sg);
      }    
    });

    // 下移
    $(document).delegate('.detls-next','click',function(){
        var detls_sg = $(this).parents('.detls-sg');
        var detls_sg_next = detls_sg.next();

        var index = detls_sg_next.index();
        if(index>0){
          detls_sg_next.after(detls_sg);
        }   
    });
    
    $(document).delegate('.i1','click',function(){
        $('.add-modular2').hide();
        $('.main').css('overflow','auto');
        $('.detls-modular').css('overflow','auto');
        $('.bdccc-das').remove();
      
    });
  }