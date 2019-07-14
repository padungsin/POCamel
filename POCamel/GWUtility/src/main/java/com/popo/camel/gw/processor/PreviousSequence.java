package com.popo.camel.gw.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class PreviousSequence implements Processor {


	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();

		if (in.getHeader("seq") == null) {
			in.setHeader("seq", "1");
			return;
		}

		int seq = Integer.parseInt((String) in.getHeader("seq"));
		seq--;

		in.setHeader("seq", String.valueOf(seq));

	}

}
