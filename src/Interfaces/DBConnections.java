package Interfaces;

import Model.User;

public interface DBConnections {
    void connectToDB();
    void addUser(User user);
    void showAllUsers();
    void showUserByID(int id);
}