package com.river.learn.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.river.learn.web.bean.DataBaseBean;

/**
 * 数据源服务数据层
 * @author River
 * @date 2018年9月17日
 */
public interface DataBaseMapper {
	/**
	 * @Description: 添加数据源信息
	 * @date 2018年9月17日
	 * @param bean
	 */
	public void post(DataBaseBean bean);
	/**
	 * @Description: 删除数据源信息
	 * @date 2018年9月17日
	 * @param id
	 */
	public void delete(@Param("id") String id);
	/**
	 * @Description: 修改数据源信息
	 * @date 2018年9月17日
	 * @param bean
	 */
	public void put(DataBaseBean bean);
	/**
	 * @Description: 获取数据源信息
	 * @date 2018年9月17日
	 * @param id
	 */
	public DataBaseBean get(@Param("id") String id);
	/**
	 * @Description: 按照条件获取
	 * @date 2018年9月17日
	 * @param bean
	 * @return
	 */
	public List<DataBaseBean> getDataBases(DataBaseBean bean);
}
