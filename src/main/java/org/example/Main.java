package org.example;

import org.example.Controller.MainController;
import org.example.Utils.DbTypeEnum;

public class Main {
    public static void main(String[] args) {
        new MainController().startWith(DbTypeEnum.FILE);
    }
}