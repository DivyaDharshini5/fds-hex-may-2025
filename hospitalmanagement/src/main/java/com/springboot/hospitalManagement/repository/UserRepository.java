package com.springboot.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.hospitalManagement.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	User getUserByUsername(String username);

}
