package main.java.View;

import main.java.Controller.LoginController;
import main.java.Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;

public class LoginGui extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DBConnections dataBase;
    private LoginController loginController;

    public LoginGui(DBConnections dbConnections) {
        dataBase = dbConnections;
        loginController = new LoginController(dataBase);
        initFrame();
        loginPanel();
    }

    private void initFrame() {
        setTitle("NOTEPAD \uD83D\uDCD3 ");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    private void loginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 30));
        loginPanel.setLayout(new GridLayout(3, 1, 5, 20)); // 3 wiersze, 2 kolumny, odstępy 5 pikseli
        loginPanel.setBackground(new Color(43,45,48));

        JLabel usernameLabel = ComponentsFactory.getInstance().createLabel("Login");
        usernameField = new JTextField();

        JLabel passwordLabel = ComponentsFactory.getInstance().createLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = ComponentsFactory.getInstance().createButton("Login");
        loginButton.addActionListener(e -> loginController.performLogin(usernameField,passwordField,this));

        JButton backButton = new JButton("Back");
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(e -> loginController.goBack(this));

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(backButton);
        loginPanel.add(loginButton);

        add(loginPanel);
        setVisible(true);
    }


}
