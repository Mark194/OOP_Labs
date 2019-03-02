package sample;


import form.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    public void loadGraphicsEditor(){
        Main main = new Main();
        try {
            main.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
