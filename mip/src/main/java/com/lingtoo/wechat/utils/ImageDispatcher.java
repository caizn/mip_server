package com.lingtoo.wechat.utils;

/**
 * 查找图片地址
 */

import java.io.File;
/**
 * 
 * @author cwj
 *
 *
 */
public class ImageDispatcher {
	
	/**
	 * 从数据库中得到图片地址	
	 * @param filePath		/img/2007/10/18/15/T3NHUG4MRO1D.jpg
	 * @param type			1,2,3
	 * @return
	 */
	public static String getImg(String filePath){
		String imgPath = "";
	
		if (filePath == null) {

			return null;
		}
		
		File file = ImageDispatcher.findFile(filePath);
		if (file == null) {
			//原图片不存在！
			return null;
		} else {
			imgPath = SystemConfig.getImageServer() + filePath;
		}
		return imgPath;
	}
		
	
	/**
	 * 根据相对路径查找真实路径,如果不存在返回空
	 * @param absPath 2000/10/10/19/9GZJO8QN1QWX.jpg
	 * @return 
	 */
	public static File findFile(String filePath) {
		File file = new File(SystemConfig.getAbsolutePath() + filePath);
		if (file.isFile()) {
			return file;
		}
		return null;
	}

}
