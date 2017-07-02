package com.thomasvitale.config;

import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

	@Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .nullDestMatcher().authenticated() 
                .simpDestMatchers("/app/**").permitAll()
                .simpSubscribeDestMatchers("/topic/**").permitAll()
                .anyMessage().denyAll();

    }
	
	@Override
    protected boolean sameOriginDisabled() {
        return true;
    }
	
}
