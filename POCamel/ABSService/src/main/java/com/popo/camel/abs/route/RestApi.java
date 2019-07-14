package com.popo.camel.abs.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestApi extends RouteBuilder {

	


    @Override
    public void configure() {
        restConfiguration()
            .contextPath("/abs-service").apiContextPath("/api-doc")
                .apiProperty("api.title", "ABS REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port("9500")
            .bindingMode(RestBindingMode.json);
        

        
        rest("/policyx").consumes("application/json").produces("application/json")
        	.post("/")
        		.description("ABS Policy.")
        		.id("ABSPolicy")
        		.bindingMode(RestBindingMode.json)
        		.to("direct:startRoute");


    }
}
