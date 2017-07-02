package com.thomasvitale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.thomasvitale.model.Signal;
import com.thomasvitale.service.SignalService;

@Controller
@CrossOrigin(origins="*", allowedHeaders={"Accept", "Authorization", "Content-Type"})
public class SignalController {

	@Autowired
    private SignalService service;
    
    @MessageMapping("/signal")
    @SendTo("/topic/signals")
    public List<Signal> getScores() {
        
        List<Signal> signals = service.getSignals();
        
        return signals;
    }
	
}
