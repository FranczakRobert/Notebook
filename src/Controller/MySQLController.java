package Controller;

import Interfaces.DBConnections;
import Model.User;
import Utils.Names;

import java.sql.*;

public class MySQLController implements DBConnections {
    private Connection connection = null;
    @Override
    public void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Names.db_connectionString, Names.db_login,Names.db_password);
        }
        catch(Exception e) {
            System.out.println("[MySQLController] [connectToDB] - " + e.getMessage());
        }
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (login, password) VALUES (?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("User added to the database!");
        }
        catch (SQLException e) {
            System.out.println("\nError connecting to the database: " + e.getMessage());
        }
    }

    @Override
    public void showAllUsers() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next())
                System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3));
            connection.close();
        }
        catch (Exception e) {
            System.out.println("[MySQLController] [showAllUsers] -" + e.getMessage());
        }
    }

    @Override
    public void showUserByID(int id) {
        String query = "SELECT login, password FROM users WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");

                    System.out.println("Login: " + login);
                    System.out.println("Password: " + password);
                }
                else {
                    System.out.println("User with ID: " + id + " - not found.");
                }
            }
        }
        catch (SQLException e) {
            System.out.println("\nError connecting to the database: " + e.getMessage());
        }
    }

    @Override
    public void deleteUserByID(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("[MySQLController] [deleteUserByID] - deleted successfully");
        }
        catch (SQLException e) {
            System.out.println("\nError connecting to the database: " + e.getMessage());
        }
    }
}
