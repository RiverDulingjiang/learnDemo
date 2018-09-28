package com.river.basic.exception;

/**
 * Session失效异常或者session为空
 * @author River
 * @date 2018年9月28日
 */
public class SessionException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SessionException(String msg){
		this.msg =msg;
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
