package Controller;

import Config.ChatAppConfig;
import Data.DataStorage;
import Models.Gender;
import Models.Group;
import Models.PasswordHash;
import Models.User;

import java.util.*;

public class Controller {
    private User currentUser = null;
    private List<Group> groupList;
    private List<User> userList;
    private DataStorage dataStorage;
    private ChatAppConfig chatAppConfig = ChatAppConfig.getConfigInstance();
    private final int sentCode = this.chatAppConfig.getIntegerProperty("CODE_SENT_FRIEND_REQUEST"),
            declineCode = this.chatAppConfig.getIntegerProperty("CODE_DECLINE_FRIEND_REQUEST"),
            acceptCode = this.chatAppConfig.getIntegerProperty("CODE_ACCEPT_FRIEND_REQUEST");

    public Controller() {
        this.dataStorage = DataStorage.getInstance();
        this.userList = this.dataStorage.readListUserAsByte();
        this.groupList = this.dataStorage.readListGroupAsByte();
    }

    public User findUserByID(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public boolean addNewUser(Gender gender, Date dob, String firstName, String lastName, String username, String password){
        String id = UUID.randomUUID().toString();
        password = PasswordHash.getSHA256Password(password);
        User user = new User(id, gender,dob, firstName, lastName, username, password);
        User tempUser = this.findUserByID(user.getId());
        if (tempUser == null) {
            this.userList.add(user);
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        username = username.trim();
        password = password.trim();
        String hashedPass = PasswordHash.getSHA256Password(password);
        for (User user : userList) {
            String usernameCheck = user.getUsername();
            String passwordCheck = user.getPassword();
            if (usernameCheck.equals(username) && passwordCheck.equals(hashedPass)) {
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public void changeRelationship(int code, String invitingUserID, String invitedUserID) {
        switch (code) {
            case sentCode ->
        }
    }

    public Group findGroupById(String id) {
        for (Group group : groupList) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
    }

    public void createGroup(String name, boolean isPrivate) {
        String id = UUID.randomUUID().toString();
        Group g = new Group(id, name, isPrivate);
        List<User> admins = new ArrayList<>();
        admins.add(currentUser);
        g.setAdmin(admins);
    }

    public boolean joinGroupByCode(String inviteCode) {
        Group g = findGroupById(inviteCode);
        if (!g.isPrivate()) {
            List<User> members = g.getMembers();
            if (!members.contains(currentUser)) {
                members.add(currentUser);
                g.setMembers(members);
                return true;
            }
        }
        return false;
    }


}
