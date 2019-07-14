package com.popo.camel.gw.abs.model;

public class Person {
	
	
    private long id;
    

    private String personType;
    private String firstname;
    private String lastname;
    private String address;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    

}
