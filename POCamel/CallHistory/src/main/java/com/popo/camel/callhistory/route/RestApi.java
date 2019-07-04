package com.popo.camel.callhistory.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestApi extends RouteBuilder {

	
	@Autowired
	private CallHistoryProcessor callHistoryProcessor;

    @Override
    public void configure() {
        restConfiguration()
            .contextPath("/camel-rest-jpa").apiContextPath("/api-doc")
                .apiProperty("api.title", "CallHistory REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port("8086")
            .bindingMode(RestBindingMode.json);
        
        
        rest("/say")
        .get("/hello").to("direct:hello")
        .get("/bye").consumes("application/json").to("direct:bye")
        .post("/bye").to("mock:update");
        
        
        rest("/callHistory").consumes("application/json").produces("application/json")
        	.post("/").bindingMode(RestBindingMode.json).to("direct:startRoute");

        
        from("direct:hello")
        	.transform().constant("Hello World");
        from("direct:bye")
        	.transform().constant("Bye World");
        
        //from("direct:startRoute").process(callHistoryProcessor).transform(body());

//        rest("/callHistory").description("CallHistory REST service")
//            .get("/").description("The list of all the books")
//                .route().routeId("books-api")
//                .bean(Database.class, "findBooks")
//                .endRest()
//            .post("/").description("Save CallHistory")
//                .route().routeId("callhistory-api")
//                .bean(Database.class, "saveCallHistory")
//            .get("order/{id}").description("Details of an order by id")
//                .route().routeId("order-api")
//                .bean(Database.class, "findOrder(${header.id})");
    }
}
