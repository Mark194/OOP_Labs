package main.message;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class MessageAlert implements Message{

    private static final String FILE = "file:";
    private static final String FOLDER = "\\resources\\image\\";

    File f = new File(".");
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(new StringBuilder(FILE)
                .append(f.getAbsolutePath())
                .append(FOLDER)
                .append("error.png").toString()));
        alert.showAndWait();
    }

    public void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(new StringBuilder(FILE)
                .append(f.getAbsolutePath())
                .append(FOLDER)
                .append("message2.png").toString()));
        alert.showAndWait();
    }

    public Alert showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, null, ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(new StringBuilder(FILE)
                .append(f.getAbsolutePath())
                .append(FOLDER)
                .append("confirmation.png").toString()));
        alert.showAndWait();
        return alert;
    }


}
