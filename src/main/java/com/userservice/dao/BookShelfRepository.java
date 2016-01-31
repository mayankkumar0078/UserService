package com.userservice.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import com.userservice.entity.BookShelf;
import com.userservice.entity.User;

/**
 * @author Mayank
 * 
 */
@Repository
@Transactional
public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {
	
	@Query("Select bs from BookShelf bs where bs.userId=:user AND bs.bookShelfName=:name")
	public BookShelf findByUsernameAndShelfName(@Param("user") User user,@Param("name") String name);
	
	@Query("Select bs from BookShelf bs where bs.userId=:user")
	public List<BookShelf> getAllBookShelvesForUsername(@Param("user") User user);
}

