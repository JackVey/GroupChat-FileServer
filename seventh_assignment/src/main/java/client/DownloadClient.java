package client;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class DownloadClient {
    private Socket socket;
    private final BufferedWriter bufferedWriter;
    private final DataInputStream dataInputStream;
    private final String DOWNLOADED_FILE_ADDRESS = "C:\\Users\\varin\\Documents\\Intellij\\Seventh-Assignment-Socket-Programming\\seventh_assignment\\download\\";

    public DownloadClient(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendRequest(String filename){
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("request", "download");
        jsonRequest.put("filename", filename);

        try {
            bufferedWriter.write(jsonRequest.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();

//            jsonRequest = new JSONObject(bufferedReader.readLine());
//
//            bufferedWriter.write(jsonRequest.toString());
//            bufferedWriter.newLine();
//            bufferedWriter.flush();
//
//            if (jsonRequest.get("response").equals("file is ready to send")){
//                jsonRequest.clear();
//                jsonRequest.put("request", "send the file");
//            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        receiveFile();
    }

    public void receiveFile(){
        try {
            int fileNameLength = dataInputStream.readInt();

            byte[] fileNameBytes = new byte[fileNameLength];
            dataInputStream.readFully(fileNameBytes, 0, fileNameLength);

            String fileNameString = new String(fileNameBytes);

            int fileContentLength = dataInputStream.readInt();

            byte[] fileContentBytes = new byte[fileContentLength];

            dataInputStream.readFully(fileContentBytes, 0, fileContentLength);
            saveFile(fileNameString, fileContentBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile(String filename, byte[] fileContent){
        File fileToDownload = new File(DOWNLOADED_FILE_ADDRESS + filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
            fileOutputStream.write(fileContent);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeEverything(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("request", "close");
            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.close();
            dataInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
