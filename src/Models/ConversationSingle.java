package Models;

import java.util.*;

public class ConversationSingle extends Conversation{
    private List<User> member;

    public ConversationSingle(String id, List<User> member) {
        super(id);
        this.member = member;
    }

    public List<User> getMember() {
        return member;
    }

    public void setMember(List<User> member) {
        this.member = member;
    }
}
