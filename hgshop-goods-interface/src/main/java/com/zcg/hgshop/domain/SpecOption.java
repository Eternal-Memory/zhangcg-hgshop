package com.zcg.hgshop.domain;

import java.io.Serializable;
/**
 * 规格属性值实体类
 * @author hp
 *
 */
public class SpecOption implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String optionName;
	private int specId;
	private int orders;
	private String specName;
	
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public int getSpecId() {
		return specId;
	}
	public void setSpecId(int specId) {
		this.specId = specId;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "SpecOption [id=" + id + ", optionName=" + optionName + ", specId=" + specId + ", orders=" + orders
				+ "]";
	}
	
	
	
}
