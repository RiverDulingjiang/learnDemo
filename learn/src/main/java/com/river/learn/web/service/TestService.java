package com.river.learn.web.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	 public void activiti() {
	    System.out.println("任务已经执行.....................................");
	 }
	 public List<String> user() {
		 return Arrays.asList("xiaoming","river");
	 }
	 public String service() {
		    System.out.println("Service.............");
		    return "seccuss";
	 }
}
