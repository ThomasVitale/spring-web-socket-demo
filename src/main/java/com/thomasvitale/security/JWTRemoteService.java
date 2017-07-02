package com.thomasvitale.security;

import org.springframework.security.core.Authentication;

public interface JWTRemoteService {
	
	/**
	 * The JWTAuthenticationProvider calls this method to verify the user authentication.
	 * If the token is not valid, the authentication fails and the request will be refused.
	 * For the verification the services provided by the Authentication Module are exploited.
	 * 
	 * @param token	An authentication token to verify.
	 * @return
	 */
	Authentication getRemoteAuthentication(String token);

	/**
	 * The Channel Interceptor calls this method to verify the user authentication.
	 * If the token is not valid, the authentication fails and the request will be refused.
	 * For the verification the services provided by the Authentication Module are exploited.
	 * 
	 * @param token	An authentication token to verify.
	 * @return
	 */
	String getRemoteUsername(String token);

}
