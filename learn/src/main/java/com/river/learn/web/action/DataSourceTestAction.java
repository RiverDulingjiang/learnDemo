package com.river.learn.web.action;

import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.datasource.DBProperties;

@Controller
@RequestMapping("/db")
public class DataSourceTestAction {
	
	 @RequestMapping("/home.htm")
	 @ResponseBody
	 public Boolean home() {
		 Properties p = new Properties();
		 p.setProperty(DBProperties.URL, "jdbc:mysql://localhost:3306/db_learn_rbac?useUnicode=true&characterEncoding=utf8");
		 p.setProperty(DBProperties.USER_NAME, "root");
		 p.setProperty(DBProperties.PASSWORD, "123");
		 p.setProperty(DBProperties.DRVIER_CLASS_NAME, "com.mysql.jdbc.Driver");
		 Boolean is = DBProperties.setDBmap("_common", p);
	     return is;
	 }
}
