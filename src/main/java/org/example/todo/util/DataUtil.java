package org.example.todo.util;

import java.util.Collection;
import java.util.Locale;


public class DataUtil {

	/**
	 * 判断list是否为空,true是不为空，FALSE是空
	 * 
	 * @return
	 */
	public static Boolean judgeListIsNotNull(Collection<?> list) {
		Boolean bl = false;
		try {
			if (list != null) {
				if (!list.isEmpty()) {
					if (list.size() > 0) {
						bl = true;
					} else {
						bl = false;
					}
				} else {
					bl = false;
				}
			} else {
				bl = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bl;
	}

	/**
	 * 判断list是否为空,true是不为空，FALSE是空
	 * 
	 * @return
	 */
	public static Boolean judgeListIsNull(Collection<?> list) {
		Boolean bl = false;
		try {
			if (list != null) {
				if (!list.isEmpty()) {
					if (list.size() > 0) {
						bl = true;
					} else {
						bl = false;
					}
				} else {
					bl = false;
				}
			} else {
				bl = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bl;
	}



	/**
	 * 判断是什么系统
	 * 
	 * @return
	 */
	public static int judgeOsName() {
		int os = 0;
		try {
			String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.US);
			String name = OS_NAME.substring(0, 3);
			if (name.equals("win")) {// windows系统
				os = 1;
			} else if (name.equals("lin")) {// linux系统
				os = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return os;
	}

	/**
	 * 非空验证
	 * 
	 * @param value
	 * @return 空 true 非空false
	 */
	public static final boolean isNull(String value) {
		boolean result = false;
		if (value == null || value.trim().length() == 0 || value.equals("null")) {
			result = true;
		}
		return result;
	}


	/**
	 * 二行数据制转换成十六进制字符串.
	 * 
	 * @param b
	 *            byte[] 字节流
	 * @return String 十六进制字符串
	 * @since BASE 0.1
	 */
	public static String byteToHexString(byte[] b) { //

		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}

			if (n < b.length - 1)
				hs = hs + "";
		}
		// return hs.toUpperCase();
		return hs;
	}


	public static void main(String[] args) {


	}
}
