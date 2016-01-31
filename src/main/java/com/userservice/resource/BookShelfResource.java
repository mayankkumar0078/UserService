package com.userservice.resource;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.dto.BookShelfDTO;
import com.userservice.entity.BookShelf;
import com.userservice.exception.BookShelfException;
import com.userservice.response.BookShelfResponse;
import com.userservice.responsetransformer.BookShelfResponseTransformer;
import com.userservice.service.BookShelfService;

@RestController
@RequestMapping(value = "/userModule/BookShelves/")
public class BookShelfResource {
	private static final Logger log = Logger.getLogger(BookShelfResource.class);

	@Autowired
	private BookShelfService bookShelfService;

	@Autowired
	private BookShelfResponseTransformer bookShelfResponseTransformer;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public final BookShelfResponse createBookShelf(
			@RequestBody BookShelfDTO bookShelfDTO) {

		try {
			BookShelf bookShelf = bookShelfService
					.createBookShelf(bookShelfDTO);
			BookShelfResponse bookShelfResponse=bookShelfResponseTransformer.transformIntoResponse(Arrays.asList(bookShelf));
			log.info(bookShelfResponse);
			return bookShelfResponse;
		} catch (BookShelfException e) {
			log.error(e.getMessage());
			return bookShelfResponseTransformer.buildExceptionResponse(e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public final BookShelfResponse findBookShelfUsingUsernameAndShelfName(
			@RequestParam("username") String username,@RequestParam("bookShelfName") String bookShelfName) {
		try {
			BookShelf bookShelf = bookShelfService.getBookShelf(username, bookShelfName);
			return bookShelfResponseTransformer.transformIntoResponse(Arrays.asList(bookShelf));
		} catch (BookShelfException e) {
			log.error(e.getMessage());
			return bookShelfResponseTransformer.buildExceptionResponse(e);
		}
	}

	@RequestMapping(value = "/all-Shelves", method = RequestMethod.GET, produces = "application/json")
	public final BookShelfResponse findAllBookShelvesForUsername(
			@RequestParam("username") String username) {
		try {
			List<BookShelf> bookShelves = bookShelfService.getAllBookShelvesForUsername(username);
			return bookShelfResponseTransformer.transformIntoResponse(bookShelves);
		} catch (BookShelfException e) {
			log.error(e.getMessage());
			return bookShelfResponseTransformer.buildExceptionResponse(e);
		}
	}
	
	
}
