package Services;

import Config.ChatAppConfig;
import Data.DataStorage;
import Models.*;

import java.util.*;
import java.io.*;

public class ConversationService {
    private List<Conversation> conversationList;
    private DataStorage dataStorage;
    private final ChatAppConfig chatAppConfig = ChatAppConfig.getConfigInstance();


    public ConversationService() {
        this.dataStorage = DataStorage.getInstance();
        this.conversationList = this.dataStorage.readConversationList();
    }

    public ConversationSingle getConversationSingle(User currentUser, User friend) {
        List<ConversationSingle> conversationSingleList = currentUser.getConversationSingleList();
        ConversationSingle returnConversation = null;
        boolean isExisted = false;
        for (ConversationSingle conversationSingle : conversationSingleList) {
            for (User user : conversationSingle.getMember()) {
                if (user == friend) {
                    returnConversation = conversationSingle;
                    isExisted = true;
                    break;
                }
            }
        }
        if (!isExisted) {
            String id = generateConversationID();
            List<User> member = new ArrayList<>();
            member.add(currentUser);
            member.add(friend);
            returnConversation = new ConversationSingle(id, member);
            this.conversationList.add(returnConversation);
        }
        return returnConversation;

    }

    public ConversationGroup getConversationGroup(User currentUser, Group group) {
        List<ConversationGroup> conversationGroupList = currentUser.getConversationsGroupList();
        ConversationGroup returnConversation = null;
        boolean isExisted = false;
        for (ConversationGroup conversationGroup : conversationGroupList) {
            Group currGroup = conversationGroup.getReceiver();
                if (currGroup == group) {
                    returnConversation = conversationGroup;
                    isExisted = true;
                    break;
                }

        }
        if (!isExisted) {
            String id = generateConversationID();
            returnConversation = new ConversationGroup(id, group);
            this.conversationList.add(returnConversation);
        }
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

    public boolean sendTextMessage(String text, Conversation conversation, User sender) {
        Calendar cal = Calendar.getInstance();
        text = text.trim();
        if (text.equals("")) {
            return false;
        }
        TextMessage textMessage = new TextMessage(text, cal.getTime(), sender, MessageStatus.Sent);
        conversation.addMessage(textMessage);
        updateConversationInList(conversation);
        return true;
    }

    public boolean sendAttachment(String directory, Conversation conversation, User sender) {
        Calendar cal = Calendar.getInstance();
        File file = new File(directory);
        String type = getFileExtension(directory);
        AttachmentType attachmentType = AttachmentType.File;
        String[] audioType = chatAppConfig.getStringProperty("audioType").split(",");
        String[] videoType = chatAppConfig.getStringProperty("videoType").split(",");
        String[] imageType = chatAppConfig.getStringProperty("imageType").split(",");
        String[] documentType = chatAppConfig.getStringProperty("documentType").split(",");
        for (String s : audioType) {
            if (type.equals(s)) {
                attachmentType = AttachmentType.Audio;
                break;
            }
        }
        for (String s : videoType) {
            if (type.equals(s)) {
                attachmentType = AttachmentType.Video;
                break;
            }
        }
        for (String s : imageType) {
            if (type.equals(s)) {
                attachmentType = AttachmentType.Image;
                break;
            }
        }
        for (String s : documentType) {
            if (type.equals(s)) {
                attachmentType = AttachmentType.Document;
                break;
            }
        }
        Attachment attachment = new Attachment(cal.getTime(), sender, MessageStatus.Sent, directory, attachmentType);
        conversation.addMessage(attachment);
        updateConversationInList(conversation);
        return false;
    }

    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public void deleteTextMessage(Message message) {
        message.setStatus(MessageStatus.Deleted);
    }

    public List<Message> showMessageFromConversation(int m, int k, Conversation conversation) {
        List<Message> chatLog = conversation.getChatLog();
        List<Message> returnMessageList = new ArrayList<>();
        for (int i = m; i < k; i++) {
            returnMessageList.add(chatLog.get(i));
        }
        return returnMessageList;
    }

    public void updateConversationInList(Conversation conversationToUpdate) {
        String conversationToUpdateId = conversationToUpdate.getId();
        for (Conversation conversation : this.conversationList) {
            if (conversation.getId().equals(conversationToUpdateId)) {
                this.conversationList.remove(conversation);
                this.conversationList.add(conversationToUpdate);
                return;
            }
        }
    }

    public void saveConversationData() {
        this.dataStorage.saveConversationList(this.conversationList);
    }
}
