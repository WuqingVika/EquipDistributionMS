package com.guanghua.edms.common.web;
/**
 * 自定义异常
 * @author Administrator
 *
 */
public class CustomException extends RuntimeException {
	public CustomException(String msg) {
		super(msg);
	}

	public CustomException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
