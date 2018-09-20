package com.river.rbac.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.rbac.mapper.DepartmentMapper;

@Service
public class DepartmentService {
	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);
	@Autowired
	private DepartmentMapper departmentMapper;
	
	public String getChildPointId(String startId) {
		String childIds = "";
		// 获得所有部门数据
		log.info("正在请求数据库数据..........");
		List<HashMap<String, Object>> deptList = departmentMapper.selectDeptList();
		// 递归出所有子节点id并拼接成字符串
		for (HashMap<String, Object> map : deptList) {
			String id = (String)map.get("id");
			if (id.equals(startId)) {
				childIds += (id + ",");
				childIds = getChildId(id, childIds, deptList);
			}
		}
		return childIds;
	}
	private String getChildId(String id, String childIds, List<HashMap<String, Object>> deptList) {

		for (HashMap<String, Object> map : deptList) {
			String pid = (String) map.get("pid");
			String cid = (String) map.get("id");
			if (id.equals(pid)) {
				childIds += (cid + ",");
				childIds = getChildId(cid, childIds, deptList);
			}
		}
		return childIds;
	}
}
