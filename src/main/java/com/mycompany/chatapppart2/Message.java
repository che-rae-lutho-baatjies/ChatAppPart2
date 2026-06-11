/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.chatapppart2;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lutho
 */
public class Message {
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    // Part 3: Arrays to store message info
    public static List<String> sentMessages = new ArrayList<>();
    public static List<String> disregardedMessages = new ArrayList<>();
    public static List<String> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();
    public static List<String> recipientList = new ArrayList<>();

    
    // Construtor 
    public Message (String recipient, String messageText, int messageNumber1) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID ();
        this.messageHash = createMessageHash();
    }

    Message(String string,String short_message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Generates a random 10- digit message ID using string manipulation
     * @return 10- character string ID 
     */
    private String generateMessageID() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append((int)(Math.random()* 10));
            //

        }
        return id.toString();
    }
  
    /**
     * Make sure message ID is not more than 10 characters
     * @return true if valid,otherwise  false
     */
      public boolean checkMessageID() {
          return messageID.length( )<= 10;
      }

    /**
     * Validates recipient cell number 
     * @return Success or failure message
     */

    public String checkRecipientCell() {
        // Reuse from Login.java - international code + max 10 chars after
        if (recipient.startsWith("+27") && recipient.length() ==12){
            return "Cell phone number successfully captured." ;

        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";

        }
    }

    /**
     * Checks message length (max 250 chars)
     * @return Success or failure message with exact char count
     */

    public String checkMessageLength() {
        if (messageText.length() >=250){
            return "Message ready to send.";

        } else {
            int exceededBy= messageText.length() - 250;
            return "Message exceeds 250 characters by " + exceededBy + ",  please reduce the size.";


        }
    }

    /**
     * Creates message hash: first2digits: number:firstword+lastword(All UPPERCASE)
     * @return formatted hash string
     */ 

    public String createMessageHash() {
        String idPart = messageID.substring(0, 3);
        String [] words= messageText.split(" ");
        String firstWord = words[0] ;
        String lastWord = words[words.length - 1];

        String hash = idPart +":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    /** 
     * Asks user what to do: Send, Disregard, or Store
     * @return Status message
     */
    public String sentMessage() {
        System.out.println("What would you like to do with this message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");

        java.util.Scanner scanner = new java.util.Scanner (System.in);
        int option = scanner.nextInt() ;

        switch (option) {
            case 1:
                // Add message info to arrays
                Message.sentMessages.add(this.messageText);
                Message.messageHashes.add(this.messageHash);
                Message.messageIDs.add(this.messageID);
                Message.recipientList.add(this.recipient);
                return "Meesage successfully sent.";
            case 2: 
                Message.disregardedMessages.add(this.messageText);
                return "Message disregarded. ";
            case 3: 
                storeMessage();
                Message.storedMessages.add(this.messageText);
                Message.messageHashes.add(this.messageHash);
                Message.messageIDs.add(this.messageID);
                Message.recipientList.add(this.recipient);
                return "Message successfully stored.";

            default: 
                return "Invalid option selected.";
        }
    }

    /**
     * Saves message to JSON file using org.json library 
     */

    public void storeMessage() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("messageID" , this.messageID);
            obj.put("recipient", this.recipient);
            obj.put("message", this.messageText);

            java.io.FileWriter fw = new java.io.FileWriter ("message.json", true);
            fw.write(obj.toString() + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving message: " + e.getMessage());
        }


    }

    /**
     * Returns all message details in correct order
     * @return Formatted string with ID, Hash, Recipient, Message
     */

    public String printMessages() {
        return "Message ID: " + messageID + "\n" +"Message Hash: " + messageHash + "\n" + "Recipient:" + recipient + "\n" + "Message: " + messageText;

    }

    /**
     * Returns total count of message sent
     * @return Message count 
     */

    public int returnTotalMessages() {
        return 1;
        // Simplified for this demo
    }

    // Part 3:

    public static String displayLongestMessage() {
        String longest = "";
        for (String message : storedMessages) {
            if (message.length() > longest.length()) {
                longest = message;
            }
        }
        return longest;
    }

    public static String searchByMessageID(String id) {
        for (int i = 0; i <messageIDs.size() ; i++) {

            if (messageIDs.get(i).equals(id)) {
                return "Recipient: "
                        + recipientList.get(i)
                        + "\nMessage: "
                        + storedMessages.get(i);
            }  
        }
        return "Message not found.";
    } 


    public static String searchByRecipient(String recipient) {
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < recipientList.size(); i++) {
            if (i < storedMessages.size()) {
                results.append(storedMessages.get(i))
                        .append("\n");
            }
        }
        if (results.length() == 0) {
            return "No messages found.";
        } 
            return results.toString();
        }


    public static String deleteByHash(String hash) {
        for(int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                String deletedMessage = storedMessages.get(i);
                messageHashes.remove(i);
                storedMessages.remove(i);
                messageIDs.remove(i);
                recipientList.remove(i);
                return "Message: " 
                        + deletedMessage 
                        + " successfully deleted.";
            }  
        }
        return "Hash not found.";
    }



    public static void loadStoredMessages() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("messages.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject obj = new JSONObject(line);
                String messageText = obj.getString("message");
                String messageID = obj.getString("messageID");
                String recipient = obj.getString("recipient");
                // Generate hash for this message
                String hash = messageID.substring(0, 3) + ":" + "1" + ":" + messageText.split(" ")[0].toUpperCase() + messageText.split(" ")[messageText.split(" ").length - 1].toUpperCase();
                // Add to arrays
                storedMessages.add(messageText);
                messageIDs.add(messageID);
                messageHashes.add(hash);
                recipientList.add(recipient);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("No previous messages to load.");
        }
    }

    public static String displayReport() {
        StringBuilder report = new StringBuilder();

        report.append("=== Message Report ===\n");

        for (int i = 0; i < storedMessages.size(); i++) {
        }
            report.append("---------------------------\n");
            int i = 0;

            report.append("Message Hash: ")
                    .append(messageHashes.get(i))
                    .append("\n");
            report.append("Recipient: ")
                    .append(recipientList.get(i))
                    .append("\n");
            report.append("Message: ")
                    .append(sentMessages.get(i))
                    .append("\n");


        return report.toString();
    }





    //Getters for testing 
    public String getMessageID()
    { return messageID; }
    public int getMessageNumber()
    { return messageNumber; }
    public String getRecipient() 
    { return recipient; }
    public String getMessageText()
    { return messageText; }
    public String getMesageHash()
    {return messageHash; }
    }

