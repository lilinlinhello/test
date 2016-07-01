package com.yunda.gps.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;


public class CommPageBean {
	public static final int 			DEFAULT_PAGE_NUM 	= 	  1;
	public static final int             DEFAULT_PAGE_SISE   =     10;
	
	public static final String 			DEFAULT_PAGE_NAME = "page";
	public static final String 			DEFAULT_ROWS_NAME = "rows";
	
	public static CommPageBean getPageBeanByParam(final Map<String, String> paramMap) {
		return getPageBeanByParam(paramMap, DEFAULT_PAGE_NUM, DEFAULT_PAGE_SISE);
	}
	
	public static final CommPageBean getPageBeanByParam(final Map<String, String> paramMap,
			int defaultPage, int defaultRows) {
		return getPageBeanByParam(paramMap, DEFAULT_PAGE_NAME, defaultPage,
				DEFAULT_ROWS_NAME, defaultRows);
	}
	
	public static final CommPageBean getPageBeanByParam(final Map<String, String> paramMap,
			String pageName, int defaultPage, String rowsName, int defaultRows) {
		CommPageBean commPageBean = new CommPageBean();
		if (!StringUtils.isNumeric(paramMap.get(pageName))) {
			commPageBean.setCurPage(defaultPage);
		} else {
			commPageBean.setCurPage(Integer.parseInt(paramMap.get(pageName)));
		}		
		if (!StringUtils.isNumeric(paramMap.get(rowsName))) {
			commPageBean.setPageSize(defaultRows);
		} else {
			commPageBean.setPageSize(Integer.parseInt(paramMap.get(rowsName)));
		}
		return commPageBean;
	}
	
	private long totalNum;
	private int pageSize = DEFAULT_PAGE_SISE;
	private int curPage = DEFAULT_PAGE_NUM;
	private int totalPage;
	
	public long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
