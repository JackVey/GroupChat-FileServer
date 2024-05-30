# **Group Chat and File Server Java Application with JavaFX**


[![JackVey][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

## Overview
This project aims to create a simple **Group Chat Application** that uses **TCP (Transmission Control Protocol)** for communication. The application will allow clients to connect, exchange messages, and download files within a server. Here are the main features:

1. **Client Authentication**: Users have to enter a username before accessing the Group Chat App.
2. **Server-Client Architecture**: Messages from clients go to the server, which then distributes them to all connected clients.
3. **Chat history**: Clients who has joined the, can see 50 previous messages.
4. **Server logs**: Server creates logs tracking what user is doing

### Bulit with
[![Java][Java.badge]][Java-url]
[![CSS][CSS.badge]][CSS-url]

## Project Structure

![UML Diagram][UML.image]

- **Client.java**: Represents the client-side of the chat application.
- **Server.java**: Represents the server-side of the application.
- **ClientHandler.java**: Represents the main chat program of chat which will run in seperate thread for each user.
- **ChatController.java**: Controller class for chat page.
- **FileHandler.java**: Represents the file handler of the application.
- **API.java**: Represents the API of the application.
- **UIApplication.java**: Represents the main class of the application.
- **MainPageController.java**: Controller class for main page.
- **DownloadPageController.java**: Controller class for main page.
- **DownloadClient.java**: Represents the main download program of chat which will run in seperate thread for each user.

## Image of program
**Main page**

![mainPage.image]

**Chat page**

![chat.image]

**Download page**

![downloadPage.image]

## How to use?
1. Clone the project using following code
   - `git clone https://github.com/JackVey/GroupChat-FileServer.git`
2. **IDE Setup**: Use IntelliJ or any other IDE of your choice. It is recommended to use IntelliJ along Gradle.
3. Install java fx and add its lib folder to the projects libraries
4. Use the configuration placed in the main folder of the project named `UIApplication.run.xml`
5. Edit the final addresse in the `FileHandler.java` and `DownloadClient.java` to your local addresse
```java
    private final String DOWNLOADED_FILE_ADDRESS = "C:\\Users\\varin\\Documents\\Intellij\\Seventh-Assignment-Socket-Programming\\seventh_assignment\\download\\";
    private static final String MESSAGES_FILE_ADDRESS = "C:\\Users\\varin\\Documents\\Intellij\\Seventh-Assignment-Socket-Programming" +
            "\\seventh_assignment\\src\\main\\java\\file\\Files\\messages.txt";
    private static final String DATA_FILE_ADDRESS = "C:\\Users\\varin\\Documents\\Intellij\\Seventh-Assignment-Socket-Programming" +
            "\\seventh_assignment\\src\\main\\java\\server\\data\\";
```
6. First Start the server and then the UIApplication
7. Enjoy!

## License
Distributed under the MIT License. See `LICENSE.txt` for more information.

## Contact

My Gmail - varin.rain3@gmail.com


> [!IMPORTANT]
> Do not skip how to use steps!.
> [!WARNING]
> You are going to be unable to run the application if you do not import java fx into project properly.

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/JackVey/GroupChat-FileServer.svg?style=for-the-badge&logo=github&logoColor=violet
[contributors-url]: https://github.com/JackVey/GroupChat-FileServer/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/JackVey/GroupChat-FileServer?style=for-the-badge&logo=github&logoColor=violet&color=orange
[forks-url]: https://github.com/JackVey/GroupChat-FileServer/forks
[stars-shield]: https://img.shields.io/github/stars/JackVey/GroupChat-FileServer.svg?style=for-the-badge&logo=github&logoColor=violet
[stars-url]: https://github.com/JackVey/GroupChat-FileServer/stargazers
[issues-shield]: https://img.shields.io/github/issues/JackVey/GroupChat-FileServer.svg?style=for-the-badge&logo=github&logoColor=violet&color=yellow
[issues-url]: https://github.com/JackVey/GroupChat-FileServer/issues
[license-shield]: https://img.shields.io/github/license/JackVey/GroupChat-FileServer?style=for-the-badge&color=purple
[license-url]: https://github.com/JackVey/GroupChat-FileServer/blob/develop/LICENSE
[UML.image]: https://github.com/JackVey/GroupChat-FileServer/assets/161158007/abf5688e-ef30-413a-b095-6b1e5a8144cc
[Java.badge]: https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white
[CSS.badge]: https://img.shields.io/badge/CSS-1572B6?logo=css3&logoColor=fff
[Java-url]: https://www.java.com/
[CSS-url]: https://www.w3.org/Style/CSS/Overview.en.html
[mainPage.image]: https://github.com/JackVey/GroupChat-FileServer/assets/161158007/d3c0b73c-4129-4720-a1ce-4a0c4d902243
[downloadPage.image]: https://github.com/JackVey/GroupChat-FileServer/assets/161158007/e16bec51-d791-4ae6-b3a8-9f84c0efd7e1
[chat.image]: https://github.com/JackVey/GroupChat-FileServer/assets/161158007/c1c52b0d-bc30-43f4-a245-a93c619ba6b9
