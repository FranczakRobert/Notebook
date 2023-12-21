package main.java.View;

import main.java.Controller.MyNotesController;
import main.java.Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyNotesGui extends JFrame {
    private DBConnections dataBase;
    private MyNotesController myNotesController;
    private int userId;

    public MyNotesGui(DBConnections dataBase ,int userId) {
        this.dataBase = dataBase;
        this.userId = userId;
        myNotesController = new MyNotesController(dataBase,userId);
        initFrame();
        myNotesPanel();
    }

    private void initFrame() {
        setTitle("NOTEPAD \uD83D\uDCD3 ");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void myNotesPanel() {
        JPanel usrPanel = new JPanel();
        usrPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        usrPanel.setLayout(new GridBagLayout());
        usrPanel.setBackground(new Color(43,45,48));
        usrPanel.setLayout(new BoxLayout(usrPanel, BoxLayout.Y_AXIS));
        ArrayList<String> notes = myNotesController.fetchAllNotes();

        if(!notes.isEmpty()) {
            for(int i = 0; i < notes.size(); i++) {
                usrPanel.add(ComponentsFactory.getInstance().createLabel("NOTE " + (i + 1) + " ---------------------------"));
                usrPanel.add(ComponentsFactory.getInstance().createLabel("         "));
                usrPanel.add(ComponentsFactory.getInstance().createLabel(notes.get(i)));
                usrPanel.add(ComponentsFactory.getInstance().createLabel("         "));
                usrPanel.add(ComponentsFactory.getInstance().createLabel("----------------------------------"));
                usrPanel.add(ComponentsFactory.getInstance().createLabel("         "));
                usrPanel.add(ComponentsFactory.getInstance().createLabel("         "));
                usrPanel.add(ComponentsFactory.getInstance().createLabel("         "));
                usrPanel.add(ComponentsFactory.getInstance().createLabel("         "));
            }
        }
        else  {
            usrPanel.add(ComponentsFactory.getInstance().createLabel("YOU HAVE NO NOTES ADDED"));
        }

        JScrollPane scrollPane = new JScrollPane(usrPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel buttonPanel = new JPanel();
        JButton backButton = ComponentsFactory.getInstance().createButton("Back");
        backButton.addActionListener(e -> myNotesController.goBack(this));


        buttonPanel.add(backButton);

        JTextField noteNumberField = new JTextField();
        noteNumberField.setColumns(10);
        JButton deleteButton = ComponentsFactory.getInstance().createButton("Delete");

        deleteButton.addActionListener(e -> myNotesController.deleteNote(this, noteNumberField));

        backButton.addActionListener(e -> myNotesController.goBack(this));

        buttonPanel.add(backButton);
        buttonPanel.add(ComponentsFactory.getInstance().createLabel("|"));
        buttonPanel.add(noteNumberField);
        buttonPanel.add(deleteButton);


        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
