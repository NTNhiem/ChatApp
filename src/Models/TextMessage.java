package Models;

import java.util.Date;

public class TextMessage extends Message {

    private String content;

    public TextMessage(int id, String content, Date sendDate, User sender, MessageStatus status) {
        super(id, sendDate, sender, status);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
