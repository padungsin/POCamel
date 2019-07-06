package com.popo.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.callhistory.CallHistory;
import com.popo.camel.rsa.CallHistoryRepository;
import com.popo.camel.rsa.model.AssistanceCase;

@Component
public class CreateAssistanceCaseProcessor implements Processor {

	@Autowired
	CallHistoryRepository callHistoryRepository;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		
		AssistanceCase assistanceCase = in.getBody(AssistanceCase.class);
		//in.setBody(callHistoryRepository.save(callHistory));
		//System.out.println(callHistory.getStatus());
	}

}
