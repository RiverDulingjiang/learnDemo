package com.river.learn.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.river.learn.web.bean.DataSourceBean;

@Mapper
public interface DataSourceMapper {
	public List<DataSourceBean> get();
}
