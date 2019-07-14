package com.popo.camel.gw.model;

import com.popo.camel.gw.rsa.model.Policy;

public class Result {
	private Policy policy;
	private Validation validation;

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}

}
