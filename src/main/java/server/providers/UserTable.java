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
                user.setUserSex(resultSet.getString("userSex"));
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

    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            PreparedStatement getAllUsersStatement = getConnection().prepareStatement("SELECT * FROM users");
            resultSet = getAllUsersStatement.executeQuery();
            userGetter(allUsers);
            resultSet.close();
            getAllUsersStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    private void userGetter(ArrayList<User> myUsers) throws SQLException {
        while (resultSet.next()) {
            User user = new User();
            user.setIdUser(resultSet.getInt("idUser"));
            user.setNameUser(resultSet.getString("nameUser"));
            user.setRFIDUser(resultSet.getString("RFIDUser"));
            user.setUserIsAdmin(resultSet.getInt("userIsAdmin"));
            user.setUserSex(resultSet.getString("userSex"));
            myUsers.add(user);
        }
    }

    public boolean createUser(User user) throws SQLException {
        PreparedStatement createUserStatement = getConnection().prepareStatement("INSERT INTO users (nameUser, RFIDUser, userIsAdmin, userSex) VALUES (?,?,?,?)");
        createUserStatement.setString(1, user.getNameUser());
        createUserStatement.setString(2, user.getRFIDUser());
        createUserStatement.setInt(3, user.getUserIsAdmin());
        createUserStatement.setString(4, user.getUserSex());

        int rowsAffected = createUserStatement.executeUpdate();

        if (rowsAffected != 1) {
            throw new SQLException("More or less than 1 row was affected");
        }
        createUserStatement.close();
        return true;
    }

    public boolean updateUser(User user) throws SQLException {
        PreparedStatement updateUserStatement = null;

        updateUserStatement = getConnection().prepareStatement("UPDATE users SET nameUser = ?, RFIDUser = ?, userIsAdmin = ?, userSex = ? WHERE idUser = ?");

        updateUserStatement.setString(1, user.getNameUser());
        updateUserStatement.setString(2, user.getRFIDUser());
        updateUserStatement.setInt(3, user.getUserIsAdmin());
        updateUserStatement.setString(4, user.getUserSex());
        updateUserStatement.setInt(5, user.getIdUser());

        int rowsAffected = updateUserStatement.executeUpdate();
        if (rowsAffected != 1) {
            return false;
        }
        updateUserStatement.close();
        return true;
    }

    public boolean deleteUser(User user) throws SQLException {

        PreparedStatement deleteUserStatement = getConnection().prepareStatement("DELETE FROM users WHERE idUser = ?");
        deleteUserStatement.setInt(1, user.getIdUser());

        int rowsUpdated = deleteUserStatement.executeUpdate();
        if (rowsUpdated != 1) {
            throw new SQLException("More or less than 1 row was affected");
        }
        deleteUserStatement.close();
        return true;
    }
}