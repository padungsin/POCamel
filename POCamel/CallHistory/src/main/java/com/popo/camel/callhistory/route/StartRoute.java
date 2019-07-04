package com.popo.camel.callhistory.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class StartRoute extends RouteBuilder {

	@Autowired
	private CallHistoryProcessor callHistoryProcessor;

	@Override
	public void configure() throws Exception {
		from("direct:startRoute").log("Inside StartRoute").process(callHistoryProcessor).transform(body());
	}

}
