package Models;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private Gender gender;
    private Date dob;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String salt;
    private List<Group> groups;
    private Map<String, User> friends;
    private Map<String, String> friendsAlias;
    private Map<String, Relationship> relationships;
    private List<Conversation> conversations;

    public User(String id, Gender gender, Date dob, String firstName, String lastName, String username, String password, String salt) {
        this.id = id;
        this.gender = gender;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.groups = new ArrayList<>();
        this.friends = new HashMap<>();
        this.relationships = new HashMap<>();
        this.conversations = new ArrayList<>();
        this.friendsAlias = new HashMap<>();
    }

    public Map<String, String> getFriendsAlias() {
        return friendsAlias;
    }

    public void setFriendsAliasMap(Map<String, String> friendsWithAlias) {
        this.friendsAlias = friendsWithAlias;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFriendList() {
        return (List<User>) friends.values();
    }

    public Map<String, User> getFriendMap() {
        return friends;
    }

    public void setFriends(Map<String, User> friends) {
        this.friends = friends;
    }

    public void addNewFriend(User friend) {
        this.friends.put(friend.username, friend);
    }

    public Map<String, Relationship> getRelationshipMap() {
        return relationships;
    }

    public void setRelationshipMap(Map<String, Relationship> relationships) {
        this.relationships = relationships;
    }

    public void setRelationship(Relationship relationship) {
        this.relationships.put(relationship.getRelationshipId(), relationship);
    }

    public void addNewRelationship(Relationship relationship) {
        this.relationships.put(relationship.getRelationshipId(), relationship);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<ConversationGroup> getConversationsGroupList() {
        List<ConversationGroup> returnList = new ArrayList<>();
        for (Conversation conversation : conversations) {
            if (conversation instanceof ConversationGroup) {
                returnList.add((ConversationGroup) conversation);
            }
        }
        return returnList;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public List<ConversationSingle> getConversationSingleList() {
        List<ConversationSingle> returnList = new ArrayList<>();
        for (Conversation conversation : conversations) {
            if (conversation instanceof ConversationSingle) {
                returnList.add((ConversationSingle) conversation);
            }
        }
        return returnList;
    }
}
