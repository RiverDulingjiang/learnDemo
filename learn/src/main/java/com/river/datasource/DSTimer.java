package com.river.datasource;

import org.apache.tomcat.jdbc.pool.DataSource;
 
/**
 * 数据源计时器：判断传入的数据源是否需要被关闭
 * @author River
 * @date 2018年8月25日
 */
public class DSTimer {
 
    /**
     * 数据库空闲时间
     */
    private static long keepTime = 10 * 60 * 1000;
    /**
     * 上一次访问的时间
     */
    private long lastAccessTime;
    /**
     * 动态数据源
     */
    private DataSource  dynamicDataSource;
 
    public DSTimer(DataSource ds) {
        this.dynamicDataSource = ds;
        this.lastAccessTime = System.currentTimeMillis();
    }
    
    /**
     * @Description:  更新最近访问时间
     * @date 2018年8月25日
     */
    public void refreshTime() {
    	lastAccessTime = System.currentTimeMillis();
    } 
    
    /**
     * @Description: 数据源是否活跃
     * @date 2018年8月25日
     * @return true：活跃；false:不活跃，关闭数据源
     */
    public boolean isLively() {
        if(System.currentTimeMillis() - lastAccessTime > keepTime){
        	dynamicDataSource.close();
            return false;
        }
        return true;
    } 
    
    /**
     * @Description: 获取数据源
     * @date 2018年8月25日
     * @return
     */
    public DataSource getDynamicDataSource() {
        return dynamicDataSource;
    }
}
