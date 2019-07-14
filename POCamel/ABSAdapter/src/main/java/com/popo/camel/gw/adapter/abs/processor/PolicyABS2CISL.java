package com.popo.camel.gw.adapter.abs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.callhistory.model.CanonicalPersonType;
import com.popo.camel.gw.db.Database;
import com.popo.camel.gw.model.Result;
import com.popo.camel.gw.model.Validation;
import com.popo.camel.gw.rsa.model.Person;
import com.popo.camel.gw.rsa.model.Policy;
import com.popo.camel.gw.transcode.model.Transcode.TranscodeType;

@Component
public class PolicyABS2CISL implements Processor {

	@Autowired
	Database db;

	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		
		Validation validation = new Validation();
		Result result = new Result();

		// convert to abs policy
		com.popo.camel.gw.abs.model.Result absResult = in.getBody(com.popo.camel.gw.abs.model.Result.class);
		
		if(absResult.isSuccess()){
			
			com.popo.camel.gw.abs.model.Policy absPolicy = (com.popo.camel.gw.abs.model.Policy)absResult.getPolicy();
			Policy policy = new Policy();
			policy.setId(absPolicy.getId());
			policy.setPolicyNumber(absPolicy.getAbsPolicyNumber());
			policy.setReference(absPolicy.getExternalPolicyNumber());
			policy.setStartCoverDate(absPolicy.getStartCoverDate());
			policy.setEndCoverDate(absPolicy.getEndCoverDate());

			

			for (com.popo.camel.gw.abs.model.Person absPerson : absPolicy.getParties()) {
				
				if (CanonicalPersonType.PolicyHolder.toString().equals(db.getTranscodeFromPartnerValue(TranscodeType.personType, absPerson.getPersonType(), "ABS").getCanonical())) {
					Person person = new Person();
					person.setFirstname(absPerson.getFirstname());
					person.setLastname(absPerson.getLastname());
					person.setAddress(absPerson.getAddress());
					person.setId(absPerson.getId());
					policy.setPolicyHolder(person);
				
				}
				
				if (CanonicalPersonType.PolicySeller.toString().equals(db.getTranscodeFromPartnerValue(TranscodeType.personType, absPerson.getPersonType(), "ABS").getCanonical())) {
					Person person = new Person();
					person.setFirstname(absPerson.getFirstname());
					person.setLastname(absPerson.getLastname());
					person.setAddress(absPerson.getAddress());
					person.setId(absPerson.getId());
					policy.setPolicySeller(person);
					
				}
			}

			validation.setStatus("000");
			validation.setDescription(absResult.getMessage());
			result.setPolicy(policy);
			
			
		}else{
			validation.setStatus("004");
			validation.setDescription(absResult.getMessage());
			validation.getError().add(absResult.getMessage());
		}
		
		result.setValidation(validation);
		
		in.setBody(result);
	}

}
