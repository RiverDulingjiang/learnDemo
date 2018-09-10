package com.river.rbac.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.basic.Constant;
import com.river.basic.ResponseBean;
import com.river.datasource.DSIdentification;

@Controller
public class AccessAction {
	/**
	 * @Description: 登录操作
	 * @date 2018年9月3日
	 * @param account
	 * @param password
	 * @param rememberMe
	 * @return
	 */
	@RequestMapping("/login.htm")
	@ResponseBody
    public String login(@RequestParam("account")String account,@RequestParam("password")String password,@RequestParam("rememberMe")Boolean rememberMe) {
		ResponseBean<String> bean = new ResponseBean<>();
		DSIdentification.setIdentification("_common");
		String error = null;
        Subject subject = SecurityUtils.getSubject();
        //String newPassword = new SimpleHash("md5", password,  ByteSource.Util.bytes(account), 2).toHex();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            token.setRememberMe(rememberMe);//记住我
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "The account isn't be found";
        } catch (IncorrectCredentialsException e) {
            error = "The password is error";
        } catch (AuthenticationException e) {
            error = "The unknow error:" + e.getMessage();
        }
        if(error != null) {//出错了
        	bean.setCode(Constant.RESULT_FAIL);
        	bean.setData("fail");
        	bean.setDesc(error);
        	return bean.toJson();
        } else {//登录成功
        	bean.setData("success");
        	return bean.toJson();
        }
    }
}
