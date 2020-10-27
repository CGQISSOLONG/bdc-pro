package com.bdc.util;

import com.bdc.common.Constants;

/**
 * Copyright(C) 
 *
 * Module: 
 * @author  
 * @version
 * @see
 * @description: <MD5加密>
 * @log:
 */
public class MD5 {

	private static final MD5 INSTANCE = new MD5();
	/**
	 * @todo Returns a new String of MD5 for input String.
	 * @return MD5
	 */
	public static MD5 getInstance() {
		return INSTANCE;
	}

	public synchronized String getMD5(String str) {
		return StringUtil.hash(str);
	}

	 
	public static void main(String[] args) {
		MD5 md5 = getInstance();
		//System.out.println("MD5(\"abcefg\"):" + md5.getMD5("150127007"+ System.currentTimeMillis()+ Constants.MD5_KEY));
		System.out.println("MD5(\"abcefg\"):" + md5.getMD5("admin"+ System.currentTimeMillis()+ Constants.MD5_KEY));
	}
}
