package server.controllers;

import server.models.Product;
import server.models.User;
import server.providers.ProductTable;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {

    private ProductTable productTable = new ProductTable();

    public ArrayList getAllActiveProducts() {
        ArrayList getAllActiveProducts = productTable.getAllActiveProducts();
        productTable.close();
        return getAllActiveProducts;
    }

    public ArrayList getAllProducts() {
        ArrayList getAllProducts = productTable.getAllProducts();
        productTable.close();
        return getAllProducts;
    }
    public ArrayList getEverythingBought() {
        ArrayList getEverythingBought = productTable.getEverythingBought();
        productTable.close();
        return getEverythingBought;
    }

    public boolean updateProduct(Product product) throws SQLException {
        boolean updateProduct = productTable.updateProduct(product);
        productTable.close();
        return updateProduct;
    }

    public boolean deleteProduct(Product product) throws Exception {
        boolean deleteProduct = productTable.deleteProduct(product);
        productTable.close();
        return deleteProduct;
    }

    public boolean createProduct(Product product) throws SQLException {
        boolean createProduct = productTable.createProduct(product);
        productTable.close();
        return createProduct;
    }

    public boolean buyProduct(Product product, String userRFID, User user) throws SQLException {
        boolean buyProduct = productTable.buyProduct(product, userRFID, user);
        productTable.close();
        return buyProduct;
    }
}
