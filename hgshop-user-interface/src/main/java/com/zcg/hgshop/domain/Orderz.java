package com.zcg.hgshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Orderz implements Serializable{
	private static final long serialVersionUID = 1L;
	private int oid;
	private int uid;
	private BigDecimal sumtotal;
	private String address;
	private Date createTime ;
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public BigDecimal getSumtotal() {
		return sumtotal;
	}
	public void setSumtotal(BigDecimal sumtotal) {
		this.sumtotal = sumtotal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Orderz [oid=" + oid + ", uid=" + uid + ", sumtotal=" + sumtotal + ", address=" + address
				+ ", createTime=" + createTime + "]";
	}
	
	
	
	
}
