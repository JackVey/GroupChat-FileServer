package client;

import javafx.scene.layout.VBox;
import ui.ChatController;

import java.io.*;
import java.net.Socket;

// Client Class
public class Client {
    public Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;
    private VBox vbox_messages;
    public Client(Socket socket, String userName, VBox vBox){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = userName;

            this.vbox_messages = vBox;

            //passing the username via socket to client handler
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            closeEverything();
        }
    }

    public void sendMessage(String messageToSend){
        try {
            if (!socket.isClosed()){
                bufferedWriter.write(userName + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything();
        }
    }

    public void listenForMessages(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromClient;

                while (socket.isConnected()){
                    try {
                        messageFromClient = bufferedReader.readLine();
                        //TODO -> here i have to show it in GUI
                        ChatController.addMessageToVbox(messageFromClient, vbox_messages);
                        System.out.println(messageFromClient);
                    }
                    catch (IOException e){
                        closeEverything();
                    }
                }
                closeEverything();
                System.out.println("Client" + userName + "has stopped");
            }
        }).start();
    }

    public void closeEverything(){
        sendMessage("[CODE:404]" + userName + " has left the chat!");
        try {
            bufferedWriter.close();
            bufferedReader.close();
            socket.close();
            System.out.println("[SERVER]: Client " + userName + " has disconnected from chat");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

    }
}