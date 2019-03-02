package form;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("fxml/form.fxml"));
        Parent parent = loader.load();
        Controller controller = loader.getController();
        primaryStage.setTitle("Графический редактор");
        primaryStage.setScene(new Scene(parent,600, 400));
        primaryStage.show();
    }
}