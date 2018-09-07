package com.river.main;


import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.basic.Constant;
import com.river.basic.ResponseBean;

/**
 * 异常拦截
 * @author River
 * @date 2018年9月7日
 */
@ControllerAdvice
public class ExceptionHandle {

    //拦截未授权页面
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public ResponseBean<String> handleException(UnauthorizedException e) {
    	ResponseBean<String> bean = new ResponseBean<>();
    	bean.setCode(Constant.RESULT_NO_ACCESS);
    	bean.setDesc("无接口权限");
        return bean;
    }
    @ExceptionHandler(AuthorizationException.class)
    public ResponseBean<String> handleException2(AuthorizationException e) {
    	ResponseBean<String> bean = new ResponseBean<>();
    	bean.setCode(Constant.RESULT_NO_ACCESS);
    	bean.setDesc("无接口权限");
        return bean;
    }
}
