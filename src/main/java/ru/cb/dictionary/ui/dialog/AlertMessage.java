package ru.cb.dictionary.ui.dialog;

import javafx.scene.control.Alert;

/**
 * Created by libragimov on 27.03.2018.
 */
public class AlertMessage {

    private Alert alert;

    public AlertMessage() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Проверьте правильность заполнения полей");
    }

    public void show(String message) {
        alert.setContentText(message);
        alert.show();
    }

}
