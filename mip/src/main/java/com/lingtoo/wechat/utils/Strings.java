package com.lingtoo.wechat.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lingtoo.common.utils.StringUtils;



public class Strings {
	
	/**
	 * 数组去重
	 * @param arrays
	 * @return
	 */
	public static String[] removeRepeat(String[] arrays){
		if(arrays==null||arrays.length==0)return null;
	    List<String> list = new ArrayList<String>();
	    for (int i=0; i<arrays.length; i++) {
		if(!list.contains(arrays[i])) {//如果数组 list 不包含当前项，则增加该项到数组中
			list.add(arrays[i]);
			}
	    }
	    String[] newStr =  list.toArray(new String[1]); 
	    return newStr;
	}
	
    /**
     * 随机数
     * @param num
     * @return
     */
    public  static String getRandomNum(String beginNum,int count){
		
		  	for(int i=0;i<count;i++){
		  		int j = (int)(java.lang.Math.random()*10);
		  		beginNum+=String.valueOf(j);
		  	}
		  	return beginNum;
	} 
    
    public static List<String> numToCharList(int i){
    	List<String> list=new ArrayList<String>();
    	for(int k=1;k<=i;k++){
    		list.add(Strings.numToEnChar(k));
    	}
    	return list;
    }

	public static String numToEnChar(int i){
		if(i==1)return "A";
		else if(i==2)return "B";
		else if(i==3)return "C";
		else if(i==4)return "D";
		else if(i==5)return "E";
		else if(i==6)return "F";
		else if(i==7)return "G";
		else if(i==8)return "H";
		else if(i==9)return "I";
		else if(i==10)return "J";
		else if(i==11)return "K";
		else if(i==12)return "L";
		else if(i==13)return "M";
		else if(i==14)return "N";
		else if(i==15)return "O";
		else if(i==16)return "P";
		else if(i==17)return "Q";
		else if(i==18)return "R";
		else if(i==19)return "S";
		else if(i==20)return "T";
		else if(i==21)return "U";
		else if(i==22)return "V";
		else if(i==23)return "W";
		else if(i==24)return "X";
		else if(i==25)return "Y";
		else if(i==26)return "Z";
		return "";
	}
	/**
	 * 多数组转双人赛
	 * @return
	 */
	public static List<String[]> arrayToDouble(String[] array){
		Map<String,String> map=new HashMap<String,String>();
		List<String[]>  list=new ArrayList<String[]>();
		int n=2;
		String[] b=new String[n];
		submit(array,0,0,n,b,map);
		Iterator iter = map.keySet().iterator();
		while(iter.hasNext()){
			String str = (String)map.get( (String)iter.next());
			list.add(str.split(","));
		}
			
		return list;
	}
	
	public static void submit(String[] a,int c,int i,int n,String[] b,Map<String,String> map) {

		//a原始数组，c循环的开始值，i数组b索引，n控制递归的次数，b存放结果的数组 
		for(int j=c;j<a.length-(n-1);j++)  {   
			b[i]=a[j];   
			if(n==1)   {    
				String str="";
				for(String o:b){
					if(!StringUtils.isEmpty(o))
					str+=(StringUtils.isEmpty(str)?"":",")+o.trim();
				}
				map.put(str,str);
				} else   {    
					n--;    
					i++;    
				  submit(a,j+1,i,n,b,map);
				  //递归调用    
				  n++;
				  //还原n,i的值    
				  i--;  
				}
			} 

	}
	
//	public static void main(String[] arge){
//		System.out.println(arrayToDouble(new String[]{"1","2","3","4"}));
//	}
}
