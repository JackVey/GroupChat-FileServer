package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController  {
    @FXML
    protected TextField name_field;
    @FXML
    protected TextArea text_message;
    private static String userName;

    public static synchronized String getUserName() {
        return userName;
    }

    @FXML
    protected void onStartChatButtonClick() throws IOException {

        userName = name_field.getText();
        if (userName.isBlank() || userName.isEmpty()){
            text_message.setText("This field cannot be empty\nplease enter your username");
            text_message.setStyle("-fx-text-fill: red");
        }else {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("GroupChatUI.fxml")), 1280, 720);
            UIApplication.stage.setScene(scene);
        }
    }
    @FXML
    protected void onDownloadFileButtonClick() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DownloadPageUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);
    }
}
