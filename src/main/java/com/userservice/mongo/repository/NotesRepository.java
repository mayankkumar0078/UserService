package com.userservice.mongo.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cd.book.notes.document.NotesDocument;

public interface NotesRepository extends MongoRepository<NotesDocument, Serializable>{

}
