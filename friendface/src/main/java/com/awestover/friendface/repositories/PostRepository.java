package com.awestover.friendface.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.awestover.friendface.models.Post;

//...
@Repository
public interface PostRepository extends CrudRepository<Post, Long>{
	// this method retrieves all the posts from the database
	List<Post> findAll();

}
