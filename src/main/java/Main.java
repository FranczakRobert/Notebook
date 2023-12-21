package main.java;

import main.java.Controller.MainController;
import main.java.Utils.DbTypeEnum;

public class Main {
    public static void main(String[] args) {
        new MainController().startWith(DbTypeEnum.FILE);
    }
}