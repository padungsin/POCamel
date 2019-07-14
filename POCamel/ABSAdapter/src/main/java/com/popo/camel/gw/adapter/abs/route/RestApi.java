package com.popo.camel.gw.adapter.abs.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestApi extends RouteBuilder {

	


    @Override
    public void configure() {
    	//getContext().addComponent("jetty", new Jet);
    
        restConfiguration()
            .contextPath("/adapter-abs").apiContextPath("/api-doc")
                .apiProperty("api.title", "ABS Adapter API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port("9100")
            .bindingMode(RestBindingMode.json);
        

        
        rest("/policy").consumes("application/json").produces("application/json")
        	.post("/")
        		.description("ABS Adapter Policy.")
        		.id("ABSAdapterPolicy")
        		.bindingMode(RestBindingMode.json)
        		.to("direct:startRoute");


    }
}
