package com.popo.camel.gw.rsa.model;

public class AssistanceCase {



    private long id;
	private String caseId;
	private String policyNumber;
	private String externalPolicyNumber;
	
	private String caseType;
	private double lat;
	private double lng;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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

	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
	
}
