package ui;

import client.Client;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class ChatController implements Initializable {
    @FXML
    private Button send_button;
    @FXML
    private ScrollPane chat_pane;
    @FXML
    private VBox vbox_messages;
    @FXML
    private TextField chat_field;
    private Client client;
    @FXML
    protected void onStartChatButtonClick() throws IOException {
        String username = getInputFromTheBox();
        System.out.println(username);
        try {
            Socket socket = new Socket("localhost", 8888);
            client = new Client(socket, username);
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Error in creating server");
        }

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("GroupChatUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);

        vbox_messages.heightProperty().addListener((observable, oldValue, newValue) -> chat_pane.setVvalue((Double) newValue));

        //TODO -> have to make a listener to my client handler

        send_button.setOnAction(actionEvent -> {
            String messageToSend = chat_field.getText();
            if (!messageToSend.isEmpty()){
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));

                TextFlow textFlow = new TextFlow(new Text(messageToSend));
                textFlow.setId("my_messages");

                textFlow.setPadding(new Insets(5, 10, 5, 10));
                //TODO -> have to set textFlow color

                hBox.getChildren().add(textFlow);
                vbox_messages.getChildren().add(hBox);

                //TODO -> have to broad cast the 'messageToSend'

                chat_field.clear();
            }
        });

    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("mainPageUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);
    }

    public static void addMessageToVbox(String messageFromClient, VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        TextFlow textFlow = new TextFlow(new Text(messageFromClient));
        textFlow.setId("my_messages");

        textFlow.setPadding(new Insets(5, 10, 5, 10));
        //TODO -> have to set textFlow color

        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }

    public String getInputFromTheBox(){
        String[] username = new String[1];
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input Dialog");
        td.setHeaderText("Please enter your name:");
        td.showAndWait().ifPresent(new Consumer<String>() {
            @Override
            public void accept(String s) {
                username[0] = s;
            }
        });
        return username[0];
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}