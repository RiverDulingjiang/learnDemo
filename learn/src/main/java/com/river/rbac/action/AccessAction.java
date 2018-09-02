package com.river.rbac.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.datasource.DSIdentification;

@Controller
public class AccessAction {
	@RequestMapping("/login.htm")
	@ResponseBody
    public String login(@RequestParam("account")String account,@RequestParam("password")String password,@RequestParam("rememberMe")Boolean rememberMe) {
		DSIdentification.setIdentification("_common");
		String error = null;
        Subject subject = SecurityUtils.getSubject();
        String newPassword = new SimpleHash("md5", password,  ByteSource.Util.bytes(account), 2).toHex();
        UsernamePasswordToken token = new UsernamePasswordToken(account, newPassword);
        try {
            token.setRememberMe(rememberMe);//记住我
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }
        if(error != null) {//出错了，返回登录页面
        	return "fail";
        } else {//登录成功
        	return "success";
        }
    }
	 @RequestMapping("/home1.htm")
	 @ResponseBody
	 public String home() {
	       return "Hello World1!";
	 }

}
