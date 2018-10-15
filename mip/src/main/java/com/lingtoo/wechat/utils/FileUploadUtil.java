package com.lingtoo.wechat.utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifDirectory;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 文件上传
 * 
 * @author Dell
 *
 */
public class FileUploadUtil {

	public static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

	public static String uploadScaleImg(MultipartFile imageFile, String imagePath, String logoPath) {
		String path = "";
		try {

			// 取得当前时间
			Calendar c = Calendar.getInstance();
			String year = String.valueOf(c.get(Calendar.YEAR));
			String month = String.valueOf(c.get(Calendar.MONTH) + 1);
			if (month.length() == 1) {
				month = "0" + month;
			}
			logoPath += year + "/" + month + "/";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmSSS");
			String imgName = sdf.format(new Date()) + Strings.getRandomNum("1", 5) + "."
					+ FileOperation.getExtension(imageFile.getOriginalFilename()).toLowerCase();
			logger.info("---------------image path=" + (imagePath + logoPath + imgName));
			File f = new File(imagePath + logoPath);
			if (!f.exists()) {
				if (!f.mkdirs()) {

				}
			}

			BufferedImage srcBufferImage = ImageIO.read(imageFile.getInputStream());

			// BufferedImage scaledImage;
			// ScaleImage scaleImage = ScaleImage.getInstance();
			int yw = srcBufferImage.getWidth();
			int yh = srcBufferImage.getHeight();
			int w = 400, h = 400;
			// 如果上传图片 宽高 比 压缩的要小 则不压缩
			if (w > yw && h > yh) {

				imageFile.transferTo(new File(imagePath + logoPath + imgName));
			} else {

				// FileOutputStream out = new
				// FileOutputStream(imagePath+logoPath+imgName);
				// int type = srcBufferImage.getType();
				// BufferedImage target = null;
				// if (type == BufferedImage.TYPE_CUSTOM) { // handmade
				// ColorModel cm = srcBufferImage.getColorModel();
				// WritableRaster raster = cm.createCompatibleWritableRaster(w,
				// h);
				// boolean alphaPremultiplied = cm.isAlphaPremultiplied();
				// target = new BufferedImage(cm, raster, alphaPremultiplied,
				// null);
				// } else {
				// target = new BufferedImage(w, h, type);
				// }
				// Graphics2D g = target.createGraphics();
				// // smoother than exlax:
				// g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
				//
				// g.drawRenderedImage(srcBufferImage,
				// AffineTransform.getScaleInstance(yw, yh));
				// g.dispose();
				// ImageIO.write(target,"jpeg",out);

				// int angel=getRotateAngleForPhoto(imageFile);
				//
				// Rectangle rect_des = CalcRotatedSize(new Rectangle(new
				// Dimension(yw, yh)), angel);
				//
				// BufferedImage res;
				//
				// int type = srcBufferImage.getType();
				// if (type == BufferedImage.TYPE_CUSTOM) { // handmade
				// ColorModel cm = srcBufferImage.getColorModel();
				// WritableRaster raster = cm.createCompatibleWritableRaster(w,
				// h);
				// boolean alphaPremultiplied = cm.isAlphaPremultiplied();
				// res = new BufferedImage(cm, raster, alphaPremultiplied,
				// null);
				// } else {
				// res = new BufferedImage(w, h, type);
				// }
				//
				// Graphics2D g2 = res.createGraphics();
				//
				// g2.translate((rect_des.width - yw) / 2,
				// (rect_des.height - yh) / 2);
				// g2.rotate(Math.toRadians(angel), yw / 2, yh / 2);
				//
				// g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
				//
				// g2.drawRenderedImage(srcBufferImage,
				// AffineTransform.getScaleInstance(yw, yh));
				// g2.dispose();
				//
				// FileOutputStream out = new
				// FileOutputStream(imagePath+logoPath+imgName);
				// ImageIO.write(res,"jpeg",out);

				// System.out.println(angel);
				// Thumbnails.of(imageFile.getInputStream()).size(w,h).keepAspectRatio(false).rotate(angel).toFile(new
				// File(imagePath+logoPath+imgName));
				Thumbnails.of(imageFile.getInputStream()).size(w, h).keepAspectRatio(false)
						.toFile(new File(imagePath + logoPath + imgName));

			}

			path = logoPath + imgName;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

	public static String uploadResourceScaleImg(MultipartFile imageFile, String imagePath, String logoPath) {
		String path = "";
		try {

			// 取得当前时间
			Calendar c = Calendar.getInstance();
			String year = String.valueOf(c.get(Calendar.YEAR));
			String month = String.valueOf(c.get(Calendar.MONTH) + 1);
			if (month.length() == 1) {
				month = "0" + month;
			}
			logoPath += year + "/" + month + "/";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmSSS");
			String imgName = sdf.format(new Date()) + Strings.getRandomNum("1", 5) + "."
					+ FileOperation.getExtension(imageFile.getOriginalFilename()).toLowerCase();
			logger.info("---------------image path=" + (imagePath + logoPath + imgName));
			File f = new File(imagePath + logoPath);
			if (!f.exists()) {
				if (!f.mkdirs()) {

				}
			}

			BufferedImage srcBufferImage = ImageIO.read(imageFile.getInputStream());

			// BufferedImage scaledImage;
			// ScaleImage scaleImage = ScaleImage.getInstance();
			// 如果上传图片 宽高 比 压缩的要小 则不压缩
			int yw = srcBufferImage.getWidth();
			int yh = srcBufferImage.getHeight();
			int w = 400, h = 400;
			// 如果上传图片 宽高 比 压缩的要小 则不压缩
			if (w > yw && h > yh) {
				imageFile.transferTo(new File(imagePath + logoPath + imgName));
			} else {
				int neww = 0, newh = 0;
				if (w < yw && h < yh) {
					if (yw > yh) {
						neww = 400;
						newh = 400 * yh / yw;
					} else {
						newh = 400;
						neww = 400 * yw / yh;
					}
				} else if (w < yw) {
					neww = 400;
					newh = 400 * yh / yw;
				} else {
					newh = 400;
					neww = 400 * yw / yh;
				}
				Thumbnails.of(imageFile.getInputStream()).size(neww, newh).keepAspectRatio(false)
						.toFile(new File(imagePath + logoPath + imgName));
			}

			path = logoPath + imgName;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

	public static String uploadScaleHeadImg(MultipartFile imageFile, String imagePath, String logoPath,Integer height,Integer width) {
		String path = "";
		try {

			// 取得当前时间
			Calendar c = Calendar.getInstance();
			String year = String.valueOf(c.get(Calendar.YEAR));
			String month = String.valueOf(c.get(Calendar.MONTH) + 1);
			if (month.length() == 1) {
				month = "0" + month;
			}
			logoPath += year + "/" + month + "/";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmSSS");
			String imgName = sdf.format(new Date()) + Strings.getRandomNum("1", 5) + "."
					+ FileOperation.getExtension(imageFile.getOriginalFilename()).toLowerCase();
			logger.info("---------------image path=" + (imagePath + logoPath + imgName));
			File f = new File(imagePath + logoPath);
			if (!f.exists()) {
				if (!f.mkdirs()) {

				}
			}

			String type = imgName.substring(imgName.indexOf(".") + 1, imgName.length()).toLowerCase();
			//BufferedImage srcBufferImage = ImageUtils.cutImage(imageFile.getInputStream(), type, x, y, width, height);// ImageIO.read(imageFile.getInputStream());

			int y=0,x=0;
			if(height>width){
				y=(height-width)/2;
				x=0;
				height=width;
			}else{
				x=(width-height)/2;
				y=0;
				width=height;
			}
			BufferedImage srcBufferImage = ImageUtils.cutImage(imageFile.getInputStream(), type, x, y, width, height);
			if(height>400){
				srcBufferImage.flush(); 
		         
			        ByteArrayOutputStream bs = new ByteArrayOutputStream();  
			        InputStream is = null; 
			        ImageOutputStream imOut; 
			        try { 
			            imOut = ImageIO.createImageOutputStream(bs); 
			             
			            ImageIO.write(srcBufferImage, "png",imOut); 
			             
			            is= new ByteArrayInputStream(bs.toByteArray()); 
			             
			        } catch (IOException e) { 
			            e.printStackTrace(); 
			        }  
				Thumbnails.of(is).size(400, 400).keepAspectRatio(false)
						.toFile(new File(imagePath + logoPath + imgName));
			}else{
	            ImageIO.write(srcBufferImage, type, new FileOutputStream(imagePath + logoPath + imgName)); 
			}
 
			
			/*Thumbnails.of(imageFile.getInputStream()).size(w, h).keepAspectRatio(false)
						.toFile(new File(imagePath + logoPath + imgName));*/

			path = logoPath + imgName;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

	/**
	 * 获取图片正确显示需要旋转的角度（顺时针）
	 * 
	 * @return
	 * @throws IOException
	 */
	public static int getRotateAngleForPhoto(MultipartFile imageFile) throws IOException {

		int angle = 0;

		Metadata metadata;
		try {
			metadata = JpegMetadataReader.readMetadata(imageFile.getInputStream());
			Directory directory = metadata.getDirectory(ExifDirectory.class);
			if (directory.containsTag(ExifDirectory.TAG_ORIENTATION)) {

				// Exif信息中方向
				int orientation = directory.getInt(ExifDirectory.TAG_ORIENTATION);

				// 原图片的方向信息
				if (6 == orientation) {
					// 6旋转90
					angle = 90;
				} else if (3 == orientation) {
					// 3旋转180
					angle = 180;
				} else if (8 == orientation) {
					// 8旋转90
					angle = 270;
				}
			}
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (MetadataException e) {
			e.printStackTrace();
		}

		return angle;
	}

	public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
		if (angel >= 90) {
			if (angel / 90 % 2 == 1) {
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

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new Rectangle(new Dimension(des_width, des_height));
	}

}
