package com.cd.book.notes.document;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.userservice.mongo.domain.College;
import com.userservice.mongo.domain.Program;
import com.userservice.mongo.domain.Specialisation;
import com.userservice.mongo.domain.Subject;
import com.userservice.mongo.domain.User;
import com.userservice.mongo.domain.UserComment;

@Document(collection="notes")
public class NotesDocument {
	@Id
	private String id;
	
	private College college;
	private Program program;
	private Specialisation specialisation;
	private Subject subject;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public Specialisation getSpecialisation() {
		return specialisation;
	}
	public void setSpecialisation(Specialisation specialisation) {
		this.specialisation = specialisation;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
