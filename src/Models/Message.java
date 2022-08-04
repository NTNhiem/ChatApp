package Models;

import java.util.Date;
import java.util.List;

public class Message {
	private int id ;
	private String content;
	private Date sendDate;
	private User sender ;
	private List<User> receiver;
	private Group group;
    
	public Message(int id, String content, Date sendDate, User sender, List<User> receiver, Group group,
			List<Attachment> attachments) {
		super();
		this.id = id;
		this.content = content;
		this.sendDate = sendDate;
		this.sender = sender;
		this.receiver = receiver;
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public List<User> getReceiver() {
		return receiver;
	}

	public void setReceiver(List<User> receiver) {
		this.receiver = receiver;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
