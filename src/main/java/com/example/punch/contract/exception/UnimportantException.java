package com.example.punch.contract.exception;

/**
 * 不重要的异常
 */
public class UnimportantException extends ServiceException {

	public UnimportantException() {
	}

	public UnimportantException(String message) {
		super(message);
		setResultCode(API_OK);
	}
	
	public UnimportantException(String message, String statusCode) {
		super(message, statusCode);
		setResultCode(API_OK);
	}
	
	public UnimportantException(String message, Throwable cause) {
		super(message, cause);
		setResultCode(API_OK);
	}

	public UnimportantException(String message, String statusCode, Throwable cause) {
		super(message, statusCode, cause);
		setResultCode(API_OK);
	}
}
