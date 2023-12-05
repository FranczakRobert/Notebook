package Controller;

import Interfaces.DBConnections;
import View.LoginGui;
import View.MainGui;
import View.UserPanelGui;

import javax.swing.*;

public class LoginController {
    private DBConnections dataBase;

    public LoginController(DBConnections connections) {
        this.dataBase = connections;
    }

    public void performLogin(JTextField usernameField, JPasswordField passwordField, LoginGui gui) {
        int checker = -1;
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        checker = dataBase.findUserByUsernameAndPassword(username,password);

        if(checker > 0) {
            gui.dispose();

            SwingUtilities.invokeLater(() -> new UserPanelGui(dataBase));
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
