package com.lingtoo.wechat.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtils {
	public static void cutJPG(InputStream input, OutputStream out, int x,  
            int y, int width, int height) throws IOException {  
        ImageInputStream imageStream = null;  
        try {  
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");  
            ImageReader reader = readers.next();  
            imageStream = ImageIO.createImageInputStream(input);  
            reader.setInput(imageStream, true);  
            ImageReadParam param = reader.getDefaultReadParam();  
              
            System.out.println(reader.getWidth(0));  
            System.out.println(reader.getHeight(0));  
            Rectangle rect = new Rectangle(x, y, width, height);  
            param.setSourceRegion(rect);  
            BufferedImage bi = reader.read(0, param);  
        } finally {  
            imageStream.close();  
        }  
    }  
      
      
    public static void cutPNG(InputStream input, OutputStream out, int x,  
            int y, int width, int height) throws IOException {  
        ImageInputStream imageStream = null;  
        try {  
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");  
            ImageReader reader = readers.next();  
            imageStream = ImageIO.createImageInputStream(input);  
            reader.setInput(imageStream, true);  
            ImageReadParam param = reader.getDefaultReadParam();  
              
            System.out.println(reader.getWidth(0));  
            System.out.println(reader.getHeight(0));  
              
            Rectangle rect = new Rectangle(x, y, width, height);  
            param.setSourceRegion(rect);  
            BufferedImage bi = reader.read(0, param);  
            ImageIO.write(bi, "png", out);  
        } finally {  
            imageStream.close();  
        }  
    }  
      
    public static BufferedImage cutImage(InputStream input, String type,int x,  
            int y, int width, int height) throws IOException {  
        ImageInputStream imageStream = null;  
        try {  
            String imageType=(null==type||"".equals(type))?"jpg":type;  
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(imageType);  
            ImageReader reader = readers.next();  
            imageStream = ImageIO.createImageInputStream(input);  
            reader.setInput(imageStream, true);  
            ImageReadParam param = reader.getDefaultReadParam();  
            Rectangle rect = new Rectangle(x, y, width, height);  
            param.setSourceRegion(rect);  
            BufferedImage bi = reader.read(0, param);  
            return bi; 
        } finally {  
            //imageStream.close();  
        }
    }  
    
    
    public static String mgrImgByUrl(String imagePath,String imgPath, Integer x,
			Integer y, Integer width, Integer height) {
    	File file=new File(imagePath  + imgPath);
    	if(file.exists()){;
    		try {
        		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
    			String type=imgPath.substring(imgPath.indexOf(".")+1,imgPath.length());
    			BufferedImage img=ImageUtils.cutImage(bis, type.toLowerCase(), x, y, width, height);
                ImageIO.write(img, type, new FileOutputStream(imagePath  + imgPath));  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return imgPath;
    }
  
      
    public static void main(String[] args) throws Exception {  
        ImageUtils.cutPNG(new FileInputStream("C:\\Users\\Administrator\\Desktop\\face.png"),  
                  new FileOutputStream("c:\\face2.png"), 0,0,200,100);  
          
        /*ImageUtils.cutPNG(new FileInputStream("c:\\1.png"),  
                new FileOutputStream("c:\\test3.png"), 0,0,50,40);  */
    }  
}
