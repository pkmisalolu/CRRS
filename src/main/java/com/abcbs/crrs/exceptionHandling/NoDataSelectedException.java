package com.abcbs.crrs.exceptionHandling;

public class NoDataSelectedException extends RuntimeException {

	public NoDataSelectedException(String message) {
        super(message);
    }
}
