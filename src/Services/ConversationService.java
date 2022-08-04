package Services;

import Data.DataStorage;
import Models.*;

import java.util.*;

public class ConversationService {
    private List<Conversation> conversationList;
    private DataStorage dataStorage;

    public ConversationService() {
        this.dataStorage = DataStorage.getInstance();
        this.conversationList = this.dataStorage.readListConvAsByte();
    }

    public Conversation getConversationSingle(User currentUser, User friend) {
        List<ConversationSingle> conversationSingleList = currentUser.getConversationSingleList();
        ConversationSingle returnConversation = null;
        boolean isExisted = true;
        for (ConversationSingle conversationSingle : conversationSingleList) {
            for (User user : conversationSingle.getMember()) {
                if (user == friend) {
                    returnConversation = conversationSingle;
                }
            }
        }

        String id = generateConversationID();
        List<User> member = new ArrayList<>();
        member.add(currentUser);
        member.add(friend);
        returnConversation = new ConversationSingle(id, member);
        return returnConversation;

    }


    public Conversation findConversationByID(String id) {
        for (Conversation conversation : conversationList) {
            if (conversation.getId().equals(id)) {
                return conversation;
            }
        }
        return null;
    }

    public TextMessage findMessageText(String keyword, Conversation conversation) {
        keyword = keyword.trim().toLowerCase();
        TextMessage foundMessage = null;
        for (Message message : conversation.getChatLog()) {
            if (message instanceof TextMessage) {
                String content = ((TextMessage) message).getContent().toLowerCase();
                if (content.contains(keyword) || content.startsWith(keyword)) {
                    foundMessage = (TextMessage) message;
                    return foundMessage;
                }
            }
        }
        return null;
    }

    public String generateConversationID() {
        String id;
        do {
            id = UUID.randomUUID().toString();
        } while (findConversationByID(id) != null);
        return id;
    }

    public List<Attachment> getAllAttachmentInConversation(Conversation conversation) {
        return conversation.getAttachmentList();
    }



}
