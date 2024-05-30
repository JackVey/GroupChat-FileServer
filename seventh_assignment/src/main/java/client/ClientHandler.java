package client;


import file.FileHandler;
import server.Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public String userName;
    private ArrayList<ClientHandler> clientHandlers;
    public ClientHandler(Socket socket, ArrayList<ClientHandler> clientHandlers){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //the username will be passed here from client constructor
            this.clientHandlers = clientHandlers;
            this.userName = bufferedReader.readLine();
//            addClientHandler(this);
            //TODO -> have to broad cast that this user has entered the chat
            broadcastMessage("[CODE:404]" + userName + " has joined the chat!");

        } catch (Exception e) {
            closeEverything();
        }
    }

//    public void addClientHandler(ClientHandler clientHandler){
//        clientHandlers.add(clientHandler);
//    }

    public void broadcastMessage(String messageToSend){
        for (ClientHandler clientHandler : clientHandlers){
            try {
                if (!clientHandler.equals(this)){
                    if (clientHandler.socket.isConnected() && !clientHandler.socket.isClosed()) {
                        clientHandler.bufferedWriter.write(messageToSend);
                        clientHandler.bufferedWriter.newLine();
                        clientHandler.bufferedWriter.flush();
                    }
                }
            }
            catch (IOException e){
                closeEverything();
            }
        }

        FileHandler.writeMessage(messageToSend);
    }

    public synchronized void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("[CODE:404]"+ userName + " has left the chat!");
    }

//    public synchronized static void removeClientHandler(Client client){
//       for (int i = 0; i < clientHandlers.size(); i++){
//            if (clientHandlers.get(i).socket.equals(client.socket)){
//                clientHandlers.remove(i);
//            }
//        }
//    }

    public void closeEverything(){
        removeClientHandler();
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

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()){
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            }
            catch (IOException e){
                closeEverything();
                break;
            }
        }
        closeEverything();
        System.out.println("Client Handler has stopped");
    }
}
