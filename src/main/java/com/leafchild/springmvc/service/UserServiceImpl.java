package com.leafchild.springmvc.service;

import com.leafchild.springmvc.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Created by: leaf
 * Project: spring-mvc-angular-test
 * Date: 11/26/15
 * Time: 12:53 PM
 * Service class
 * Contains all operations related to data
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;
	private static User dummy = new User("Dummy");

	static {
		users = populateDummyUsers();
	}

	@Override
	public User findOne(long id) {
		return users.stream().filter(user -> user.getId() == id)
		  .findFirst().orElse(dummy);
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public User findByName(String name) {
		return users.stream().filter(user -> user.getUserName().equals(name))
		  .findFirst().orElse(dummy);
	}

	@Override
	public User save(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
		return user;
	}

	@Override
	public void update(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	@Override
	public boolean exists(long userId) {
		return !isDummyUser(findOne(userId));
	}

	@Override
	public boolean isDummyUser(User user) {
		return user.equals(dummy);
	}

	@Override
	public void delete(long id) {
		users.removeIf(user -> user.getId() == id);
	}

	@Override
	public void deleteAll() {
		users.clear();
	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User(counter.incrementAndGet(), "Sam", "NY", "sam@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Tomy", "ALBAMA", "tomy@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Kelly", "NEBRASKA", "kelly@abc.com"));
		return users;
	}
}
