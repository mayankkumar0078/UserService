package com.userservice.mongo.domain;

public class College {
	private int collegeId;
	private String collegeCode;
	private String collegeDesc;
	
	public int getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	public String getCollegeCode() {
		return collegeCode;
	}
	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}
	public String getCollegeDesc() {
		return collegeDesc;
	}
	public void setCollegeDesc(String collegeDesc) {
		this.collegeDesc = collegeDesc;
	}
}
