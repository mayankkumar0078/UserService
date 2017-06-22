/*package com.userservice.mongo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.notes.mongo.domain.NotesShelf;
import com.userservice.mongo.document.NotesDocument;
import com.userservice.mongo.domain.College;
import com.userservice.mongo.domain.Program;
import com.userservice.mongo.domain.Specialisation;
import com.userservice.mongo.domain.Subject;
import com.userservice.mongo.domain.User;
import com.userservice.mongo.dto.request.NotesSearchCriteria;
import com.userservice.mongo.exception.UserNotesServiceException;
import com.userservice.mongo.repository.NotesRepository;
import com.userservice.mongo.response.NotesResponse;
import com.userservice.mongo.response.transformer.UserNotesResponseTransformer;
import com.userservice.mongo.service.NotesService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "user/notes", produces = {"application/json", "application/xml"})
public class NotesController {

	//@Autowired
	//private UserNotesRepository userNotesRepository;
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private NotesService userNotesService;
	
	@Resource
	private UserNotesResponseTransformer userNotesResponseTransformer; 
	
	@ResponseBody
	@RequestMapping( method = RequestMethod.POST, produces = {"application/json", "application/xml"})
	public boolean insertHasrdCodedData(){
		try {
			//insert into the table with data
			insertData();
	}catch(Exception e){
		System.out.println("problem occured: "+e);
		return false;
	}
		return true;
	}
	
	
	@ResponseBody
	@RequestMapping( value = "/get/all", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
	public NotesResponse retrieveUserNotes(@RequestBody NotesSearchCriteria criteria){
		NotesResponse response = new NotesResponse();
		try {
			//insert into the table with data
			//insertData();
			 response = userNotesService.getAllNotes(criteria);
			 
		} catch (UserNotesServiceException e) {
			return (NotesResponse) userNotesResponseTransformer.buildExceptionResponse(e, response);
		}
		return userNotesResponseTransformer.transformIntoSuccessResponse(response);
	}
	private void insertData(){
		
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		User user4 = new User();
		User user5 = new User();
		User user6 = new User();
		user1.setUserId("1");
		user1.setUserName("abhishek");
		
		user2.setUserId("2");
		user2.setUserName("swapna");
		
		user3.setUserId("3");
		user3.setUserName("Shiva");
		
		user4.setUserId("4");
		user4.setUserName("ganesh");
		
		user5.setUserId("5");
		user5.setUserName("sagar");
		
		user6.setUserId("6");
		user6.setUserName("hari");
		
		NotesShelf shelf1 = new NotesShelf();
		NotesShelf shelf2 = new NotesShelf();
		NotesShelf shelf3 = new NotesShelf();
		NotesShelf shelf4 = new NotesShelf();
		NotesShelf shelf5 = new NotesShelf();
		NotesShelf shelf6 = new NotesShelf();
		
		shelf1.setDescription("abhishek shelf");
		shelf1.setShelfName("abhishek shelf");
		
		shelf2.setDescription("swapna shelf");
		shelf2.setShelfName("swapna shelf");
		
		shelf3.setDescription("shiva shelf");
		shelf3.setShelfName("shiva shelf");
		
		shelf4.setDescription("ganesh shelf");
		shelf4.setShelfName("ganes shelf");
		
		shelf5.setDescription("sagar shelf");
		shelf5.setShelfName("sagar shelf");
		
		shelf6.setDescription("hari shelf");
		shelf6.setShelfName("hari shelf");
		
		NotesDocument note1 = new NotesDocument();
		NotesDocument note2 = new NotesDocument();
		NotesDocument note3 = new NotesDocument();
		NotesDocument note4 = new NotesDocument();
		NotesDocument note5 = new NotesDocument();
		NotesDocument note6 = new NotesDocument();
		
		NotesDocument note7 = new NotesDocument();
		NotesDocument note8 = new NotesDocument();
		NotesDocument note9 = new NotesDocument();
		NotesDocument note10 = new NotesDocument();
		NotesDocument note11 = new NotesDocument();
		NotesDocument note12 = new NotesDocument();
		
		College c1 = new College();
		Program p1 = new  Program();
		Specialisation spe1 = new Specialisation();
		Subject s1 = new Subject();
		
		College c2 = new College();
		Program p2 = new  Program();
		Specialisation spe2 = new Specialisation();
		Subject s2 = new Subject();
		
		College c3 = new College();
		Program p3 = new  Program();
		Specialisation spe3 = new Specialisation();
		Subject s3 = new Subject();
		
		College c4 = new College();
		Program p4 = new  Program();
		Specialisation spe4 = new Specialisation();
		Subject s4 = new Subject();
		
		College c5 = new College();
		Program p5 = new  Program();
		Specialisation spe5 = new Specialisation();
		Subject s5 = new Subject();
		
		College c6 = new College();
		Program p6 = new  Program();
		Specialisation spe6 = new Specialisation();
		Subject s6 = new Subject();
		
		College c7 = new College();
		Program p7 = new  Program();
		Specialisation spe7 = new Specialisation();
		Subject s7 = new Subject();
		
		College c8 = new College();
		Program p8 = new  Program();
		Specialisation spe8 = new Specialisation();
		Subject s8 = new Subject();
		
		College c9 = new College();
		Program p9 = new  Program();
		Specialisation spe9 = new Specialisation();
		Subject s9 = new Subject();
		
		College c10 = new College();
		Program p10 = new  Program();
		Specialisation spe10 = new Specialisation();
		Subject s10 = new Subject();
		
		College c11 = new College();
		Program p11 = new  Program();
		Specialisation spe11 = new Specialisation();
		Subject s11 = new Subject();
		
		College c12 = new College();
		Program p12 = new  Program();
		Specialisation spe12 = new Specialisation();
		Subject s12 = new Subject();
		
		c1.setCollegeId(1);
		c1.setCollegeCode("smvdu");
		c1.setCollegeDesc("sri mata vaishno devi university");
		p1.setProgramId(1);
		p1.setProgramCode("b.tech");
		spe1.setSpecialisationId(1);
		spe1.setSpecialisationCode("cse");
		s1.setSubjectId(1);
		s1.setSubjectCode("csp");
		s1.setSubjectDesc("Computer Systems and Programming");
		note1.setTopic("Introduction to system programming");
		note1.setCollege(c1);
		note1.setProgram(p1);
		note1.setSpecialisation(spe1);
		note1.setSubject(s1);
		
		c2.setCollegeId(1);
		c2.setCollegeCode("smvdu");
		c2.setCollegeDesc("sri mata vaishno devi university");
		p2.setProgramId(2);
		p2.setProgramCode("bba");
		p2.setProgramDesc("");
		spe2.setSpecialisationId(5);
		spe2.setSpecialisationCode("finance");
		s2.setSubjectId(8);
		s2.setSubjectCode("be-1");
		s2.setSubjectDesc("Business English - I");
		note2.setTopic("How to write good letters");
		note2.setCollege(c2);
		note2.setProgram(p2);
		note2.setSpecialisation(spe2);
		note2.setSubject(s2);
		
		c3.setCollegeId(1);
		c3.setCollegeCode("smvdu");
		c3.setCollegeDesc("sri mata vaishno devi university");
		p3.setProgramId(5);
		p3.setProgramCode("mba");
		p3.setProgramDesc("");
		spe3.setSpecialisationId(6);
		spe3.setSpecialisationCode("marketing");
		s3.setSubjectId(10);
		s3.setSubjectCode("pme");
		s3.setSubjectDesc("Principles of Micro Economics");
		note3.setTopic("micro economics overview");
		note3.setCollege(c3);
		note3.setProgram(p3);
		note3.setSpecialisation(spe3);
		note3.setSubject(s3);
		
		c4.setCollegeId(2);
		c4.setCollegeCode("bc");
		c4.setCollegeDesc("Bharati College");
		p4.setProgramId(5);
		p4.setProgramCode("mba");
		p4.setProgramDesc("");
		spe4.setSpecialisationId(7);
		spe4.setSpecialisationCode("es");
		s4.setSubjectId(11);
		s4.setSubjectCode("pfa");
		s4.setSubjectDesc("Principles of Financial Accounting");
		note4.setTopic("financial accounting step by step");
		note4.setCollege(c4);
		note4.setProgram(p4);
		note4.setSpecialisation(spe4);
		note4.setSubject(s4);
		
		c5.setCollegeId(2);
		c5.setCollegeCode("bc");
		c5.setCollegeDesc("Bharati College");
		p5.setProgramId(2);
		p5.setProgramCode("bba");
		p5.setProgramDesc("");
		spe5.setSpecialisationId(6);
		spe5.setSpecialisationCode("m");
		s5.setSubjectId(9);
		s5.setSubjectCode("bm-1");
		s5.setSubjectDesc("Business Mathematics - I");
		note5.setTopic("calulating business mathematics by theory");
		note5.setCollege(c5);
		note5.setProgram(p5);
		note5.setSpecialisation(spe5);
		note5.setSubject(s5);
		
		c6.setCollegeId(5);
		c6.setCollegeCode("iitk");
		c6.setCollegeDesc("iit kanpur");
		p6.setProgramId(1);
		p6.setProgramCode("b.tech");
		p6.setProgramDesc("");
		spe6.setSpecialisationId(2);
		spe6.setSpecialisationCode("ece");
		s6.setSubjectId(2);
		s6.setSubjectCode("oops");
		s6.setSubjectDesc("Fundamentals of Object Oriented Programming");
		note6.setTopic("Concepts for multiple inheritance");
		note6.setCollege(c6);
		note6.setProgram(p6);
		note6.setSpecialisation(spe6);
		note6.setSubject(s6);
		
		c7.setCollegeId(5);
		c7.setCollegeCode("iitk");
		c7.setCollegeDesc("iit kanpur");
		p7.setProgramId(1);
		p7.setProgramCode("b.tech");
		p7.setProgramDesc("");
		spe7.setSpecialisationId(3);
		spe7.setSpecialisationCode("me");
		s7.setSubjectId(8);
		s7.setSubjectCode("be-1");
		s7.setSubjectDesc("Business English - I");
		note7.setTopic("vocabulary pronounication");
		note7.setCollege(c7);
		note7.setProgram(p7);
		note7.setSpecialisation(spe7);
		note7.setSubject(s7);
		
		c8.setCollegeId(7);
		c8.setCollegeCode("iitd");
		c8.setCollegeDesc("iit delhi");
		p8.setProgramId(1);
		p8.setProgramCode("b.tech");
		p8.setProgramDesc("");
		spe8.setSpecialisationId(2);
		spe8.setSpecialisationCode("ece");
		s8.setSubjectId(4);
		s8.setSubjectCode("lc");
		s8.setSubjectDesc("Liner circuits");
		note8.setTopic("Primary circuit disign");
		note8.setCollege(c8);
		note8.setProgram(p8);
		note8.setSpecialisation(spe8);
		note8.setSubject(s8);
		
		c9.setCollegeId(7);
		c9.setCollegeCode("iitd");
		c9.setCollegeDesc("iit delhi");
		p9.setProgramId(1);
		p9.setProgramCode("b.tech");
		p9.setProgramDesc("");
		spe9.setSpecialisationId(2);
		spe9.setSpecialisationCode("ece");
		s9.setSubjectId(4);
		s9.setSubjectCode("lc");
		s9.setSubjectDesc("Liner circuits");
		note9.setTopic("How to desing advanced cirtuit for machines");
		note9.setCollege(c9);
		note9.setProgram(p9);
		note9.setSpecialisation(spe9);
		note9.setSubject(s9);
		
		c10.setCollegeId(7);
		c10.setCollegeCode("iitd");
		c10.setCollegeDesc("iit delhi");
		p10.setProgramId(1);
		p10.setProgramCode("b.tech");
		p10.setProgramDesc("");
		spe10.setSpecialisationId(1);
		spe10.setSpecialisationCode("cse");
		s10.setSubjectId(1);
		s10.setSubjectCode("csp");
		s10.setSubjectDesc("");
		note10.setTopic("System programming and basic concepts");
		note10.setCollege(c10);
		note10.setProgram(p10);
		note10.setSpecialisation(spe10);
		note10.setSubject(s10);
		
		c11.setCollegeId(7);
		c11.setCollegeCode("iitd");
		c11.setCollegeDesc("iit delhi");
		p11.setProgramId(1);
		p11.setProgramCode("b.tech");
		p11.setProgramDesc("");
		spe11.setSpecialisationId(1);
		spe11.setSpecialisationCode("cse");
		s11.setSubjectId(1);
		s11.setSubjectCode("csp");
		s11.setSubjectDesc("");
		note11.setTopic("System programming and advanced concepts");
		note11.setCollege(c11);
		note11.setProgram(p11);
		note11.setSpecialisation(spe11);
		note11.setSubject(s11);
		
		c12.setCollegeId(1);
		c12.setCollegeCode("smvdu");
		c12.setCollegeDesc("sri mata vaishno devi university");
		p12.setProgramId(1);
		p12.setProgramCode("b.tech");
		p12.setProgramDesc("");
		spe12.setSpecialisationId(1);
		spe12.setSpecialisationCode("cse");
		s12.setSubjectId(1);
		s12.setSubjectCode("csp");
		s12.setSubjectDesc("Computer Systems and Programming");
		note12.setTopic("Algorithm desing for programming");
		note12.setCollege(c12);
		note12.setProgram(p12);
		note12.setSpecialisation(spe12);
		note12.setSubject(s12);
		
		List<NotesDocument> notesList1 = new ArrayList<>();
		List<NotesDocument> notesList2 = new ArrayList<>();
		List<NotesDocument> notesList3 = new ArrayList<>();
		List<NotesDocument> notesList4 = new ArrayList<>();
		List<NotesDocument> notesList5 = new ArrayList<>();
		List<NotesDocument> notesList6 = new ArrayList<>();
		
		notesList1.add(note1);
		notesList1.add(note2);
		
		notesList2.add(note3);
		notesList2.add(note4);
		
		notesList3.add(note5);
		notesList3.add(note6);
		
		notesList4.add(note7);
		notesList4.add(note8);
		
		notesList5.add(note9);
		notesList5.add(note10);
		
		notesList6.add(note11);
		notesList6.add(note12);
		notesList6.add(note1);
		notesList6.add(note2);
	
		shelf1.setNotesList(notesList1);
		shelf2.setNotesList(notesList2);
		shelf3.setNotesList(notesList3);
		shelf4.setNotesList(notesList4);
		shelf5.setNotesList(notesList5);
		shelf6.setNotesList(notesList6);
		
		UserNotesDocument doc1 = new UserNotesDocument();
		UserNotesDocument doc2 = new UserNotesDocument();
		UserNotesDocument doc3 = new UserNotesDocument();
		UserNotesDocument doc4 = new UserNotesDocument();
		UserNotesDocument doc5 = new UserNotesDocument();
		UserNotesDocument doc6 = new UserNotesDocument();
		
		doc1.setUser(user1);
		List<NotesShelf> list1 = new ArrayList<>();
		list1.add(shelf1);
		doc1.setNotesShelves(list1);
		
		doc2.setUser(user2);
		List<NotesShelf> list2 = new ArrayList<>();
		list1.add(shelf2);
		doc2.setNotesShelves(list2);
		
		doc3.setUser(user3);
		List<NotesShelf> list3 = new ArrayList<>();
		list3.add(shelf3);
		doc3.setNotesShelves(list3);
		
		doc4.setUser(user4);
		List<NotesShelf> list4 = new ArrayList<>();
		list4.add(shelf4);
		doc4.setNotesShelves(list4);
		
		doc5.setUser(user5);
		List<NotesShelf> list5 = new ArrayList<>();
		list5.add(shelf5);
		doc5.setNotesShelves(list5);
		
		doc6.setUser(user6);
		List<NotesShelf> list6 = new ArrayList<>();
		list6.add(shelf6);
		doc6.setNotesShelves(list6);
		
		try {
			//notesRepository.deleteAll();
			//save all the user notes to db
			notesRepository.save(note1);
			notesRepository.save(note2);
			notesRepository.save(note3);
			notesRepository.save(note4);
			notesRepository.save(note5);
			notesRepository.save(note6);
			notesRepository.save(note7);
			notesRepository.save(note8);
			notesRepository.save(note9);
			notesRepository.save(note10);
			notesRepository.save(note11);
			notesRepository.save(note12);
		} catch (Exception e) {
			System.out.println("The exception is: "+e);
		}
		
	}
}
*/