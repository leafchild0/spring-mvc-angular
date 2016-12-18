package com.leafchild.springmvc.service;

import com.leafchild.springmvc.model.User;

import java.util.List;


/**
 * Created by: leaf
 * Project: spring-mvc-angular-test
 * Date: 11/26/15
 * Time: 12:53 PM
 */

public interface UserService {

	User findOne(long id);

	User findByName(String name);

	User save(User user);

	void update(User user);

	void delete(long id);

	List<User> findAll();

	void deleteAll();

	boolean exists(long userId);

	boolean isDummyUser(User user);

}
