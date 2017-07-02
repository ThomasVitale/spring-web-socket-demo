package com.thomasvitale.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thomasvitale.model.Signal;

@Service
public class SignalService {
	
	private List<Signal> signals = initialSignals();
	
	public List<Signal> getSignals() {
		
		for (Signal s : signals) {
			s.setLastAccess(new Date());
		}
		
		return signals;
	}

	private List<Signal> initialSignals() {
		
		List<Signal> initialSignals = new ArrayList<>();
		
		Signal signal1 = new Signal("1", "Thomas", "Via Roma", "Lavori in corso", new Date(), new Date(),5.0);
		initialSignals.add(signal1);
		
		Signal signal2 = new Signal("2", "Harry", "Grimmauld Place", "Happening weird things", new Date(), new Date(), 3.0);
		initialSignals.add(signal2);
		
		return initialSignals;
	}

}
