package View;

import Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;

public class ComponentsFactory extends JFrame {
    private static ComponentsFactory instance = null;
    private ComponentsFactory() {};

    public static ComponentsFactory getInstance() {
        if(instance == null) {
            instance = new ComponentsFactory();
        }
        return instance;
    }


    public JLabel createLabel(String text) {
        JLabel usernameLabel = new JLabel(text,JLabel.CENTER);
        usernameLabel.setForeground(Color.WHITE);
        return usernameLabel;
    }

    public JButton createButton(String text, DBConnections dataBase) {
        JButton backButton = new JButton("Back");
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(e -> {
            dispose();

            SwingUtilities.invokeLater(() -> {
                Thread mainGUI = new Thread(new MainGui(dataBase));
                mainGUI.start();
            });
        });

        return backButton;
    }
}
