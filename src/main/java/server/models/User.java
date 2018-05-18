package server.models;

public class User {

    private String nameUser, RFIDUser, nameProduct, userSex;
    private int idUser, userIsAdmin, amountBought;

    public User(int idUser, String nameUser, String RFIDUser, int userIsAdmin, String userSex) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.RFIDUser = RFIDUser;
        this.userIsAdmin = userIsAdmin;
        this.userSex = userSex;
    }

    public User(int idUser, String RFIDUser, int amountBought, String nameProduct) {
        this.idUser = idUser;
        this.RFIDUser = RFIDUser;
        this.nameProduct = nameProduct;
        this.amountBought = amountBought;
    }

    public User() {
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(int amountBought) {
        this.amountBought = amountBought;
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

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
