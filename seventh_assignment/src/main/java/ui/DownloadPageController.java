package ui;

import client.DownloadClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class DownloadPageController implements Initializable {
    @FXML
    private ListView files_list;
    @FXML
    private Label file_label;
    private DownloadClient downloadClient;
    @FXML
    protected void onBackButtonClick() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("mainPageUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);
    }
    @FXML
    public void onDownloadButtonClick() {
        String selectedFileName = "";
        selectedFileName = files_list.getSelectionModel().getSelectedItem().toString();
        downloadClient.sendRequest(selectedFileName);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] fileNames = {"all-of-me-john-legend.txt", "a-man-without-love-ngelbert-Hmperdinck.txt"
        , "birds-imagine-dragons.txt", "blinding-lights-the-weekend.txt", "dont-matter-to-me-drake.txt"
        , "feeling-in-my-body-elvis.txt", "out-of-time-the-weekend.txt", "something-in-the-way-nirvana.txt"
        , "why-you-wanna-trip-on-me-michael-jackson.txt", "you-put-a-spell-on-me-austin-giorgio.txt"};

        files_list.getItems().addAll(fileNames);

        try {
            Socket socket = new Socket("localhost", 7777);
            downloadClient = new DownloadClient(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        files_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            file_label.setText(newValue.toString());
        });

    }
}
