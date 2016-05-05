package com.userservice.mongo.dto.request;

import java.util.List;

public class NotesSearchCriteria extends BasicSearchCriteria{

	private List<Integer> collegeIds;
	private List<Integer> programIds;
	private List<Integer> specialisationIds;
	private List<Integer> subjectIds;
	
	public List<Integer> getCollegeIds() {
		return collegeIds;
	}
	public void setCollegeIds(List<Integer> collegeIds) {
		this.collegeIds = collegeIds;
	}
	public List<Integer> getProgramIds() {
		return programIds;
	}
	public void setProgramIds(List<Integer> programIds) {
		this.programIds = programIds;
	}
	public List<Integer> getSpecialisationIds() {
		return specialisationIds;
	}
	public void setSpecialisationIds(List<Integer> specialisationIds) {
		this.specialisationIds = specialisationIds;
	}
	public List<Integer> getSubjectIds() {
		return subjectIds;
	}
	public void setSubjectIds(List<Integer> subjectIds) {
		this.subjectIds = subjectIds;
	}
	
}
