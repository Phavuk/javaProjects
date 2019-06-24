package sample;

import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfterLog implements Initializable {
    public Button logout;
    public Label afterLogName;
    public Label afterLogSurname;
    public Label afterLogPosition;

    public Label clientName;
    public Label clientSurname;
    public Label clientMail;

    public Label accountIDField;
    public Label accNumField;
    public Label amountField;

    public Label cardRslt;
    public Tab tabCards;
    public Tab tabIB;

    public Label newPass;
    public Label ibPassRslt;

    public Label pinHeader;
    public Label pinRslt;

    ArrayList<Client> clientList;
    ArrayList<Account> accList;
    ArrayList<Card> cardList;

    Globals generated = new Globals();

    public void setupAfterlog(Employee person, String position) {

        String name = person.getFirstname();
        String surname = person.getSurname();

        afterLogName.setText(name);
        afterLogSurname.setText(surname);
        afterLogPosition.setText(position);


    }


    public void clients(ActionEvent actionEvent) {

    }

    public void newClient(ActionEvent actionEvent) {

    }
    @FXML
    ComboBox<String> combobox;
    @FXML
    ComboBox<String> comboboxAcc;
    @FXML
    ComboBox comboboxCards;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillDropdown();
    }


    public void fillDropdown(){
        try {
            System.out.println("Filled successfully");
            clientList = Globals.db.selectClients();
            ObservableList<String> oblist = FXCollections.observableArrayList();
            for (int i = 0; i < clientList.size(); i++) {
                oblist.add(clientList.get(i).getFirstname() + " " + clientList.get(i).getLastname());
            }
            combobox.setItems(oblist);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void clientInfo() throws SQLException {

        Client selectedUser=Globals.db.selectClientInfo(getIDClient());
        System.out.println(selectedUser);
        clientName.setText(selectedUser.getFirstname());
        clientSurname.setText(selectedUser.getLastname());
        clientMail.setText(String.valueOf(selectedUser.getMail()));
        fillDropdownAccounts();
    }
    public int getIDAccount() {

        return accList.get(comboboxAcc.getSelectionModel().getSelectedIndex()).getIDacc();

    }

    public int getIDofSelected() {
        System.out.println(combobox.getSelectionModel().getSelectedIndex());
        return combobox.getSelectionModel().getSelectedIndex();
    }

    public int getIDClient() {
        System.out.println(clientList.get(combobox.getSelectionModel().getSelectedIndex()).getID());
        return clientList.get(combobox.getSelectionModel().getSelectedIndex()).getID();
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }

    public void fillDropdownAccounts(){
        accList = Globals.db.selectAccountsToList(getIDClient());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<accList.size();i++) {
            oblist.add(accList.get(i).getIDacc() + " " + accList.get(i).getAccNum());
        }

        comboboxAcc.setItems(oblist);
    }

    public void AccInfo() throws SQLException {

        Account acc = Globals.db.selectAccInfo(getIDAccount());
        accountIDField.setText(String.valueOf(acc.getIDacc()));
        accNumField.setText(acc.getAccNum());
        amountField.setText(String.valueOf(acc.getMoney()));
        tabIB.setDisable(false);
        tabCards.setDisable(false);
        fillDropdownCards();
    }




    public void createUser(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createUser.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage3 = new Stage();
            stage3.setTitle("Create User");
            stage3.setScene(new Scene(root1));
            stage3.initModality(Modality.APPLICATION_MODAL);
            stage3.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillDropdownCards(){
        cardList = Globals.db.clientCards(getIDAccount());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<cardList.size();i++) {
            oblist.add(cardList.get(i).getId() + " " + cardList.get(i).getIda() + " yy/mm " +cardList.get(i).getExpireM() +"/"+ cardList.get(i).getExpireY());
        }

        comboboxCards.setItems(oblist);
        System.out.println(comboboxCards.getItems().size());
    }

    public int getIDcard() {
        return cardList.get(comboboxCards.getSelectionModel().getSelectedIndex()).getId();
    }

    public void unblockBtnAction(ActionEvent actionEvent) {
        Globals.db.unblockCard(getIDClient(),getIDAccount());
    }

    public void blockBtnAction(ActionEvent actionEvent) {
        Globals.db.blockCard(getIDClient(),getIDAccount());
    }

    public void addCardAction(ActionEvent actionEvent) {
        String PIN = generated.generatePIN();
        Globals.db.createCard(getIDAccount(),PIN);
        fillDropdownCards();
        cardRslt.setText("New Card Added");
        pinHeader.setText("New PIN is:");
        pinRslt.setText(PIN);
    }
}
