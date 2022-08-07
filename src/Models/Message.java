package Models;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public abstract class Message {
	private static final AtomicInteger count = new AtomicInteger(0);
	private int id ;
	private Date sendDate;
	private User sender;

	private MessageStatus status;
    
	public Message(Date sendDate, User sender, MessageStatus status) {
		super();
		this.id = count.incrementAndGet();
		this.sendDate = sendDate;
		this.sender = sender;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}
}
