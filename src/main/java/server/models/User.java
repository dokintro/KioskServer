package server.models;

public class User {

    private String nameUser, RFIDUser;
    private int idUser, userIsAdmin, idProduct, amountBought;

    public User(int idUser, String nameUser, String RFIDUser, int userIsAdmin) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.RFIDUser = RFIDUser;
        this.userIsAdmin = userIsAdmin;
    }

    public User(int idUser, String RFIDUser, int idProduct, int amountBought) {
        this.idUser = idUser;
        this.RFIDUser = RFIDUser;
        this.idProduct = idProduct;
        this.amountBought = amountBought;
    }

    public int getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(int amountBought) {
        this.amountBought = amountBought;
    }

    public User() {


    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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
