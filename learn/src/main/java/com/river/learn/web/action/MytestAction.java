package com.river.learn.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.river.datasource.DSIdentification;
import com.river.learn.web.bean.DataSourceBean;
import com.river.learn.web.mapper.DataSourceMapper;

@Controller
@RequestMapping("/test")
public class MytestAction {
	
	 @Autowired
	 private DataSourceMapper mapper;
	
	 @RequestMapping("/home.htm")
	 @ResponseBody
	 public String home() {
	       return "Hello World!";
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
	       return "Hello World!";
	 }
}
