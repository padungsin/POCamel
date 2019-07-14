package com.popo.camel.gw.model;

import java.util.ArrayList;
import java.util.List;

public class Validation {
	private String status;
	private String description;
	private List<String> error = new ArrayList<String>();
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getError() {
		return error;
	}
	public void setError(List<String> error) {
		this.error = error;
	}
	
	
}
