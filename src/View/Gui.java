package View;

import Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements Runnable {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DBConnections dataBase;

    public Gui(DBConnections dbConnections) {
        dataBase = dbConnections;
    }

    @Override
    public void run() {
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
        loginPanel.setLayout(new GridLayout(3, 3, 5, 20)); // 3 wiersze, 2 kolumny, odstępy 5 pikseli
        loginPanel.setBackground(new Color(43,45,48));

        JLabel usernameLabel = new JLabel("Login:",JLabel.CENTER);
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:",JLabel.CENTER);
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
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
            afterLogin();
        }
        else  {
            JOptionPane.showMessageDialog(this, "Incorrect login or password...");
        }


        usernameField.setText("");
        passwordField.setText("");
    }

    private void afterLogin() {
        // Zamknij bieżącą ramkę logowania
            dispose();

            // Przejdź do następnego GUI
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
//                    new NextGUI();
                    JFrame frame = new JFrame();
                    frame.setSize(350,200);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().setBackground(new Color(43,45,48));
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                }
            });

    }

}