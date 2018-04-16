package server.controllers;

import server.providers.UserTable;

import java.util.ArrayList;

public class FraggelController {
    private UserTable userTable = new UserTable();

    public ArrayList getAllUsers() {
        ArrayList getAllProducts = userTable.getAllUsers();
        userTable.close();
        return getAllProducts;
    }
}
