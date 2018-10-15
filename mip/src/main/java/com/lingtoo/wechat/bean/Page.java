package com.lingtoo.wechat.bean;


/**
 * @Copyright: Copyright (c) 2015 FFCS All Rights Reserved
 * @Company: 福州创鑫壹佳壹网络有限公司
 * @author 陈为金 2015-8-12
 * @version 1.00.00
 * @history:
 * 
 */
public class Page {
	private int page = 1;
	private int pageSize = 10;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
