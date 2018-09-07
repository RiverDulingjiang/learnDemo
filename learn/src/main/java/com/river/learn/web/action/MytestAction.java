package com.river.learn.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.river.basic.Constant;
import com.river.basic.ResponseBean;
import com.river.datasource.DSIdentification;
import com.river.learn.web.bean.DataSourceBean;
import com.river.learn.web.mapper.DataSourceMapper;
import com.river.rbac.bean.UserBean;

@Controller
@RequestMapping("/test")
public class MytestAction {
	
	 @Autowired
	 private DataSourceMapper mapper;
	
	 @RequestMapping("/home.htm")
	 @ResponseBody
	 @RequiresRoles("leader")
	 public String home(){
		 ResponseBean<String> bean = new ResponseBean<>();
		 bean.setData("Hello World!");
	     return bean.toJson();
	 }
	 
	 @RequestMapping("/activiti.htm")
	 @ResponseBody
	 public String activiti() {
	       return "Hello World!";
	 }
	 
	 @RequestMapping("/dataSource.htm")
	 @ResponseBody
	 public String dataSource(String projectCode) {
		 System.out.println("projectCode:---"+projectCode);
		 DSIdentification.setIdentification(projectCode);
		 List<DataSourceBean> bean = mapper.get();		
		 return  new Gson().toJson(bean);
	 }
	 
	 @RequestMapping("/rbac.htm")
	 @ResponseBody
	 @RequiresPermissions("")
	 public String rbac() {
		 UserBean bean = new UserBean();
		 bean.setAccount("zhangsan");
		 ResponseBean<UserBean> resultBean = new ResponseBean<>();
		 resultBean.setCode(Constant.RESULT_SUCCESS);
		 resultBean.setData(bean);
	     return new Gson().toJson(resultBean);
	 }
	 
	 @RequestMapping("/exception.htm")
	 @ResponseBody
	 public String exception() {
		 Integer aString = 1/0;
	      return aString.toString();
	 }
	 
	 @RequestMapping("/tiaozhuan.htm")
	 public ModelAndView tiaozhuan() {
		 ModelAndView view = new ModelAndView("/403.html");
	      return view;
	 }
	 @RequestMapping("/tiaozhuan1.htm")
	 @ResponseBody
	 public String tiaozhuan1() {
		
	      return "redirect:/403.html";
	 }
}
