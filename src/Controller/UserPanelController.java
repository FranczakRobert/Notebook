package Controller;

import Interfaces.DBConnections;
import View.MainGui;
import View.MyNotesGui;
import View.NotePanelGui;
import View.UserPanelGui;

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

    public void showNotes(UserPanelGui userPanelGui,int userId) {
        userPanelGui.dispose();
        SwingUtilities.invokeLater(() -> new MyNotesGui(dataBase,userId));
    }
}
