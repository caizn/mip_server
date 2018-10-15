//////////////////////

//省市县三级联动start
//初始化

var province,city,area;

function initValue(a1, a2 , a3 , obj1, obj2,obj3) {
	  var count = 0;
	  obj1.length = 0; 
	  province=a1;
	  city=a2;area=a3
	  for(var i = 0; i< regionOne.length;i++){
		var oOptA=document.createElement("OPTION");
		   oOptA.value=regionOne[i][0];
	       oOptA.innerHTML=regionOne[i][1];
	   	if(province==regionOne[i][0]){
	  		oOptA.selected = true;
	   	}	
	       obj1.appendChild(oOptA);     
	  	if(province==regionOne[i][0]){
	  		changeOne(count,obj2,obj3);	  		
	    }	

	    count++;
	  }	
}

//一级动二级
function changeOne(index_1, target,target2){
	  target.length = 0;
	  var count = 0;
	  var temp = 0;
	  var oOptB=document.createElement("OPTION");
	  oOptB.value="";
      oOptB.innerHTML="请选择市";
      target.appendChild(oOptB);  
	  for(var j = 0; j<regionTwo[index_1].length; j++){  	
	  	var oOptA=document.createElement("OPTION");
		   oOptA.value=regionTwo[index_1][j][0];
	       oOptA.innerHTML=regionTwo[index_1][j][1];
	       if(city==regionTwo[index_1][j][0]){
	    	   oOptA.selected = true;			
	       }
	       target.appendChild(oOptA);     
	  	if(city==regionTwo[index_1][j][0]){
	  		//changeOne(index_1,temp,target2);	  
	  		temp = count+1;
	    }
	    count++;
    }	
    changeTwo(index_1, temp, target2); 
}	

//二级动三级
function changeTwo(index_1, index_2 ,target){
	  target.length = 0;
	  var count = 0;
	  
	  var oOptB=document.createElement("OPTION");
	  oOptB.value="";
      oOptB.innerHTML="请选择区/县";
      target.appendChild(oOptB);  
      if(index_2!=0)index_2--;
	  for(var k = 0; k<regionThree[index_1][index_2].length; k++){
		var oOptA=document.createElement("OPTION");
		   oOptA.value=regionThree[index_1][index_2][k][0];
	       oOptA.innerHTML=regionThree[index_1][index_2][k][1];   
	  	
	  	if(area==regionThree[index_1][index_2][k][0]){
	  		oOptA.selected = true;	
	  		//target[count].selected = true;				
	    }	
	       target.appendChild(oOptA);  
	    count++;
    }	
}	
//省市县三级联动end



//初始化省市

function initRegion(key,obj,obj2){
	if(obj!=null && obj2!=null){	
		obj.length = 0;	
		var count = 0;	
		
		for(var i = 0; i< regionOne.length;i++){
			obj.options[obj.length] = new Option(regionOne[i][1],regionOne[i][0]);
				if(key == regionOne[i][0]){				
					obj[count].selected = true;	
					//alert(count)	
					changeRegion(count,obj2);			
				}
			count ++;			
		}
		
		if( key == "" || key =="null" ){		
			changeRegion(0,obj2);
		}
	}	
}
//////////
///一级省改变控制二级市的改变
function changeRegion(index,target){		
	//index = index -1
	if(target !=null){
		target.length = 0;	
		//target.options[0] = new Option("er","");		
		for(var i = 0 ;i < regionTwo[index].length;i++){
			target.options[target.length] = new Option(regionTwo[index][i][1],regionTwo[index][i][0]);
			if(regionTwoCode !=null ){
				if(regionTwoCode == regionTwo[index][i][0]){
					target[i].selected = true;
					//alert(target[i].innerHTML)
				}
			}
		}
	}
}




/////////////
//初始化区域分类

function initCatalog_a(key,obj,clientAreaKey){
	if(obj!=null){
		obj.length = 0;		
		var count =0;		
		//alert(clientAreaKey)	
		obj.options[obj.length] = new Option("请选择区域","");	
		for(var i = 0; i< areaOne.length;i++){
			for(var j = 0 ; j < areaTwo[i].length; j++){	
					obj.options[obj.length] = new Option(areaTwo[i][j][1]+"市",areaTwo[i][j][0]);							
				for(var k = 0 ; k < areaThree[i][j].length;k++){					 					
					obj.options[obj.length] = new Option("      |-" + areaThree[i][j][k][1],areaThree[i][j][k][0]);
					
					if(key == areaTwo[i][j][0]){						
						obj.options[count - k - i + j + 1].selected=true; //改+1,发现读取时区域上移
					}else	if(key == areaThree[i][j][k][0] ){						
						obj.options[count +j+1 + 1  ].selected=true; //改+1,发现读取时区域上移
					}//else if(clientAreaKey ==areaTwo[i][j][0]){
					//	obj.options[count - k - i + j].selected=true;
					//}
					count ++;
				}							
			}
		}
	}
}


function checkImgType(obj){
	
	if(typeof(obj)=="undefined"){return false;}
	var imgSrc  = obj.value;
	var imgtype =".jpg.bmp.gif";
	var checkFlag;
	var len = imgSrc.lastIndexOf(".");
	var typeStr;
	if(len < 0){
		alert("请确认上传的图片格式是否正确！");
		obj.value ="";
		//obj.focus();
		subFlag = false
		return subFlag;
	}else{
		typeStr = imgSrc.substring(len);
		typeStr = typeStr.toLowerCase();
		checkFlag = imgtype.match(typeStr);
		if(checkFlag!=null){
			return subFlag;
		}else{
			obj.select();			
			alert("你所上传的图片格式系统暂不支持，请重新上传。\n系统支持.jpg、.gif、.bmp 格式的图片！");			
			subFlag = false
			return subFlag;
		}
	}	
	
	return true;
}//
function checkStr(obj){
	if(obj.innerHTML){
		if(obj.innerHTML.hasHTMLCode()){
			alert("描述不能包含特殊字符 如： & <>等。");
			obj.select();
			return false;
		}
	}else {
		if(obj.value.hasHTMLCode()){
			alert("标题不能包含特殊字符 如： & <>等。");
			obj.select();
			return false;
		}
	}
}

//全角检测
//<INPUT name="CardId" onKeyUp="quanjiao(this);">
function quanjiao(obj)
{
    var str=obj.value;
    if (str.length>0)
    {
        for (var i = str.length-1; i >= 0; i--)
        {
            unicode=str.charCodeAt(i);
            if (unicode>65280 && unicode<65375)
            {
                alert("不能输入全角字符，请输入半角字符");
                obj.value=str.substr(0,i);
            }
        }
    }
}
function quanjiao(obj)
{
    var str=obj.value;
    if (str.length>0)
    {
        for (var i = str.length-1; i >= 0; i--)
        {
            unicode=str.charCodeAt(i);
            if (unicode>65280 && unicode<65375 || unicode == 12288)
            {
                alert("不能输入全角字符，请输入半角字符");
                obj.value=str.substr(0,i);
            }
        }
    }
}