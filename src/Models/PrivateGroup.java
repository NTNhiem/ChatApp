package models;

import java.util.List;

public class PrivateGroup extends Group {
	public User admin;

	public PrivateGroup(int id, String name, List<User> members, User admin) {
		super(id, name, members);
		this.admin = admin;
	}
	
	
}
