package com.river.basic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 工具类
 * @author River
 * @date 2018年9月10日
 */
public class Tools {
	/**
	 * @Description: 随机UUID
	 * @date 2018年9月10日
	 * @return
	 */
	public static String randomUUID(){		
		return UUID.randomUUID().toString();
	}
	
	/**
	 * @Description: 日期转字符串
	 * @date 2018年9月12日
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		String dateString = format.format(date);
		return dateString;
	}
}
