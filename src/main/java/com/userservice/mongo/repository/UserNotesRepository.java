package com.userservice.mongo.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cd.book.notes.document.UserNotesDocument;

public interface UserNotesRepository extends MongoRepository<UserNotesDocument, Serializable>{

	//String query = {user.userId:{'$eq':?0}};
	//MongoOperations 
	//List<UserNotesDocument> findBy
}
