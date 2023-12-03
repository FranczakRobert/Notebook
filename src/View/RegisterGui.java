package View;

import Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;

public class RegisterGui extends JFrame {
    private final DBConnections dataBase;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField secPasswordField;
    public RegisterGui(DBConnections dbConnections) {
        this.dataBase = dbConnections;
        initFrame();
        registerPanel();
    }

    private void initFrame() {
        setTitle("NOTEPAD \uD83D\uDCD3 ");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void registerPanel() {
        JPanel registerPanel = new JPanel();
        registerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,30));
        registerPanel.setLayout(new GridLayout(4,1,5,20));
        registerPanel.setBackground(new Color(43,45,48));

        JLabel usernameLabel = ComponentsFactory.getInstance().createLabel("Login");
        usernameField = new JTextField();

        JLabel passwordLabel = ComponentsFactory.getInstance().createLabel("Password");
        passwordField = new JPasswordField();

        JLabel secPasswordLabel = ComponentsFactory.getInstance().createLabel("Password");
        secPasswordField = new JPasswordField();

        JButton registerButton = new JButton("Register");
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
        registerButton.addActionListener(e -> registerCheck());

        JButton backButton = ComponentsFactory.getInstance().createButton("Back",dataBase);

        registerPanel.add(usernameLabel);
        registerPanel.add(usernameField);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordField);
        registerPanel.add(secPasswordLabel);
        registerPanel.add(secPasswordField);
        registerPanel.add(backButton);
        registerPanel.add(registerButton);
        add(registerPanel);
        setVisible(true);
    }

    private void registerCheck() {

    }




}
