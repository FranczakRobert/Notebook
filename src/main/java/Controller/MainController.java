package main.java.Controller;

import main.java.Interfaces.DBConnections;
import main.java.Utils.DbTypeEnum;
import main.java.View.MainGui;
import main.java.View.View;
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
        Thread dbConnectionThread = new Thread(db);
        Thread gui = new Thread(new MainGui(db));

        try {
            dbConnectionThread.start();
            dbConnectionThread.join();
            gui.start();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() {
        view = new View();
        scanner = new Scanner(System.in);
    }
}
