package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}