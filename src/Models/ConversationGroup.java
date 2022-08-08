package Models;

public class ConversationGroup extends Conversation {
    private Group receiver;

    public ConversationGroup(String id, Group receiver) {
        super(id);
        this.receiver = receiver;
    }

    public Group getGroup() {
        return receiver;
    }

    public void setGroup(Group receiver) {
        this.receiver = receiver;
    }
}
