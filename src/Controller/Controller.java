package Controller;

import Data.DataStorage;
import Models.Gender;
import Models.PasswordHash;
import Models.User;

import java.util.*;

public class Controller {
    private User currentUser = null;
    private List<User> userList;
    private DataStorage dataStorage;

    public Controller() {
        this.dataStorage = DataStorage.getInstance();
        this.userList = this.dataStorage.readListUserAsByte();
    }

    public User findUserByID(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByUsername(String searchUsername) {
        for (User user : userList) {
            if (user.getUsername().equals(searchUsername)) {
                return user;
            }
        }
        return null;
    }

    public List<User> findUserByName(String name) {
        List<User> searchList = new ArrayList<>();
        for (User user : userList) {
            String fullName = user.getFullName();
            if(fullName.length() >= name.length()) {
                if (fullName.contains(name) || fullName.startsWith(name)) {
                    searchList.add(user);
                }
            }
        }
        return searchList;
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
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public void changeRelationship(int code) {

    }

}
