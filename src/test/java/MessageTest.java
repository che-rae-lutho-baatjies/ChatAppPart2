import com.mycompany.chatapppart2.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author lutho
 */
public class MessageTest {

    @BeforeEach
    public void clearMessageLists() {
        Message.sentMessages.clear();
        Message.disregardedMessages.clear();
        Message.storedMessages.clear();
        Message.messageHashes.clear();
        Message.messageIDs.clear();
        Message.recipientList.clear();
    }

    @Test
    public void testMessageDetailsAreCaptured() {
        Message message = new Message("+27718693002", "Test message", 1);

        assertEquals("+27718693002", message.getRecipient());
        assertEquals("Test message", message.getMessageText());
        assertNotNull(message.getMessageID());
        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testPrintMessagesIncludesMessageDetails() {
        Message message = new Message("+27718693002", "Test message", 1);

        String report = message.printMessages();

        assertTrue(report.contains("Message ID: " + message.getMessageID()));
        assertTrue(report.contains("Message Hash: " + message.getMesageHash()));
        assertTrue(report.contains("Recipient:+27718693002"));
        assertTrue(report.contains("Message: Test message"));
    }

    @Test
    public void testReturnTotalMessages() {
        Message message = new Message("+27718693002", "Test", 1);

        assertEquals(1, message.returnTotalMessages());
    }

    @Test
    public void testMessageHashFormat() {
        Message message = new Message("+27718693002", "Hi Mike", 1);

        String hash = message.getMesageHash();

        assertTrue(hash.matches("\\d{3}:\\d+:HIMIKE"));
    }

    @Test
    public void testDisplayLongestMessage() {
        Message.storedMessages.add("Short message");
        Message.storedMessages.add("This is the longest message among these");
        Message.storedMessages.add("Medium length");

        String longest = Message.displayLongestMessage();

        assertEquals("This is the longest message among these", longest);
    }

    @Test
    public void testSearchByMessageID() {
        String id = "1234567890";
        Message.messageIDs.add(id);
        Message.recipientList.add("+2781234567");
        Message.storedMessages.add("Sample message");

        String result = Message.searchByMessageID(id);
        String notFound = Message.searchByMessageID("nonexistent");

        assertTrue(result.contains("+2781234567"));
        assertTrue(result.contains("Sample message"));
        assertEquals("Message not found.", notFound);
    }

    @Test
    public void testSearchByRecipient() {
        Message.recipientList.add("+27838884567");
        Message.storedMessages.add("Message 1");
        Message.recipientList.add("+27838884567");
        Message.storedMessages.add("Message 2");
        Message.recipientList.add("+27834567890");

        String results = Message.searchByRecipient("+27838884567");

        assertTrue(results.contains("Message 1"));
        assertTrue(results.contains("Message 2"));
    }

    @Test
    public void testDeleteByHash() {
        String hash = "123:1:HELLO";
        Message.messageHashes.add(hash);
        Message.storedMessages.add("Hello world");
        Message.messageIDs.add("123");
        Message.recipientList.add("+27838884567");

        String result = Message.deleteByHash(hash);

        assertEquals("Message: Hello world successfully deleted.", result);
        assertFalse(Message.storedMessages.contains("Hello world"));
        assertTrue(Message.messageHashes.isEmpty());
        assertTrue(Message.messageIDs.isEmpty());
        assertTrue(Message.recipientList.isEmpty());
    }
}
