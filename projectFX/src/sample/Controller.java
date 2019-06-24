package sample;

import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    public TextField txtOutput;
    public TextField first;
    public TextField second;
    public CheckBox check;
    public TextField change;

    public void btnClick(ActionEvent actionEvent) {
        System.out.println("it is working");
        txtOutput.setText("noNazdar");
    }

    public void btnCount(ActionEvent actionEvent) {
        int a = Integer.parseInt(first.getText());
        int b = Integer.parseInt(second.getText());
        int result;
        result = a+b;
        first.getText();
        second.getText();
        txtOutput.setText(String.valueOf(a)+"+"+String.valueOf(b)+"="+result);

    }


    public void btnDate(ActionEvent actionEvent) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
        System.out.println(dateFormat.format(date));
        txtOutput.setText(dateFormat.format(date));
    }

    public void btnShow(ActionEvent actionEvent) {
        if(check.isSelected())
            txtOutput.setVisible(true);

        else
            txtOutput.setVisible(false);

    }

    public void btnChange(MouseEvent mouseEvent) {
        if (true)
            txtOutput.setFont(Font.font("Roboto",20));
         else
             txtOutput.setFont(Font.font("Verdana",25));


    }

    public void btnMove(MouseEvent mouseEvent) {



    }
}
