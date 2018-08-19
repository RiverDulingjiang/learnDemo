package com.river.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {
	@Autowired  
	private RuntimeService runtimeService;  
	@Autowired  
	private TaskService taskService;  
	@Autowired  
	private HistoryService historyService;  
	@Autowired  
	private RepositoryService repositoryService;  
	@Autowired  
	private ProcessEngineConfigurationImpl processEngineConfiguration;  

	public void startProcess(){
		
	}
}
