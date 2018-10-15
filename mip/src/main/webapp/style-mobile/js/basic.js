/******************
** 自定义插件 
******************/


var myFunct={
			/** num1:手机版滑动删除 **/ 
	slideDelete: function (options){
		/**			
		 	<div class="slide-del-box">
				hhh
				<a href="javascript:void(0);" class="slide-del">删除</a>
		  	</div> 

		  	mgL:要滑动的距离
			speed:时间
			del 是删除方法
		**/

		var defaultOptions = {
            boxName:'slide-del-box',
            aName:'slide-del',
            mgL:80,
            speed:10,
            del:function(){}
        };
	   	var option = $.extend(defaultOptions, options);
		var elems = $('.'+option.boxName);

		if(elems.length>0){		
			for(var i=0; i<elems.length;i++){
				var obj=elems[i];			
				myFunct.slideDeleteFunct(obj,option);
			}		
		}
	},
	slideDeleteFunct : function(obj,option){
		/**
			手机版滑动删除 slideDelete
			obj - jquery 对象
		**/
		var spanX=0;
    	position = {
            x: 0,
            y: 0
        } 
    	obj.addEventListener('touchstart', function(event){    	    	
    	   	//event.preventDefault();
    	   	var touch = event.targetTouches[0];
          	position.x = touch.pageX ;    	    	
     	});

    	obj.addEventListener('touchmove', function(event){
    	    if (event.targetTouches.length == 1) {
    	    	event.preventDefault();
          		var touch = event.targetTouches[0];
          		spanX = touch.pageX - position.x;

          		var y = option.mgL;
                var mgL=parseFloat($(this).css('margin-left'));

          		if(spanX<0){ //向左 显示删除 
          				if(spanX<=-y){
          					spanX=-y;
          				} 
                  		if(mgL>-y){
                  		  $(this).animate({'margin-left':spanX+'px'},option.speed); 
                  		}
          				
          		}else{
                  		// console.log('向右');
          				if(spanX>=y || spanX==0){

          					spanX=0;
          					
          				}else{
                    		spanX = -(y-spanX);
                  		}
                  		if(mgL<0){

                  		  $(this).animate({'margin-left':spanX+'px'},option.speed);  

                  		}
          				    	
          		}  
          					          		
    	    }

     	});

     	obj.addEventListener('touchend', function(event){    	    	
    	    //event.preventDefault();
          	position.x = 0 ; 
          	var y = option.mgL;

          	// 左边
          	if(spanX<-(y/2) && spanX>=-y) {

          			spanX=-y;

          	}else{

          		if(spanX>=-(y/2) && spanX<0) {

          				spanX=0;

          		}else{

          			if(spanX>(y/2) && spanX<y) {

          					spanX=0;

          			}else{

          				if(spanX>=0 && spanX<=(y/2)) {
          						// spanX=-y;
                      		spanX=0;
          				} 
          			}
          		}
          	} 
          		
          	$(this).animate({'margin-left':spanX+'px'},option.speed); 	    	
     	});
    	
    	var aObj=$(obj).find('.'+option.aName);
    	aObj[0].addEventListener('click', function(event){    	
    		option.del(obj);
    	}); 
	},
	slideDeleteUpdate:function(obj,options){
		/**
		* 滑动删除
		* 加载数据时 
		* obj -jquery 对象
		* 
		**/
		var defaultOptions = {
            boxName:'slide-del-box',
            aName:'slide-del',
            mgL:80,
            speed:10,
            del:function(){}
        };
	   	var option = $.extend(defaultOptions, options);
		myFunct.slideDeleteFunct(obj,option);
	},
	

	
	loadImage:function(obj,type){
		/**
		* 公用的 img.onload
		* obj-带有图片信息的限制层
		* callback-处理方法 type
		**/	

		myFunct.loading(obj);
		var url = obj.attr('src');

		if(!url){ return false;}

		var img = new Image();  
		img.onload =function(){  
		    img.onload =null;               
		    //callback(obj,img,option); 		    		
		    switch (type){ 
				case 1 :  myFunct.screenFillImgFunct(obj,img) ;break; 
				case 2 :  myFunct.middleImgFunct(obj,img) ;break;	
				case 3 :  myFunct.levelImgFunct(obj,img) ;break;
				default : console.log('错误！！') ; 
			}  
		}  
		img.src = url; 
	},
	screenFillImgFunct:function(obj,img){
		/**
		* 开始
		* 在限制层里面 图片一定要铺满
		* obj-图片对象
		* img-处理方法
		**/	
		var obj_img=$('<img src="'+obj.attr('src')+'"/>');
		var wid = obj.width(); 
		var heig= obj.height();
		var w=img.width;           	//图片本身的图片
		var h=img.height;          	//图片本身的高度
		if(w>=wid){                 //表示图片的宽度比限制层大						
			var hx=(wid*h)/w;       //如果图片按照限制层的宽为宽，算出此时图片的
			if(hx>heig){     		//如果图片的高比限制层大，设置宽100%、margin-top 
				var top = -(hx-heig)/2;    
				obj_img.css({'width':'100%','margin-top':top});
			}else{           		//高100%,margin-left
				var wx=(w*heig)/h;
				var left = -(wx-wid)/2;
				obj_img.css({'height':'100%','margin-left':left});
			}					
		}
		if(w<wid){           		//表示图片的宽度比限制层小													 
			var hx=(wid*h)/w; 	    //设置宽100%时，图片的高hx
			if(hx>heig){        	//hx大于限制层的高 宽100% margin-top 
				var top = -(hx-heig)/2;
				obj_img.css({'width':'100%','margin-top':top});
			}else{ 					//hx 小于限制层的高  宽100% 高100%
				obj_img.css({'height':'100%','width':'100%'});
			}				
		}
		obj.empty();//obj.find('.n-def-img').empty();
		obj.append(obj_img);
		/** 结束 **/
	},	
	screenFillImg:function(options){
		/** 
		* 开始
		* 图片一定铺满限制层,用于手机
		* elems-带有图片信息的限制层
		* 默认以下这种 
		* <div class="screenFill"  src=".....">	</div>
		**/
		var defaultOptions = {
            boxName:'screenFill'
        };
	   	var option = $.extend(defaultOptions, options);
		var elems = $('.'+option.boxName);

		if(elems.length>0){		
			for(var i=0; i<elems.length;i++){
				var obj=$(elems[i]);			
				myFunct.loadImage(obj,1);
			}		
		}		
		/** 结束 **/
	},
	screenFillImgUpdate:function(obj){
		/** 
		* 开始
		* 再次添加数据,图片一定铺满限制层,用于手机
		* obj - 带有图片信息的限制层
		* <div class="screenFill"></div>
		**/
	   	myFunct.loadImage(obj,1);				
		/** 结束 **/
	},
	middleImgFunct:function(obj,img){
		/** 
		* 开始
		* obj - 带有图片信息的限制层
		* <div class="screenFill"></div>
		**/
	   	var obj_img=$('<img src="'+obj.attr('src')+'"/>');
		var wid = obj.width();
		var heig= obj.height();
		var w=img.width;           	//图片本身的图片
		var h=img.height;          	//图片本身的高度

		if(w>=wid){                 //表示图片的宽度比限制层大															
			var hx=(wid*h)/w;       //如果图片按照限制层的宽为宽，算出此时图片的高
			if(hx>heig){            //如果图片的高比限制层大，那么设置图片高度 100%      
				obj_img.css({'height':'100%'});
			}else{  				//宽度为100%,高度是相对应的
				obj_img.css({'width':'100%','vertical-align':'middle'});
			}					
		}
		if(w<wid){                  //表示图片的宽度比限制层小											
			var wx=(w*heig)/wid; 	
			if(h>heig){        		//原图片的高度比外边框大,那么就给图片的高度设置100%;   
				obj_img.css({'height':'100%'});
			}else{					//按照图片原图展示,什么样式都不加 
				obj_img.css({'vertical-align':'middle'});
			}				
		}
		obj.empty();
		obj.append(obj_img);	//要在里面添加
		/** 结束 **/
	},
	middleImg:function(options){
		/**
		* 图片居中
		**/
		var defaultOptions = {
            boxName:'n-middle-img'
        };
	   	var option = $.extend(defaultOptions, options);
		var elems = $('.'+option.boxName);

		if(elems.length>0){		
			for(var i=0; i<elems.length;i++){
				var obj=$(elems[i]);			
				myFunct.loadImage(obj,2);
			}		
		}
		/** 结束 **/
	},
	middleImgUpdate:function(obj){
		/** 
		* 图片居中,再次添加数据
		* obj - 带有图片信息的限制层
		**/
	   	myFunct.loadImage(obj,2);				
		/** 结束 **/
	},
	levelImgFunct:function(obj,img){
		/** 
		* 图片水平居中
		* obj - 带有图片信息的限制层
		* <div class="screenFill"></div>
		**/
	   	var obj_img=$('<img src="'+obj.attr('src')+'"/>');
		var wid = obj.width();
		var heig= obj.height();
		var w=img.width;           	//图片本身的图片
		var h=img.height;          	//图片本身的高度

		if(w>=wid){                 //表示图片的宽度比限制层大											
			obj_img.css({'width':'100%'});									
		}
		/*if(w<wid){ 表示图片的宽度比限制层小 什么都不做 }*/

		obj.empty();
		obj.append(obj_img);	//要在里面添加
		/** 结束 **/
	},
	levelImg:function(options){
		/**
		* 图片水平居中
		**/
		var defaultOptions = {
            boxName:'n-level-img'
        };
	   	var option = $.extend(defaultOptions, options);
		var elems = $('.'+option.boxName);

		if(elems.length>0){		
			for(var i=0; i<elems.length;i++){
				var obj=$(elems[i]);			
				myFunct.loadImage(obj,3);
			}		
		}
		/** 结束 **/
	},
	switchDiv:function(){
		/** div切换 **/
		
	},
	loading:function(obj,type,size){
		/**
		* 图片未加载出来的时候显示转圈圈
 		* obj 
 		* type 类型选择  默认-在里面 如table则在下面
 		* size 形状 0-默认最小
		**/
		var iType = type || 0;
		var iSize = size || 0;
		var size_div = '';

		switch (iSize){ 
			case 1 :  break; 
			case 2 :  break; 	
			default : size_div = $('<div class="n-def-img"><img src="images/onload.gif"></div>'); 
		} 

		switch (iType){ 
			case 1 :  obj.after(size_div); break; 	
			default : obj.append(size_div); break;  
		} 
		/** 结束 **/
	},
	circular:function(objBox,obj,r){
		/** 
		* 图片按照圆形轨迹运动 
		* objBox 外层
		* obj    目标
		* r		 半径
<div class="d"> 设置高宽
	<span> 设置位置
		<img src="8-2.png" alt="">
	</span>	
</div>	
myFunct.circular($('.d'),$('.d img'),20);	
		**/
		if(objBox.length<1 || obj.length<1 || r<1){ return false;}
		obj = $(obj)[0];
		var timer;
		var angle = 0;
		$(objBox).hover(function(){

			timer = setInterval(function(){
				obj.style.marginLeft = (Math.sin(angle)) * r +'px';
				obj.style.marginTop  = (Math.cos(angle)) * r +'px';
				angle++;
			},100);

		},function(){
			clearInterval(timer);
			obj.style.marginLeft = '0px';
			obj.style.marginTop  = '0px';
			angle=0;
		});
	}

};