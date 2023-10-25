package com.richa.exception.global.customexceptions;

import com.richa.exception.global.customexceptions.BaseException;

public class BookCreateException extends BaseException {

	public BookCreateException() {
		super();
	}
	public BookCreateException(String message) {
		super(message);
	}

	public BookCreateException(String message, int errCode) {
		super(message, errCode);
	}
}
