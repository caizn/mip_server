// JavaScript Document

$(function(){
	initial();
});
/********* 页面初始化 ***********/
function initial(){
	leftH();
	window.onresize=function(){ leftH();};
	breadcrumbBtn();
}

/**
* 处理页面加载时的高度和宽度 	
*/
function leftH(){ 
	var h = $(window).height();
	$('.col-main').css('height',h+'px');	
	$('.main').css('height',h+'px');	
	$('.col-sub').css('height',h+'px');		
}

/**
* 按钮
*/
function breadcrumbBtn(){
	$('.page-breadcrumb').on('click',function(){
		var col_sub = $('.col-sub');
		if(col_sub.is(':hidden')){
			var topH = parseInt($('.top-navi').height());	
			var h = $(window).height()-topH;	
			$('.col-sub').css('height',h-2+'px');	
			col_sub.slideDown();
			$('body').bind("touchmove",function(e){    
					e.preventDefault();	//禁用    
			}); 
		}else{
			col_sub.slideUp();
			$("body").unbind("touchmove");
		}
	});
}


  /**
  * 自定义下拉
  **/
function selectBlock(){
$(document).delegate('.sel-div','click',function(e){
    var clas= $.trim(e.target.className);

    if(clas.indexOf('sel-sp')!=-1){
        $(e.target).remove();
    }else{
      if($(e.target).parents('.sel-sp').length>0){
        $(e.target).parents('.sel-sp').remove();
      }else{

        var parent = $(this).parents('.sel-block');
        var elems_li = parent.find('.li');
        var menu_div = parent.find('.sel-menu');
        if(!parent || elems_li.length<0 || menu_div.length<0){ return false;}
        if(menu_div.is(':hidden')){
          $('.sel-menu').removeClass('show');
          menu_div.addClass('show');
        }else{
          menu_div.removeClass('show');
        }

      }
        
    }
        
});

$(document).delegate('.sel-block .li','click',function(e){
   
    var parent = $(this).parents('.sel-block');
    var menu_div = parent.find('.sel-menu');
    var menu_div_1 = parent.find('.sel-div-1');
    if(!parent || menu_div.length<0){ return false;}
    //只选择一个
    var boolNum = $(this).parents('.sel-num1'); 
    var val = $(this).attr('val');
    var txt = $(this).text();
    var boolVal = menu_div_1.find('[val="'+val+'"]');
    if(boolNum.length<1){ //多     
      if(boolVal.length<1){             
        menu_div_1.append('<span class="sel-sp" val="'+val+'"><i class="fa fa-times"></i>'+txt+'</span> ');
      }
    }else{ //单
      menu_div_1.empty();
      menu_div_1.append('<span class="sel-sp" val="'+val+'"><i class="fa fa-times"></i>'+txt+'</span> ');
    }
}) ;

/*处理事件源*/
$('body').on('click',function(e){
  var clas= $.trim(e.target.className);
  var elems = $('.sel-menu').filter('.show');
  var bl = $(e.target).parents('.sel-block');
  if(bl.length<=0 && elems.length>0){
      elems.removeClass('show');
  }
}) ;
}

  /**
  * 自定义左右数据交换
  **/
function dataExchgBlock(){
  $(document).delegate('.qxdd','click',function(){
    if($(this).hasClass ('ddon')){
      $(this).removeClass('ddon');      
    }else{
      $(this).addClass('ddon');
    };
  });
  $(document).delegate('.addDD','click',function(){ /*添加*/
    var thisElems = $('.ddBlock1').find('.ddon');
    var otherElems = $('.ddBlock2');
    metho(thisElems,otherElems);
    
  });
  $(document).delegate('.removeDD','click',function(){ /*移除*/
    var thisElems = $('.ddBlock2').find('.ddon');
    var otherElems = $('.ddBlock1');
    metho(thisElems,otherElems);
  });
  function metho(thisElems,otherElems){
    if(thisElems.length>0){
      for(var i=0;i<thisElems.length;i++){
        var elem = $(thisElems[i]);

        var dt = elem.parent().find('dt')[0];
        var dtVal = $(dt).attr('val');
  
        var boolDt = otherElems.find('[val="'+dtVal+'"]');/*判断是否有dt*/
        var dl ;
        var dtClone;
        var ddClone
        if(boolDt.length<1){ //没有dt
          dl = $('<dl></dl>');
          dtClone = $(dt).clone();
          ddClone = elem.clone();
          ddClone.removeClass('ddon');
          dl.append(dtClone);
          dl.append(ddClone);
          otherElems.append(dl);
        }else{ //已经有dt了
          ddClone = elem.clone();
          ddClone.removeClass('ddon');
          $(boolDt[0]).parents('dl').append(ddClone);
        }
        //移除
        var ddSibl = elem.siblings('dd');
        if(ddSibl.length<1){
          elem.parents('dl').remove();
        }else{
          elem.remove();
        }       
      }
    }
  }
}

//将1....20 转换成 A....W
function converted(num){
  var str = '';
  switch (num){ 
    case 4 : str='D'; break; 
    case 5 : str='E'; break;
    case 6 : str='F'; break;
    case 7 : str='G'; break;
    case 8 : str='H'; break; 
    case 9 : str='I'; break;
    case 10 : str='J'; break;
    case 11 : str='K'; break;
    case 12 : str='L'; break;
    case 13 : str='M'; break;
    case 14 : str='N'; break;
    case 15 : str='O'; break;
    case 16 : str='P'; break;
    case 17 : str='Q'; break;
    case 18 : str='R'; break;
    case 19 : str='S'; break;
    case 20 : str='H'; break;
    case 21 : str='U'; break;
    case 22 : str='V'; break;
    case 23 : str='W'; break;
    case 24 : str='X'; break;
    case 25 : str='Y'; break;
    case 26 : str='Z'; break;
    default : str='C'; 
  } 
  return str;
}