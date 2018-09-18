package com.river.datasource;

/**
 * 自定义数据库异常
 * @author River
 * @date 2018年9月18日
 */
public class DatabaseException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg){
		
	}
	/**
	 * 异常提示
	 */
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
