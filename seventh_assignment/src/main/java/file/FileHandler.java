package file;

import org.json.JSONArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileHandler {

    private static final String MESSAGES_FILE_ADDRESS = "C:\\Users\\varin\\Documents\\Intellij\\Seventh-Assignment-Socket-Programming" +
            "\\seventh_assignment\\src\\main\\java\\file\\Files\\messages.txt";
    private static final String DATA_FILE_ADDRESS = "C:\\Users\\varin\\Documents\\Intellij\\Seventh-Assignment-Socket-Programming" +
            "\\seventh_assignment\\src\\main\\java\\server\\data";
    private static final Lock lock = new ReentrantLock();
    public static synchronized void writeMessage(String messageToWrite){
        lock.lock();
        JSONArray jsonArray;
        File messagesFile = new File(MESSAGES_FILE_ADDRESS);

        try {
            Scanner scanner = new Scanner(messagesFile);
            jsonArray= new JSONArray(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (messageToWrite.contains("[CODE:404]")){
            jsonArray.put( messageToWrite.substring( messageToWrite.indexOf("[CODE:404]") ) );
        }else {
            jsonArray.put(messageToWrite);
        }

        try {
            FileWriter messageWriter = new FileWriter(messagesFile);
            messageWriter.write(jsonArray.toString());
            messageWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
    }

    public static synchronized ArrayList<String> readMessages(){
        lock.lock();
        ArrayList<String> messages = new ArrayList<>();

        JSONArray jsonArray;
        File messagesFile = new File(MESSAGES_FILE_ADDRESS);

        try {
            Scanner scanner = new Scanner(messagesFile);
            jsonArray = new JSONArray(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            messages.add(jsonArray.get(i).toString());
        }
        lock.unlock();
        return messages;
    }

    public static boolean checkIfFileExist(String filename){
        File fileToCheck = new File(DATA_FILE_ADDRESS);
        if (fileToCheck.exists()){
            return true;
        }
        return false;
    }
}
