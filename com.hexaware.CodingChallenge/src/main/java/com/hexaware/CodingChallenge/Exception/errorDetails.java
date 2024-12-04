package com.hexaware.CodingChallenge.Exception;

import java.time.LocalDateTime;

public class errorDetails {

	LocalDateTime time;
	String message;
	String path;
	String errorCode;
	
	public errorDetails() {
		
	}
	
	public errorDetails(LocalDateTime time, String msg, String path, String errorCode) {
		super();
		this.time = time;
		this.message = msg;
		this.path = path;
		this.errorCode = errorCode;
	}

	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getMsg() {
		return message;
	}
	public void setMsg(String msg) {
		this.message = msg;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	@Override
	public String toString() {
		return "errorDetails [time=" + time + ", msg=" + message + ", path=" + path + ", errorCode=" + errorCode + "]";
	}
	
}
