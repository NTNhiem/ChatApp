package Models;

import java.util.Date;

public class Attachment extends Message{
    private String directory;
    private String fileName;
    private AttachmentType type;

    private String extension;

    public Attachment(Date sendDate, User sender, MessageStatus status, String directory, AttachmentType type, String fileName, String extension) {
        super(sendDate, sender,status);
        this.directory = directory;
        this.type = type;
        this.fileName = fileName;
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
