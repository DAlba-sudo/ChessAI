package sample;

import backend.ui.UIManager;
import frontend.StartUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private UIManager uiManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        StartUI home = new StartUI();

        uiManager = new UIManager(home, primaryStage);
        UIManager.getCurrent_ui().getRoot().setMaxWidth(Double.MAX_VALUE);
        Scene scene = new Scene(UIManager.getCurrent_ui().getRoot(), 500, 600);

        primaryStage.setScene(scene);
        UIManager.displayUI(UIManager.getCurrent_ui());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
