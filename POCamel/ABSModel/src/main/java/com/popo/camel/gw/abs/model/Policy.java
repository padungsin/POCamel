package com.popo.camel.gw.abs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Policy {

    private long id;
    private String absPolicyNumber;
    private String externalPolicyNumber;
    private Date startCoverDate;
    private Date endCoverDate;
    private List<Person> parties = new ArrayList<Person>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAbsPolicyNumber() {
		return absPolicyNumber;
	}
	public void setAbsPolicyNumber(String absPolicyNumber) {
		this.absPolicyNumber = absPolicyNumber;
	}
	public String getExternalPolicyNumber() {
		return externalPolicyNumber;
	}
	public void setExternalPolicyNumber(String externalPolicyNumber) {
		this.externalPolicyNumber = externalPolicyNumber;
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
	public List<Person> getParties() {
		return parties;
	}
	public void setParties(List<Person> parties) {
		this.parties = parties;
	}

    
    
    
}
