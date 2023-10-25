package com.richa.exception.global.customexceptions;

import com.richa.exception.global.customexceptions.BaseException;

public class PaymentException extends BaseException {

	public PaymentException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentException(String message) {
		super(message);
	}

	public PaymentException(String message, int errCode) {
		super(message, errCode);
	}
}
