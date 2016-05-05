package com.userservice.mongo.service;

import com.userservice.mongo.dto.request.NotesSearchCriteria;
import com.userservice.mongo.exception.UserNotesServiceException;
import com.userservice.mongo.response.NotesResponse;

public interface UserNotesService {

	NotesResponse getAllNotes(NotesSearchCriteria searchCriteria) throws UserNotesServiceException;
}
