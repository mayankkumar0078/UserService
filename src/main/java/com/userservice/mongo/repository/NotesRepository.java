package com.userservice.mongo.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.userservice.mongo.document.NotesDocument;

public interface NotesRepository extends MongoRepository<NotesDocument, Serializable>{

}
