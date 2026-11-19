import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class Message {

    // These variables store the information for each message.
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String flag;
    private String messageHash;

    // Constructor
    public Message(String messageID, String recipient, String messageText, String flag, String messageHash) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.flag = flag;
        this.messageHash = messageHash;
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getFlag() {
        return flag;
    }

    public String getMessageHash() {
        return messageHash;
    }

    boolean displayMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

}