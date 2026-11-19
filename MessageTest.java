import org.junit.jupiter.api.Test;

// Import the methods used to check test results.
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // Check that the message ID has 10 characters.
    @Test
    public void testMessageIDLength() {

        // Create a message to use for testing.
        Message message = new Message(
                1,
                "+27821234567",
                "Hello World");

        // The message ID should pass the length check.
        assertTrue(message.checkMessageID());

        // The message ID should contain exactly 10 characters.
        assertEquals(10, message.getMessageID().length());
    }

    // Check that a correctly formatted recipient is accepted.
    @Test
    public void testValidRecipient() {

        Message message = new Message(
                1,
                "+27821234567",
                "Hello World");

        // The method should return true for this number.
        assertTrue(message.checkRecipientCell());
    }

    // Check that an incorrectly formatted recipient is rejected.
    @Test
    public void testInvalidRecipient() {

        Message message = new Message(
                1,
                "abc123",
                "Hello World");

        // The method should return false for this input.
        assertFalse(message.checkRecipientCell());
    }

    // Check that a short message passes the length test.
    @Test
    public void testValidMessageLength() {

        Message message = new Message(
                1,
                "+27821234567",
                "Hello World");

        // The message is shorter than 250 characters.
        assertTrue(message.checkMessageLength());
    }

    // Check that the correct message number is stored.
    @Test
    public void testMessageNumber() {

        Message message = new Message(
                5,
                "+27821234567",
                "Hello World");

        // The message number should be 5.
        assertEquals(5, message.getMessageNumber());
    }

    // Check that the message text is stored correctly.
    @Test
    public void testMessageText() {

        Message message = new Message(
                1,
                "+27821234567",
                "Hello World");

        // Compare the expected text with the actual text.
        assertEquals(
                "Hello World",
                message.getMessageText());
    }

    // Check that the message hash is generated.
    @Test
    public void testMessageHashIsGenerated() {

        Message message = new Message(
                1,
                "+27821234567",
                "Hello World");

        // The hash should not be null.
        assertNotNull(message.getMessageHash());

        // The hash should not be an empty string.
        assertFalse(message.getMessageHash().isEmpty());
    }

    private void assertFalse(boolean empty) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertNotNull(String messageHash) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(String hello_World, String messageText) {
        assertEquals(hello_World, messageText);
    }

    private void assertEquals(int hello_World, int messageText) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}