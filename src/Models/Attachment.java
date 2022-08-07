package Models;

import java.util.Date;

public class Attachment extends Message{
    private String directory;
    private AttachmentType type;

    public Attachment(Date sendDate, User sender, MessageStatus status, String directory, AttachmentType type) {
        super(sendDate, sender,status);
        this.directory = directory;
        this.type = type;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }
}
