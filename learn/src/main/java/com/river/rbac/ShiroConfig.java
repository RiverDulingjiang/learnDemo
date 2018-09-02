package com.river.rbac;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {	
/*	//配置数据库的基本链接信息
    @Bean(name = "rbacDataSource")
    @ConfigurationProperties(prefix = "rbac.datasource")    //可以在application.properties中直接导入
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("rbacDataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }*/
    /**
     * 记住我：自动登录-1
     */
    @Bean
    public SimpleCookie getSimpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("rememberMe");
        simpleCookie.setMaxAge(20000000);
        return simpleCookie;
    }

    /**
     * 记住我：自动登录-2
     */
    @Bean
    public CookieRememberMeManager getCookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(getSimpleCookie());
        return cookieRememberMeManager;
    }

    /**
     * 开启MD5加密
     * @return
     */
    @Bean
    public HashedCredentialsMatcher getMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        return matcher;
    }

    /**
     * 自定义Realm密码验证与加密
     * @return
     */
    @Bean
    public MyShiroRealm getCustomRealm(){
    	MyShiroRealm customRealm = new MyShiroRealm();
    	customRealm.setCredentialsMatcher(getMatcher());
    	return customRealm;
    }

    /**
     * 创建SecurityManager环境
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getCustomRealm());
        securityManager.setRememberMeManager(getCookieRememberMeManager());
        return securityManager;
    }

    /**
     * Shiro在Web项目中的过滤
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getfilterFactoryBean(){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(getSecurityManager());
        /**
         *  只有在下面配置路径访问权限，Shiro才会执行自动跳转。
         *  如果使用Shiro注解权限，就只会报异常，
         *  就只能采用统一异常处理的方法。
         */
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/user/exit", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/user/user", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        filterFactoryBean.setLoginUrl("/must/login.html");
        // 登录成功后要跳转的链接
        filterFactoryBean.setSuccessUrl("/must/home.html");
        //未授权界面;
        filterFactoryBean.setUnauthorizedUrl("/user/login");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }


    /**
     * 保证Shiro的声明周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro授权生效
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        return new AuthorizationAttributeSourceAdvisor();
    }
}
