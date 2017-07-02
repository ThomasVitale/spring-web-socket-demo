package com.thomasvitale.security;

import static java.util.Collections.emptyList;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JWTRemoteServiceImpl implements JWTRemoteService {
	
private static final String REMOTE_AUTHENTICATION_ENDPOINT = "http://localhost:8081/authenticate";
	
	@Override
	public Authentication getRemoteAuthentication(String token) {

		// Create the request body containing the token
		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("token", token);
		
		// Send a POST request to the Authentication Module
		RestTemplate restTemplate = new RestTemplate();
		RemoteAuthentication remoteAuthentication;
		try {
			remoteAuthentication = restTemplate.postForObject(
					REMOTE_AUTHENTICATION_ENDPOINT,
					requestBody,
					RemoteAuthentication.class);
			
		} catch (Exception e) {
			// Authentication failed
			System.err.println(e.getMessage());
			return null;
		}
		
		// Create a new Authentication object using the data received from the Authentication Module
		String username = remoteAuthentication.getUsername();
		
		if (username == null) {
			// Remote authentication failed
			return null;
		}
		
		Authentication userAuthentication = new UsernamePasswordAuthenticationToken(username, token, emptyList());
		
		return userAuthentication;

	}
	
	@Override
	public String getRemoteUsername(String token) {

		// Create the request body containing the token
		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("token", token);
		
		// Send a POST request to the Authentication Module
		RestTemplate restTemplate = new RestTemplate();
		RemoteAuthentication remoteAuthentication;
		try {
			remoteAuthentication = restTemplate.postForObject(
					REMOTE_AUTHENTICATION_ENDPOINT,
					requestBody,
					RemoteAuthentication.class);
			
		} catch (Exception e) {
			// Authentication failed
			System.err.println(e.getMessage());
			return null;
		}
		
		// Create a new Authentication object using the data received from the Authentication Module
		String username = remoteAuthentication.getUsername();
		
		return username;

	}
}
