package com.richa.exception.global.handlers;

import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.exception.global.customexceptions.CustomerCreateException;
import com.richa.exception.global.customexceptions.PaymentException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerCreateException.class)
    public final ResponseEntity<ErrorResponse> handleCustomerCreateException(
    		CustomerCreateException CustomerEx, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(CustomerEx.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(CustomerEx.getErrCode(), CustomerEx.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
 @ExceptionHandler(Exception.class)
 public final ResponseEntity<ErrorResponse> handleExceptionClass(
 		Exception Ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse( Ex.getMessage());
		System.out.println("Global Exception msg");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
 }
 @ExceptionHandler(PaymentException.class)
 public final ResponseEntity<ErrorResponse> handlePaymentException(
 		CustomerCreateException PaymentEx, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(PaymentEx.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(PaymentEx.getErrCode(), PaymentEx.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
 }
 @ExceptionHandler(BookCreateException.class)
 public final ResponseEntity<ErrorResponse> handleCannotCreateCustomerException(
 		BookCreateException BookEx, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(BookEx.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(BookEx.getErrCode(), BookEx.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
 }
 
 }

