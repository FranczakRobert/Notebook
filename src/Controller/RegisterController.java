package Controller;

import Interfaces.DBConnections;
import Model.User;
import View.RegisterGui;
import View.UserPanelGui;

import javax.swing.*;
import java.util.Arrays;

public class RegisterController {
    private DBConnections dataBase;

    public RegisterController(DBConnections connections) {
        this.dataBase = connections;
    }

    public void registerProcess(RegisterGui registerGui, JTextField usernameField, JPasswordField passwordField, JPasswordField secPasswordField) {

        StringBuilder password = new StringBuilder();
        for (char character : passwordField.getPassword()) {
            password.append(character);
        }

        if(checkIfPasswordsAreTheSame(passwordField,secPasswordField)) {
            if(checkIfCanBeRegister(usernameField.getText(), password.toString())) {
                dataBase.addUser(new User(usernameField.getText(), password.toString()));
                registerGui.dispose();
                SwingUtilities.invokeLater(() -> new UserPanelGui(dataBase));
            }
            else  {
                JOptionPane.showMessageDialog(registerGui, "Incorrect login or password format");
                usernameField.setText("");
                passwordField.setText("");
                secPasswordField.setText("");
            }


        }
        else  {
            JOptionPane.showMessageDialog(registerGui, "Passwrods are not the same...");
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
