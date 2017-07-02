package com.thomasvitale.exceptions;

import static java.lang.String.format;

public class FailedToAuthenticateException extends IllegalArgumentException {

	private static final long serialVersionUID = -4054597124848897187L;

	public FailedToAuthenticateException(String message) {
        super(format("Failed to authenticate: " + message));
    }
	
}
