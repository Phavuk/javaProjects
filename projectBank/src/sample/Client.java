package sample;

public class Client {
    private String Firstname;
    private String Lastname;
    private String Mail;
    private int ID;

    public Client(String firstname, String lastname, String mail, int ID) {
        Firstname = firstname;
        Lastname = lastname;
        Mail = mail;
        this.ID = ID;
    }


    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getMail() {
        return Mail;
    }

    public int getID() {
        return ID;
    }
}
