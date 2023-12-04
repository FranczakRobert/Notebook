package View;

import Interfaces.DBConnections;

import javax.swing.*;
import java.awt.*;

public class UserPanelGui extends JFrame{
    private DBConnections dataBase;

    public UserPanelGui(DBConnections dbConnections) {
        dataBase = dbConnections;
        initFrame();
        userPanel();
    }
    private void initFrame() {
        setTitle("NOTEPAD \uD83D\uDCD3");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void userPanel() {
        JPanel usrPanel = new JPanel();
        usrPanel.setBorder(BorderFactory.createEmptyBorder(10, 200, 200, 200));
        usrPanel.setLayout(new GridBagLayout()); // Używamy GridBagLayout
        usrPanel.setBackground(new Color(43,45,48));


        add(usrPanel);
        setVisible(true);
    }
}
