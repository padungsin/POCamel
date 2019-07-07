package com.popo.camel.gw.rsa.route.adapter.unity;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.processor.RequestCallHistoryProcessor;
import com.popo.camel.gw.processor.ResponseCallHistoryProcessor;
import com.popo.camel.gw.rsa.model.AssistanceCase;
import com.popo.camel.gw.rsa.route.adapter.unity.processor.CISL2UnityAssistanceCase;

@Component
public class UnityCreateAssistanceCase extends RouteBuilder {

	@Autowired
	private RequestCallHistoryProcessor requestCallHistoryProcessor ;
	
	@Autowired
	private ResponseCallHistoryProcessor responseCallHistoryProcessor ;
	
	@Autowired
	private CISL2UnityAssistanceCase  cisl2UnityAssistanceCase;
	
	@Override
	public void configure() throws Exception {
		from("direct:" + getClass().getName())
		.setHeader("seq", constant("1"))
		.process(requestCallHistoryProcessor)
		.marshal().json(JsonLibrary.Jackson)
		.unmarshal().json(JsonLibrary.Jackson, AssistanceCase.class)
		.process(cisl2UnityAssistanceCase)
		.setHeader("seq", constant("2"))
		.to("direct:unity");
		

	}

}
