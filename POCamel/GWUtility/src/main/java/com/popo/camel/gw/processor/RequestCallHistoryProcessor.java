package com.popo.camel.gw.processor;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popo.camel.gw.callhistory.model.CallHistory;
import com.popo.camel.gw.db.Database;

@Component
public class RequestCallHistoryProcessor implements Processor {

	@Autowired
	Database db;

	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();

		CallHistory callHistory = new CallHistory();
		callHistory.setRef((String) in.getHeader("ref"));
		callHistory.setApplicationId((String) in.getHeader("applicationId"));
		callHistory.setCountryCode((String) in.getHeader("countryCode"));
		callHistory.setServiceType((String) in.getHeader("serviceType"));
		callHistory.setSubserviceType((String) in.getHeader("subserviceType"));
		callHistory.setSeq(Integer.parseInt((String) in.getHeader("seq")));
		callHistory.setEndpoint((String) in.getHeader("endpoint"));

		System.out.println("request in.getBody().getClass().getName()-->" + in.getBody().getClass().getName());
		ObjectMapper mapper = new ObjectMapper();
		callHistory.setRequest(mapper.writeValueAsString(in.getBody()));
		callHistory.setRequestTime(new Date());

		db.saveCallHistory(callHistory);

	}

}
