package com.thomasvitale.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.thomasvitale.service.SignalService;

@Configuration
@EnableScheduling
public class ScoreScheduler {

	@Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    SignalService service;

    @Scheduled(fixedRate = 5000)
    public void publishUpdates(){
       
        template.convertAndSend("/topic/signals", service.getSignals());
       
    }
	
}
