package com.zcg.hgshop.common;

public class MsgData {
	//正常返回
	private int errorCode=0;
	//错误信息
	private String errorInfo="";
	//返回的数据
	private Object data;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public MsgData(int errorCode, String errorInfo) {
		super();
		this.errorCode = errorCode;
		this.errorInfo = errorInfo;
	}

	public MsgData(Object data) {
		super();
		this.data = data;
	}
	
	
}
