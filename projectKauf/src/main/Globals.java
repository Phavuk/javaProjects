package main;

public class Globals {
public static final int MAXITEMS;
    public static final String username = "johny";
    public static final String password = "styrinuly";
    public static final String host = "jdbc:mysql://localhost:3306/projectKauf";
    static {
        MAXITEMS =9;

    }

    public void println(String message){
        System.out.println(message);
    }
    public void print(String message){
        System.out.print(message);
    }
}
