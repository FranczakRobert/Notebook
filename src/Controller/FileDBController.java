package Controller;

import Interfaces.DBConnections;
import Model.Nvm;
import Model.User;

import java.io.*;
import java.util.*;

public class FileDBController implements DBConnections {
    private final String fileDBName = "Users_Data";
    private static FileDBController instance = null;
    private Nvm nvm = new Nvm();
    private FileDBController(){}

    public static FileDBController getInstance() {
        if(instance == null)
            instance = new FileDBController();
        return instance;
    }

    @Override
    public void connectToDB() {
        try(FileInputStream inputStream = new FileInputStream(fileDBName)) {
            if(inputStream.available() == 0) {
                System.out.println("[INFO] [FileDBController] [connectToDB] - DataBase is empty...");
            }

            System.out.println("[INFO] [FileDBController] [connectToDB] - Connection established...");
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] [FileDBController] [connectToDB] - " + e.getMessage());
            createFile();

        } catch (IOException e) {
            System.out.println("Erorr...");
        }
    }

    @Override
    public void addUser(User user) {
        nvm.getUsers().add(user);
        serialize();
    }

    @Override
    public void showAllUsers() {
        deserialize();
        for (User user : nvm.getUsers()) {
            System.out.printf("ID: %d Name: %s Password: %s \n%n", user.getId(),user.getLogin(),user.getPassword());
        }
    }

    @Override
    public void showUserByID(int id) {
        deserialize();
        for (User user : nvm.getUsers()) {
            if (user.getId() == id) {
                System.out.println("Login: " + user.getLogin());
                System.out.println("Password: " + user.getPassword());
                break;
            }
        }
    }

    @Override
    public void deleteUserByID(int id) {
        deserialize();
        for (User user : nvm.getUsers()) {
            if (user.getId() == id) {
                nvm.getUsers().remove(user);
                System.out.println("[MySQLController] [deleteUserByID] - deleted successfully");
                break;
            }
        }
    }

    @Override
    public int findUserByUsername(String login) {
        if(deserialize()) {
            for (User user : nvm.getUsers()) {
                if(user.getLogin().equals(login)) {
                    return user.getId();
                }
            }
        }
        else  {
            System.out.println("No user");
            return -1;
        }
        return -1;
    }

    @Override
    public int findUserByUsernameAndPassword(String login, String password) {
        deserialize();
        for (int i = 0 ; i < nvm.getUsers().size(); i++) {
            if(nvm.getUsers().get(i).getLogin().equals(login) && nvm.getUsers().get(i).getPassword().equals(password)) {
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addNote(int id, String note) {
        ArrayList<String> array = nvm.getNotes().get(id);
        if(array == null) {
            ArrayList<String> newArray = new ArrayList<>();
            newArray.add(note);
            nvm.getNotes().put(id,newArray);
        }
        else {
            array.add(note);
            nvm.getNotes().put(id,array);
        }
    }

    @Override
    public ArrayList<String> getAllNotes(int userId) {
        ArrayList<String> notepad = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<String>> entry : nvm.getNotes().entrySet()) {
            if(entry.getKey().equals(userId)) {
                for (int i = 0; i < entry.getValue().size(); i++) {
                    notepad.add(entry.getValue().get(i));
                }
            }
            serialize();
        }
        return notepad;
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
            oos.writeObject(nvm);

        } catch (FileNotFoundException e) {
            System.out.println("[FileDBController] [serialize] - No file...");
        } catch (IOException e) {
            System.out.println("[FileDBController] [serialize] - " + e.getMessage());
        }
    }

    public boolean deserialize() {
        try (FileInputStream fis = new FileInputStream("Users_Data");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
             nvm = (Nvm) ois.readObject();

            for(int i = 0; i < nvm.getUsers().size(); i++) {
                nvm.getUsers().get(i).setId(i);
            }

        } catch (FileNotFoundException e) {
            System.out.println("[FileDBController] [deserialize] - " + e.getMessage());
            return false;
        } catch (IOException ioe) {
            System.out.println("[FileDBController] [deserialize] - " + ioe.getMessage());
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("[FileDBController] [deserialize] - Class not found");
            return false;
        }

//        for (User user : nvm.getUsers()) {
//            System.out.println(user.getLogin());
//            System.out.println(user.getPassword());
//            System.out.println(user.getId());
//
//        }
        return true;
    }

    @Override
    public void run() {
        connectToDB();
    }
}
