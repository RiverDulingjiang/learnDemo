package com.river.learn.web.service;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.river.basic.Tools;
import com.river.datasource.DBProperties;
import com.river.learn.web.bean.DataBaseBean;
import com.river.learn.web.mapper.DataBaseMapper;

/**
 * 动态数据库管理服务类
 * @author River
 * @date 2018年9月17日
 */
@Service
public class DataBaseService {
	@Autowired
	private DataBaseMapper dataBaseMapper;
	/**
	 * @Description: 添加数据源信息
	 * @date 2018年9月17日
	 * @param bean
	 */
	public void postDataBase(DataBaseBean bean){
		bean.setCreateTime(Tools.dateToString(new Date()));
		dataBaseMapper.post(bean);
	}
	
	/**
	 * @Description: 修改数据源信息
	 * @date 2018年9月17日
	 * @param bean
	 */
	public void putDataBase(DataBaseBean bean){
		bean.setUpdateTime(Tools.dateToString(new Date()));
		dataBaseMapper.put(bean);
	}
	/**
	 * @Description: 删除数据源信息
	 * @date 2018年9月17日
	 * @param id
	 */
	public void deleteDataBase(String id){
		dataBaseMapper.delete(id);
	}
	/**
	 * @Description: 获取数据源信息
	 * @date 2018年9月17日
	 * @param id
	 * @return
	 */
	public DataBaseBean getDataBase(String id){
		return dataBaseMapper.get(id);
	}
	/**
	 * @Description: 根据数据库标识查询
	 * @date 2018年9月17日
	 * @param flag
	 * @return
	 */
	public DataBaseBean getDataBaseByFlag(String flag){
		DataBaseBean bean= new DataBaseBean();
		bean.setFlag(flag);
		List<DataBaseBean> beans = dataBaseMapper.getDataBases(bean);
		if(beans!=null){
			return beans.get(0);
		}else {
			return null;
		}
	}
	/**
	 * @Description: 按条件查询
	 * @date 2018年9月17日
	 * @param bean
	 * @return
	 */
	public List<DataBaseBean> getDataBases(DataBaseBean bean){
		return dataBaseMapper.getDataBases(bean);
	}
	/**
	 * @Description: 加入所有有效数据源
	 * @date 2018年9月11日
	 * @param beans 
	 */
	public void addDataSources(List<DataBaseBean> beans){
		if(beans!=null){
			for(DataBaseBean bean:beans){
				Properties p = new Properties();
				p.setProperty(DBProperties.URL,bean.getUrl());
				p.setProperty(DBProperties.USER_NAME, bean.getUsername());
				p.setProperty(DBProperties.PASSWORD, bean.getPassword());
				p.setProperty(DBProperties.DRVIER_CLASS_NAME, bean.getDriverClassName());
				DBProperties.setDBmap(bean.getFlag(),p);
			}
		}
	}
	/**
	 * @Description: 加入某一数据源
	 * @date 2018年9月17日
	 * @param bean
	 */
	public void addDataSource(DataBaseBean bean){
		if(bean!=null){
			Properties p = new Properties();
			p.setProperty(DBProperties.URL,bean.getUrl());
			p.setProperty(DBProperties.USER_NAME, bean.getUsername());
			p.setProperty(DBProperties.PASSWORD, bean.getPassword());
			p.setProperty(DBProperties.DRVIER_CLASS_NAME, bean.getDriverClassName());
			DBProperties.setDBmap(bean.getFlag(),p);
		}
	}
	/**
	 * @Description: 加入某一数据源
	 * @date 2018年9月17日
	 * @param flag 数据源标识
	 */
	public void removeDataSource(String flag){
		DBProperties.removeDBmap(flag);
	}
}
