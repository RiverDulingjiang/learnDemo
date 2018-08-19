package com.river.learn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.river.learn.web.service.TestService;
import com.river.main.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiTest {

	@Autowired
    TestService testService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired  
	private TaskService taskService;  

	
	@Test
    public void TestStartProcess() {
		System.out.println("Start.........");
		Map<String, Object> map = new HashMap<>();
		map.put("name", "xiaoming");
		map.put("year", 2018);
		map.put("id", "AO20180810");
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcess", map);
        System.out.println("流程启动成功，流程id:"+pi.getId());
    }
	
	@Test
	public void findTasksByUserId() {
		String userId ="xiaoming";
		List<Task> resultTask = taskService.createTaskQuery().processDefinitionKey("myProcess").taskCandidateOrAssigned(userId).list();
		for(Task task:resultTask){
			System.out.println("Assignee:"+task.getAssignee()+"||ProcessVariables: "+task.getProcessVariables());
			System.out.println("-------------"+task.getFormKey());
		}
		System.out.println("任务列表："+resultTask);
	}
	@Test
	public void completeTask() {
		String taskId="72501";
		String userId="dulingjiang";
		String result="true";
		//获取流程实例
		taskService.claim(taskId, userId);		
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("sign", result);
		taskService.complete(taskId, vars);
	}


}
