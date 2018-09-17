package com.river.datasource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 数据源配置工具
 * @author River
 * @date 2018年8月29日
 */
/**
 * @author River
 * @date 2018年9月17日
 */
public class DBProperties {
	public static String URL="url";
	public static String USER_NAME="username";
	public static String PASSWORD="password";
	public static String DRVIER_CLASS_NAME="driverClassName";
	
	
	/**
	 * 存储所有的数据源信息，永远有效！
	 * --其他数据源可以在需要的时候加入
	 */
	private static Map<String, Properties> DBMap = new HashMap<String, Properties>();
	
    private static DBProperties instance = new DBProperties();
    private DBProperties (){} 
    /**
     * @Description: 单例
     * @date 2018年8月29日
     * @return
     */
    public static DBProperties instance() {
        return instance;
    }
    /**
     * @Description: 设置新的数据源连接信息
     * @date 2018年8月29日
     * @param identification 数据源标识
     * @param p 数据源配置文件
     * @return
     */
    public static boolean setDBmap(String identification,Properties p) {
    	if(p!=null){
    		if(p.getProperty(DBProperties.URL)==null||p.getProperty(DBProperties.URL)==""){
    			throw new NullPointerException("数据库URL不能为null");
    		}
    		if(p.getProperty(DBProperties.USER_NAME)==null||p.getProperty(DBProperties.USER_NAME)==""){
    			throw new NullPointerException("数据库USER_NAME不能为null");
    		}
    		if(p.getProperty(DBProperties.PASSWORD)==null||p.getProperty(DBProperties.PASSWORD)==""){
    			throw new NullPointerException("数据库PASSWORD不能为null");
    		}
    		if(p.getProperty(DBProperties.DRVIER_CLASS_NAME)==null||p.getProperty(DBProperties.DRVIER_CLASS_NAME)==""){
    			throw new NullPointerException("数据库DRVIER_CLASS_NAME不能为null");
    		}
    		if(DBMap.containsKey(identification)){
				return false;
        	}else {
        		DBMap.put(identification, p);
        		return true;
    		} 
    	}else{
    		throw new NullPointerException("无数据库连接信息");
    	}     
    }
    /**
     * @Description: 移除某个数据源
     * @date 2018年9月17日
     * @param identification
     * @return
     */
    public static Map<String, Properties> removeDBmap(String identification){
    	DBMap.remove(identification);
    	return DBMap;
    }
    /**
     * @Description: 获取活跃的数据库信息
     * @date 2018年9月3日
     * @return
     */
    public static Map<String, Properties> getDBmap(){
    	return DBMap;
    }
    
    
	/**
	 * @Description: 获取某数据源的URL
	 * @date 2018年9月17日
	 * @param identification
	 * @return
	 */
	public static String getUrl(String identification) {
		return DBMap.get(identification).getProperty(DBProperties.URL);
	}
	/**
	 * @Description: 获取某数据源的username
	 * @date 2018年9月17日
	 * @param identification
	 * @return
	 */
	public static String getUsername(String identification) {		
		return DBMap.get(identification).getProperty(DBProperties.USER_NAME);
	}
	/**
	 * @Description: 获取某数据源的密码
	 * @date 2018年9月17日
	 * @param identification
	 * @return
	 */
	public static String getPassword(String identification) {
		return DBMap.get(identification).getProperty(DBProperties.PASSWORD);
	}
	/**
	 * @Description: 获取某数据源的驱动
	 * @date 2018年9月17日
	 * @param identification
	 * @return
	 */
	public static String getDriverClassName(String identification) {
		return DBMap.get(identification).getProperty(DBProperties.DRVIER_CLASS_NAME);
	}
}
