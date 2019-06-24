package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.Database;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gelnica National Banking Inc. ©");
        primaryStage.setScene(new Scene(root, 420, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {


        launch(args);
    }
}
