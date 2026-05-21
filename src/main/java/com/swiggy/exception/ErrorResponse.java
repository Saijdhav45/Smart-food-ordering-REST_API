package com.swiggy.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	private LocalDateTime timestamp;
	private String message;
	private String path;
	private String error;
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(LocalDateTime timestamp, String message, String path, String error) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.path = path;
		this.error = error;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
	
}
