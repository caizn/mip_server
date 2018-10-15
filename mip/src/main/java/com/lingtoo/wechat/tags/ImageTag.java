package com.lingtoo.wechat.tags;



import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.google.common.base.Strings;
import com.lingtoo.wechat.utils.ImageDispatcher;

/**
 * 通过数据库保存的图片路径 得到完整的图片URL
 * @author cwj
 * @version 2011-11-30
 *
 */
public class ImageTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private String src;//图片的路径
	private String srcBak;//备用图片路径
	private String srcHead;//头像
	
	public int doStartTag()throws JspException  {                                        
	       JspWriter out=pageContext.getOut(); 
	       try                                
	       {   	 
	    	   if(!Strings.isNullOrEmpty(src)&&src.indexOf("http://")!=-1){
	    		   out.println(src.trim());
	    	   }else{
	    	 	   StringBuffer imgStr=new StringBuffer();
		    	   String image=ImageDispatcher.getImg(src);
		    	   if(srcHead==null){
		    		   imgStr.append(image==null ? srcBak : image);
			    	   out.println(imgStr.toString().trim());
		    	   }else{
		    		   imgStr.append(image==null ? srcHead : image);
			    	   out.println(imgStr.toString().trim());
		    	   }
	    	   }
	   
	    	 
	       }catch(IOException e)               
	       {                                   
	           throw new JspException(e);      
	       }                                   
	       return SKIP_BODY; //不包含主体内容  
	}
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getSrcBak() {
		return srcBak;
	}
	public void setSrcBak(String srcBak) {
		this.srcBak = srcBak;
	}

	public String getSrcHead() {
		return srcHead;
	}

	public void setSrcHead(String srcHead) {
		this.srcHead = srcHead;
	}


	
	
}
