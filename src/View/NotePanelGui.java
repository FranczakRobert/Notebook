package View;

import Controller.NotePanelController;
import Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;

public class NotePanelGui extends JFrame {
    private DBConnections dataBase;
    private int userId;
    private NotePanelController notePanelController;

    public NotePanelGui(DBConnections dbConnections, int userId) {
        dataBase = dbConnections;
        notePanelController = new NotePanelController(dataBase,userId);
        this.userId = userId;
        initFrame();
        userPanel();
    }
    private void initFrame() {
        setTitle("NOTEPAD \uD83D\uDCD3");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }



    private void userPanel() {
        JPanel usrPanel = new JPanel();
        usrPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 50, 100));
        usrPanel.setLayout(new GridBagLayout());
        usrPanel.setBackground(new Color(43,45,48));

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(255, 255, 255));


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);
        scrollPane.setBackground(new Color(43,45,48));

        JButton button = new JButton("Add Note");
        notePanelController.saveNote(this,button,textArea);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> notePanelController.goBack(this,userId));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        usrPanel.add(ComponentsFactory.getInstance().createLabel("------------------------------------------"), gbc);

        gbc.gridy++;
        gbc.gridwidth = 3;
        usrPanel.add(ComponentsFactory.getInstance().createLabel("ADD NOTE"), gbc);

        gbc.gridy++;
        gbc.gridwidth = 3;
        usrPanel.add(ComponentsFactory.getInstance().createLabel("------------------------------------------"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        usrPanel.add(scrollPane, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        usrPanel.add(backButton, gbc);

        gbc.gridx = 2;
        usrPanel.add(button, gbc);

        add(usrPanel);
        setVisible(true);
    }
}
