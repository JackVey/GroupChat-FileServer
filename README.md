# Socket Programming Assignment - Group Chat(💬) + file Transfer (📁)

## Table of Contents 🍽️

1. [Introduction](#introduction-)
2. [Objectives](#objectives-)
3. [Assignment Overview](#assignment-overview-)
   - [Task 1: Group Chat (Sending Text Messages)](#task-1-group-chat-sending-text-messages-)
   - [Task 2: file Transfer (Downloading Text Files from the Server)](#task-2-file-transfer-downloading-text-files-from-the-server-)
        - [Application's `Client-Server API`](#applications-client-server-api-)
5. [Notes](#notes-)
6. [Getting Started](#getting-started-)
7. [Bonus Features](#bonus-features-)
8. [Submission](#submission-)
9. [Troubleshooting](#troubleshooting-)
10. [Resources for Further Studying](#some-resources-for-further-studying-)

## Introduction 📚
Welcome to your 7th assignment! This assignment focuses on creating a command-line interface (CLI) application using **socket programming** concepts in Java. The objective is to create a robust and efficient mechanism for sending text messages between multiple clients and transferring text files from the server side to the client side.

## Objectives 🔎

- Reviewing the concepts of socket programming
- Creating a local server with the ability to handle incoming requests from clients
- Enabling clients to send massages in the group chat or download text files from the server
- Designing a request-response mechanism from scratch

## Assignment Overview 👀   
The assignment comprises two main tasks as following:

### Task 1: Group Chat (Sending Text Messages) 💬
In this task, the server will handle requests from clients to send text messages in a group chat. Each client shall be able to read the name of the sender of the massage with the message content in their own terminal. (Make sure that you are using sockets to transfer these massages between the clients and the server)

This task is something like the **massage broadcasting** example you had in you TA's session, so to make it a bit different you are urged to implement a **chat history** feature as well.

So this means that if a new client is connected to the server, he must be able to see **all the previous** chats in the group as well as the new massages being sent after his connection being stablished with the server. (**BONUS TASK: How to implement this feature in a way that a new member of the group chat can only see a limited number of the previous chats? That is, only $n$ previous massages where n is an arbitrary fix number!**)

#### Task 1 in Summery 💊
- designing server and client side of the application in a way that users can comminate with each other through a group chat with the feature that newly joined members can see either a portion or all of the previous chats (massages sent before them joining the group chat)


### Task 2: file Transfer (Downloading Text Files from the Server) 📁
In this task, you will extend the functionality of the application you designed to allow clients to download text files from the server.Thus the client should be able to view a list of the available files on the server and select one for download. Once the file is downloaded, it should be **copied** (**NOT cut**) to a directory related specifically to the client who sent the request to download the file. (In other words, the file must be transferred though a socket from the server side to the client side can be saved somewhere on the client side)

Also, you must design the **client-server API** of this project which is explained below: 
#### Application's `Client-Server API` ⚠️
     This part is one of the most important features of this project
To enable effective communication between the client and server sides of the application, a well-defined and predetermined interaction mechanism is implemented. This is achieved through a series of Request-Response interactions, where the client sends requests to the server, conveying specific instructions or queries. The server processes these requests and generates appropriate responses, providing the requested information or indicating the outcome of the actions. By employing this structured request-response approach, the client and server can seamlessly exchange data and instructions, ensuring synchronized communication and facilitating efficient coordination between the two components.

The designing of the API can be broken down into 2 main parts:

##### Request

A request must have these features:

- A request is sent from the client to the Server over the network
- You should create different types of requests for different actions
- A client can either request to see the list of available files, to download a file or to enter the group chat
- It is up to you to design the request's format. A request can be a JSON string which is easy to send on a socket, a string or any other data that can be sent through a server socket.


##### Response

A response must have these features:

- A response is sent from the server to the client over the network
- You should create different response types corresponding to the received Request
- Attach appropriate data to the response based on the request. A response might contain the data a user has requested, or it might just be a boolean indicating the result of a previously sent request
- Again, it is up to you to design the response's format. Similar to a request, a response can also be a JSON string, a string or whatever you think suits best

#### Task 2 in Summery 🔫
Absolutely not! I shall not embark on the arduous task of concocting a summary for this  masterpiece! It's like attempting to tame a wild unicorn, a true feat of mythical proportions! Simply revisit the entire segment in case you stumble upon any predicaments, and may the forces of academic fortune be ever in your favor!

## Notes 📝
- Each request received from the client must be answered with a response from the server
- Each client request must receive a corresponding server response.
- Upon client connection, the server prompts the client to choose between file download or group chat options, utilizing the request-response system.
- For file downloads, the server employs the **file handler** to provide a list of available files and ensures controlled transfer upon client selection.
- Group chat functionality is facilitated by a **chat handler**, processing and broadcasting messages to connected clients.
- The request-response system establishes an organized communication flow, coordinating file downloads and group chat.


## Getting Started 🏎️
To begin working on the assignment, follow these steps:

1. Fork this repository. (**DO NOT clone it! Fork it first**)

2. Clone the forked repository from your own GitHub account to your local machine.

3. Make your own branch

4. The repository contains a README file and several text files (reachable through the path: `seventh_assignment/src/main/Server/data`), each storing the lyrics of a song.

5. Implement the client-side and server-side code as per the assignment requirements. Refer to the provided code comments and any additional guidelines mentioned in the code.

## Bonus Features 🎉🎁
Consider implementing the following bonus features to enhance your application:

- User Interface: Add a graphical user interface (GUI) using Java's Swing or JavaFX libraries to improve the user experience.

- Progress Tracking: Provide a way for clients to monitor the progress of file transfers, allowing them to track the download progress. If you are familiar with JavaFX, consider implementing this with JavaFX.

- Although writing a documentation for this assignment is not mandatory (and won't earn extra points), it will help mentors understand your project's progress. Additionally, it will make your code more comprehensible for others if you decide to keep this assignment on your GitHub account.

**Of course implementing any other features are welcomed! The limit is your imagination!**

## Submission 🚀
When you have completed the assignment, please prepare your submission as follows:

1. Include all relevant Java source code files for the client and server components, along with the text files.

2. (Optional) Provide any additional documentation or notes explaining your implementation details, design choices, and testing approach.

3. (Optional) If you implemented a GUI, include any necessary resources or instructions for successfully running the GUI.

4. Push your final code to the forked repository and send a PR (Pull Request) to your mentor. The submission deadline is **May 24th (Khordad 4th)**.

## Troubleshooting 🤔
If you encounter any issues or have questions regarding the assignment, please reach out to your mentors for assistance.

## Some Resources for Further Studying 🤓
Consider watching the following videos and reading the blogs to grasp a better understanding of how socket programming in Java works:
- [Java Socket Programming Client Server Messenger by WittCode](https://youtu.be/gchR3DpY-8Q?si=dSyRSnFmB6fLIpej)
- [Java Socket Programming - Multiple Clients Chat by WittCode](https://www.youtube.com/watch?v=gLfuZrrfKes&t=739s)
- [An IBM documentation on the **request-response design pattern**](https://developer.ibm.com/articles/awb-request-response-messaging-pattern-introduction/)
- [A medium article on **request-response design pattern**](https://ritikchourasiya.medium.com/request-response-a-deep-dive-into-backend-communication-design-pattern-47d641d9eb90)
- [A brief intro to request-response design pattern](https://youtu.be/TD1wxsJYAP0?si=G5Xq2WM5uST0e53E)

A high quality place to learn how to transmit data between sockets:
  - [InputStream](https://jenkov.com/tutorials/java-io/inputstream.html)
  - [OutputStream](https://jenkov.com/tutorials/java-io/outputstream.html)
  - [DataInputStream](https://jenkov.com/tutorials/java-io/datainputstream.html)
  - [DataOutputStream](https://jenkov.com/tutorials/java-io/dataoutputstream.html)
  - [FileInputStream](https://jenkov.com/tutorials/java-io/fileinputstream.html)
  - [FileOutputStream](https://jenkov.com/tutorials/java-io/fileoutputstream.html)
  - [PrintWriter](https://jenkov.com/tutorials/java-io/printwriter.html)

Some links to learn how to work and create json in java: (You are highly recommended to use `Jackson` library or `JSON.org`, since other libraries seem to be deprecated by now!)
  -  [Working with jackson](https://jenkov.com/tutorials/java-json/jackson-installation.html)
  - [JSON.org](https://www.baeldung.com/java-org-json)

Best regards<br>
Shayan and Mohammad 😎