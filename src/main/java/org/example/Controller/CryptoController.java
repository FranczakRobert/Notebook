package org.example.Controller;

public class CryptoController {

    private static CryptoController instance = null;

    public static CryptoController getInstance() {

        if(instance == null) {
            instance = new CryptoController();
        }
        return  instance;
    }

    private CryptoController(){}

    public String encryptPassword(char[] psswd) {

        StringBuilder encryptedPassword = new StringBuilder();

        for (char character : psswd) {
            encryptedPassword.append(character);
        }

        return String.format("%s", encryptedPassword.toString().hashCode());
    }
}
