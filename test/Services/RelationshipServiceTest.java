package Services;

import Data.DataStorage;
import Models.Gender;
import Models.Relationship;
import Models.RelationshipStatus;
import Models.User;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipServiceTest {
    private static RelationshipService relationshipService;
    private static User testUser1;
    private static User testUser2;
    private static User testUser3;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Starting unit test");
        System.out.println("Initializing relationship service...");
        relationshipService = new RelationshipService();
    }

    @BeforeEach
    void setUp() {
        System.out.println("Testing...");
        System.out.println("Creating test users");
        testUser1 = new User(
                "12",
                Gender.Male,
                null,
                "",
                "",
                "user1",
                "",
                ""
        );
        testUser2 = new User(
                "34",
                Gender.Female,
                null,
                "",
                "",
                "user2",
                "",
                ""
        );
        testUser3 = new User(
                "56",
                Gender.Male,
                null,
                "",
                "",
                "user3",
                "",
                ""
        );
    }

    @AfterEach
    void tearDown() {
        System.out.println("Done");
    }

    @Test
    @DisplayName("Test send a friend invitation")
    void sendInvitation() {
        boolean result = relationshipService.sendInvitation(testUser1, testUser2);
        assertTrue(result);
        assertTrue(testUser1.getFriendMap().isEmpty());
        String relationshipId = testUser1.getId() + testUser2.getId();
        Relationship relationship1 = testUser1.getRelationshipMap().get(relationshipId);
        Relationship relationship2 = testUser2.getRelationshipMap().get(relationshipId);
        assertNotNull(relationship1);
        assertNotNull(relationship2);
        assertEquals(relationship1, relationship2);
        assertEquals(RelationshipStatus.pending, relationship2.getStatus());
        assertEquals(RelationshipStatus.pending, relationship1.getStatus());
    }

    @Test
    @DisplayName("Test approve friend invitation")
    void changeInvitationStatus() {
        relationshipService.sendInvitation(testUser1, testUser2);
        relationshipService.changeInvitationStatus(true ,testUser1, testUser2);
        assertFalse(testUser1.getFriendMap().isEmpty());
        assertEquals(testUser2.getId(), testUser1.getFriendList().get(0).getId());
        String relationshipId = testUser1.getId() + testUser2.getId();
        Relationship relationship1 = testUser1.getRelationshipMap().get(relationshipId);
        assertEquals(RelationshipStatus.friended, relationship1.getStatus());
    }

    @Test
    @DisplayName("Test decline friend invitation")
    void changeInvitationStatus2() {
        relationshipService.sendInvitation(testUser1, testUser2);
        relationshipService.changeInvitationStatus(false ,testUser1, testUser2);
        assertTrue(testUser1.getFriendMap().isEmpty());
        String relationshipId = testUser1.getId() + testUser2.getId();
        Relationship relationship1 = testUser1.getRelationshipMap().get(relationshipId);
        assertEquals(RelationshipStatus.declined, relationship1.getStatus());
    }

    @Test
    @DisplayName("Test get the full friend list")
    void getFriendList() {
        relationshipService.sendInvitation(testUser1, testUser2);
        relationshipService.changeInvitationStatus(true ,testUser1, testUser2);
        relationshipService.sendInvitation(testUser1, testUser3);
        relationshipService.changeInvitationStatus(true ,testUser1, testUser3);
        List<User> friendList = relationshipService.getFriendList(testUser1);
        assertNotNull(friendList);
        assertFalse(friendList.isEmpty());
        assertEquals(2, friendList.size());
        assertEquals(testUser2.getId(),friendList.get(0).getId());
        assertEquals(testUser3.getId(),friendList.get(1).getId());
    }

    @Test
    @DisplayName("Test search friend username")
    void searchFriendList() {
        relationshipService.sendInvitation(testUser1, testUser2);
        relationshipService.changeInvitationStatus(true ,testUser1, testUser2);
        relationshipService.sendInvitation(testUser1, testUser3);
        relationshipService.changeInvitationStatus(true ,testUser1, testUser3);
        List<User> resultList = relationshipService.searchFriendList(testUser1, "er");
        assertEquals(2,resultList.size());
        assertEquals(testUser2.getId(), resultList.get(0).getId());
        resultList = relationshipService.searchFriendList(testUser1, "3");
        assertEquals(1,resultList.size());
        assertEquals(testUser3.getId(), resultList.get(0).getId());
    }
}