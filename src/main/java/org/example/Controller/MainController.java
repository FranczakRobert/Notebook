package org.example.Controller;

import org.example.Interfaces.DBConnections;
import org.example.Utils.DbTypeEnum;
import org.example.View.MainGui;
import org.example.View.View;

import java.util.Scanner;

public class MainController  {
    Scanner scanner =null;
    View view = null;

    public MainController() {
        init();
    }

    public void startWith(DbTypeEnum dbType) {
        DBConnections connections = dbType.equals(DbTypeEnum.MYSQL) ? MySQLController.getInstance() : FileDBController.getInstance();
        start(connections);
    }

    private void start(DBConnections db) {
        view.showHeader();
        new MainGui(db);
    }

    private void init() {
        view = new View();
        scanner = new Scanner(System.in);
    }
}
