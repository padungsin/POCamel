package com.popo.camel.gw.adapter.abs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.abs.model.Person;
import com.popo.camel.gw.callhistory.model.CanonicalPersonType;
import com.popo.camel.gw.db.Database;
import com.popo.camel.gw.rsa.model.Policy;
import com.popo.camel.gw.transcode.model.Transcode.TranscodeType;



@Component
public class PolicyCISL2ABS implements Processor {

	@Autowired
	Database db;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		
		Policy policy = in.getBody(Policy.class);
		//convert to abs policy
		com.popo.camel.gw.abs.model.Policy absPolicy = new com.popo.camel.gw.abs.model.Policy();
		absPolicy.setId(policy.getId());
		absPolicy.setAbsPolicyNumber(policy.getPolicyNumber());
		absPolicy.setExternalPolicyNumber(policy.getReference());
		absPolicy.setStartCoverDate(policy.getStartCoverDate());
		absPolicy.setEndCoverDate(policy.getEndCoverDate());
		
		Person absPolicyHolder = new Person();

		absPolicyHolder.setFirstname(policy.getPolicyHolder().getFirstname());
		absPolicyHolder.setLastname(policy.getPolicyHolder().getLastname());
		absPolicyHolder.setAddress(policy.getPolicyHolder().getAddress());
		absPolicyHolder.setId(policy.getPolicyHolder().getId());
		absPolicyHolder.setPersonType(db.getTranscodeFromCanonical(TranscodeType.personType, CanonicalPersonType.PolicyHolder.toString(), "ABS").getValue());
		absPolicy.getParties().add(absPolicyHolder);
		
		
		Person absPolicySeller = new Person();

		absPolicySeller.setFirstname(policy.getPolicySeller().getFirstname());
		absPolicySeller.setLastname(policy.getPolicySeller().getLastname());
		absPolicySeller.setAddress(policy.getPolicySeller().getAddress());
		absPolicySeller.setId(policy.getPolicySeller().getId());
		absPolicySeller.setPersonType(db.getTranscodeFromCanonical(TranscodeType.personType, CanonicalPersonType.PolicySeller.toString(), "ABS").getValue());
		absPolicy.getParties().add(absPolicySeller);
		
		in.setBody(absPolicy);
	


	}

}
