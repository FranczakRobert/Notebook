package Interfaces;

import Model.User;

import java.util.ArrayList;

public interface DBConnections extends Runnable{
    void connectToDB();
    void addUser(User user);
    void showAllUsers();
    void showUserByID(int id);

    void deleteUserByID(int id);
    int findUserByUsername(String login);
    int findUserByUsernameAndPassword(String login,String password);
    void addNote(int userId, String note);
    ArrayList<String> getAllNotes(int userId);
}
