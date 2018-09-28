package com.river.rbac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
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
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		//<!--登录成功后要跳转的链接 -->
		shiroFilterFactoryBean.setSuccessUrl("/");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("../403.html");
		
		//<!--设置拦截器--顺序拦截-->
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		//<!--anon:所有url都可以匿名访问-->
		filterChainDefinitionMap.put("/*.html", "anon");
		filterChainDefinitionMap.put("/basic/**", "anon");
		filterChainDefinitionMap.put("/learn/**", "anon");
		filterChainDefinitionMap.put("/public/**", "anon");
		filterChainDefinitionMap.put("/uiFrame/**", "anon");
		//<!--放行登录请求-->
		filterChainDefinitionMap.put("/**/login", "anon");
		filterChainDefinitionMap.put("/logout", "logout");		
		//<!--authc:所有url都必须认证通过才可以访问;一般将/**放在最为下边 -->
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 配置核心安全事务管理器
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		//1.设置自定义权限规则
		securityManager.setRealm(myShiroRealm());
		
		//2.配置记住我 
        // securityManager.setRememberMeManager(rememberMeManager());

        //3.配置 ehcache缓存管理器 
        //securityManager.setCacheManager(ehCacheManager());

        //4.配置自定义session管理，使用ehcache 或redis
        securityManager.setSessionManager(sessionManager());

		return securityManager;
	}
	/**
	 * 配置会话管理器，设定会话超时及保存
	 * @return
	 */
	@Bean("sessionManager")
	public SessionManager sessionManager() {

	    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
	    Collection<SessionListener> listeners = new ArrayList<SessionListener>();
	    //配置监听
	    listeners.add(sessionListener());
	    sessionManager.setSessionListeners(listeners);
	    sessionManager.setSessionIdCookie(sessionIdCookie());
	    sessionManager.setSessionDAO(sessionDAO());
	    //sessionManager.setCacheManager(ehCacheManager());

	    //全局会话超时时间（单位毫秒），默认30分钟  暂时设置为10秒钟 用来测试
	    sessionManager.setGlobalSessionTimeout(1800000);
	    //是否开启删除无效的session对象  默认为true
	    sessionManager.setDeleteInvalidSessions(true);
	    //是否开启定时调度器进行检测过期session 默认为true
	    sessionManager.setSessionValidationSchedulerEnabled(true);
	    //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
	    //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
	    sessionManager.setSessionValidationInterval(3600000);
	    //取消url 后面的 JSESSIONID
	    sessionManager.setSessionIdUrlRewritingEnabled(false);

	    return sessionManager;

	}
	/**
	 * @Description: 自定义权限规则
	 * @date 2018年9月19日
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}
	
    /**
     * 加入注解的使用，不加入这个注解不生效
     * @Description: 
     * @date 2018年9月19日
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 配置session监听
     * @return
     */
    @Bean("sessionListener")
    public ShiroSessionListener sessionListener(){
        ShiroSessionListener sessionListener = new ShiroSessionListener();
        return sessionListener;
    }
    /**
     * 配置会话ID生成器
     * @return
     */
    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }
    /**
     * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
     * MemorySessionDAO 直接在内存中进行会话维护
     * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     * @return
     */
    @Bean
    public SessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        
        //使用ehCacheManager
        //enterpriseCacheSessionDAO.setCacheManager(ehCacheManager());
        
        //设置session缓存的名字 默认为 shiro-activeSessionCache
        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        
        //sessionId生成器
        enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        
        return enterpriseCacheSessionDAO;
    }
    /**
     * 配置保存sessionId的cookie 
     * 注意：这里的cookie 不是上面的记住我 cookie 记住我需要一个cookie session管理 也需要自己的cookie
     * @return
     */
    @Bean("sessionIdCookie")
    public SimpleCookie sessionIdCookie(){
        //这个参数是cookie的名称
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：

        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //maxAge=-1表示浏览器关闭时失效此Cookie
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }
}