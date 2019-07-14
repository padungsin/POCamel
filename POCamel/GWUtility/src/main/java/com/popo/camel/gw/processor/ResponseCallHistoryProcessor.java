package com.popo.camel.gw.processor;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.apache.camel.AsyncCallback;
import org.apache.camel.AsyncProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popo.camel.gw.callhistory.model.CallHistory;
import com.popo.camel.gw.db.Database;

@Component
public class ResponseCallHistoryProcessor implements Processor {

	@Autowired
	Database db;

	@Override
	public void process(Exchange exchange) throws Exception {

		Message in = exchange.getIn();
		
		
		CallHistory callHistory = db.getCallHistory((String)in.getHeader("ref"), Integer.parseInt((String)in.getHeader("seq")));
		
		
		System.out.println("response in.getBody().getClass().getName()-->" + in.getBody().getClass().getName());
		ObjectMapper mapper = new ObjectMapper();
		callHistory.setResponse(mapper.writeValueAsString(in.getBody()));
		callHistory.setRespondTime(new Date());
		callHistory.setStatus("000");
		callHistory.setDescription("No Error.");
		db.saveCallHistory(callHistory);
		
		
	}

}
