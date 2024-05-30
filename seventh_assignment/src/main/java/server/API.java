package server;

import file.FileHandler;
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

    private void handleRequest(){
        String filename = "";
        JSONObject request;
        try {
            request = new JSONObject(bufferedReader.readLine());
            if (request.get("request").equals("download")){
                filename = request.getString("filename");
            }
            if (!filename.isEmpty() || !filename.isBlank()){
                if(FileHandler.checkIfFileExist(filename)){
                    sendResponse("file is ready to send");
                }
            }
            request = new JSONObject(bufferedReader.readLine());
            if (request.get("request").equals("send the file")){
                sendFile(filename);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void sendResponse(String response){
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("response", response);
        try {
            bufferedWriter.write(jsonResponse.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendFile(String filename){
        byte[] fileNameBytes = FileHandler.readFileNameByte(filename);
        byte[] fileContentBytes = FileHandler.readFileContentByte(filename);

        try {
            dataOutputStream.writeInt(fileNameBytes.length);
            dataOutputStream.write(fileNameBytes);

            dataOutputStream.writeInt(fileContentBytes.length);
            dataOutputStream.write(fileContentBytes);
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
