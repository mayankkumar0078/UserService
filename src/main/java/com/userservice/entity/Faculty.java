package com.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Faculty extends User {
	
	@Column(name = "SUBJECTS", length = 100)
    private String subjects;

	@Column(name = "QUALIFICATION", length = 100)
    private String qualification;

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	
}
