package com.awestover.friendface.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.bind.annotation.RestController;
import com.awestover.friendface.models.User;
import com.awestover.friendface.models.Post;
import com.awestover.friendface.services.AppService;
import com.awestover.friendface.validators.UserValidator;

import java.util.List;
import javax.validation.Valid;

@Controller
public class MainController {
private final AppService appService;
private final UserValidator userValidator;
 
 public MainController(AppService appService, UserValidator userValidator) {
     this.appService = appService;
     this.userValidator = userValidator;
 }
 
 
 //LOGIN AND REGISTRATION
 @GetMapping("/")
 public String showIndex(@ModelAttribute("user") User user, @ModelAttribute("error") String error, Model model) {
	 return "reglogin.jsp";
 }
 
 @PostMapping("/register")
 public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
	 //if errors
	 userValidator.validate(user, result);
	 if(result.hasErrors()) {
		 return "reglogin.jsp";
	 }
	 //else
	 else {
		 User newUser = this.appService.registerUser(user);
		 session.setAttribute("user_id", newUser.getId());
		 return "redirect:/posts"; 
	 }
 }

 @PostMapping("/login")
 public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirs, HttpSession session) {
	 //if user is authenticated then save the user id in the session
	 if(this.appService.authenticateUser(email, password)) {
		 User loggedInUser = this.appService.getUserByEmail(email);
		 session.setAttribute("user_id",loggedInUser.getId());
		 return "redirect:/posts";
	 }
	 //else error
	 else {
		 redirs.addFlashAttribute("error", "Invalid credentials. Please try again!");
		 return "redirect:/";
	 }
 }
 
 //LOGOUT
 @GetMapping("/logout")
 public String logoutUser(HttpSession session) {
	 //clear session (invalidate)
	 session.invalidate();
	 return "redirect:/";
 }
 
 //DASHBOARD
 @GetMapping("/posts")
 public String dashboard(Model model, HttpSession session) {
	 //redirect if there's no user in session
	 Long userId = (Long) session.getAttribute("user_id");
	 if (userId == null) {
		 return "redirect:/";
	 }
	 //else
	 else {
		 User currentUser = this.appService.getUserById(userId);
		 List <Post> allPosts = this.appService.getAllPosts();
		 model.addAttribute("user",currentUser);
		 model.addAttribute("posts",allPosts);
		 return "dashboard.jsp";
	 }
 }
 
 //NEW IDEA
 @GetMapping("/posts/new")
 public String postCreatePage(@ModelAttribute("post") Post post, Model model, HttpSession session) {
	 Long userId = (Long) session.getAttribute("user_id");
	 //redirect if no user in session
	 if (userId == null) {
		 return "redirect:/";
	 }
	 //else
	 else {
		 User currentUser = this.appService.getUserById(userId);
		 model.addAttribute("user",currentUser);
		 return "new.jsp";
	 }
 }
 
 @PostMapping("/posts/new")
 public String createPost(@Valid @ModelAttribute("post") Post post, BindingResult result, Model model, HttpSession session) {
	 if(result.hasErrors()) {
		 Long userId = (Long) session.getAttribute("user_id");
		 User currentUser = appService.getUserById(userId);
		 model.addAttribute("user",currentUser);
		 return "new.jsp";
	 }
	 else {
		 appService.createPost(post);
		 return "redirect:/posts";
	 }
 }
 
 //SHOW PAGE
 @GetMapping("/users/{id}")
 public String showUserPage(@ModelAttribute("error") String error, @PathVariable("id") Long id, Model model, HttpSession session) {
	 Long userId = (Long) session.getAttribute("user_id");
	 User userSelected = this.appService.getUserById(id);
	 //check for user
	 if(userId == null) {
		 return "redirect:/";
	 }
	 //else if check for idea
	 else if (userSelected == null){
		 return "redirect:/posts";
	 }
	 //else
	 else {
		 User currentUser = appService.getUserById(userId);
		 model.addAttribute("user",currentUser);
		 model.addAttribute("userselected",userSelected);
		 model.addAttribute("error", error);
		 return "show.jsp";
	 }
 }
 
 //EDIT
 @GetMapping("/posts/{id}/edit")
 public String editPost(@ModelAttribute("post") Post post, @PathVariable("id") Long id, Model model, HttpSession session) {
	 Long userId = (Long) session.getAttribute("user_id");
	 Post postSelected = this.appService.getPostById(id);
	 //check for user
	 if(userId == null) {
		 return "redirect:/";
	 }
	 //else if - check for idea
	 else if(postSelected == null || !postSelected.getCreator().getId().equals(userId) ) {
		 return "redirect:/posts";
	 }
	 //else
	 User currentUser = this.appService.getUserById(userId);
	 model.addAttribute("user",currentUser);
	 model.addAttribute("post",postSelected);
	 return "edit.jsp";
 }
 @PutMapping("/posts/{id}/edit")
 public String editingPosts(@Valid @ModelAttribute("post") Post post, BindingResult result, @PathVariable("id") Long id, Model model, HttpSession session) {
	 //if errors
	 if(result.hasErrors()) {
		 Long userId = (Long) session.getAttribute("user_id");
		 User currentUser = this.appService.getUserById(userId);
		 Post postSelected = this.appService.getPostById(id);
		 String error = "Error: Must not be empty!";
		 model.addAttribute("user",currentUser);
		 model.addAttribute("idea",postSelected);
		 model.addAttribute("error", error);
		 return "edit.jsp";
	 }
	 //else
	 else {
		 this.appService.updatePost(post);
		 return "redirect:/posts";
	 }
 }


 //DELETE IDEA
 @GetMapping("/posts/{id}/delete")
 public String deletePost(@ModelAttribute("post") Post post, @PathVariable("id") Long id, Model model, HttpSession session) {
	 Long userId = (Long) session.getAttribute("user_id");
	 Post postSelected = this.appService.getPostById(id);
	 if (!postSelected.getCreator().getId().equals(userId)) {
		 return "redirect:/posts";
	 }
	 else {
		 this.appService.deletePostById(id);
		 return "redirect:/posts";
	 }
 }
 
 
 
 
 
 
 
 
 
}