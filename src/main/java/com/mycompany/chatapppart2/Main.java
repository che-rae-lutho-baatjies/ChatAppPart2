/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart2;

import java.util.Scanner;

/**
 *
 * @author lutho
 */
public class Main {
    public static void main(String[] args) {

        // Allows user to enter information
        Scanner input = new Scanner(System.in); 
        
        //Object of Login class and call its method 
        Login login = new Login(); 
        
        //---REGISTRATION SECTION---
        System.out.println("===USER REGISTRATION ===");
        System.out.println("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = input.nextLine();
        
        //---USERNAME INPUT WITH VALIDATION---
        String username = "";
        boolean validUsername = false;
        while (!validUsername) {
            System.out.print("Enter a username: ");
            username = input.nextLine();

            if (login.checkUserName(username)) { 
                validUsername = true;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        //---PASSWORD INPUT WITH VALIDATION---
        String password = "";
        boolean validPassword = false;
        while (!validPassword) {
            System.out.print("Enter a password: ");
            password = input.nextLine();

            if (login.checkPasswordComplexity(password)) {
                validPassword = true;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that your password contains a capital letter, number, special character and is at least 8 characters long.");
            }
        }

        //---PHONE INPUT WITH VALIDATION---
        String phone = "";
        boolean validPhone = false;
        while (!validPhone) {
            System.out.print("Enter your South African phone number (+27...): ");
            phone = input.nextLine();

            if (login.checkCellPhoneNumber(phone)) {
                validPhone = true;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        //The registerUser method is called and store the message it returns
        String response = login.registerUser(username, password, phone);

        //The registration message should be showned 
        System.out.println(response);

        //---LOGIN SECTION---
        System.out.println("\n==USER LOGIN==");

        //---LOGIN USERNAME WITH WHILE LOOP---
        String loginUsername = "";
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter your username: ");
            loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            // The loginUser will be called to check if the details match the stored ones
            loggedIn = login.loginUser(loginUsername, loginPassword);

            //The correct login message must be printed out 
            String loginMessage = login.returnLoginStatus(loggedIn);
            System.out.println(loginMessage);

            // If login successful, exit loop and continue to welcome message
            if (loggedIn) {
                break;
            }
        }

        //✅ AFTER LOGIN SUCCESS → CONTINUES DIRECTLY TO WELCOME MESSAGE ✅
        System.out.println("\n== Welcome to the CHAT APP ===");
        Scanner scanner = new Scanner(System.in);
        
        boolean running = true;

        while (running) {
            System.out.println("\n1) Send Message");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            // Clear buffer

            switch (choice) {
                case 1:
                    System.out.print("How many messages would you like to send? ");
                    int numMessages = scanner.nextInt();
                    scanner.nextLine();
                    
                    for (int i = 0; i < numMessages; i++) {
                        int messageNumber = i + 1;
                        System.out.println("\n--Message " + messageNumber + " --");

                        //---RECIPIENT INPUT WITH WHILE LOOP---
                        String recipient = "";
                        boolean validRecipient = false;
                        while (!validRecipient) {
                            System.out.print("Enter recipient cell number: ");
                            recipient = scanner.nextLine();

                            // Create temporary message object for validation
                            Message tempMsg = new Message(recipient, "", messageNumber);
                            String recipientCheck = tempMsg.checkRecipientCell();
                            System.out.println(recipientCheck);

                            if (recipientCheck.contains("valid") || recipientCheck.contains("Valid")) {
                                System.out.println("✓ Recipient validated successfully!");
                                validRecipient = true;  // ✅ BREAKS LOOP → CONTINUES TO MESSAGE TEXT
                            } else {
                                System.out.println("Please try again.");
                            }
                        }

                        // ✅ CONTINUES HERE AFTER RECIPIENT VALIDATION ✅
                        System.out.print("Enter your message: ");
                        String messageText = scanner.nextLine();

                        //Create message object
                        Message msg = new Message(recipient, messageText, messageNumber);

                        //Validate
                        System.out.println(msg.checkRecipientCell());
                        String lengthCheck = msg.checkMessageLength();
                        System.out.println(lengthCheck);

                        if (lengthCheck.equals("Message ready to send.")) {
                            String action = msg.sentMessage();
                            System.out.println(action);
                            System.out.println(msg.printMessages());
                        }
                    }
                    System.out.println("Total messages processed: " + numMessages);
                    break; 

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }   
    

    

