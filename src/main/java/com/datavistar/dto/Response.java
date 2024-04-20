package com.datavistar.dto;

public class Response {

	private String message;
	private boolean success;

	public Response(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
