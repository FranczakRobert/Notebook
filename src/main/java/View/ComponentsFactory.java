package main.java.View;

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

    public JButton createButton(String text) {
        JButton backButton = new JButton(text);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);

        return backButton;
    }



}
