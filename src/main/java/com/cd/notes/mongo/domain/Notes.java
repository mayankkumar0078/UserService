package com.cd.notes.mongo.domain;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import com.userservice.mongo.domain.User;
import com.userservice.mongo.domain.UserComment;

public class Notes {
	private User user;
	private int collegeId;
	private String collegeCode;
	private String collegeDesc;
	
	private int programId;
	private String programCode;
	private String programDesc;
	
	private int specialisationId;
	private String specialisationCode;
	private String specialisationDesc;
	
	private int subjectId;
	private String subjectCode;
	private String subjectDesc;
	
    private String topic;
    private List<User> likedBy;
	private Integer likes;
	private List<UserComment> userComments;
	private String fileLocation;
	private transient List<File> files;
	private Calendar date;
	
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<User> getLikedBy() {
		return likedBy;
	}
	public void setLikedBy(List<User> likedBy) {
		this.likedBy = likedBy;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public List<UserComment> getUserComments() {
		return userComments;
	}
	public void setUserComments(List<UserComment> userComments) {
		this.userComments = userComments;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
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
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getProgramCode() {
		return programCode;
	}
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	public String getProgramDesc() {
		return programDesc;
	}
	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}
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
	public String getSpecialisationDesc() {
		return specialisationDesc;
	}
	public void setSpecialisationDesc(String specialisationDesc) {
		this.specialisationDesc = specialisationDesc;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
