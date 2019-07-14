package com.popo.camel.abs.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ABS_ASSISTANCE_CASE")
public class AssistanceCase {
	public enum CaseType {
		XI1, XH1
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String caseId;
	private String policyNumber;
	private String externalPolicyNumber;

	@Enumerated(EnumType.STRING)
	private CaseType caseType;
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

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
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
