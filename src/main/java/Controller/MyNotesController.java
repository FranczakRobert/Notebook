package main.java.Controller;

import main.java.Interfaces.DBConnections;
import main.java.View.MyNotesGui;
import main.java.View.UserPanelGui;

import javax.swing.*;
import java.util.ArrayList;

public class MyNotesController {
    private DBConnections dataBase;
    private int userId;
    public MyNotesController(DBConnections dataBase, int userId) {
        this.userId = userId;
        this.dataBase = dataBase;
    }

    public ArrayList<String> fetchAllNotes() {
        ArrayList<String> notes = dataBase.getAllNotes(userId);
        return notes;
    }

    public void goBack(MyNotesGui gui) {
        gui.dispose();
        SwingUtilities.invokeLater(() -> new UserPanelGui(dataBase,userId));
    }
}
