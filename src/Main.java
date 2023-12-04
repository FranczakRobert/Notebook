import Controller.MySQLController;
import View.UserPanelGui;

public class Main {
    public static void main(String[] args) {
//        new MainController().startWith(DbTypeEnum.MYSQL);

        new UserPanelGui(MySQLController.getInstance());
    }
}