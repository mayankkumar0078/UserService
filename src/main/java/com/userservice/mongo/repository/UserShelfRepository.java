package com.userservice.mongo.repository;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongodb.DBObject;
import com.userservice.mongo.document.UserShelfDocument;

public interface UserShelfRepository extends MongoRepository<UserShelfDocument, Serializable>{

	@Query("{user.userId:{'$eq':?0}}")
	UserShelfDocument findByUserId(String userId);
	
	@Query("?0")
	UserShelfDocument findByUserId1(DBObject userId);
}
