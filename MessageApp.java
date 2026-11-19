import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
        // Pre-populate initial test data from the assignment brief
        populateTestData();

        int choice = 0;
        System.out.println("Welcome to QuickChat!");

        do {
            System.out.println("\n--- QUICKCHAT MAIN MENU ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Display Messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    sendMessagesInteractive();
                    break;
                case 2:
                    displayFullReport();
                    break;
                case 3:
                    System.out.println("Exiting application. Goodbye!");
                    break;
                case 4:
                    displayStoredMessagesMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } while (choice != 3);

        scanner.close();
    }

    
    public static void populateTestData() {
        messages.add(new Message("0838884567", "+27834557896", "Did you get the cake?", "Sent", "Test Message 1"));
        messages.add(new Message("+27838884567", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "Test Message 2"));
        messages.add(new Message("+27834484567", "+27834484567", "Yohoooo, I am at your gate.", "Disregard", "Test Message 3"));
        messages.add(new Message("0838884567", "+27838884567", "It is dinner time!", "Sent", "Test Message 4"));
        messages.add(new Message("+27838884567", "+27838884567", "Ok, I am leaving without you.", "Stored", "Test Message 5"));
    }

    // Option 1: Interactive Message Sending with validation & message count
    public static void sendMessagesInteractive() {
        System.out.print("How many messages would you like to send? ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
            return;
        }
        int numMessages = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 1; i <= numMessages; i++) {
            System.out.println("\n--- Entering Message " + i + " of " + numMessages + " ---");

            // Recipient Validation
            String recipient;
            while (true) {
                System.out.print("Enter recipient cellphone number: ");
                recipient = scanner.nextLine().trim();
                if (checkCellPhoneNumber(recipient)) {
                    System.out.println("Valid cellphone number.");
                    break;
                } else {
                    System.out.println("Invalid cellphone number. Please ensure it contains 10-15 digits or starts with '+' (e.g., +27...). Please re-enter.");
                }
            }

            // Message Length Validation
            String text;
            while (true) {
                System.out.print("Enter your message (max 250 characters): ");
                text = scanner.nextLine();
                if (text.length() <= 250) {
                    System.out.println("Message successfully sent.");
                    break;
                } else {
                    System.out.println("Please ensure that your message is 250 characters or less. Current length: " + text.length() + ". Please re-enter.");
                }
            }

            // Generate details and store
            String id = "0838884567";
            String hash = "Test Message " + (messages.size() + 1);
            String flag = "Stored";

            messages.add(new Message(id, recipient, text, flag, hash));
            System.out.println("Message successfully stored.");
        }
    }

    // Helper method to validate phone number format
    public static boolean checkCellPhoneNumber(String number) {
        if (number == null || number.isEmpty()) {
            return false;
        }
        return number.matches("^[+]?[0-9]{10,15}$");
    }

    // Fourth Main Menu Option Sub-Menu
    public static void displayStoredMessagesMenu() {
        String option;
        do {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID and display recipient and message");
            System.out.println("d. Search for all messages sent or stored regarding a particular recipient");
            System.out.println("e. Delete a message using the message hash");
            System.out.println("f. Display a report that lists the full details of all stored messages");
            System.out.println("g. Return to Main Menu");
            System.out.println("h. Read JSON file into an array and display it");  
            System.out.print("Choose an option (a-h): ");
            
            option = scanner.nextLine().trim().toLowerCase();

            switch (option) {
                case "a":
                    displayStoredSendersAndRecipients();
                    break;
                case "b":
                    displayLongestMessage();
                    break;
                case "c":
                    System.out.print("Enter Message ID to search: ");
                    searchByMessageID(scanner.nextLine());
                    break;
                case "d":
                    System.out.print("Enter Recipient to search: ");
                    searchByRecipient(scanner.nextLine());
                    break;
                case "e":
                    System.out.print("Enter Message Hash to delete: ");
                    deleteByMessageHash(scanner.nextLine());
                    break;
                case "f":
                    displayFullReport();
                    break;
                case "g":
                    System.out.println("Returning to Main Menu...");
                    break;
                    case "h":
    Message[] loadedFromJson = JsonMessageReader.readJsonIntoArray("messages.json");
    JsonMessageReader.displayJsonMessages(loadedFromJson);
    break;
                default:
                    System.out.println("Invalid choice. Please enter a letter between a and g.");
            }
        } while (!option.equals("g"));
    }

    // a. Display sender and recipient of all stored messages[span_3](start_span)[span_3](end_span)
    public static void displayStoredSendersAndRecipients() {
        System.out.println("\n--- Stored Messages (Senders & Recipients) ---");
        boolean found = false;
        for (Message m : messages) {
            if (m.getFlag().equalsIgnoreCase("Stored")) {
                System.out.println("Sender ID: " + m.getMessageID() + " | Recipient: " + m.getRecipient());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No stored messages found.");
        }
    }

    // b. Display the longest stored message[span_4](start_span)[span_4](end_span)
    public static void displayLongestMessage() {
        if (messages.isEmpty()) {
            System.out.println("No messages available.");
            return;
        }
        Message longest = messages.get(0);
        for (Message m : messages) {
            if (m.getMessageText().length() > longest.getMessageText().length()) {
                longest = m;
            }
        }
        System.out.println("\n--- Longest Message ---");
        System.out.println(longest.getMessageText());
    }

    // c. Search for a message ID and display recipient and message[span_5](start_span)[span_5](end_span)
    public static void searchByMessageID(String id) {
        System.out.println("\n--- Search Results for Message ID: " + id + " ---");
        boolean found = false;
        for (Message m : messages) {
            if (m.getMessageID().equals(id)) {
                System.out.println("Recipient: " + m.getRecipient() + " | Message: " + m.getMessageText());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No message found with ID: " + id);
        }
    }

    // d. Search for all messages sent or stored regarding a particular recipient[span_6](start_span)[span_6](end_span)
    public static void searchByRecipient(String recipient) {
        System.out.println("\n--- Search Results for Recipient: " + recipient + " ---");
        boolean found = false;
        for (Message m : messages) {
            if (m.getRecipient().equals(recipient)) {
                System.out.println(m.getMessageText());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for recipient: " + recipient);
        }
    }

    // e. Delete a message using the message hash[span_7](start_span)[span_7](end_span)
    public static void deleteByMessageHash(String hash) {
        boolean deleted = false;
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getMessageHash().equalsIgnoreCase(hash)) {
                System.out.println("Message: \"" + messages.get(i).getMessageText() + "\" successfully deleted.");
                messages.remove(i);
                deleted = true;
                break;
            }
        }
        if (!deleted) {
            System.out.println("Message hash not found.");
        }
    }

    // f. Display a report that lists the full details of all messages[span_8](start_span)[span_8](end_span)[span_9](start_span)[span_9](end_span)
    public static void displayFullReport() {
        System.out.println("\n--- Full Message Report ---");
        if (messages.isEmpty()) {
            System.out.println("No messages present.");
            return;
        }
        for (Message m : messages) {
            System.out.println("Hash: " + m.getMessageHash() + 
                               " | ID: " + m.getMessageID() + 
                               " | Recipient: " + m.getRecipient() + 
                               " | Flag: " + m.getFlag() + 
                               " | Message: " + m.getMessageText());
        }
    }
    /*
 * Attribution / Reference:
 * Manual JSON array and object parser adapted for PROG5121 POE QuickChat
 * to read JSON entries into a Message[] array and display all required fields
 * (messageID, recipient, messageText, flag, messageHash).
 */
public class JsonMessageReader {

    public static Message[] readJsonIntoArray(String filePath) {
        List<Message> loadedMessages = new ArrayList<>();

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                System.out.println("JSON file not found at: " + path.toAbsolutePath());
                return new Message[0];
            }

            String content = Files.readString(path).trim();
            if (content.isEmpty() || content.equals("[]")) {
                System.out.println("No messages found in " + filePath + ".");
                return new Message[0];
            }

            String body = content;
            if (body.startsWith("[") && body.endsWith("]")) {
                body = body.substring(1, body.length() - 1).trim();
            }

            List<String> rawObjects = new ArrayList<>();
            int braceDepth = 0;
            boolean inQuotes = false;
            int objStart = -1;

            for (int i = 0; i < body.length(); i++) {
                char ch = body.charAt(i);

                if (ch == '"' && (i == 0 || body.charAt(i - 1) != '\\')) {
                    inQuotes = !inQuotes;
                } else if (!inQuotes) {
                    if (ch == '{') {
                        if (braceDepth == 0) {
                            objStart = i;
                        }
                        braceDepth++;
                    } else if (ch == '}') {
                        braceDepth--;
                        if (braceDepth == 0 && objStart != -1) {
                            rawObjects.add(body.substring(objStart, i + 1));
                            objStart = -1;
                        }
                    }
                }
            }

            for (String obj : rawObjects) {
                String messageID = getJsonValue(obj, "messageID");
                String recipient = getJsonValue(obj, "recipient");
                String messageText = getJsonValue(obj, "messageText");
                String flag = getJsonValue(obj, "flag");
                String messageHash = getJsonValue(obj, "messageHash");

                loadedMessages.add(new Message(messageID, recipient, messageText, flag, messageHash));
            }

        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }

        return loadedMessages.toArray(new Message[0]);
    }

    public static void displayJsonMessages(Message[] jsonMessages) {
        if (jsonMessages == null || jsonMessages.length == 0) {
            System.out.println("No JSON messages to display.");
            return;
        }

        System.out.println("\n=== ALL JSON MESSAGES READ INTO ARRAY (" + jsonMessages.length + " total) ===");
        for (int i = 0; i < jsonMessages.length; i++) {
            Message m = jsonMessages[i];
            System.out.println("----------------------------------------");
            System.out.println("Record #" + (i + 1));
            System.out.println("  Message ID   : " + safeValue(m.getMessageID()));
            System.out.println("  Recipient    : " + safeValue(m.getRecipient()));
            System.out.println("  Message Text : " + safeValue(m.getMessageText()));
            System.out.println("  Flag         : " + safeValue(m.getFlag()));
            System.out.println("  Message Hash : " + safeValue(m.getMessageHash()));
        }
        System.out.println("----------------------------------------");
    }

    public static String getJsonValue(String jsonObject, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = jsonObject.indexOf(searchKey);
        if (keyIndex == -1) {
            return "";
        }

        int colonIndex = jsonObject.indexOf(":", keyIndex + searchKey.length());
        if (colonIndex == -1) {
            return "";
        }

        int i = colonIndex + 1;
        while (i < jsonObject.length() && Character.isWhitespace(jsonObject.charAt(i))) {
            i++;
        }

        if (i >= jsonObject.length()) {
            return "";
        }

        if (jsonObject.charAt(i) == '"') {
            int startQuote = i + 1;
            StringBuilder sb = new StringBuilder();
            for (int j = startQuote; j < jsonObject.length(); j++) {
                char c = jsonObject.charAt(j);
                if (c == '\\' && j + 1 < jsonObject.length()) {
                    sb.append(jsonObject.charAt(j + 1));
                    j++;
                } else if (c == '"') {
                    return sb.toString();
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

        int endIndex = i;
        while (endIndex < jsonObject.length()
                && jsonObject.charAt(endIndex) != ','
                && jsonObject.charAt(endIndex) != '}') {
            endIndex++;
        }
        return jsonObject.substring(i, endIndex).trim();
    }

    private static String safeValue(String val) {
        return (val != null && !val.trim().isEmpty()) ? val : "[missing]";
    }

        private JsonMessageReader() {
        }
    }
  
    }



