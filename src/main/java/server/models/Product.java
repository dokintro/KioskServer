package server.models;

public class Product {

    private int idProduct, priceProduct, isActive, amountBought;
    private String nameProduct;

    public Product(int idProduct, String nameProduct, int priceProduct, int isActive, int amountBought) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.isActive = isActive;
        this.amountBought = amountBought;
    }


    public Product() {

    }

    public Product(String nameProduct, int amountBought) {
        this.nameProduct = nameProduct;
        this.amountBought = amountBought;
    }

    public int getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(int amountBought) {
        this.amountBought = amountBought;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
