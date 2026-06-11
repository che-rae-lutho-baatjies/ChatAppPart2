Chat Application - Part  3 
Student Information
-Name: Che-Rae

Student Number : ST10511240

Module: PROG 5121

Project Information
Focusing on (login.java, main.java, and loginTest.java)

### Features

#### 1. Message Creation

The class allows users to create a message with:

* Recipient phone number
* Message text
* Message number
* Automatically generated Message ID
* Automatically generated Message Hash

**Constructor:**

```java
Message(String recipient, String messageText, int messageNumber)
```

---

#### 2. Automatic Message ID Generation

Each message receives a unique random 10-digit Message ID.

**Method:**

```java
generateMessageID()
```

**Example ID:**

```
1234567890
```

---

#### 3. Message ID Validation

Ensures that the generated Message ID does not exceed 10 characters.

**Method:**

```java
checkMessageID()
```

**Returns:**

* `true` if valid
* `false` if invalid

---

#### 4. Recipient Phone Number Validation

Validates South African phone numbers using the international code `+27`.

**Requirements:**

* Must start with `+27`
* Must contain exactly 12 characters

**Method:**

```java
checkRecipientCell()
```

**Success Message:**

```
Cell phone number successfully captured.
```

**Failure Message:**

```
Cell phone number is incorrectly formatted or does not contain an international code.
```

---

#### 5. Message Length Validation

Checks whether a message is within the maximum allowed length of 250 characters.

**Method:**

```java
checkMessageLength()
```

**Valid:**

```
Message ready to send.
```

**Invalid:**

```
Message exceeds 250 characters by X characters.
```

---

#### 6. Message Hash Generation

Creates a unique hash using:

* First three digits of Message ID
* Message Number
* First word of message
* Last word of message

The hash is converted to uppercase.

**Method:**

```java
createMessageHash()
```

**Example:**

```
123:1:HELLOWORLD
```

---

#### 7. Send, Disregard, or Store Messages

Users can choose to:

1. Send Message
2. Disregard Message
3. Store Message for later

**Method:**

```java
sentMessage()
```

The method stores information in different arrays depending on the selected option.

---

#### 8. JSON Message Storage

Messages can be saved to a JSON file for future retrieval.

**Method:**

```java
storeMessage()
```

**Stored Information:**

* Message ID
* Recipient
* Message Text

**File Created:**

```
message.json
```

---

#### 9. Display Message Details

Displays all message information.

**Method:**

```java
printMessages()
```

**Output Example:**

```
Message ID: 1234567890
Message Hash: 123:1:HELLOWORLD
Recipient: +27821234567
Message: Hello World
```

---

#### 10. Total Messages Counter

Returns the number of messages sent.

**Method:**

```java
returnTotalMessages()
```

---

## Part 3 Features

#### 11. Longest Stored Message

Finds and displays the longest stored message.

**Method:**

```java
displayLongestMessage()
```

---

#### 12. Search by Message ID

Searches for a message using its Message ID.

**Method:**

```java
searchByMessageID(String id)
```

**Returns:**

* Recipient
* Message Text

---

#### 13. Search by Recipient

Searches all messages sent to a specific recipient.

**Method:**

```java
searchByRecipient(String recipient)
```

Returns all matching messages.

---

#### 14. Delete Message by Hash

Deletes a stored message using its message hash.

**Method:**

```java
deleteByHash(String hash)
```

**Success Example:**

```
Message successfully deleted.
```

---

#### 15. Load Previously Stored Messages

Reads messages from a JSON file and loads them into memory.

**Method:**

```java
loadStoredMessages()
```

The following information is loaded:

* Message Text
* Message ID
* Recipient
* Message Hash

---

#### 16. Generate Message Report

Creates a report showing:

* Message Hash
* Recipient
* Message Content


