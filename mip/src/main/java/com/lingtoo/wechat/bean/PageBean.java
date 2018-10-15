package com.lingtoo.wechat.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Copyright (c) 2015 FFCS All Rights Reserved
 * @Company: 福州创鑫壹佳壹网络有限公司
 * @author 陈为金 2015-8-12
 * @version 1.00.00
 * @history:
 * 
 */
public class PageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private static final int DEFAULT_ROWCOUNT = 0;
    private static final int DEFAULT_PAGECOUNT = 0;
    private static final int DEFAULT_PAGESIZE = 10;
    private static final int DEFAULT_PAGENO = 1;


	private Integer[] slider;
	private List queryList = new ArrayList();
	private Object obj=new Object();
	private Object[] objs=new Object[]{};
    private int rowCount = DEFAULT_ROWCOUNT;
    private int pageCount = DEFAULT_PAGECOUNT;
    private int pageSize = DEFAULT_PAGESIZE;
    private int pageNo = DEFAULT_PAGENO;
    private String hasnext;//是否还有下一页 0有 1 没有
    public static final int SLIDE_LENGTH = 10;
    //用于标识存放与request中的Pagebean的名字
    public static final String QUERYBEAN = "QueryPage";
    public static final String QUERYACTION = "QueryAction";

    public PageBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return Returns the pageCount.
     */
    public int getPageCount() {
        return pageCount;
        //return (this.rowCount-1)/this.pageSize+1;
    }

    /**
     * @param pageCount The pageCount to set.
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return Returns the pageNo.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo The pageNo to set.
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return Returns the pageSize.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize The pageSize to set.
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.pageCount = (this.rowCount-1)/this.pageSize+1;
    }

    /**
     * @return Returns the queryList.
     */
    public List getQueryList() {
        return queryList;
    }

    /**
     * @param queryList The queryList to set.
     */
    public void setQueryList(List queryList) {
        this.queryList = queryList;
    }

    /**
     * @return Returns the rowCount.
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount The rowCount to set.
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.pageCount = (this.rowCount-1)/this.pageSize+1;
    }

    public int getFirstResult(){
        if(this.pageNo>this.getPageCount()){
            this.pageNo = this.getPageCount();
        }
        int startIndex = (this.pageNo - 1) * this.pageSize;
       /* if (startIndex < 1)
          startIndex = 1;
         */
        if (startIndex < 0)
         startIndex = 0;
       return startIndex;
    }
    
    public boolean isLastPage(){
        if(this.pageNo==this.getPageCount()){
            return true;
        }

        return false;
    }

	public Integer[] getSlider() {
		int n = PageBean.SLIDE_LENGTH;

		if (n > this.getPageCount()) {
			n = this.getPageCount();
		}

		Integer[] slider = new Integer[n];
		int first = pageNo - ((n - 1) / 2);

		if (first < 1) {
			first = 1;
		}

		if (((first + n) - 1) > this.getPageCount()) {
			first = this.getPageCount() - n + 1;
		}

		for (int i = 0; i < n; i++) {
			slider[i] = new Integer(first + i);
		}

		return slider;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Object[] getObjs() {
		return objs;
	}

	public void setObjs(Object[] objs) {
		this.objs = objs;
	}

	public String getHasnext() {
		return hasnext;
	}

	public void setHasnext(String hasnext) {
		this.hasnext = hasnext;
	}
	
}
