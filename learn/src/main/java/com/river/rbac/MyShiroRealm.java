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

import com.river.rbac.bean.PermissionBean;
import com.river.rbac.bean.RoleBean;
import com.river.rbac.bean.UserBean;

public class MyShiroRealm extends AuthorizingRealm{

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
		    System.out.println(token.getCredentials());
		    //通过username从数据库中查找 User对象，如果找到，没找到.
		    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		    UserBean userInfo=null;
		    try (SessionFactory factory = new SessionFactory();) {		    	 
		    	userInfo = BaseDataService.getLoginUserInfo(factory, account);
		    	SecurityUtils.getSubject().getSession().setAttribute(Constant.USER, userInfo);
		    }	   
		    System.out.println("----->>userInfo="+userInfo);
		    if(userInfo == null){
		        return null;
		    }
		    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
		            userInfo, //用户名
		            userInfo.getPassword(), //密码
		            getName()  //realm name
		    );
		    return authenticationInfo;
	}

}
