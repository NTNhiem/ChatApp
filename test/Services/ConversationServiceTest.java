package Services;

import Data.DataStorage;
import Models.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversationServiceTest {
    private static ConversationService conversationService;
    private static DataStorage dataStorage;
    private static User testUser1;
    private static User testUser2;

    private static User testUser3;

    private static Group group;

    private static File testFile1;

    private static File testFile2;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Starting unit test");
        System.out.println("Initializing conversation service...");
        conversationService = new ConversationService();
        System.out.println("Creating test users");

        dataStorage = DataStorage.getInstance();
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

        System.out.println("Creating test files");
        testFile1 = new File("test.txt");
        testFile2 = new File("test2.txt");
        try {
            testFile1.createNewFile();
            testFile2.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
        assertNotNull(member);
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
        assertEquals(3, resultGroup.getMembers().size());
    }

    @Test
    @DisplayName("Test send a text message between 2 users")
    void sendTextMessage() {
        String expectedContent = "This is a test message";
        ConversationSingle conversationSingle = conversationService.getConversationSingle(testUser1, testUser2);
        conversationService.sendTextMessage(expectedContent, conversationSingle, testUser1);
        List<TextMessage> textMessagesList = conversationSingle.getTextMessagesList();
        assertNotNull(textMessagesList);
        assertEquals(1, textMessagesList.size());
        String resultContent = textMessagesList.get(0).getContent();
        assertEquals(expectedContent, resultContent);
    }

    @Test
    @DisplayName("Test send an attachment in a group")
    void sendAttachment() {
        String dir = testFile1.getPath();
        ConversationGroup conversationGroup = conversationService.getConversationGroup(testUser1,group);
        conversationService.sendAttachment(dir,conversationGroup,testUser1);
        assertEquals(1, conversationGroup.getAttachmentList().size());
    }

    @Test
    @DisplayName("Test user 1 delete a text message in a conversation with user 2")
    void deleteTextMessage() {
        String expectedContent = "This is a test message";
        ConversationSingle conversationSingle = conversationService.getConversationSingle(testUser1, testUser2);
        conversationService.sendTextMessage(expectedContent, conversationSingle, testUser1);
        List<TextMessage> textMessagesList = conversationSingle.getTextMessagesList();
        TextMessage textMessage = textMessagesList.get(0);
        conversationService.deleteTextMessage(textMessage);
        assertNotNull(textMessage);
        assertEquals(MessageStatus.Deleted,textMessage.getStatus());
        assertEquals(1,textMessagesList.size());
        assertEquals(expectedContent, textMessage.getContent());
    }

    @Test
    @DisplayName("Test get all files in a conversation")
    void getAllAttachmentInConversation() {
        String dir1 = testFile1.getPath();
        String dir2 = testFile2.getPath();
        ConversationSingle conversationSingle = conversationService.getConversationSingle(testUser1, testUser2);
        conversationService.sendAttachment(dir1,conversationSingle,testUser1);
        conversationService.sendAttachment(dir2,conversationSingle,testUser2);
        List<Attachment> attachmentList = conversationService.getAllAttachmentInConversation(conversationSingle);
        assertEquals(2,attachmentList.size());
        Attachment resultAttachment = attachmentList.get(1);
        Attachment resultAttachment2 = attachmentList.get(0);
        String expectedDirectory = dataStorage.getAttachmentPath() + "\\" + resultAttachment.getId();
        assertEquals(expectedDirectory,resultAttachment.getDirectory());
        assertEquals("test2.txt", resultAttachment2.getFileName());
    }

    @Test
    @DisplayName("Test get all files and text messages in a conversation")
    void showMessageFromConversation() {
        String expectedContent1 = "This is a test message 1";
        ConversationSingle conversationSingle = conversationService.getConversationSingle(testUser1, testUser2);
        conversationService.sendTextMessage(expectedContent1, conversationSingle, testUser1);

        String expectedContent2 = "testMessage2.txt";

        File testFile3 = new File(expectedContent2);
        try {
            testFile3.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String dir1 = testFile3.getPath();
        conversationService.sendAttachment(dir1,conversationSingle,testUser1);

        String expectedContent3 = "This is a test message 3";
        conversationService.sendTextMessage(expectedContent3, conversationSingle, testUser2);

        String expectedContent4 = "testMessage4.txt";

        File testFile4 = new File(expectedContent4);
        try {
            testFile4.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String dir2 = testFile4.getPath();
        conversationService.sendAttachment(dir2,conversationSingle,testUser1);
        List<Message> returnMessageList = conversationService.showMessageFromConversation(0, 4, conversationSingle);

        TextMessage message1 = (TextMessage) returnMessageList.get(0);
        Attachment message2 = (Attachment) returnMessageList.get(1);
        TextMessage message3 = (TextMessage) returnMessageList.get(2);
        Attachment message4 = (Attachment) returnMessageList.get(3);

        assertEquals(expectedContent4, message4.getFileName());
        assertEquals(expectedContent3, message3.getContent());
        assertEquals(expectedContent2, message2.getFileName());
        assertEquals(expectedContent1, message1.getContent());
    }

    @Test
    void findMessageText() {
        ConversationSingle conversationSingle = conversationService.getConversationSingle(testUser1, testUser2);
        String expectedContent1 = "This is a test message 1";
        String expectedContent2 = "This is a test message 2 find";
        conversationService.sendTextMessage(expectedContent1, conversationSingle, testUser1);
        conversationService.sendTextMessage(expectedContent2, conversationSingle, testUser2);
        List<TextMessage> returnList = conversationService.findMessageText("find", conversationSingle);
        assertEquals(expectedContent2, returnList.get(0).getContent());
    }
}