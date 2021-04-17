package uz.dev.springcourse.dao;

import org.springframework.stereotype.Component;
import uz.dev.springcourse.models.Student;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDAO {

    private static int STUDENT_ID;
    private List<Student> students;

    {
        students = new ArrayList<>();

        students.add(new Student(++STUDENT_ID,"Aziz",20,"aziz@mail.ru",true ,"Math"));
        students.add(new Student(++STUDENT_ID,"Lola",19, "lola@gmail.com",false ,"Chemistry"));
        students.add(new Student(++STUDENT_ID,"Gulnora",20,"gulnora@mail.ru", false,"Biology"));
        students.add(new Student(++STUDENT_ID,"Farrux",18,"farrux@yahoo.com",true ,"Mechanical"));

    }

    public  List<Student> index(){
        return students;
    }

    public Student show(int id){
        return students.stream().filter(student->student.getId()==id).findAny().orElse(null);
    }

    public void save(Student student){
        student.setId(++STUDENT_ID);
        students.add(student);
    }

    public void update(int id, Student updateStudent){
        Student studentToBeUpdate = show(id);

        studentToBeUpdate.setName(updateStudent.getName());
        studentToBeUpdate.setAge(updateStudent.getAge());
        studentToBeUpdate.setEmail(updateStudent.getEmail());
        studentToBeUpdate.setGender(updateStudent.getGender());
        studentToBeUpdate.setFaculty(updateStudent.getFaculty());
    }

    public void delete(int id){
        students.removeIf(s->s.getId()==id);
    }


}
