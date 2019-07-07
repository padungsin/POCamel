package com.popo.camel.gw.rsa.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestApi extends RouteBuilder {

	@Override
	public void configure() {
		restConfiguration()
			.contextPath("/Roadsied-Assistance")
			.apiContextPath("/api-doc")
			.apiProperty("api.title", "Assistance Case REST API")
			.apiProperty("api.version", "1.0").apiProperty("cors", "true")
			.apiContextRouteId("doc-api")
			.port("8086")
			.bindingMode(RestBindingMode.json);

		rest("/assistanceCases").consumes("application/json").produces("application/json")
			.post("/").bindingMode(RestBindingMode.json).to("direct:createAssistanceCase")
			.get("/{assistanceCaseId}").bindingMode(RestBindingMode.json).to("direct:getAssistanceCase");
		

	}
}
