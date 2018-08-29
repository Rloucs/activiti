package com.activiti.pojo;

public class ReturnObject {
	
	private boolean success;
	private Object data;
	private String message;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ReturnObject [success=" + success + ", data=" + data + ", message=" + message + "]";
	}
}
