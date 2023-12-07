package main.java.Controller;

import main.java.Interfaces.DBConnections;
import main.java.View.NotePanelGui;
import main.java.View.UserPanelGui;

import javax.swing.*;

public class NotePanelController {
    private DBConnections dataBase;
    private int userId;

    public NotePanelController(DBConnections dataBase,int userId) {
        this.dataBase = dataBase;
        this.userId = userId;
    }

    public void goBack(NotePanelGui notePanelGui, int userId) {
        notePanelGui.dispose();
        SwingUtilities.invokeLater(() -> new UserPanelGui(dataBase, userId));
    }

    public void saveNote(NotePanelGui gui,JButton button,JTextArea textArea) {
        button.addActionListener(actionEvent -> {
               dataBase.addNote(userId,textArea.getText());
               gui.dispose();
               SwingUtilities.invokeLater(() -> new NotePanelGui(dataBase,userId));
            }
        );
    }
}
