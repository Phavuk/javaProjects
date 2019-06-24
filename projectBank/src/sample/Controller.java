package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.Database;

import java.io.IOException;

public class Controller {
    public TextField username;
    public TextField pass;

    public void login(ActionEvent actionEvent) {

        String name = username.getText();
        String password = pass.getText();
        Database db = Database.getInstance();
        Employee person = db.compareEmployee(name,password);
        String position = Globals.db.getPosition(person);


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afterLog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("New");
            stage2.setScene(new Scene(root1));
            stage2.show();
            AfterLog acc;
            acc = fxmlLoader.getController();
            acc.setupAfterlog(person,position);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void pass(ActionEvent actionEvent) {

    }
}
