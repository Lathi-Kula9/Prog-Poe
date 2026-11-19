public class MessageTest.java {

    public static void main(String[] args) {

        System.out.println("MESSAGE TESTING");

        // Create a message for testing
        Message message = new Message(
                5,
                "+27821234567",
                "Hello bro how are you"
        );

        // Test 1: Check message ID
        System.out.println("Test 1: Check Message ID");

        if (message.checkMessageID()) {
            System.out.println("PASSED: Message ID is valid.");
        } else {
            System.out.println("FAILED: Message ID is invalid.");
        }

        // Test 2: Check recipient cellphone number
        System.out.println("Test 2: Check Recipient Number");

        if (message.checkRecipientCell()) {
            System.out.println("PASSED: Recipient number is valid.");
        } else {
            System.out.println("FAILED: Recipient number is invalid.");
        }

        // Test 3: Check message length
        System.out.println("Test 3: Check Message Length");

        if (message.checkMessageLength()) {
            System.out.println("PASSED: Message length is valid.");
        } else {
            System.out.println("FAILED: Message length is invalid.");
        }

        // Test 4: Check message number
        System.out.println("Test 4: Check Message Number");

        if (message.getMessageNumber() == 5) {
            System.out.println("PASSED: Message number is correct.");
        } else {
            System.out.println("FAILED: Message number is incorrect.");
        }

        // Test 5: Check message text
        System.out.println("Test 5: Check Message Text");

        if (message.getMessageText().equals("Hello bro how are you")) {
            System.out.println("PASSED: Message text is correct.");
        } else {
            System.out.println("FAILED: Message text is incorrect.");
        }

        // Test 6: Check recipient
        System.out.println("Test 6: Check Recipient");

        if (message.getRecipient().equals("+27821234567")) {
            System.out.println("PASSED: Recipient is correct.");
        } else {
            System.out.println("FAILED: Recipient is incorrect.");
        }

        // Test 7: Check message hash
        System.out.println("Test 7: Check Message Hash");

        if (message.getMessageHash() != null
                && !message.getMessageHash().isEmpty()) {
            System.out.println("PASSED: Message hash was generated.");
        } else {
            System.out.println("FAILED: Message hash was not generated.");
        }

        // Test 8: Check displayed message
        System.out.println("Test 8: Display Message");

        if (message.displayMessage() != null
                && message.displayMessage().contains("Hello bro how are you")) {
            System.out.println("PASSED: Message can be displayed.");
        } else {
            System.out.println("FAILED: Message could not be displayed.");
        }

        // Test 9: Check invalid recipient
        System.out.println("Test 9: Invalid Recipient");

        Message invalidMessage = new Message(
                6,
                "abc123",
                "Testing an invalid recipient"
        );

        if (!invalidMessage.checkRecipientCell()) {
            System.out.println("PASSED: Invalid recipient was rejected.");
        } else {
            System.out.println("FAILED: Invalid recipient was accepted.");
        }

        // Test 10: Check message that is too long
        System.out.println("Test 10: Message Too Long");

        String longText = "";

        for (int i = 0; i < 251; i++) {
            longText += "A";
        }

        Message longMessage = new Message(
                7,
                "+27821234567",
                longText
        );

        if (!longMessage.checkMessageLength()) {
            System.out.println("PASSED: Long message was rejected.");
        } else {
            System.out.println("FAILED: Long message was accepted.");
        }

        System.out.println("TESTING COMPLETED");
    }
}