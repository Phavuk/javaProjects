package database;

import bill.Bill;
import main.Globals;

import java.sql.*;

public class Database {
    private static Database dab = new Database();

    private Database() {
    }

    public static Database getInstance() {
        return dab;
    }

    private Connection getConnection()
    {
        Connection conn;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
            conn = DriverManager.getConnection(Globals.host, Globals.username, Globals.password);
            return conn;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void closeConnection(Connection conn){
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertBillDetails(Date date, float totalPrice) {
        Connection conn = getConnection();

        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO bill (dateTime, totalPrice) VALUES (?,?)");

            stm.setString(1, String.valueOf(date));
            stm.setString(2, String.valueOf(totalPrice));

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(conn);
    }

    public void insertBillItems(Bill bill) {
        Connection conn = getConnection();


    }


}
