package com.userservice.mongo.domain;

public class Specialisation {
	private int specialisationId;
	private String specialisationCode;
	private String speciaisationDesc;
	
	public int getSpecialisationId() {
		return specialisationId;
	}
	public void setSpecialisationId(int specialisationId) {
		this.specialisationId = specialisationId;
	}
	public String getSpecialisationCode() {
		return specialisationCode;
	}
	public void setSpecialisationCode(String specialisationCode) {
		this.specialisationCode = specialisationCode;
	}
	public String getSpeciaisationDesc() {
		return speciaisationDesc;
	}
	public void setSpeciaisationDesc(String speciaisationDesc) {
		this.speciaisationDesc = speciaisationDesc;
	}
}
