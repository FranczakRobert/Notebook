package Interfaces;

import Model.User;

public interface DBConnections {
    void connectToDB();
    void addUser(User user);
    void showAllUsers();
    void showUserByID(int id);

    void deleteUserByID(int id);
    int findUserByUsernameAndPassword(String login, String password);
    void addNote(int id);
}
