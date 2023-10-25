package com.richa.exception.global.customexceptions;

import com.richa.exception.global.customexceptions.BaseException;

public class CustomerCreateException extends BaseException {

	public CustomerCreateException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerCreateException(String message) {
		super(message);
	}

	public CustomerCreateException(String message, int errCode) {
		super(message, errCode);
	}
}
