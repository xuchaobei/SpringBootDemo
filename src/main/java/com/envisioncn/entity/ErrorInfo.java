package com.envisioncn.entity;

import java.util.Date;

public class ErrorInfo {
	private final String uri;
	private final int code;
	private final String msg;
	private final Date timestamp;

	public ErrorInfo(String uri, int code, Exception ex, Date timestamp ) {
		this.uri = uri;
		this.code = code;
		this.msg = ex.getLocalizedMessage();
		this.timestamp = timestamp;
	}
	
	public ErrorInfo(String uri, int code, String msg, Date timestamp) {
		this.uri = uri;
		this.code = code;
		this.msg = msg;
		this.timestamp = timestamp;
	}

	public String getUri() {
		return uri;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	
	
}
