package server.controllers;

import server.models.User;
import server.providers.UserTable;

import java.sql.SQLException;
import java.util.ArrayList;

public class FraggelController {
    private UserTable userTable = new UserTable();

    public ArrayList getAllUsers() {
        ArrayList getAllUsers = userTable.getAllUsers();
        userTable.close();
        return getAllUsers;
    }

    public boolean createUser(User user) throws SQLException {
        boolean createUser = userTable.createUser(user);
        userTable.close();
        return createUser;
    }
    public boolean updateUser(User user) throws SQLException {
        boolean updateUser = userTable.updateUser(user);
        userTable.close();
        return updateUser;
    }
    public boolean deleteUser(User user) throws Exception {
        boolean deleteUser = userTable.deleteUser(user);
        userTable.close();
        return deleteUser;
    }


}
