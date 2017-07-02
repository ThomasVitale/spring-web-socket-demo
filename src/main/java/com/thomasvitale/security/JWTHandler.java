package com.thomasvitale.security;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTHandler {
	
	final static String SECRET = "ThisIsASecret";				// the secret key (better reading it from external file)
	final static long EXPIRATIONTIME = 15*60*1000; 				// 15 minutes
	
	final public static String TOKEN_PREFIX = "Bearer";			// the prefix of the token in the http header
	final public static String HEADER_STRING = "Authorization";	// the http header containing the prexif + the token
	
	/**
	 * Parse a token and extract the subject (username).
	 * 
	 * @param token		A token to parse.
	 * 
	 * @return			The subject (username) of the token.
	 */
	public static String parse(String token) {
		
		// Verify the validity of the token and retrieve the subject (username)
		// If it's not valid => SignatureException
		String username = Jwts.parser()
				  		  	  .setSigningKey(SECRET)
				  		  	  .parseClaimsJws(token)
				  		  	  .getBody()
				  		  	  .getSubject();
		
		return username;
		
	}
	
	/**
	 * Generate a token from the username.
	 * 
	 * @param username	The subject from which generate the token.
	 * 
	 * @return			The generated token.
	 */
	public static String build(String username) {
		
		Date now = new Date();
			
		String JWT = Jwts.builder()
						 .setId(UUID.randomUUID().toString())
				 		 .setSubject(username)
				 		 .setIssuedAt(now)
				 		 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				 		 //.compressWith(CompressionCodecs.DEFLATE) // uncomment to enable token compression
				 		 .signWith(SignatureAlgorithm.HS512, SECRET)
				 		 .compact();
		
		return JWT;
		
	}

	/**
	 * Parse and verify a token to extract the relative username.
	 * 
	 * @param token
	 * @return
	 */
	public static String getUsernameFromToken(String token) {
		return parse(token);
	}

}
