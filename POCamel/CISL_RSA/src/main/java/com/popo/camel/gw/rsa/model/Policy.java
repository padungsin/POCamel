package com.popo.camel.gw.rsa.model;

import java.util.Date;


public class Policy {

    private long id;
    private String policyNumber;
    private String reference;
    private Date startCoverDate;
    private Date endCoverDate;
    private Person policyHolder;
    private Person policySeller;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getStartCoverDate() {
		return startCoverDate;
	}
	public void setStartCoverDate(Date startCoverDate) {
		this.startCoverDate = startCoverDate;
	}
	public Date getEndCoverDate() {
		return endCoverDate;
	}
	public void setEndCoverDate(Date endCoverDate) {
		this.endCoverDate = endCoverDate;
	}
	public Person getPolicyHolder() {
		return policyHolder;
	}
	public void setPolicyHolder(Person policyHolder) {
		this.policyHolder = policyHolder;
	}
	public Person getPolicySeller() {
		return policySeller;
	}
	public void setPolicySeller(Person policySeller) {
		this.policySeller = policySeller;
	}
    
    
    
}
