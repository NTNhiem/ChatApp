package Models;

import java.util.List;

public class PublicGroup extends Group {
	public User admin;

	public PublicGroup(int id, String name, List<User> members, User admin) {
		super(id, name, members);
		this.admin = admin;
	}
	
	
}
