import Controller.MainController;
import Utils.DbTypeEnum;

public class Main {
    public static void main(String[] args) {
        new MainController().startWith(DbTypeEnum.MYSQL);

//        new UserPanelGui(MySQLController.getInstance(),0);
    }
}