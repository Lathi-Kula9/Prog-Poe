import java.util.Scanner;

public class QuickChat {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to QuickChat.");

        boolean running = true;

        // while loop keeps the menu going until the user picks Quit
        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");

            int option = readNumber(input);

            if (option == 1) {
                sendMessages(input);
            } else if (option == 2) {
                System.out.println("Coming Soon.");
            } else if (option == 3) {
                System.out.println("Goodbye!");
                running = false;
            } else {
                System.out.println("Please enter 1, 2 or 3.");
            }
        }
        input.close();
    }

    // handles option 1: asks how many messages then loops with a for loop
    private static void sendMessages(Scanner input) {
        System.out.print("How many messages do you want to enter? ");
        int howMany = readNumber(input);

        for (int i = 0; i < howMany; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + howMany + " ---");

            // keep asking until the cell number is correct
            String recipient;
            while (true) {
                System.out.print("Enter recipient cell number (with international code, e.g. +27718693002): ");
                recipient = input.nextLine().trim();
                Message check = new Message("0000000000", 0, recipient, "check");
                String result = check.checkRecipientCell();
                System.out.println(result);
                if (result.equals("Cell phone number successfully captured.")) {
                    break;
                }
            }

            // keep asking until the message is 250 characters or less
            String text;
            Message msg;
            while (true) {
                System.out.print("Enter your message (max 250 characters): ");
                text = input.nextLine();
                if (text.trim().isEmpty()) {
                    System.out.println("The message cannot be empty.");
                    continue;
                }
                msg = new Message(recipient, text);
                String lengthResult = msg.checkMessageLength();
                System.out.println(lengthResult);
                if (lengthResult.equals("Message ready to send.")) {
                    break;
                }
            }

            System.out.println(msg.generateMessageID());

            // ask what to do with the message
            System.out.println("What would you like to do with this message?");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");

            int choice = readNumber(input);
            while (choice < 1 || choice > 3) {
                System.out.print("Please enter 1, 2 or 3: ");
                choice = readNumber(input);
            }

            String outcome = msg.sentMessage(choice);
            System.out.println(outcome);

            if (choice == 1) {
                // show the full details after sending
                System.out.println("\n" + msg.getDetails());
            } else if (choice == 2) {
                // user must press 0 to delete
                int del = readNumber(input);
                if (del == 0) {
                    System.out.println("Message deleted.");
                } else {
                    System.out.println("Message was not deleted.");
                }
            }
        }

        // total shown once all the messages are done
        System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
    }

    // reads a whole number and asks again if the user types letters
    private static int readNumber(Scanner input) {
        while (true) {
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a number: ");
            }
        }
    }
}