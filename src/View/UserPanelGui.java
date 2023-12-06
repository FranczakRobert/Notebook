package View;

import Controller.UserPanelController;
import Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;

public class UserPanelGui extends JFrame{
    private DBConnections dataBase;
    private UserPanelController userPanelController;
    private int userID;


    public UserPanelGui(DBConnections dbConnections, int userID) {
        this.userID = userID;
        dataBase = dbConnections;
        userPanelController = new UserPanelController(dataBase);
        initFrame();
        userPanel();
    }
    private void initFrame() {
        setTitle("NOTEPAD \uD83D\uDCD3");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void userPanel() {
        JPanel usrPanel = new JPanel();
        usrPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        usrPanel.setLayout(new GridLayout(4, 3, 5, 5));
        usrPanel.setBackground(new Color(43,45,48));

        JButton addNote = ComponentsFactory.getInstance().createButton("Add Note");
        addNote.addActionListener(e -> userPanelController.addNote(this,userID));

        JButton showNotes = ComponentsFactory.getInstance().createButton("My Notes");
        showNotes.addActionListener(e -> userPanelController.showNotes(this,userID));

        JButton logoutButton = ComponentsFactory.getInstance().createButton("Logout");
        logoutButton.addActionListener(e -> userPanelController.logout(this));

        usrPanel.add(ComponentsFactory.getInstance().createLabel("******** YOUR NOTEPAD ********"));
        usrPanel.add(addNote);
        usrPanel.add(showNotes);
        usrPanel.add(logoutButton);

        add(usrPanel);
        setVisible(true);
    }
}
