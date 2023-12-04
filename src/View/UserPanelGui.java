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
        usrPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        usrPanel.setLayout(new GridLayout(1, 1, 5, 20)); // 3 wiersze, 2 kolumny, odstępy 5 pikseli
        usrPanel.setBackground(new Color(43,45,48));

        add(usrPanel);
        setVisible(true);

    }

}
