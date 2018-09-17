package com.river.basic;

/**
 * 常量
 * @author Administrator
 *
 */
public class Constant {
	
	public static final String DATABASIC_MAIN = "_mainDataBase";
	/**
	 * 未登陆
	 */
	public static final Integer LOGIN_STATUS_NOT = 1000;
	/**
	 * 账户处于冻结状态
	 */
	public static final Integer LOGIN_STATUS_FREEZE = 100;
	/**
	 * 登录人session
	 */
	public static final String LOGIN_USER_SESSION = "user_session";
	
	/**
	 * 请求成功
	 */
	public static final Integer RESULT_SUCCESS = 0;
	/**
	 * 请求失败
	 */
	public static final Integer RESULT_FAIL = 1;
	/**
	 * 请求无权限
	 */
	public static final Integer RESULT_NO_ACCESS = 2;
	/**
	 * 请求数据异常
	 */
	public static final Integer RESULT_EXCEPT = 3;

}
