package com.popo.camel.gw.rsa.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.callhistory.model.RouteConfig;
import com.popo.camel.gw.db.Database;
import com.popo.camel.gw.processor.RequestCallHistoryProcessor;
import com.popo.camel.gw.processor.ResponseCallHistoryProcessor;
import com.popo.camel.gw.rsa.model.AssistanceCase;
import com.popo.camel.gw.rsa.route.adapter.unity.processor.CISL2UnityAssistanceCase;
import com.popo.camel.gw.processor.GatewayInitialProcessor;
@Component
public class StartRoute extends RouteBuilder {

	@Autowired
	Database db;
	
	@Autowired
	private RequestCallHistoryProcessor requestCallHistoryProcessor ;
	
	@Autowired
	private ResponseCallHistoryProcessor responseCallHistoryProcessor ;
	
	@Autowired
	private GatewayInitialProcessor gatewayInitialProcessor;
	

	@Override
	public void configure() throws Exception {

		from("direct:createAssistanceCase")
		.setHeader("serviceType", constant("RSA.CreateAssistanceCase"))
		.process(gatewayInitialProcessor)
		.to("direct:${header.routeConfig}");
		//.multicast().to("direct:callHistory", "direct:createAssistance");
		
/*
		from("direct:callHistory")
		.setHeader("seq", constant("1"))
		.process(requestCallHistoryProcessor);
		*/
//		from("direct:createAssistance")
//		.to("direct:${header.routeConfig}");
			//.marshal().json(JsonLibrary.Jackson)
			//.unmarshal().json(JsonLibrary.Jackson, AssistanceCase.class)
			//.toD("bean:${header.routeConfig}")
			//.setHeader("seq", constant("1"))
			//.process(responseCallHistoryProcessor)
			//.transform(body()).setId("direct.createAssistance");
		
	}

}
