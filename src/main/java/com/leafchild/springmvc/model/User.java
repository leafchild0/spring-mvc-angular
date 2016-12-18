package com.leafchild.springmvc.model;

/**
 * Created by: leaf
 * Project: spring-mvc-angular-test
 * Date: 11/26/15
 * Time: 12:53 PM
 */

public class User {

	private long id;
	private String userName;
	private String address;
	private String email;

	public User(long id, String username, String address, String email){
		this.id = id;
		this.userName = username;
		this.address = address;
		this.email = email;
	}

	public User() {
		this(0, "No User", "", "");
	}

	public User(String username){
		this(0, username, "", "");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id
		  + ", userName=" + userName
		  + ", address=" + address
		  + ", email=" + email + "]";
	}
}
