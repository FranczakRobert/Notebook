package View;

public class View {

    public void showHeader() {
        System.out.println("***********************************************************\n" +
                "******************* NOTEPAD JUST FOR FUN ******************\n" +
                "***********************************************************");
    }
    public void showMenu() {
        String menu = "\n\t\tMENU \n" +
                "1) Login \n" +
                "2) Register\n" +
                "3) Exit";
        System.out.println(menu);
    }
}
