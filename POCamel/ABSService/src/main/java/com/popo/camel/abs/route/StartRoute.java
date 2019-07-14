package com.popo.camel.abs.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.abs.model.Policy;
import com.popo.camel.abs.processor.PolicyProcessor;
@Component
public class StartRoute extends RouteBuilder {

	@Autowired
	private PolicyProcessor policyProcessor;

	@Override
	public void configure() throws Exception {
		//from("direct:startRoute").log("Inside StartRoute").unmarshal().json(JsonLibrary.Jackson, CallHistory.class).process(callHistoryProcessor).transform(body());
		from("direct:startRoute")
		.marshal().json(JsonLibrary.Jackson)
        .unmarshal().json(JsonLibrary.Jackson, Policy.class)
        .process(policyProcessor).transform(body());
        
  
	}

}
