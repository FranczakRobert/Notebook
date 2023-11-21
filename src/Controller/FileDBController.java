package Controller;

import Interfaces.DBConnections;
import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDBController implements DBConnections {

    private List<User> users = new ArrayList<>();
    private final String fileDBName = "Users_Data";

    @Override
    public void connectToDB() {
        try(FileInputStream inputStream = new FileInputStream(fileDBName)) {
            if(inputStream.available() == 0) {
                System.out.println("[INFO] [FileDBController] [connectToDB] - DataBase is empty...");
            }

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] [FileDBController] [connectToDB] - " + e.getMessage());
            createFile();

        } catch (IOException e) {
            System.out.println("Erorr...");
        }
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void showAllUsers() {
        deserialize();
        for (User user : users) {
            System.out.printf("ID: %d Name: %s Password: %s \n%n", user.getId(),user.getLogin(),user.getPassword());
        }
    }

    @Override
    public void showUserByID(int id) {

    }


    private void createFile() {
        try {
            File file = new File(fileDBName);
            if(file.createNewFile()) {
                System.out.println("[INFO][FileDBController] [createFile] - DB File created successfully...");
            } else {
                System.out.println("[INFO][FileDBController] [createFile] - File already exists.");
            }
        } catch (IOException ioException) {
            System.out.println("[FileDBController] [connectToDB] - " + ioException.getMessage());
        }
    }

    public void serialize()  {
        try(FileOutputStream output = new FileOutputStream("Users_Data");
            ObjectOutputStream oos = new ObjectOutputStream(output)) {
            oos.writeObject(users);

        } catch (FileNotFoundException e) {
            System.out.println("Error - No file...");
        } catch (IOException e) {
            System.out.println("Erorr...");
        }
    }

    public void deserialize(){
        try (FileInputStream fis = new FileInputStream("Users_Data");
             ObjectInputStream ois = new ObjectInputStream(fis);) {

            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[FileDBController] [deserialize] - " + e.getMessage());
        } catch (IOException ioe) {
            System.out.println("[FileDBController] [deserialize] - " + ioe.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
        }
    }
}
