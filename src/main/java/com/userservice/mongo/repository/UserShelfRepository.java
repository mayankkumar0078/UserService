package com.userservice.mongo.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.userservice.mongo.document.UserShelfDocument;

public interface UserShelfRepository extends MongoRepository<UserShelfDocument, Serializable>{

	@Query("{user.userId:{'$eq':?0}}")
	UserShelfDocument findByUserId(String userId);
}
