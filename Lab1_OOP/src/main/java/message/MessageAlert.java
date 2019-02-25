package message;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Main;

import java.io.File;

public class MessageAlert implements Message{

    File f = new File(".");
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Main.class.getClassLoader()
                .getResource("image/error.png").toString()));
        alert.showAndWait();
    }

    public void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Main.class.getClassLoader()
                .getResource("image/message2.png").toString()));
        alert.showAndWait();
    }

    public Alert showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, null, ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Main.class.getClassLoader()
                .getResource("image/confirmation.png").toString()));
        alert.showAndWait();
        return alert;
    }


}
