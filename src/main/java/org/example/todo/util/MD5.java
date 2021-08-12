package org.example.todo.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @类功能说明：  MD5加密
 * @公司名称：artfire 
 * @作者：lyj  
 * @创建时间：2017年9月29日 下午6:26:46  
 * @版本：V1.0
 * @类修改者：  
 * @修改日期：  
 * @修改说明：
 */
public class MD5 {
	public MD5() {
	}

	/**
	 * 计算MD5值.
	 *
	 * @param sourceData String
	 * @return String MD5计算结果
	 * @since BASE 0.1
	 */
	public static String getMD5(String sourceData) {
		return getMD5Digest(sourceData.getBytes());
	}

	/**
	 * 直接对byte数组进行加密
	 * @param sourceData 需要加密的byte数组
	 * @return String 返回MD5计算结果字符串
	 */
	public static String getMD5Digest(byte[] sourceData) {
		try {
			MessageDigest alga = MessageDigest.getInstance("MD5");
			alga.update(sourceData);
			byte[] digesta = alga.digest();
			return DataUtil.byteToHexString(digesta).toUpperCase();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 方法功能说明：  验证密码是否相同
	 * 创建：2017年9月29日 下午6:27:04  by lyj   
	 * @参数： @param password
	 * @参数： @param inputString
	 * @参数： @return      
	 * @return boolean     
	 * @throws
	 * @修改内容：
	 */
	public static boolean validatePassword(String password, String inputString) {
		if (password.equals(getMD5(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getMD5("admin"));
		System.out.println(validatePassword("96E79218965EB72C92A549DD5A330112", "111111"));
	}

}
