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

    public List<Message> getChatLog() {
        List<Message> returnList = new ArrayList<>();
        for (Message message : chatLog) {
            if(message.getStatus() == MessageStatus.Deleted) {
                returnList.add(new TextMessage("Message deleted"
                        ,message.getSendDate()
                        ,message.getSender()
                        ,message.getStatus()));
            } else {
                returnList.add(message);
            }
        }
        returnList.sort(Comparator.comparing(Message::getSendDate));
        return returnList;
    }

    public void setChatLog(List<Message> chatLog) {
        this.chatLog = chatLog;
    }

    public List<TextMessage> getTextMessagesList() {
        List<TextMessage> returnList = new ArrayList<>();
        for (Message message : chatLog) {
            if(message.getStatus() != MessageStatus.Deleted) {
                if (message instanceof TextMessage) {
                    returnList.add((TextMessage) message);
                }
            }
        }
        returnList.sort((a,b) -> b.getSendDate().compareTo(a.getSendDate()));
        return returnList;
    }



    public List<Attachment> getAttachmentList() {
        List<Attachment> returnList = new ArrayList<>();
        for (Message message : chatLog) {
            if(message.getStatus() != MessageStatus.Deleted) {
                if (message instanceof Attachment) {
                    returnList.add((Attachment) message);
                }            }
        }
        returnList.sort((a,b) -> b.getSendDate().compareTo(a.getSendDate()));

        return returnList;
    }

    public void addMessage(Message m) {
        this.chatLog.add(m);
    }
}
