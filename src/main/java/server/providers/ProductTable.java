package server.providers;

import server.models.Product;
import server.models.User;

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

    public ArrayList<User> getEverythingBought() {
        ArrayList<User> everythingBought = new ArrayList<User>();
        try {
            PreparedStatement getEverythingBoughtStatement = getConnection().prepareStatement("SELECT * FROM student_has_purchased");
            resultSet = getEverythingBoughtStatement.executeQuery();
            while (resultSet.next()) {
                User userBoughtItem = new User();
                userBoughtItem.setIdUser(resultSet.getInt("users_idUser"));
                userBoughtItem.setRFIDUser(resultSet.getString("users_RFIDUser"));
                userBoughtItem.setNameProduct(resultSet.getString("products_nameProduct"));
                userBoughtItem.setAmountBought(resultSet.getInt("amountBought"));
                everythingBought.add(userBoughtItem);
            }
            resultSet.close();
            getEverythingBoughtStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return everythingBought;
    }

    //Handles getting products and properly adding them to the resultSet for both getAllProducts and getAllActiveProducts
    private void productGetter(ArrayList<Product> myProducts) throws SQLException {
        while (resultSet.next()) {
            Product product = new Product();
            product.setIdProduct(resultSet.getInt("idProduct"));
            product.setNameProduct(resultSet.getString("nameProduct"));
            product.setPriceProduct(resultSet.getInt("priceProduct"));
            product.setStockProduct(resultSet.getInt("stockProduct"));
            product.setIsActive(resultSet.getInt("isActive"));
            myProducts.add(product);
        }
    }

    public boolean updateProduct(Product product) throws SQLException {
        PreparedStatement updateProductStatement = null;

        updateProductStatement = getConnection().prepareStatement("UPDATE products SET nameProduct = ?, priceProduct = ?, isActive = ?, stockProduct = ? WHERE idProduct = ?");

        updateProductStatement.setString(1, product.getNameProduct());
        updateProductStatement.setInt(2, product.getPriceProduct());
        updateProductStatement.setInt(3, product.getIsActive());
        updateProductStatement.setInt(4, product.getStockProduct());
        updateProductStatement.setInt(5, product.getIdProduct());

        int rowsAffected = updateProductStatement.executeUpdate();
        if (rowsAffected != 1) {
            return false;
        }
        updateProductStatement.close();
        return true;
    }

    public boolean deleteProduct(Product product) throws SQLException {

        PreparedStatement deleteProductStatement = getConnection().prepareStatement("DELETE FROM products WHERE idProduct = ?");
        deleteProductStatement.setInt(1, product.getIdProduct());

        int rowsUpdated = deleteProductStatement.executeUpdate();
        if (rowsUpdated != 1) {
            throw new SQLException("More or less than 1 row was affected");
        }
        deleteProductStatement.close();
        return true;
    }

    public boolean refillProduct(Product product) throws SQLException {

        PreparedStatement refillProductStatement = getConnection().prepareStatement("UPDATE products SET stockProduct = stockProduct + ? WHERE idProduct = ?");
        refillProductStatement.setInt(1, product.getStockProduct());
        refillProductStatement.setInt(2, product.getIdProduct());

        int rowsUpdated = refillProductStatement.executeUpdate();
        if (rowsUpdated != 1) {
            throw new SQLException("More or less than 1 row was affected");
        }
        refillProductStatement.close();
        return true;
    }

    public boolean createProduct(Product product) throws SQLException {
        PreparedStatement createProductStatement = getConnection().prepareStatement("INSERT INTO products (nameProduct, priceProduct, stockProduct, isActive) VALUES (?,?,?,?)");

        createProductStatement.setString(1, product.getNameProduct());
        createProductStatement.setInt(2, product.getPriceProduct());
        createProductStatement.setInt(3, product.getStockProduct());
        createProductStatement.setInt(4, product.getIsActive());

        int rowsAffected = createProductStatement.executeUpdate();

        if (rowsAffected != 1) {
            throw new SQLException("More or less than 1 row was affected");
        }
        createProductStatement.close();
        return true;
    }

    public Product getProductByName(Product product) {
        try {
            PreparedStatement getProductByNameStatement = getConnection().prepareStatement("SELECT * FROM products WHERE nameProduct = ?");

            getProductByNameStatement.setString(1, product.getNameProduct());
            resultSet = getProductByNameStatement.executeQuery();

            while (resultSet.next()) {
                product = new Product();
                product.setIdProduct(resultSet.getInt("idProduct"));
                product.setNameProduct(resultSet.getString("nameProduct"));
                product.setPriceProduct(resultSet.getInt("priceProduct"));
                product.setIsActive(resultSet.getInt("isActive"));
            }
            resultSet.close();
            getProductByNameStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean buyProduct(Product product, String userRFID, User user) throws SQLException {
        PreparedStatement buyProductStatement = getConnection().prepareStatement("INSERT INTO student_has_purchased (users_idUser,users_RFIDUser, products_nameProduct, amountBought) VALUES (?, ?, ?, ?)");
        buyProductStatement.setInt(1, user.getIdUser());
        buyProductStatement.setString(2, userRFID);
        buyProductStatement.setString(3, product.getNameProduct());
        buyProductStatement.setInt(4, product.getAmountBought());

        int rowsAffected = buyProductStatement.executeUpdate();
        if (rowsAffected != 1) {
            return false;
        }
        buyProductStatement.close();
        PreparedStatement updateProductStockStatement = getConnection().prepareStatement("UPDATE products SET stockProduct = stockProduct - ? WHERE nameProduct = ?");
        updateProductStockStatement.setInt(1, product.getAmountBought());
        updateProductStockStatement.setString(2, product.getNameProduct());
        rowsAffected = updateProductStockStatement.executeUpdate();
        if (rowsAffected != 1) {
            return false;
        }
        updateProductStockStatement.close();
        return true;
    }

    public boolean deleteArrangementData() throws SQLException {
        PreparedStatement deleteFromStudentHasPurchasedStatement = getConnection().prepareStatement("DELETE FROM student_has_purchased");
        deleteFromStudentHasPurchasedStatement.executeUpdate();
        deleteFromStudentHasPurchasedStatement.close();
        PreparedStatement deleteNonAdminsStatement = getConnection().prepareStatement("DELETE FROM users WHERE userIsAdmin = 0");
        deleteNonAdminsStatement.executeUpdate();
        deleteNonAdminsStatement.close();
        return true;
    }
}