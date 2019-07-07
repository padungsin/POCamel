package com.popo.camel.rsa.abs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.popo.camel.abs.db.AssistanceCaseRepository;
import com.popo.camel.callhistory.CallHistory;

@Component
public class CallHistoryProcessor implements Processor {

	@Autowired
	AssistanceCaseRepository callHistoryRepository;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		
		CallHistory callHistory = in.getBody(CallHistory.class);
		in.setBody(callHistoryRepository.save(callHistory));
		
//		System.out.println(callHistory.getStatus());
	}

}
