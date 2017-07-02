package com.thomasvitale.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// Disable CSRF protection since tokens are immune to it
			.csrf().disable()
			// Security policy
			.authorizeRequests()
				// Allow only requests on the web socket api
				.antMatchers("/signal-websocket/**").permitAll()
				// Any request must be authenticated
				.anyRequest().authenticated().and()
			// Disable resource caching
			.headers().cacheControl();
	}	

}
