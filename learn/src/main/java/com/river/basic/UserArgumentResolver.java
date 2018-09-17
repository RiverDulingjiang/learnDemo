package com.river.basic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义用户参数解析器
 * @author River
 * @date 2018年9月17日
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver{

	/*
	 * 判断方法是否含有指定注解，含有，则执行下面<resolveArgument>方法
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Boolean is = parameter.hasParameterAnnotation(UserSession.class);
		UserSession us = parameter.getParameterAnnotation(UserSession.class);
		if(is && us!=null){
			return true;
		}else {
			return false;
		}		
	}
	
	/*
	 * 给注解返回值
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		//获取注解类型
		UserSession us = parameter.getParameterAnnotation(UserSession.class);
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		//获取session
		Object o = request.getSession().getAttribute(us.value());
		return o;
	}

}
