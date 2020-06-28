package com.zcg.hgshop.domain;

public class SpuVO extends Spu{

	private static final long serialVersionUID = 1L;
	
	private int pageNum=1;
	private int pageSize=5;
	
	private String key;
	
	private String orderType="ASC";
	
	private String orderColumn="";

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	@Override
	public String toString() {
		return "SpuVO [pageNum=" + pageNum + ", pageSize=" + pageSize + ", key=" + key + ", orderType=" + orderType
				+ ", orderColumn=" + orderColumn + ", toString()=" + super.toString() + "]";
	}
	
	
}
