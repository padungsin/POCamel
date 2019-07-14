package com.popo.camel.gw.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.callhistory.model.RouteConfig;
import com.popo.camel.gw.db.Database;

@Component
public class ResultProcessor implements Processor {



	@Override
	public void process(Exchange exchange) throws Exception {

		
		
		Message in = exchange.getIn();
		

		
		

	}

}
