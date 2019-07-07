package com.popo.camel.rsa.abs.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.callhistory.CallHistory;
import com.popo.camel.rsa.abs.processor.CallHistoryProcessor;
@Component
public class StartRoute extends RouteBuilder {

	@Autowired
	private CallHistoryProcessor callHistoryProcessor;

	@Override
	public void configure() throws Exception {
		//from("direct:startRoute").log("Inside StartRoute").unmarshal().json(JsonLibrary.Jackson, CallHistory.class).process(callHistoryProcessor).transform(body());
		from("direct:startRoute")
		.log("Inside StartRoute")
		.marshal().json(JsonLibrary.Jackson)
        .unmarshal().json(JsonLibrary.Jackson, CallHistory.class)
        .process(callHistoryProcessor).transform(body());
        
        
        /*
        .choice()
            .when().simple("${body.status} == '000'")
                .log("Success")
                .to("direct:success")
            .otherwise()
                .log("Failure")
                .to("direct:failure")
        // close the choice() block :
        .end();
        // per the javadoc for marshall(), "the output will be added to the out body" :
        //.marshal().json(JsonLibrary.Jackson);
		
		
		from("direct:success").transform().constant("Success");
		from("direct:failure").transform().constant("Failure");
		
		*/
	}

}
