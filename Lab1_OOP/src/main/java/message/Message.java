package message;

import javafx.scene.control.Alert;

public interface Message {
    void showError(String message);
    void showInformation(String message);
    Alert showConfirmation(String message);
}
