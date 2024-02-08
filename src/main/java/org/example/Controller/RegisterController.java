package org.example.Controller;


import org.example.Interfaces.DBConnections;
import org.example.Model.User;
import org.example.View.MainGui;
import org.example.View.RegisterGui;
import org.example.View.UserPanelGui;

import javax.swing.*;
import java.util.Arrays;

public class RegisterController {
    private DBConnections dataBase;

    public RegisterController(DBConnections connections) {
        this.dataBase = connections;
    }

    public void goBack(RegisterGui registerGui){
        registerGui.dispose();
        SwingUtilities.invokeLater(() -> new MainGui(dataBase));
    }

    public void registerProcess(RegisterGui registerGui, JTextField usernameField, JPasswordField passwordField, JPasswordField secPasswordField) {

        String encryptedPassword = CryptoController.getInstance().encryptPassword(passwordField.getPassword());

        if(checkIfPasswordsAreTheSame(passwordField,secPasswordField)) {
            if(checkIfCanBeRegister(usernameField.getText(), encryptedPassword)) {
                dataBase.addUser(new User(usernameField.getText(), encryptedPassword));
                int userId = dataBase.findUserByUsername(usernameField.getText());
                registerGui.dispose();
                SwingUtilities.invokeLater(() -> new UserPanelGui(dataBase,userId));
            }
            else  {
                JOptionPane.showMessageDialog(registerGui, "Incorrect login or password format");
                usernameField.setText("");
                passwordField.setText("");
                secPasswordField.setText("");
            }
        }
        else  {
            JOptionPane.showMessageDialog(registerGui, "Passwords are not the same...");
            usernameField.setText("");
            passwordField.setText("");
            secPasswordField.setText("");
        }
    }

    private boolean checkIfCanBeRegister(String login, String password) {
        boolean result = false;

        if(login.contains(" ") || password.contains(" ") || login.isEmpty() || password.isEmpty()) {
            return false;
        }

        if(-1 == dataBase.findUserByUsername(login)) {
            result = true;
        }
        return result;
    }

    private boolean checkIfPasswordsAreTheSame(JPasswordField passwordField,JPasswordField secPasswordField) {
        return Arrays.equals(passwordField.getPassword(), secPasswordField.getPassword());
    }
}
