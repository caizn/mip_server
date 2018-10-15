package com.lingtoo.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Des {
	private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};

	public static String encryptDES(String encryptString, String encryptKey) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
		return Base64.encodeBytes(encryptedData);
	}
	
	public static String decryptDES(String decryptString, String encryptKey) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		dcipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte[] decryptedData = dcipher.doFinal(Base64.decode(decryptString));
		return new String(decryptedData);
	}
	
	public static String EncoderByMd5(String str) {
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return new BigInteger(1, md.digest()).toString(16);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws Exception {
		String encryptString = encryptDES("1339469459106_10155", "besprout");
		System.out.println(encryptString);
		String decryptString = decryptDES(encryptString, "besprout");
		System.out.println(decryptString);
		System.out.println(EncoderByMd5("123456"));
	}

}
