package Models;

import java.util.*;

public abstract class Conversation {
    private String id;
    private List<Message> chatLog;

    public Conversation(String id) {
        this.id = id;
        this.chatLog = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Message> getChatLog() {
        return chatLog;
    }

    public void setChatLog(List<Message> chatLog) {
        this.chatLog = chatLog;
    }

    public List<TextMessage> getTextMessagesList() {
        List<TextMessage> returnList = new ArrayList<>();
        for (Message message : chatLog) {
            if (message instanceof TextMessage) {
                returnList.add((TextMessage) message);
            }
        }
        return returnList;
    }

    public List<Attachment> getAttachmentList() {
        List<Attachment> returnList = new ArrayList<>();
        for (Message message : chatLog) {
            if (message instanceof Attachment) {
                returnList.add((Attachment) message);
            }
        }
        return returnList;
    }
}
