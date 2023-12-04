package View;

import Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;

public class LoginGui extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DBConnections dataBase;

    public LoginGui(DBConnections dbConnections) {
        dataBase = dbConnections;
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
        loginButton.addActionListener(e -> performLogin());

        JButton backButton = new JButton("back");
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(e -> goBack());

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(backButton);
        loginPanel.add(loginButton);

        add(loginPanel);
        setVisible(true);
    }
    private void performLogin() {
        int checker = -1;
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        checker = dataBase.findUserByUsernameAndPassword(username,password);

        if(checker > 0) {
            dispose();

            SwingUtilities.invokeLater(() -> new UserPanelGui(dataBase));
        }
        else  {
            JOptionPane.showMessageDialog(this, "Incorrect login or password...");
        }

        usernameField.setText("");
        passwordField.setText("");
    }
    private void goBack() {
        dispose();
        SwingUtilities.invokeLater(() -> new MainGui(dataBase));
    }

}
