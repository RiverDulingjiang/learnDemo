package com.river.rbac;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro安全框架拦截配置
 * @author River
 * @date 2018年9月5日
 */
@Configuration
public class ShiroConfig {
	
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		//<!--如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面 -->
		shiroFilterFactoryBean.setLoginUrl("../index.html");
		//<!--登录成功后要跳转的链接 -->
		shiroFilterFactoryBean.setSuccessUrl("/");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("../403.html");
		
		//<!--设置拦截器--顺序拦截-->
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		//<!--anon:所有url都可以匿名访问-->
		filterChainDefinitionMap.put("/basic/**", "anon");
		filterChainDefinitionMap.put("/learn/**", "anon");
		filterChainDefinitionMap.put("/**/login.htm", "anon");
		filterChainDefinitionMap.put("/logout", "logout");		
		//<!--authc:所有url都必须认证通过才可以访问;一般将/**放在最为下边 -->
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}

	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}


	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}
	
	/*加入注解的使用，不加入这个注解不生效*/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}