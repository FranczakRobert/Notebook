package View;

import Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;

public class NotePanel extends JFrame {
    private DBConnections dataBase;

    public NotePanel(DBConnections dbConnections) {
        dataBase = dbConnections;
        initFrame();
        userPanel();
    }
    private void initFrame() {
        setTitle("NOTEPAD \uD83D\uDCD3");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }



    private void userPanel() {
        JPanel usrPanel = new JPanel();
        usrPanel.setBorder(BorderFactory.createEmptyBorder(10, 200, 200, 200));
        usrPanel.setLayout(new GridBagLayout()); // Używamy GridBagLayout
        usrPanel.setBackground(new Color(43,45,48));

        // Tworzenie JTextArea, w której będą wyświetlane notatki
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(255, 255, 255)); // Kolor tła JTextArea


        // Tworzenie JScrollPane i dodawanie JTextArea do niego
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);
        scrollPane.setBackground(new Color(43,45,48));

        JButton button = new JButton("Add Note"); // Zmiana na standardny JButton
        JButton backButton = new JButton("Back");

        // Dodanie komponentów przy użyciu GridBagLayout
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
