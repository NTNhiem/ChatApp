package Models;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Group {
	private String id;
	private String name;
	private List<User> admin;
	private boolean isPrivate;
	private List<User> members;

	private String inviteCode;


	public Group(String id, String name, boolean isPrivate) {
		this.id = id;
		this.name = name;
		this.isPrivate = isPrivate;
		this.admin = new ArrayList<>();
		this.members = new ArrayList<>();
		this.inviteCode = generateInviteCode();
	}

	private String generateInviteCode() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder code = new StringBuilder();
		Random random = new SecureRandom();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			code.append(c);
		}
		code.append(this.id);
		return code.toString();
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public String getId() {
		return id;
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
