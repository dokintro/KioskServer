package server.providers;

import server.models.Event;
import server.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProductTable extends DBmanager {

    private ResultSet resultSet;


    public ArrayList<Product> getAllActiveProducts() {
        ArrayList<Product> allActiveProducts = new ArrayList<>();
        try {
            PreparedStatement getAllActiveProductsStatement = getConnection().prepareStatement("SELECT * FROM products WHERE isActive = 1");
            resultSet = getAllActiveProductsStatement.executeQuery();
            productGetter(allActiveProducts);
            resultSet.close();
            getAllActiveProductsStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allActiveProducts;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();
        try {
            PreparedStatement getAllProductsStatement = getConnection().prepareStatement("SELECT * FROM products");
            resultSet = getAllProductsStatement.executeQuery();
            productGetter(allProducts);
            resultSet.close();
            getAllProductsStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    //Handles getting products and properly adding them to the resultSet for both getAllProducts and getAllActiveProducts
    private void productGetter(ArrayList<Product> myProducts) throws SQLException {
        while (resultSet.next()) {
            Product product = new Product();
            product.setIdProduct(resultSet.getInt("idProduct"));
            product.setNameProduct(resultSet.getString("nameProduct"));
            product.setPriceProduct(resultSet.getInt("priceProduct"));
            product.setIsActive(resultSet.getInt("isActive"));
            myProducts.add(product);
        }
    }

    public boolean updateProduct(Product product) throws SQLException {
        PreparedStatement updateProductStatement = null;

        updateProductStatement = getConnection().prepareStatement("UPDATE products SET nameProduct = ?, priceProduct = ?, isActive = ? WHERE idProduct = ?");

        updateProductStatement.setString(1, product.getNameProduct());
        updateProductStatement.setInt(2, product.getPriceProduct());
        updateProductStatement.setInt(3, product.getIsActive());
        updateProductStatement.setInt(4, product.getIdProduct());

        int rowsAffected = updateProductStatement.executeUpdate();
        if (rowsAffected != 1) {
            return false;
        }
        updateProductStatement.close();
        return true;
    }

    public boolean deleteProduct(Product product) throws SQLException {

        PreparedStatement deleteProductStatement = getConnection().prepareStatement("UPDATE products SET isActive = 0 WHERE idProduct = ?");
        deleteProductStatement.setInt(1, product.getIdProduct());

        int rowsUpdated = deleteProductStatement.executeUpdate();
        if (rowsUpdated != 1) {
            throw new SQLException("More or less than 1 row was affected");
        }
        deleteProductStatement.close();
        return true;
    }

    public boolean createProduct(Product product) throws SQLException {
        PreparedStatement createProductStatement = getConnection().prepareStatement("INSERT INTO products (nameProduct, priceProduct, isActive) VALUES (?,?,?)");

        createProductStatement.setString(1, product.getNameProduct());
        createProductStatement.setInt(2, product.getPriceProduct());
        createProductStatement.setInt(3, product.getIsActive());

        int rowsAffected = createProductStatement.executeUpdate();

        if (rowsAffected != 1) {
            throw new SQLException("More or less than 1 row was affected");
        }
        createProductStatement.close();
        return true;
    }

}