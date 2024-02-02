package org.example.Controller;


import org.example.Interfaces.DBConnections;
import org.example.View.LoginGui;
import org.example.View.MainGui;
import org.example.View.UserPanelGui;

import javax.swing.*;

public class LoginController {
    private DBConnections dataBase;

    public LoginController(DBConnections connections) {
        this.dataBase = connections;
    }

    public void performLogin(JTextField usernameField, JPasswordField passwordField, LoginGui gui) {
        int userID = -1;
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        userID = dataBase.findUserByUsernameAndPassword(username,password);

        if(userID > -1) {
            gui.dispose();

            int finalUserID = userID;
            SwingUtilities.invokeLater(() -> new UserPanelGui(dataBase, finalUserID));
        }
        else  {
            JOptionPane.showMessageDialog(gui, "Incorrect login or password...");
        }

        usernameField.setText("");
        passwordField.setText("");
    }
    public void goBack(LoginGui gui) {
        gui.dispose();
        SwingUtilities.invokeLater(() -> new MainGui(dataBase));
    }
}
