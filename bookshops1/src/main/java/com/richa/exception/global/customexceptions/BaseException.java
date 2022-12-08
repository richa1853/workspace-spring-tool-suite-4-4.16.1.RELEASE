package com.richa.exception.global.customexceptions;

public class BaseException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int errCode;
	
	public BaseException( ) {
		super();
	}
	
	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(String message, int errCode) {
		this(message);
		this.errCode = errCode;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}


}

