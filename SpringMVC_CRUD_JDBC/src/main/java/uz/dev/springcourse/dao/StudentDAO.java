package uz.dev.springcourse.dao;

import org.springframework.stereotype.Component;
import uz.dev.springcourse.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDAO {

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




    public  List<Student> index()  {
        List<Student> students = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT st.id, st.name,st.age,st.email,st.gender,(SELECT f.name as faculty FROM faculty f WHERE f.id=st.faculty) FROM Student st;";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Student student = new Student();

                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setEmail(resultSet.getString("email"));
                student.setGender(resultSet.getBoolean("gender"));
                student.setFaculty(resultSet.getString("faculty"));
                System.out.println(student);
                students.add(student);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return students;
    }

    public Student show(int id){
        Student student = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT st.id, st.name,st.age,st.email,st.gender,(SELECT f.name as faculty FROM faculty f WHERE f.id=st.faculty) FROM student st WHERE id = ?;");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            student.setEmail(resultSet.getString("email"));
            student.setGender(resultSet.getBoolean("gender"));
            student.setFaculty(resultSet.getString("faculty"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return student;
    }

    public void save(Student student){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Student(name, age, email, gender, faculty) VALUES(?,?,?,?,(SELECT f.id as faculty FROM faculty f WHERE f.name=?));");

            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.setString(3,student.getEmail());
            preparedStatement.setBoolean(4,student.getGender());
            preparedStatement.setString(5,student.getFaculty());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Student updateStudent){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET name=?,age=?,email=?,gender=?,faculty=(SELECT faculty.id as faculty FROM faculty  WHERE faculty.name=? ) WHERE id=?;");

            preparedStatement.setString(1,updateStudent.getName());
            preparedStatement.setInt(2,updateStudent.getAge());
            preparedStatement.setString(3,updateStudent.getEmail());
            preparedStatement.setBoolean(4,updateStudent.getGender());
            preparedStatement.setString(5,updateStudent.getFaculty());
            preparedStatement.setInt(6,id);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void delete(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM student WHERE id = ?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
