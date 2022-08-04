package Services;

import Config.ChatAppConfig;
import Data.DataStorage;
import Models.Gender;
import Models.User;
import Ultilities.PasswordHash;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserService {
    private List<User> userList;
    private DataStorage dataStorage;

    private ChatAppConfig chatAppConfig = ChatAppConfig.getConfigInstance();

    public UserService(List<User> userList) {
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

    public boolean register(Gender gender, Date dob, String firstName, String lastName, String username, String password){
        String id = generateUserID();
        String salt = PasswordHash.getSalt();
        password = PasswordHash.getSHA256Password(password, salt);
        User user = new User(id, gender,dob, firstName, lastName, username, password, salt);
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
        for (User user : userList) {
            String usernameCheck = user.getUsername();
            String passwordCheck = user.getPassword();
            String hashedPass = PasswordHash.getSHA256Password(password,user.getSalt());
            if (usernameCheck.equals(username) && passwordCheck.equals(hashedPass)) {
                return true;
            }
        }
        return false;
    }

    public String generateUserID() {
        String id;
        do {
            id = UUID.randomUUID().toString();
        } while (findUserByID(id) != null);
        return id;
    }
}
