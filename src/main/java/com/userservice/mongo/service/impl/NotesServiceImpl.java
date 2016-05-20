package com.userservice.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.userservice.constants.UserAccountConstants;
import com.userservice.mongo.document.NotesDocument;
import com.userservice.mongo.dto.request.NotesSearchCriteria;
import com.userservice.mongo.exception.UserNotesServiceException;
import com.userservice.mongo.repository.NotesRepository;
import com.userservice.mongo.response.NotesResponse;
import com.userservice.mongo.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService{
	//@Autowired
	//private UserNotesRepository userNotesRepository;
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public NotesResponse getAllNotes(NotesSearchCriteria criteria) throws UserNotesServiceException {
		NotesResponse response = new NotesResponse();
		String queryStr = "";
		//make the query string
		if(!CollectionUtils.isEmpty(criteria.getCollegeIds())){
			queryStr += "college.collegeId : {'$in':"+criteria.getCollegeIds()+"}"; 
		}
		if(!CollectionUtils.isEmpty(criteria.getProgramIds())){
			if(!StringUtils.isEmpty(queryStr)){
				queryStr += ",";
			}
			queryStr += "program.programId:{'$in':"+criteria.getProgramIds()+"}";
		}
		if(!CollectionUtils.isEmpty(criteria.getSpecialisationIds())){
			if(!StringUtils.isEmpty(queryStr)){
				queryStr += ",";
			}
			queryStr += "specialisation.specialisationId:{'$in':"+criteria.getSpecialisationIds()+"}";
		}
		if(!CollectionUtils.isEmpty(criteria.getSubjectIds())){
			if(!StringUtils.isEmpty(queryStr)){
				queryStr += ",";
			}
			queryStr += "subject.subjectId:{'$in':"+criteria.getSubjectIds()+"}";
		}
		queryStr = "{"+queryStr+"}";
		BasicQuery query = new BasicQuery(queryStr);
		//create pageable request, sort order and set it to query
		final Pageable pageableRequest = new PageRequest(criteria.getPageNo(), criteria.getPageSize());
		query.with(pageableRequest);
		
		//query.with(new Sort(Sort.Direction.ASC, criteria.getSortBy()));
		//fire the query 
		try {
			List<NotesDocument> docList = mongoTemplate.find(query, NotesDocument.class);
			long count = mongoTemplate.count(query, NotesDocument.class);
			response.setNotesList(docList);
			response.setCount(count);
		}
		catch (Exception e) {
			throw new UserNotesServiceException(UserAccountConstants.APPLICATION_CODE_USER_SERVICE, HttpStatus.INTERNAL_SERVER_ERROR.value(), 
					HttpStatus.INTERNAL_SERVER_ERROR.value(), "Can't retrieve the notes", e);
		}
		
		return response;
	}

}
