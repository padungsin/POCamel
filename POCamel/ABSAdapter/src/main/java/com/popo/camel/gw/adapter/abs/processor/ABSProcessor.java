package com.popo.camel.gw.adapter.abs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
@Component
public class ABSProcessor  implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		in.setHeader(Exchange.HTTP_METHOD, "POST");
        
		//in.requ.r .to("http4://localhost:9500/abs-service/policy")
		
	}

}
