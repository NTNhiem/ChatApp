package Models;

public class Attachment {
    private String id;
    private String directory;
    private AttachmentType type;

    public Attachment(String id, String directory, AttachmentType type) {
        this.id = id;
        this.directory = directory;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
