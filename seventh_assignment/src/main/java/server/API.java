package server;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class API implements Runnable{
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public API(Socket socket) {
        this.socket = socket;
        try {
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readRequest(){
        try {
            JSONObject request = new JSONObject(bufferedReader.readLine());
            if (request.get("request").equals("download")){
                String filename = request.getString("filename");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(!socket.isClosed()){

        }
    }
}
