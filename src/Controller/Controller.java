package Controller;

import Data.DataStorage;
import Models.Gender;
import Models.PasswordHash;
import Models.User;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Controller {
    private List<User> userList;
    private DataStorage dataStorage;

    public Controller(List<User> userList) {
        this.userList = userList;
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

    public void changeRelationship(int code) {

    }

}
