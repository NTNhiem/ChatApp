package Models;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private String id;
	private String name;
	private List<User> admin;
	private boolean isPrivate;
	private List<User> members;


	public Group(String id, String name, boolean isPrivate) {
		this.id = id;
		this.name = name;
		this.isPrivate = isPrivate;
		this.admin = new ArrayList<>();
		this.members = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public List<User> getAdmin() {
		return admin;
	}

	public void setAdmin(List<User> admin) {
		this.admin = admin;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}
    
    
}
