package com.river.main;


import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.basic.Constant;
import com.river.basic.ResponseBean;
import com.river.datasource.DatabaseException;

/**
 * 异常拦截
 * @author River
 * @date 2018年9月7日
 */
@ControllerAdvice
public class ExceptionHandle {

	private final static Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseBean<?> handle(Exception e) {
    	ResponseBean<?> bean = new ResponseBean<>();
		if (e instanceof UnauthorizedException) {  //拦截未授权页面
			log.debug("该用户无权限！");
			bean.setCode(Constant.RESULT_NO_ACCESS);
			bean.setDesc("无接口权限");
		}else if (e instanceof AuthorizationException) {
			log.debug("该用户无权限！");
			bean.setCode(Constant.RESULT_NO_ACCESS);
			bean.setDesc("无接口权限");
		}else if (e instanceof UnknownAccountException) {
			log.debug("该用户无权限！");
			bean.setCode(Constant.LOGIN_STATUS_NOT);
	    	bean.setDesc("未登录");
		}else if (e instanceof DatabaseException) {
			log.debug("数据源异常");
			bean.setCode(Constant.RESULT_EXCEPT);
	    	bean.setDesc("数据源异常");
		}else {
			log.error("【系统异常】{}", e);
			bean.setCode(Constant.RESULT_EXCEPT);
			bean.setDesc("未知错误,请联系管理员");
		}
		return bean;
	}
}
