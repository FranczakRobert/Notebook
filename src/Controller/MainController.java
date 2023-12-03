package Controller;

import Interfaces.DBConnections;
import Utils.DbTypeEnum;
import View.MainGui;
import View.View;

import java.util.Scanner;

public class MainController  {
    Scanner scanner =null;
    View view = null;

    public MainController() {
        init();
    }

    public void startWith(DbTypeEnum dbType) {
        DBConnections connections = dbType.equals(DbTypeEnum.MYSQL) ? new MySQLController() : new FileDBController();
        start(connections);
    }

    private void start(DBConnections db) {
        view.showHeader();
        db = new MySQLController();
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
