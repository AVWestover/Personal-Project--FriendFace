package com.awestover.friendface.services;

import java.util.List;
//...
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.awestover.friendface.models.User;
import com.awestover.friendface.repositories.UserRepository;
import com.awestover.friendface.models.Post;
import com.awestover.friendface.repositories.PostRepository;

@Service
public class AppService {
	
	// adding the repositories as a dependencies
  private final UserRepository uRepository;
  private final PostRepository pRepository;
  
  public AppService(UserRepository uRepository, PostRepository pRepository) {
      this.uRepository = uRepository;
      this.pRepository = pRepository;
  }
  
  
  //VALIDATE
  // register user and hash their password
  public User registerUser(User user) {
      String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
      user.setPassword(hashed);
      return uRepository.save(user);
  }
  
  // find user by email
  public User getUserByEmail(String email) {
      return uRepository.findByEmail(email);
  }
  
	// returns all the users
	public List<User> getAllUsers() {
	   return uRepository.findAll();
	}
	
  // find user by id
  public User getUserById(Long id) {
  	Optional<User> u = uRepository.findById(id);
  	
  	if(u.isPresent()) {
          return u.get();
  	} else {
  	    return null;
  	}
  }
  
  // authenticate user
  public boolean authenticateUser(String email, String password) {
      // first find the user by email
      User user = uRepository.findByEmail(email);
      // if we can't find it by email, return false
      if(user == null) {
          return false;
      } else {
          // if the passwords match, return true, else, return false
          if(BCrypt.checkpw(password, user.getPassword())) {
              return true;
          } else {
              return false;
          }
      }
  }
  
	//creates new post
	public Post createPost(Post b) {
		   return pRepository.save(b);
	}
	
	//retrieves a post
	public Post getPostById(Long id) {
		return this.pRepository.findById(id).orElse(null);
	}
	//returns all the posts
	public List<Post> getAllPosts() {
		return pRepository.findAll();
	}
	
	//updates a post
	public void updatePost(Post post) {
		this.pRepository.save(post);
	}
	
	//deleted post by id
	public void deletePostById(Long id) {
		this.pRepository.deleteById(id);
	}
	//

}
