package View;

import Interfaces.DBConnections;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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

        JButton registerButton = ComponentsFactory.getInstance().createButton("Register");
        registerButton.addActionListener(e -> registerCheck(dataBase));

        JButton backButton = ComponentsFactory.getInstance().createButton("Back");
        backButton.addActionListener(e -> {
            dispose();

            SwingUtilities.invokeLater(() -> {
                Thread mainGUI = new Thread(new MainGui(dataBase));
                mainGUI.start();
            });
        });

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

    private void registerCheck(DBConnections dataBase) {
        StringBuilder password = new StringBuilder();
        for (char character : passwordField.getPassword()) {
            password.append(character);
        }

        if(checkIfPasswordsAreTheSame()) {
            if(dataBase.checkIfCanBeRegister(usernameField.getText(), Arrays.toString(passwordField.getPassword()))) {
                dataBase.addUser(new User(usernameField.getText(), password.toString()));
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new UserPanelGui(dataBase);
                    }
                });
            }
            else  {
                JOptionPane.showMessageDialog(this, "Incorrect login or password format - do not use spaces");
                usernameField.setText("");
                passwordField.setText("");
                secPasswordField.setText("");
            }


        }
        else  {
            JOptionPane.showMessageDialog(this, "Passwrods are not the same...");
            usernameField.setText("");
            passwordField.setText("");
            secPasswordField.setText("");
        }

    }
    private boolean checkIfPasswordsAreTheSame() {
        if(Arrays.equals(passwordField.getPassword(), secPasswordField.getPassword())) {
            return true;
        }
        return false;
    }





}
