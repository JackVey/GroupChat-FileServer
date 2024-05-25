package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController  {
    @FXML
    protected void onStartChatButtonClick() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("GroupChatUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);
    }
    @FXML
    protected void onDownloadFileButtonClick() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("DownloadPageUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);
    }
}
