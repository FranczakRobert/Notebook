package Controller;

import Interfaces.DBConnections;
import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDBController implements DBConnections {
    private List<User> users = new ArrayList<>();
    private Map<Integer,String> notes = new HashMap<>();
    private final String fileDBName = "Users_Data";
    private static FileDBController instance = null;
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
        users.add(user);
        serialize();
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
        deserialize();
        for (User user : users) {
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
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                System.out.println("[MySQLController] [deleteUserByID] - deleted successfully");
                break;
            }
        }
    }

    @Override
    public int findUserByUsername(String login) {
        if(deserialize()) {
            for (User user : users) {
                if(user.getLogin().equals(login)) {
                    return user.getId();
                }
            }
        }
        else  {
            System.out.println("No user");
            return -1;
        }

        return 0;
    }

    @Override
    public int findUserByUsernameAndPassword(String login, String password) {
        deserialize();
        for (User user : users) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public void addNote(int id, String note) {
        notes.put(id,note);
    }

    @Override
    public ArrayList<String> getAllNotes(int userId) {
        ArrayList<String> notepad = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : notes.entrySet()) {
            if(entry.getKey().equals(userId)) {
                notepad.add(entry.getValue());
            }
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
            oos.writeObject(users);

        } catch (FileNotFoundException e) {
            System.out.println("[FileDBController] [serialize] - No file...");
        } catch (IOException e) {
            System.out.println("[FileDBController] [serialize] - " + e.getMessage());
        }
    }

    public boolean deserialize(){
        try (FileInputStream fis = new FileInputStream("Users_Data");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            users = (List<User>) ois.readObject();

            for(int i = 0; i < users.size(); i++) {
                users.get(i).setId(i);
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

        for (User user : users) {
            System.out.println(user.getLogin());
            System.out.println(user.getPassword());
            System.out.println(user.getId());

        }
        return true;
    }

    @Override
    public void run() {
        connectToDB();
    }
}
