package View;

import Utils.Read;

public class View {
    public void showMenu() {
        String menu = "\n\t\tMENU \n" +
                "1) Login \n" +
                "2) Register\n" +
                "3) Exit";
        System.out.println(menu);
    }

    public String[] showLogin() {
        String[] credentials = new String[2];
        System.out.println("Login: ");
        credentials[0] = Read.scanner.nextLine();
        System.out.println("Password: ");
        credentials[1] = Read.scanner.nextLine();
        return credentials;
    }

    public String[] showRegister() {
        String[] credentials = new String[3];

        System.out.println("New Login: ");
        credentials[0] = Read.scanner.nextLine();
        System.out.println("New password: ");
        credentials[1] = Read.scanner.nextLine();
        System.out.println("Confirm password: ");
        credentials[2] = Read.scanner.nextLine();
        return credentials;
    }

    public void welcome() {
        System.out.println("Welcome");
    }

    public void wrongCredentials() {
        System.out.println("Wrong credentials...");
    }

}
