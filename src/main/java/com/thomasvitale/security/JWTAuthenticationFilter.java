package com.thomasvitale.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * A filter to intercept http requests to secured endpoints.
 * It's in charge of checking the presence of a token in the Authorize header
 * in order to authenticate the user.
 * 
 */
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		// Get the Authorization http header
		String authorizationHeader = request.getHeader(JWTHandler.HEADER_STRING);
		
		// Check if there is a token and starts with the proper prefix
		if (authorizationHeader != null && authorizationHeader.startsWith(JWTHandler.TOKEN_PREFIX)) {
			
			// Extract the JWT token by removing the prefix
			String token = authorizationHeader.replaceAll(JWTHandler.TOKEN_PREFIX, "");
			
			// Create a new authentication object to deliver the token at the AuthenticationProvider
			Authentication authentication = new UsernamePasswordAuthenticationToken(null, token);
						
            // Apply the authentication to the SecurityContextHolder, ready to be read from the JWTAuthenticationProvider
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
        }
		
		// Go on processing the request
		filterChain.doFilter(request,response);
		
	}

}
