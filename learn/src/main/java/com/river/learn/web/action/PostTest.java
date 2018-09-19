package com.river.learn.web.action;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.river.basic.ResponseBean;
import com.river.learn.basic.bean.DataBaseBean;

@Controller
@RequestMapping("/post")
public class PostTest {
	
	 @RequestMapping("/post.htm")
	 @ResponseBody
	 public String home(@RequestBody DataBaseBean bean){
		 ResponseBean<DataBaseBean> res = new ResponseBean<>();
		 res.setCode(0);
		 res.setData(bean);
		 res.setDesc("success");
	     return res.toJson();
	 }
	 @RequestMapping("/post1.htm")
	 @ResponseBody
	 public String home1(@RequestBody List<DataBaseBean> beans){
		 ResponseBean<List<DataBaseBean>> res = new ResponseBean<>();
		 res.setCode(0);
		 res.setData(beans);
		 res.setDesc("success1");
	     return res.toJson();
	 }
	 @RequestMapping("/post2.htm")
	 @ResponseBody
	 public String home2(String id){
		 ResponseBean<String> res = new ResponseBean<>();
		 res.setCode(0);
		 res.setData(id);
		 res.setDesc("success2");
	     return res.toJson();
	 }
}
