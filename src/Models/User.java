package Models;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private Gender gender;
	private Date dob;
	private String firstName;
	private String lastName;
	private String password;
	private List<User> friends;

	public User(int id, Gender gender, Date dob, String firstName, String lastName, String password) {
		super();
		this.id = id;
		this.gender = gender;
		this.dob = dob;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		friends = new ArrayList<>();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<User> getFriends() {
		return friends;
	}
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
	
}
