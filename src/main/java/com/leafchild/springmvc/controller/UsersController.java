package com.leafchild.springmvc.controller;

import com.leafchild.springmvc.model.User;
import com.leafchild.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by: leaf
 * Project: spring-mvc-angular-test
 * Date: 11/26/15
 * Time: 12:53 PM
 * Users front controller
 * Contains all API for managing users in the app
 */

@RestController
public class UsersController {

	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {this.userService = userService;}


	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}


	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		User user = userService.findOne(id);
		if (userService.exists(user.getId())) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}


	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getUserName());

		if (userService.exists(user.getId())) {
			System.out.println("A User with name " + user.getUserName() + " already exists");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		userService.save(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Updating User " + id);

		User currentUser = userService.findOne(id);

		if (userService.isDummyUser(currentUser)) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		userService.update(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.findOne(id);
		if (userService.isDummyUser(user)) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deleting All Users");

		userService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
