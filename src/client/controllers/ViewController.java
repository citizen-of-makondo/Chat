package client.controllers;

import client.models.Network;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class ViewController {

    @FXML
    private TextArea textAreaMessage;

    @FXML
    private TextArea chatHistory;

    @FXML
    private Button buttonSendMessage;

    private final ObservableList<String> observableList = FXCollections.observableArrayList();

    private Network network;

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void initialize() {
        buttonSendMessage.setOnAction(actionEvent -> ViewController.this.sendMessage());
    }

    public void sendMessage() {
        addToChat(textAreaMessage.getText());
    }

    public void addToChat(String message) {
        chatHistory.appendText(message);
        chatHistory.appendText(System.lineSeparator());
        textAreaMessage.clear();
        try {
            network.getOut().writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сообщения");
        }
    }

    public void sendMessageByEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            addToChat(textAreaMessage.getText());
        }
    }
}
