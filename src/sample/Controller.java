package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Controller {

    @FXML
    private TextArea textAreaMessage;

    @FXML
    private ListView listViewChat;

    private final ObservableList<String> observableList = FXCollections.observableArrayList();


    public void initialize() {
        listViewChat.setItems(observableList);
    }

    @FXML
    void sendMessage() {
        addToList();
    }

    private void addToList() {
        String textFromTextArea = textAreaMessage.getText().trim();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String message = timeStamp + ": " + textFromTextArea;
        if (!textFromTextArea.isBlank()) {
            observableList.add(message);
            textAreaMessage.clear();
            listViewChat.scrollTo(observableList.size());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Нельзя вводить пустой текст");
            alert.show();
        }
    }

    @FXML
    void sendMessageByEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            addToList();
        }
    }
}
