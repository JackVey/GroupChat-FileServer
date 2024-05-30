package server;

import client.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// Server Class
public class Server {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void startChatServer(){
        new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    Socket socket = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(socket, clientHandlers);
                    clientHandlers.add(clientHandler);
                    Thread thread = new Thread(clientHandler);
                    thread.start();
                    System.out.println("[SERVER]: Client " + clientHandler.userName + " has disconnected to chat");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void startFileServer(){
        new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    Socket socket = serverSocket.accept();
                    API api = new API(socket);
                    System.out.println("[SERVER]: A client has disconnected to file server");
                    Thread thread = new Thread(api);
                    thread.start();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void closeEverything(){
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket chatSocket = new ServerSocket(8888);
        ServerSocket fileSocket = new ServerSocket(7777);
        Server chatServer = new Server(chatSocket);
        Server fileServer = new Server(fileSocket);
        chatServer.startChatServer();
//        fileServer.startFileServer();
    }
}