package com.river.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DSIdentification {
	private static final Logger log = LoggerFactory.getLogger(DSIdentification.class);
	/**
     * 用不同的工程编码来区分数据库
     * ThreadLocal用于保存某个线程共享变量：对于同一个static ThreadLocal，不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量
     */
    private static final ThreadLocal<String> identification = new ThreadLocal<String>();
    /** 
     * @Description: 获取当前线程的数据库标识
     * @date 2018年8月25日
     * @return
     */
    public static String getIdentification() {
		return identification.get();
	}
	/**
	 * @Description: 设置当前线程的数据库标识
	 * @date 2018年8月25日
	 * @param datasource
	 */
	public static void setIdentification(String datasource) {
		log.info("正在更改数据源为："+datasource);
		identification.set(datasource);
	}

	/**
	 * @Description: 移除
	 * @date 2018年9月20日
	 */
	public static void remove() {
		identification.remove();
	}
}
