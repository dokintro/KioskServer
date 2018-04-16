package server.providers;

import server.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserTable extends DBmanager {

    private ResultSet resultSet;
    private User user;

    public User getUserByRFID(String RFID) {

        try {
            PreparedStatement getStudentsByRFIDStatement =
                    getConnection().prepareStatement("SELECT * FROM users WHERE RFIDUser = ?");

            getStudentsByRFIDStatement.setString(1, RFID);
            resultSet = getStudentsByRFIDStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setIdUser(resultSet.getInt("idUser"));
                user.setNameUser(resultSet.getString("nameUser"));
                user.setRFIDUser(resultSet.getString("RFIDUser"));
                user.setUserIsAdmin(resultSet.getInt("userIsAdmin"));
            }
            if (user == null) {
                throw new IllegalArgumentException();
            }

            resultSet.close();
            getStudentsByRFIDStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getAllProducts() {
        ArrayList<User> allProducts = new ArrayList<>();
        try {
            PreparedStatement getAllUsersStatement = getConnection().prepareStatement("SELECT * FROM users");
            resultSet = getAllUsersStatement.executeQuery();
            resultSet.close();
            getAllUsersStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }
}