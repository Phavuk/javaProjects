package sample.database;

import javafx.scene.control.Alert;
import sample.*;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class Database {


    private static Database db = new Database();

    private Database(){

    }

    public static Database getInstance() {
        return db;
    }

    public static Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Globals.url, Globals.username, Globals.password);
            return connection;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public Client showClient(String Firstname, String Lastname){
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs;

        try {

            pst = conn.prepareStatement("SELECT * FROM Client");
            pst.setString(1,Firstname);
            pst.setString(2,Lastname);

            rs = pst.executeQuery();
            System.out.println(Firstname);
            System.out.println(Lastname);
            while (rs.next()) {
                System.out.println("It works");
                System.out.println("Finally");

                Client client = new Client(rs.getString("fname"), rs.getString("lname"),rs.getString("email"),rs.getInt("ID"));
                return client;
            }
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public Employee compareEmployee(String name, String pass){

        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs;

        try {

            pst = conn.prepareStatement("SELECT * FROM Employee INNER JOIN loginEmp ON Employee.ID = loginEmp.IDE WHERE login LIKE ? and password LIKE ?" );
            pst.setString(1,name);
            pst.setString(2,pass);
            rs = pst.executeQuery();
            while (rs.next()) {


                Employee person = new Employee(rs.getString("fname"), rs.getString("lname"),
                        rs.getInt("position"), rs.getInt("id"));
                return person;
            }
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getPosition(Employee emp){
        int position = emp.getPosition();
        String boss = "boss";
        String common = "common";

        if (position == 1) {
            return common;
        }
        else
        {
            return boss;
        }
    }
    public ArrayList<Client> selectClients() throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT * FROM client";
        ArrayList <Client> clientList = new ArrayList<>();

        try{
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()){
                Client client = new Client(rs.getString("fname"),rs.getString("lname"),rs.getString("email"),rs.getInt("ID"));
                clientList.add(client);
            }
            return clientList;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
  }


    public Client selectClientInfo(int id){
        Connection conn = getConnection();
        Client client = null;
        String query = "SELECT * FROM client where ID like ? ";

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(query);
            pst.setString(1,String.valueOf(id));
            rs = pst.executeQuery();

            while (rs.next()) {

                client = new Client(rs.getString("fname"),rs.getString("lname"),rs.getString("email"),
                        rs.getInt("ID"));

            }
            return client;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Account> selectAccountsToList(int id) {
        Connection conn = getConnection();
        ArrayList <Account> accounts = new ArrayList<>();
    try {              PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement("SELECT * FROM Account where IDC like ? ");
            pst.setInt(1,id);             rs = pst.executeQuery();
            while (rs.next()) {Account acc = new Account(rs.getInt("ID"),rs.getString("AccNum"),rs.getDouble("amount"),rs.getInt("IDC"));
            System.out.println(acc.getAccNum());
            accounts.add(acc);
            }
            }
            catch (SQLException e){e.printStackTrace();
    }         return accounts;
    }

    public Account selectAccInfo(int id){
        Connection conn = getConnection();
        Account acc = null;

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement("SELECT * FROM Account where id like ? ");
            pst.setInt(1,id);
            rs = pst.executeQuery();

            while (rs.next()) {

                acc = new Account(rs.getInt("id"),rs.getString("AccNum"),rs.getDouble("amount"),
                        rs.getInt("idc"));
            }
            return acc;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    private static final String queryEmployee = "SELECT * FROM Employee INNER JOIN loginEmp ON Employee.ID = loginEmp.IDE WHERE login LIKE ? and password LIKE ?";
    private static final String queryClient = "SELECT * FROM Client";
    private static final String queryAccount = "SELECT * FROM Account where IDC like ? ";
    private static final String queryClientInfo = "SELECT * FROM Client where ID like ? ";
    private static final String queryAccountInfo = "SELECT * FROM Account where id like ? ";
    private static final String queryCards = "SELECT * FROM card WHERE ida LIKE ?";
    private static final String queryInsertClient = "INSERT INTO client(fname,lname,email) VALUES(?,?,?) ";
    private static final String queryInsertUser = "INSERT INTO loginclient(idc,login,password) VALUES(?,?,?)";
    private static final String queryIfUserExists = "SELECT * from loginclient where idc = ? and login = ? and password = ?";
    private static final String queryUnblockCard = "UPDATE card SET Active = 1  WHERE id = ? and ida = ?";
    private static final String queryBlockCard = "UPDATE card SET Active = 0  WHERE id = ? and ida = ?";
    private static final String queryIfPassExists = "SELECT * from loginclient where idc = ? and password = ? ";
    private static final String queryUpdatePass = "UPDATE loginclient SET password = ?  WHERE idc = ? ";
    private static final String queryBlockUser = "INSERT INTO loginhistory(idl) VALUES (?)";
    private static final String queryUnblockUser = "INSERT INTO loginhistory(idl,success) VALUES(?,1)";
    private static final String queryLastRecord = "select * from loginhistory where idl = (select id from loginclient where idc = ?)order by UNIX_TIMESTAMP(logDate) desc limit 1";
    private static final String queryCreateAcc = "INSERT INTO account(idc,AccNum,amount) VALUES(?,?,?)";
    private static final String queryCreateCard = "insert into card(ida,PIN,ExpireM,ExpireY,Active) VALUES(?,?,?,?,?)";

    public void insertUser(String fname,String lname,String email,String loginName,String pass){

        int id = 0;
        try {
            Connection conn = getConnection();

            PreparedStatement pst1 = conn.prepareStatement(queryInsertClient,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pst2 = conn.prepareStatement(queryInsertUser);
            //client table
            pst1.setString(1,fname);
            pst1.setString(2,lname);
            pst1.setString(3,email);

            int insert1 = pst1.executeUpdate();


            ResultSet rs = pst1.getGeneratedKeys();
            while(rs.next()){
                System.out.println("Result Set: " + rs.toString());
                id = rs.getInt(1);
            }

            //loginclient table
            pst2.setInt(1,id);
            pst2.setString(2,loginName);
            pst2.setString(3,pass);

            int insert2 = pst2.executeUpdate();

            System.out.println("User inserted");

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Card> clientCards(int idacc){
        Connection conn = getConnection();
        ArrayList <Card> cardList = new ArrayList<>();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryCards);
            pst.setInt(1,idacc);
            rs = pst.executeQuery();

            while (rs.next()) {

                Card card = new Card(rs.getInt("id"),rs.getInt("ida"),rs.getString("PIN"),
                        rs.getInt("ExpireM"),rs.getInt("ExpireY"),rs.getBoolean("Active"));
                cardList.add(card);
            }
            return cardList;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public int unblockCard(int id , int ida ){

        Connection conn = getConnection();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryUnblockCard);
            pst.setInt(1,id);
            pst.setInt(2,ida);
            int rslt= pst.executeUpdate();

            //System.out.println(rslt);
            System.out.println("Card is actived");
            return rslt;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;

    }

    public int blockCard(int id , int ida ){

        Connection conn = getConnection();

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(queryBlockCard);
            pst.setInt(1,id);
            pst.setInt(2,ida);
            int rslt= pst.executeUpdate();

            //System.out.println(rslt);
            System.out.println("Card is blocked");
            return rslt;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;

    }

    public void unblockUserLogin(int idc){

        Connection con = getConnection();

        try {
            PreparedStatement stmnt = con.prepareStatement(queryUnblockUser);
            stmnt.setInt(1,idc);
            stmnt.execute();
            con.close();
            System.out.println("unblocked user");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void blockUserLogin(int idc){

        Connection con = getConnection();

        try {
            PreparedStatement stmnt = con.prepareStatement(queryBlockUser);
            stmnt.setInt(1,idc);
            stmnt.execute();
            con.close();
            System.out.println("blocked user");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCard(int ida,String PIN){

        Connection con = getConnection();

        try {
            PreparedStatement stmnt = con.prepareStatement(queryCreateCard);
            stmnt.setInt(1,ida);
            stmnt.setString(2,PIN);
            stmnt.setInt(3,0);
            stmnt.setInt(4,0);
            stmnt.setInt(5,1);
            stmnt.execute();
            con.close();
            System.out.println("Card inserted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

