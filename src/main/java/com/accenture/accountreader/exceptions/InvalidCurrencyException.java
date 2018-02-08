package com.accenture.accountreader.exceptions;

public class InvalidCurrencyException extends Exception {

	private static final long serialVersionUID = -5872396389498633614L;
	private String errorMessage;
	
	public InvalidCurrencyException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
