package com.lingtoo.wechat.utils;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

public class UploadImgUtil {
	public static BufferedImage dealImg(InputStream is, String type,String path,Integer rotate) {
		if(!StringUtil.isEmpty(type)){
			try {
				ImageInputStream imageStream = null;
				Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(type);
				ImageReader reader = readers.next();
				imageStream = ImageIO.createImageInputStream(is);
				reader.setInput(imageStream, true);
				ImageReadParam param = reader.getDefaultReadParam();

				/*System.out.println(reader.getWidth(0));
				System.out.println(reader.getHeight(0));*/

				BufferedImage bi = reader.read(0, param);
				
				suoxiaoDeal(reader, 750, bi, type, path, null,rotate);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static BufferedImage dealImg(MultipartFile bannerPath, String type) {

		ImageInputStream imageStream = null;
		try {
			Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(type);
			ImageReader reader = readers.next();
			imageStream = ImageIO.createImageInputStream(bannerPath.getInputStream());
			reader.setInput(imageStream, true);
			ImageReadParam param = reader.getDefaultReadParam();

			/*System.out.println(reader.getWidth(0));
			System.out.println(reader.getHeight(0));*/

			int width = reader.getWidth(0);
			int height = reader.getHeight(0);
			int y = 0, x = 0;

			if (height > width) {
				y = (height - width) / 2;
				x = 0;
				height = width;
			} else {
				x = (width - height) / 2;
				y = 0;
				width = height;
			}
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			return bi;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				imageStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	
	public static BufferedImage suoxiaoImg(MultipartFile bannerPath, String type, String filePath, Float bili,
			Integer maxSize,Integer rotate) {
		ImageInputStream imageStream = null;
		try {
			Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(type);
			ImageReader reader = readers.next();
			imageStream = ImageIO.createImageInputStream(bannerPath.getInputStream());
			reader.setInput(imageStream, true);
			ImageReadParam param = reader.getDefaultReadParam();

			/*System.out.println(reader.getWidth(0));
			System.out.println(reader.getHeight(0));*/

			BufferedImage bi = reader.read(0, param);

			if (maxSize == null)
				maxSize = 640;
			suoxiaoDeal(reader, maxSize, bi, type, filePath, bili,rotate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (imageStream != null)
					imageStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static BufferedImage suoxiaoDeal(ImageReader reader, Integer maxSize,
			BufferedImage bi, String type, String filePath, Float bili,Integer rotate) {
		try {
			Integer width = reader.getWidth(0);
			Integer height = reader.getHeight(0);
			if(rotate!=null){
				if(rotate!=0){
					bi=UploadImgUtil.Rotate(bi, rotate);
					if(rotate==90||rotate==270){
						width = reader.getHeight(0);
						height = reader.getWidth(0);
					}
				}
			}
			if(type.equals("png")||type.equals("jpg")){
				if (height > maxSize && height > width) {
					bi.flush();

					ByteArrayOutputStream bs = new ByteArrayOutputStream();
					InputStream is = null;
					ImageOutputStream imOut;
					try {
						imOut = ImageIO.createImageOutputStream(bs);

						ImageIO.write(bi, type, imOut);

						is = new ByteArrayInputStream(bs.toByteArray());

					} catch (IOException e) {
						e.printStackTrace();
					}
					String newwidthString = "";
					if (bili == null){
						newwidthString = String.valueOf(
								Float.valueOf(String.valueOf(width)) / Float.valueOf(String.valueOf(height)) * maxSize);
					}else{
						newwidthString = String.valueOf(maxSize * bili);
					}

					int newwidth = Integer.valueOf(newwidthString.substring(0, newwidthString.indexOf(".")));
					File file=new File(filePath);
					if(!file.isDirectory()) file.getParentFile().mkdirs();
					Thumbnails.of(is).size(newwidth, maxSize).keepAspectRatio(false).toFile(file);
				} else if (width > maxSize) {

					bi.flush();

					ByteArrayOutputStream bs = new ByteArrayOutputStream();
					InputStream is = null;
					ImageOutputStream imOut;
					try {
						imOut = ImageIO.createImageOutputStream(bs);

						ImageIO.write(bi, type, imOut);

						is = new ByteArrayInputStream(bs.toByteArray());

					} catch (IOException e) {
						e.printStackTrace();
					}
					String newheightString = "";
					if (bili == null){
						newheightString = String.valueOf(
								Float.valueOf(String.valueOf(height)) / Float.valueOf(String.valueOf(width)) * maxSize);
					}else{
						newheightString = String.valueOf(maxSize / bili);
					}
					int newheight = Integer.valueOf(newheightString.substring(0, newheightString.indexOf(".")));
					File file=new File(filePath);
					if(!file.isDirectory()) file.getParentFile().mkdirs();
					Thumbnails.of(is).size(maxSize, newheight).keepAspectRatio(false).toFile(file);
				} else {
					//File file = new File(filePath);
					File file=new File(filePath);
					if(!file.isDirectory()) file.getParentFile().mkdirs();
					ImageIO.write(bi, type, new FileOutputStream(file));
					//FileUtils.writeByteArrayToFile(file, bannerPath.getBytes());
				}
			}else{
				File file=new File(filePath);
				if(!file.isDirectory()) file.getParentFile().mkdirs();
				ImageIO.write(bi, type, new FileOutputStream(file));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static final InputStream byte2Input(byte[] buf) {  
        return new ByteArrayInputStream(buf);  
    }  
  
    public static final byte[] input2byte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
        }  
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    }  
    
    /**
     * 图片旋转
     * @param src
     * @param angel
     * @return
     */
    public static BufferedImage Rotate(Image src, int angel) {  
        int src_width = src.getWidth(null);  
        int src_height = src.getHeight(null);  
        // calculate the new image size  
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(  
                src_width, src_height)), angel);  
  
        BufferedImage res = null;  
        res = new BufferedImage(rect_des.width, rect_des.height,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics2D g2 = res.createGraphics();  
        // transform  
        g2.translate((rect_des.width - src_width) / 2,  
                (rect_des.height - src_height) / 2);  
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);  
  
        g2.drawImage(src, null, null);  
        return res;  
    }  

    /**
     * 图片旋转
     * @param src
     * @param angel
     * @return
     */
    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {  
        // if angel is greater than 90 degree, we need to do some conversion  
        if (angel >= 90) {  
            if(angel / 90 % 2 == 1){  
                int temp = src.height;  
                src.height = src.width;  
                src.width = temp;  
            }  
            angel = angel % 90;  
        }  
  
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;  
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;  
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;  
        double angel_dalta_width = Math.atan((double) src.height / src.width);  
        double angel_dalta_height = Math.atan((double) src.width / src.height);  
  
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha  
                - angel_dalta_width));  
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha  
                - angel_dalta_height));  
        int des_width = src.width + len_dalta_width * 2;  
        int des_height = src.height + len_dalta_height * 2;  
        return new java.awt.Rectangle(new Dimension(des_width, des_height));  
    }  
}
