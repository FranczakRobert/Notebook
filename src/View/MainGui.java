package View;

import Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui extends JFrame implements Runnable{
    private DBConnections dataBase;

    public MainGui(DBConnections dbConnections) {
        dataBase = dbConnections;
    }

    @Override
    public void run() {
        initFrame();
        loginPanel();
    }

    private void initFrame() {
        setTitle("NOTEPAD \uD83D\uDCD3");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    private void loginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        loginPanel.setLayout(new GridLayout(2, 1, 5, 20)); // 3 wiersze, 2 kolumny, odstępy 5 pikseli
        loginPanel.setBackground(new Color(43,45,48));

        JButton loginButton = new JButton("Login");
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToLoginPanel();
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goToRegisterPanel();
            }
        });

        JLabel header = new JLabel("NOTEPAD \uD83D\uDCD3 ", JLabel.RIGHT);
        header.setForeground(Color.WHITE);


        loginPanel.add(header);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        add(loginPanel);
        setVisible(true);
    }
    private void goToLoginPanel() {
        dispose();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGui(dataBase);
            }
        });
    }

    private void goToRegisterPanel() {
        dispose();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterGui(dataBase);
            }
        });
    }

}