package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class DownloadPageController {
    @FXML
    protected void onBackButtonClick() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("mainPageUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);
    }
}
