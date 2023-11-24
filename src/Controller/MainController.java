package Controller;

import Interfaces.DBConnections;
import Utils.DbTypeEnum;
import View.View;

import java.util.Scanner;

public class MainController  {
    Scanner scanner =null;
    View view = null;
    public MainController() {
        init();
    }

    public void startWith(DbTypeEnum dbType) {
        if(dbType == DbTypeEnum.MYSQL)
            start(new MySQLController());
        else if (dbType == DbTypeEnum.FILE)
            start(new FileDBController());
    }

    private void start(DBConnections dbConnections) {
        view.showHeader();
        view.showMenu();
    }

    private void init() {
        view = new View();
        scanner = new Scanner(System.in);
    }
}
