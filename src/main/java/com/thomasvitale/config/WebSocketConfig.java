package com.thomasvitale.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.thomasvitale.exceptions.FailedToAuthenticateException;
import com.thomasvitale.security.JWTHandler;
import com.thomasvitale.security.JWTRemoteService;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
	@Autowired
	private JWTRemoteService jwtRemoteService;
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/signal-websocket").setAllowedOrigins("*").withSockJS();
    }
    
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
      
    	registration.setInterceptors(new ChannelInterceptorAdapter() {

    	@Override
    	public Message<?> preSend(Message<?> message, MessageChannel channel) throws IllegalArgumentException {

    		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

    		if (StompCommand.CONNECT.equals(accessor.getCommand())) {
    			
    			// Get the Authorization http header
    			String authorizationHeader = accessor.getNativeHeader(JWTHandler.HEADER_STRING).get(0);
    			
    			// Check if there is a token and starts with the proper prefix
    			if (authorizationHeader != null && authorizationHeader.startsWith(JWTHandler.TOKEN_PREFIX)) {
    				
    				// Extract the JWT token by removing the prefix
    				String token = authorizationHeader.replaceAll(JWTHandler.TOKEN_PREFIX, "");
    				
    				String username;
    				try {
    					username = jwtRemoteService.getRemoteUsername(token);
    				} catch(Exception e) {
    					throw new FailedToAuthenticateException(e.getMessage());
    				}
    				
    				if (username == null) {
    					throw new FailedToAuthenticateException("Username is null, authentication failed");
    				}
    				
    				accessor.setLogin(username);
    				
    			}

    		}

    		return message;
    	}
          
    	});
    	
    }
    
}
