package com.popo.camel.abs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.abs.db.Database;
import com.popo.camel.abs.model.Policy;

@Component
public class PolicyProcessor implements Processor {

	@Autowired
	Database db;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		
		Policy policy = in.getBody(Policy.class);
		in.setBody(db.createPolicy(policy));
		

	}

}
