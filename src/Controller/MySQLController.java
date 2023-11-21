package Controller;

import Interfaces.DBConnections;
import Model.User;

import java.sql.*;

public class MySQLController implements DBConnections {
    private Connection connection = null;
    @Override
    public void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/note","admin","admin");
        }catch(Exception e){ System.out.println("[MySQLController] [connectToDB] - " + e.getMessage());}
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (login, password) VALUES (?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("User added to the database!");
        } catch (SQLException e) {
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
        } catch (Exception e) {System.out.println("[MySQLController] [showAllUsers] -" + e.getMessage());}
    }

    @Override
    public void showUserByID(int id) {

    }
}
