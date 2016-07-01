package com.yunda.gps.util;

import java.util.List;

public class PageData<T> {
	private List<T> listData;
	private CommPageBean pageBean;
	public List<T> getListData() {
		return listData;
	}
	public void setListData(List<T> listData) {
		this.listData = listData;
	}
	public CommPageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(CommPageBean pageBean) {
		this.pageBean = pageBean;
	}
}
