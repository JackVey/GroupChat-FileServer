package ui;

import client.Client;
import file.FileHandler;
import javafx.application.Platform;
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
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

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
    protected void onBackButtonClick() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("mainPageUI.fxml")), 1280, 720);
        UIApplication.stage.setScene(scene);
        if (client != null) {
            client.closeEverything();
        }
    }
    public static void addMessageToVbox(String messageFromClient, VBox vBox){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5, 5, 5, 5));


        if (messageFromClient.contains("[CODE:404]")) {
            hBox.setAlignment(Pos.CENTER);

            TextFlow textFlow = new TextFlow(new Text(messageFromClient.substring(messageFromClient.indexOf("[CODE:404]") + 10)));

            textFlow.setId("server_message");

            textFlow.setPadding(new Insets(10, 15, 10, 15));
            hBox.getChildren().add(textFlow);
        }
        else {
            hBox.setAlignment(Pos.CENTER_LEFT);

            TextFlow textFlow = new TextFlow(new Text(messageFromClient));

            textFlow.setId("others_messages");

            textFlow.setPadding(new Insets(10, 15, 10, 15));
            hBox.getChildren().add(textFlow);
        }


        Platform.runLater(() -> vBox.getChildren().add(hBox));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String username = MainPageController.getUserName();
        try {
            Socket socket = new Socket("localhost", 8888);
            client = new Client(socket, username, vbox_messages);
            client.listenForMessages();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Error in creating server");
        }

        vbox_messages.heightProperty().addListener((observable, oldValue, newValue) -> chat_pane.setVvalue((Double) newValue));

        send_button.setOnAction(actionEvent -> {
            String messageToSend = chat_field.getText();
            if (!messageToSend.isEmpty()){
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 5));

                TextFlow textFlow = new TextFlow(new Text(messageToSend));

                textFlow.setId("my_messages");

                textFlow.setPadding(new Insets(10, 15, 10, 15));

                hBox.getChildren().add(textFlow);
                vbox_messages.getChildren().add(hBox);

                client.sendMessage(messageToSend);

                chat_field.clear();
            }
        });

        int numberOfTheMessagesToShow = 50;
        ArrayList<String> messages = FileHandler.readMessages();
        if (messages.size() < numberOfTheMessagesToShow){
            for (String i : messages){
                addMessageToVbox(i, vbox_messages);
            }
        }else {
            for (int i = messages.size() - numberOfTheMessagesToShow; i <= messages.size() - 1; i++){
                addMessageToVbox(messages.get(i), vbox_messages);
            }
        }
    }

}