package screen.saver;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

public class StartScreen extends Preloader {
    private Stage stage;
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println();
        this.stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("fxml/screensaver.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root, 640, 400);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification scn)
    { if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
        stage.hide();
    }
    }
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        controller.getProgressLoad().setProgress(pn.getProgress());
    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info){
        Preloader.ProgressNotification ntf=(Preloader.ProgressNotification) info;
        if (ntf.getProgress()==1.0)
            stage.hide();
        else
            controller.getProgressLoad().setProgress(-1);
    }

}

