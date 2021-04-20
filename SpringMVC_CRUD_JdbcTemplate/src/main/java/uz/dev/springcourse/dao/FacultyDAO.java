package uz.dev.springcourse.dao;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FacultyDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/student";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty007";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<String> all(){
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT name FROM faculty";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                list.add(resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
}
