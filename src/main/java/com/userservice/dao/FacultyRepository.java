package com.userservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userservice.entity.Faculty;

/**
 * @author Mayank
 * 
 */
@Repository
@Transactional
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
	
}

