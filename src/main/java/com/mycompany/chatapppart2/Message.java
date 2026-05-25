/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart2;

import org.json.JSONObject; 

/**
 *
 * @author lutho
 */
public class Message {
    System.out.println("What would you like to do with this message?");
    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store Message to send later");
    
    java.util.Scanner scanner = new java.util.Scanner (System.in);
    int option = scanner.nextInt() ;
    
    switch (option) {
        case 1:
            return "Meesage successfully sent.";
        case 2: 
            return "Press 0 to delete the message.";
        case 3: 
            storeMessage();
            return "Messagesuccessfully stored.";
            
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

//Getters for testing 
public String getMessageID()
{      String messageID = null;
return messageID; }
public int getMessageNumber()
{      int messageNumber = 0;
return messageNumber; }
public String getRecipient() 
{      String recipient = null;
return recipient; }
public String getMessageText()
{      String messageText = null;
return messageText; }
public String getMesageHash()
{      String messageHash = null;
return messageHash; }


    
}

