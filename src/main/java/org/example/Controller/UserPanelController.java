package org.example.Controller;


import org.example.Interfaces.DBConnections;
import org.example.View.MainGui;
import org.example.View.MyNotesGui;
import org.example.View.NotePanelGui;
import org.example.View.UserPanelGui;

import javax.swing.*;

public class UserPanelController {
    private DBConnections dataBase;

    public UserPanelController(DBConnections dataBase) {
        this.dataBase = dataBase;
    }

    public void addNote(UserPanelGui gui,int userID) {
        gui.dispose();
        SwingUtilities.invokeLater(() -> new NotePanelGui(dataBase,userID));
    }

    public void logout(UserPanelGui gui) {
        gui.dispose();
        SwingUtilities.invokeLater(() -> new MainGui(dataBase));
    }

    public void showNotes(UserPanelGui userPanelGui, int userId) {
        userPanelGui.dispose();
        SwingUtilities.invokeLater(() -> new MyNotesGui(dataBase,userId));
    }
}
