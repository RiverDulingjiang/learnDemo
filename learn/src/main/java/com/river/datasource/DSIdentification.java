package com.river.datasource;

public class DSIdentification {
	/**
     * 用不同的工程编码来区分数据库
     * ThreadLocal用于保存某个线程共享变量：对于同一个static ThreadLocal，不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量
     */
    private static ThreadLocal<String> identification = new ThreadLocal<String>();
    /**
     * 获取当前线程的数据库标识
     * @Description: TODO
     * @date 2018年8月25日
     * @return
     */
    public static String getIdentification() {
		return identification.get();
	}
	/**
	 * 设置当前线程的数据库标识
	 * @Description: TODO
	 * @date 2018年8月25日
	 * @param datasource
	 */
	public static void setIdentification(String datasource) {
		identification.set(datasource);
	}

}
