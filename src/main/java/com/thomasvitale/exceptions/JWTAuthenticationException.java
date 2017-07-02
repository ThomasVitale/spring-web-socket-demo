package com.thomasvitale.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -8449324479821242732L;

	public JWTAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}

}
