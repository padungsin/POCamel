package com.popo.camel.gw.adapter.abs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.db.Database;
import com.popo.camel.gw.model.Result;
import com.popo.camel.gw.model.Validation;

@Component
public class ExceptionProcessor implements Processor {

	@Autowired
	Database db;

	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		
		Validation validation = new Validation();
		if(exception instanceof HttpOperationFailedException){
			validation.getError().add("Error request backend service.");
		}else{
		validation.getError().add(exception.getMessage());
		}
		validation.setStatus("004");
		
		
		
		
		Result result = new Result();
		result.setValidation(validation);
		in.setBody(result);
		

	}

}
