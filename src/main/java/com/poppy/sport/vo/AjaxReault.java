package com.poppy.sport.vo;

/**
 *  @author chenb-c
 *	@date 2016-10-18
 *  @descpiption 统一ajax异步请求返回结果
 */
public class AjaxReault {
	
	private boolean success = true;
	private Object data;
	private String message = "操作成功";

	public boolean isSuccess() {
		return success;
	}

	public AjaxReault setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public Object getData() {
		return data;
	}

	public AjaxReault setData(Object data) {
		this.data = data;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public AjaxReault setMessage(String message) {
		this.message = message;
		return this;
	}

	public AjaxReault() {
	}

	public AjaxReault(boolean success, String message, Object data) {
		super();
		this.success = success;
		this.data = data;
		this.message = message;
	}

	public static AjaxReault success(String message) {
		return new AjaxReault(true, message, null);
	}

	public static AjaxReault success(String message, Object data) {
		return new AjaxReault(true, message, data);
	}

	public static AjaxReault fail(String message) {
		return new AjaxReault(false, message, null);
	}

	public static AjaxReault fail(String message, Object data) {
		return new AjaxReault(false, message, data);
	}

}
