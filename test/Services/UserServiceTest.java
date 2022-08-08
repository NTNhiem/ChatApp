package Services;

import Data.DataStorage;
import Models.Gender;
import Models.Group;
import Models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private static UserService userService;
    private static User testUser1;
    private static User testUser2;

    @BeforeAll
    static void setupAll() {
        System.out.println("Starting unit test");
        System.out.println("Initializing user service...");
        userService = new UserService();

        System.out.println("Creating test users");
        testUser1 = new User(
                "12",
                Gender.Male,
                null,
                "",
                "",
                "",
                "",
                ""
        );

        testUser2 = new User(
                "34",
                Gender.Female,
                null,
                "",
                "",
                "",
                "",
                ""
        );

        testUser1.addNewFriend(testUser2);
        testUser2.addNewFriend(testUser1);
    }

    @Test
    @DisplayName("Test register a new account")
    void register() {
        userService.register(Gender.Male, null, "", "", "testacc1", "123");
        List<User> returnList = userService.findUserByUsername("testacc1");
        assertEquals("testacc1", returnList.get(0).getUsername());
    }

    @Test
    @DisplayName("Test login to an existing account")
    void login() {
        boolean res1 = userService.register(null, null, "", "", "newuser2", "456");
        boolean result = userService.login("newuser2", "456");
        assertTrue(result);
        List<User> returnList = userService.findUserByUsername("newuser2");
        assertEquals("newuser2", returnList.get(0).getUsername());
    }

    @Test
    @DisplayName("Test set alias to friend")
    void setAlias() {
        userService.setAlias("testAlias",testUser1, testUser2);
        Map<String,String> aliasMap = testUser1.getFriendsAlias();
        assertEquals("testAlias",aliasMap.get(testUser2.getUsername()));
    }
}
