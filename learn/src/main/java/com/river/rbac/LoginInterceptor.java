package com.river.rbac;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.river.basic.Constant;


/**
 * 登录拦截器
 * @author River
 * @date 2018年9月3日
 */
public class LoginInterceptor implements HandlerInterceptor{
	
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*HttpServletRequest httpRequest=(HttpServletRequest)request;
		String strBackUrl = "http://" + request.getServerName() + ":"
				+ request.getServerPort()
				+ httpRequest.getContextPath()
				+ httpRequest.getServletPath();
		System.out.println("strBackUrl: " + strBackUrl);*/
		//HttpSession session = request.getSession(true);
		Session session = SecurityUtils.getSubject().getSession(false);
		if(session.getAttribute(Constant.LOGIN_USER_SESSION)==null){
			log.info("------:跳转到login页面！");
			response.sendRedirect(request.getContextPath()+"/learn/must/login.htm");
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
