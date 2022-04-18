package com.awestover.friendface.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.awestover.friendface.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	//finds user by email
	User findByEmail(String email);
	// this method retrieves all the users from the database
	List<User> findAll();
}
