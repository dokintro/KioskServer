package server.models;

public class User {

    private String nameUser, RFIDUser;
    private int idUser, userIsAdmin;

    public User(int idUser, String nameUser, String RFIDUser, int userIsAdmin) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.RFIDUser = RFIDUser;
        this.userIsAdmin = userIsAdmin;
    }

    public User() {

    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getRFIDUser() {
        return RFIDUser;
    }

    public void setRFIDUser(String RFIDUser) {
        this.RFIDUser = RFIDUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(int userIsAdmin) {
        this.userIsAdmin = userIsAdmin;
    }
}
