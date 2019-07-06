package com.popo.camel.rsa.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.processor.CreateAssistanceCaseProcessor;
import com.popo.camel.rsa.model.AssistanceCase;
@Component
public class StartRoute extends RouteBuilder {

	@Autowired
	private CreateAssistanceCaseProcessor createAssistanceCaseProcessor;

	@Override
	public void configure() throws Exception {

		from("direct:createAssistanceCase")
			.log("Inside createAssistanceCase")
			.marshal().json(JsonLibrary.Jackson)
			.unmarshal().json(JsonLibrary.Jackson, AssistanceCase.class)
			.process(createAssistanceCaseProcessor).transform(body());
/*		
		from("direct:getAssistanceCase")
		.log("Inside getAssistanceCase")
		.marshal().json(JsonLibrary.Jackson)
		.unmarshal().json(JsonLibrary.Jackson, AssistanceCase.class)
		.process(createAssistanceCaseProcessor).transform(body());
        */
        
       
	}

}
