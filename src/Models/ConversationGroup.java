package Models;

import java.util.*;

public class ConversationGroup extends Conversation {
    private Group receiver;

    public ConversationGroup(String id, Group receiver) {
        super(id);
        this.receiver = receiver;
    }

    public Group getReceiver() {
        return receiver;
    }

    public void setReceiver(Group receiver) {
        this.receiver = receiver;
    }
}
