package Services;

import Models.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversationServiceTest {
    private static ConversationService conversationService;
    private static GroupService groupService;
    private static RelationshipService relationshipService;
    private static UserService userService;

    private static User testUser1;
    private static User testUser2;

    private static User testUser3;

    private static Group group;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Starting unit test");
        System.out.println("Initializing conversation service...");
        conversationService = new ConversationService();
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

        testUser1.addNewFriend(testUser2);
        testUser2.addNewFriend(testUser1);

        System.out.println("Creating test group");
        group = new Group("1","testGroup",false);
        List<User> member = new ArrayList<>();
        member.add(testUser1);
        member.add(testUser2);
        member.add(testUser3);
        group.setMembers(member);
    }

    @BeforeEach
    void setUp() {
        System.out.println("Testing...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Done");
    }

    @Test
    @DisplayName("Test get a new conversation between 2 users")
    void testGetConversationSingle() {
        ConversationSingle conversationSingle = conversationService.getConversationSingle(testUser1, testUser2);
        List<User> member = conversationSingle.getMember();
        assertEquals(2, member.size());
        assertEquals("12", member.get(0).getId());
        assertEquals("34", member.get(1).getId());
    }

    @Test
    @DisplayName("Test to see if it return correct group conversation")
    void testGetConversationGroup() {
        ConversationGroup conversationGroup = conversationService.getConversationGroup(testUser3, group);
        Group resultGroup = conversationGroup.getGroup();
        assertNotNull(resultGroup);
        assertEquals("testGroup", resultGroup.getName());
        assertEquals(2, resultGroup.getMembers().size());
    }

    @Test
    void sendTextMessage() {
        conversationService.sendTextMessage("This is a test message");
    }

    @Test
    void sendAttachment() {
    }

    @Test
    void deleteTextMessage() {
    }

    @Test
    void getAllAttachmentInConversation() {
    }

    @Test
    void showMessageFromConversation() {

    }
}