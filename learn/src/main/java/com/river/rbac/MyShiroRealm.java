package com.river.rbac;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.river.basic.Constant;
import com.river.rbac.bean.PermissionBean;
import com.river.rbac.bean.RoleBean;
import com.river.rbac.bean.UserBean;
import com.river.rbac.mapper.UserMapper;

public class MyShiroRealm extends AuthorizingRealm{
	@Autowired
	private UserMapper userMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
	    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	    UserBean userInfo  = (UserBean)principals.getPrimaryPrincipal();
	    System.out.println(userInfo.getRoleBeans());
	    for(RoleBean role:userInfo.getRoleBeans()){
	        authorizationInfo.addRole(role.getName());
	        for(PermissionBean p:role.getPermisssionBeans()){
	        	System.out.println(role.getPermisssionBeans());
	            authorizationInfo.addStringPermission(p.getName());
	        }
	    }
	    return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		 System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		    //获取用户的输入的账号。
		    String account = (String)token.getPrincipal();
		    //通过username从数据库中查找 User对象，如果找到，没找到.
		    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		    UserBean bean= new UserBean();
		    bean.setAccount(account);
		    bean =userMapper.get(bean).get(0);	  
		    if(bean == null){
		        return null;
		    }
		    SecurityUtils.getSubject().getSession().setAttribute(Constant.LOGIN_USER_SESSION, bean);
		    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
		            account, //用户名
		            bean.getPassword(), //密码
		            getName()  //realm name
		    );
		    return authenticationInfo;
	}

}
