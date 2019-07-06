package com.popo.camel.rsa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ASSISTANCE_CASE")
public class AssistanceCase {

    @Id
    @GeneratedValue
    private int id;
	private String caseId;
	private String policyNumber;
	private String externalPolicyNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getExternalPolicyNumber() {
		return externalPolicyNumber;
	}
	public void setExternalPolicyNumber(String externalPolicyNumber) {
		this.externalPolicyNumber = externalPolicyNumber;
	}
	
	
	
}
