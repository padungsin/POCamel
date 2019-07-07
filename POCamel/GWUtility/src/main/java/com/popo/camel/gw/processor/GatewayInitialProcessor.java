package com.popo.camel.gw.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.callhistory.model.RouteConfig;
import com.popo.camel.gw.db.Database;

@Component
public class GatewayInitialProcessor implements Processor {

	@Autowired
	Database db;

	@Override
	public void process(Exchange exchange) throws Exception {

		
		
		Message in = exchange.getIn();
		in.setHeader("ref", db.generateSequence());
		

		
		RouteConfig routeConfig = db.getRoute((String)in.getHeader("applicationId"), (String)in.getHeader("countryCode"), (String)in.getHeader("serviceType"));
		in.setHeader("routeConfig", routeConfig.getRoute());


	}

}
