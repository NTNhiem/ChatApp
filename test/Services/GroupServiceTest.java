package Services;

import Data.DataStorage;
import Models.Gender;
import Models.Group;
import Models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupServiceTest {
    private static GroupService groupService;
    private static User testUser1;
    private static User testUser2;

    private static User testUser3;
    private static User testUser4;


    private static Group group1;


    @BeforeAll
    static void setupAll() {
        System.out.println("Starting unit test");
        System.out.println("Initializing group service...");
        groupService = new GroupService();

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

        testUser3 = new User(
                "56",
                Gender.Male,
                null,
                "",
                "",
                "",
                "",
                ""
        );

        testUser4 = new User(
                "75",
                Gender.Male,
                null,
                "",
                "",
                "",
                "",
                ""
        );


        testUser1.addNewFriend(testUser2);
        testUser2.addNewFriend(testUser1);
        System.out.println("Creating test group");

        group1 = new Group("1","testGroup1",false);
        List<User> member1 = new ArrayList<>();
        member1.add(testUser1);
        member1.add(testUser2);
        group1.setMembers(member1);

    }

    @Test
    @DisplayName("Test create a group")
    void createGroup() {
        groupService.createGroup("testGroup3",true, testUser4);
        Group group = testUser4.getGroups().get(0);
        assertNotNull(group);
        assertEquals("testGroup3",group.getName());
        assertTrue(group.isPrivate());
        assertFalse(group.getAdmin().isEmpty());
        assertEquals(testUser4.getId(), group.getAdmin().get(0).getId());
    }

    @Test
    @DisplayName("Test join a group by code")
    void joinGroupByCode() {
        groupService.createGroup("testGroup2",false, testUser2);
        List<Group> foundGroups = groupService.findGroupByName("testGroup2");
        Group group2 = foundGroups.get(0);
        String code = group2.getInviteCode();
        boolean result = groupService.joinGroupByCode(code, testUser1);
        assertTrue(result);
        assertEquals(testUser1, groupService.findUserByID("12",group2));
    }

    @Test
    @DisplayName("Test join a group by invitation")
    void joinGroupByInvitation() {
        boolean result = groupService.joinGroupByInvitation(group1,testUser4);
        assertTrue(result);
        assertEquals(testUser4, groupService.findUserByID("75",group1));
    }

    @Test
    @DisplayName("Test remove a user from the group")
    void removeUserFromGroup() {
        groupService.removeUserFromGroup(group1,testUser2);
        assertNotSame(testUser4, groupService.findUserByID("34", group1));
    }
}