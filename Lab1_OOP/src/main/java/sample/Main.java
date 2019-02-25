package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import screen.saver.StartScreen;

public class Main extends Application {
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getClassLoader().getResource("fxml/main.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        primaryStage.setTitle("Лабораторна работа №1-2");
        primaryStage.setOnCloseRequest(event -> controller.close());
        primaryStage.getIcons().add(new Image(
                Main.class.getClassLoader().getResource("image/sorting.png").toString()));
        primaryStage.setScene(new Scene(parent, 486, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, StartScreen.class, args);
    }

    @Override
    public void init() throws Exception{
        this.notifyPreloader(new Preloader.ProgressNotification(0.0));
        Thread.sleep(5000);
        this.notifyPreloader(new Preloader.ProgressNotification(1.0));
    }
}
