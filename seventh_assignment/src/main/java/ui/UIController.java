package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class UIController {
    @FXML
    protected void onStartChatButtonClick() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("GroupChatUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);
    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("mainPageUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);
    }
}