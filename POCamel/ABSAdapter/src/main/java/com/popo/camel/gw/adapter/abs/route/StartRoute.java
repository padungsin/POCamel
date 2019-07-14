package com.popo.camel.gw.adapter.abs.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.adapter.abs.processor.ExceptionProcessor;
import com.popo.camel.gw.adapter.abs.processor.PolicyABS2CISL;
import com.popo.camel.gw.adapter.abs.processor.PolicyCISL2ABS;
import com.popo.camel.gw.processor.NextSequence;
import com.popo.camel.gw.processor.PreviousSequence;
import com.popo.camel.gw.processor.RequestCallHistoryProcessor;
import com.popo.camel.gw.processor.ResponseCallHistoryProcessor;
@Component
public class StartRoute extends RouteBuilder {

	@Autowired
	private PolicyCISL2ABS policyCISL2ABS;

	@Autowired
	private PolicyABS2CISL policyABS2CISL;
	@Autowired
	private RequestCallHistoryProcessor requestCallHistoryProcessor;
	@Autowired
	private ResponseCallHistoryProcessor responseCallHistoryProcessor;
	
	@Autowired
	private NextSequence nextSequence ;
	@Autowired
	private PreviousSequence previousSequence ;

	

	@Autowired
	private ExceptionProcessor exceptionProcessor;
	
	
	@Override
	public void configure() throws Exception {

		onException(Exception.class)
		.handled(true)
		.process(exceptionProcessor);
		
		from("direct:startRoute")
		.marshal().json(JsonLibrary.Jackson)
		.unmarshal().json(JsonLibrary.Jackson, com.popo.camel.gw.rsa.model.Policy.class)
		.setHeader("subserviceType", constant("ABS.CreatePolicy"))
		.setHeader("endpoint", constant("http4://localhost:9500/abs-service?bridgeEndpoint=true"))
        .process(policyCISL2ABS)
		.process(nextSequence)
		.process(requestCallHistoryProcessor)
        .marshal().json(JsonLibrary.Jackson)
        .setHeader(Exchange.HTTP_METHOD, simple("POST"))
        .setHeader(Exchange.HTTP_PATH, simple("/policyx"))
        .to("http4://localhost:9500/abs-service?bridgeEndpoint=true")
        .unmarshal().json(JsonLibrary.Jackson, com.popo.camel.gw.abs.model.Result.class)
        .process(responseCallHistoryProcessor)
        .process(policyABS2CISL)
        .transform(body());
		
	
	    

        
  
	}

}
