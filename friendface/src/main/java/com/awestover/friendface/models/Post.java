package com.awestover.friendface.models;

import java.util.Date;
//import java.util.List;

//import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
//import javax.persistence.Transient;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;


//
@Entity
@Table(name="ideas")
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank (message = "Field cannot be empty")
    private String postText;
    @Column(updatable=false)
    private Date createdAt;
    @Column
    private Date updatedAt;
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User creator;
    //
    public Post() {
    
    }
    public Post(String postText) {
    	this.postText = postText;
    }

    //getters and setters
	 public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPostText() {
			return postText;
		}

		public void setPostText(String postText) {
			this.postText = postText;
		}
		public User getCreator() {
			return creator;
		}
		public void setCreator(User creator) {
			this.creator = creator;
		}
		public Date getCreatedAt() {
	 		return createdAt;
	 	}

	 	public void setCreatedAt(Date createdAt) {
	 		this.createdAt = createdAt;
	 	}

	 	public Date getUpdatedAt() {
	 		return updatedAt;
	 	}

	 	public void setUpdatedAt(Date updatedAt) {
	 		this.updatedAt = updatedAt;
	 	}
		 //
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }

}