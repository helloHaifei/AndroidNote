package com.zhf.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;

public class DipHelper {
	/**
	 * dip to px
	 * 
	 * @param c
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context c, float dipValue) {
		final float scale = c.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px to dip
	 * 
	 * @param c
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context c, float pxValue) {
		final float scale = c.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	public static int getDisplayWidth(Context c){
		return c.getResources().getDisplayMetrics().widthPixels;
	}
	public static int getDisplayHeight(Context c){
		return c.getResources().getDisplayMetrics().heightPixels;
	}
	/**
	 * 判断字符串中是否含有数字
	 * @param value
	 * @return
	 */
	public static boolean isContainsNum(String value){
	  	Pattern pattern=Pattern.compile("\\d");  
        Matcher matcher=pattern.matcher(value.substring(value.indexOf("/")));
        return matcher.find();
		
	}
	/**
	 * 判断str字符串中包含element字符串的个数
	 * @param String str 比较长的串
	 * @param String element 所包含的串
	 * @return int  count
	 */
	public static int getElementCount(String str, String element) {
		int count = 0;
		char c = element.charAt(0);
		for (int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);
			if (Character.valueOf(c).equals(Character.valueOf(tmp))) {
				count++;
			}
		}
		return count;
	}
}
