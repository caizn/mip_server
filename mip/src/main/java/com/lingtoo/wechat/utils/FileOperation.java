package com.lingtoo.wechat.utils;


import java.io.File;

public class FileOperation {

	/**
	 * 获取文件扩展名
	 * @author hongjz
	 * @date 2008-11-6
	 * @param fileName 文件名(字符串)
	 * @return string 文件扩展名
	 */
	public static String getExtension(String fileName) {
		String[] arr = fileName.split("\\.");
		if(arr.length == 1) {
			return "";
		} else {
			return arr[arr.length-1];
		}
	}
	/**
	* 删除目录(删除目录必须递归删除目录下的所有文件以及文件夹)
	* @param path 路径
	*/
	public static void delDir(String path) {
		File dir = new File(path);
		if (dir.exists()) {
		
			dir.delete();
		}
	}
}