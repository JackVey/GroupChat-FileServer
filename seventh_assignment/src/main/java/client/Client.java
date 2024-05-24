package client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

// Client Class
public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;
    public Client(Socket socket, String userName){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = userName;
        } catch (IOException e) {
            closeEverything();
        }
    }

    public void sendMessage(){
        //TODO -> gotta get the message from the text field and send it here
        try {
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()){
                String messageToSend = scanner.nextLine();
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
                        System.out.println(messageFromClient);
                    }
                    catch (IOException e){
                        closeEverything();
                    }
                }
            }
        }).start();
    }

    public void closeEverything(){
        try {
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (socket != null){
                socket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter you name: ");
        // TODO -> gotta get client username first
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 8888);
        Client client = new Client(socket, username);
        client.listenForMessages();
        client.sendMessage();
    }
}