package com.river.basic;

/**
 * 分页查询实体类
 * @author River
 * @date 2018年10月30日
 */
public class PageQueryBean {

	/**
	 * 查询数据总条数
	 */
	private Integer total;
	/**
	 * 每页数据，默认10条
	 */
	private Integer pageSize =10;
	/**
	 * 第几页，默认第一页
	 */
	private Integer page = 1;
	/**
	 * 起始值
	 */
	private Integer start;
	/**
	 * 结束值
	 */
	private Integer end;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getStart() {
		start = pageSize * (page-1);
		return start;
	}
	public Integer getEnd() {
		end = pageSize * page;
		return end;
	}
}
