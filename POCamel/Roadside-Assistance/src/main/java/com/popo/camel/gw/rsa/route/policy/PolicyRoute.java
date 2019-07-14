package com.popo.camel.gw.rsa.route.policy;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.processor.GatewayInitialProcessor;
import com.popo.camel.gw.processor.NextSequence;
import com.popo.camel.gw.processor.PreviousSequence;
import com.popo.camel.gw.processor.RequestCallHistoryProcessor;
import com.popo.camel.gw.processor.ResponseCallHistoryProcessor;
import com.popo.camel.gw.rsa.model.Policy;

@Component
public class PolicyRoute extends RouteBuilder {

	
	@Autowired
	private GatewayInitialProcessor gatewayInitialProcessor; 
	@Autowired
	private RequestCallHistoryProcessor requestCallHistoryProcessor ;
	
	@Autowired
	private ResponseCallHistoryProcessor responseCallHistoryProcessor ;
	
	@Autowired
	private NextSequence nextSequence ;
	@Autowired
	private PreviousSequence previousSequence ;
	

	

	@Override
	public void configure() throws Exception {
		from("direct:createPolicy")
		.setHeader("serviceType", constant("RSA.CreatePolicy"))
		.process(gatewayInitialProcessor)
		.marshal().json(JsonLibrary.Jackson)
		.unmarshal().json(JsonLibrary.Jackson, Policy.class)
		.process(nextSequence)
		.process(requestCallHistoryProcessor)
		.setHeader(Exchange.HTTP_METHOD, simple("POST"))
		//.setHeader(Exchange.HTTP_PATH, simple("/policy"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
	    //.to("${header.routeConfig}")
		.marshal().json(JsonLibrary.Jackson)
		.toD("http4://${header.routeConfig}?bridgeEndpoint=true")
	    .unmarshal().json(JsonLibrary.Jackson, Policy.class)
	    .process(previousSequence)
		.process(responseCallHistoryProcessor)
		.transform(body());
		
		

		

	}

}
