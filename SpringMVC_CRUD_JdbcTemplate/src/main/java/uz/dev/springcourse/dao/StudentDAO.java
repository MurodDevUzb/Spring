package uz.dev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.dev.springcourse.models.Student;

import java.sql.*;
import java.util.List;

@Component
public class StudentDAO {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public  List<Student> index()  {
        return jdbcTemplate.query("SELECT st.id, st.name,st.age,st.email,st.gender,(SELECT f.name as faculty FROM faculty f WHERE f.id=st.faculty) FROM Student st;", new BeanPropertyRowMapper<>(Student.class));
    }

    public Student show(int id){

        return jdbcTemplate.query("SELECT st.id, st.name,st.age,st.email,st.gender,(SELECT f.name as faculty FROM faculty f WHERE f.id=st.faculty) FROM student st WHERE id = ?;",
                new Object[]{id},new BeanPropertyRowMapper<>(Student.class)).stream().findAny().orElse(null);
    }

    public void save(Student student){
       jdbcTemplate.update("INSERT INTO Student(name, age, email, gender, faculty) VALUES(?,?,?,?,(SELECT f.id as faculty FROM faculty f WHERE f.name=?));",student.getName(),
               student.getAge(),student.getEmail(),student.getGender(),student.getFaculty());
    }

    public void update(int id, Student updateStudent){

        jdbcTemplate.update("UPDATE student SET name=?,age=?,email=?,gender=?,faculty=(SELECT faculty.id as faculty FROM faculty  WHERE faculty.name=? ) WHERE id=?;",
                updateStudent.getName(),updateStudent.getAge(),updateStudent.getEmail(),updateStudent.getGender(),updateStudent.getFaculty(),id);

    }

    public void delete(int id){
       jdbcTemplate.update("DELETE FROM student WHERE id = ?",id);
    }


}
