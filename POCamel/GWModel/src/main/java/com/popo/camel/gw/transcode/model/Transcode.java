package com.popo.camel.gw.transcode.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSCODE")
public class Transcode {
	
	public enum TranscodeType {personType}
	
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private TranscodeType transcodeType;
    private String canonical;
    private String partner;
    private String value;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public TranscodeType getTranscodeType() {
		return transcodeType;
	}
	public void setTranscodeType(TranscodeType transcodeType) {
		this.transcodeType = transcodeType;
	}
	public String getCanonical() {
		return canonical;
	}
	public void setCanonical(String canonical) {
		this.canonical = canonical;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
    
    
}
