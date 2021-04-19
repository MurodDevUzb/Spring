package uz.dev.springcourse.models;

import javax.validation.constraints.*;


public class Student {
    private int id;

    @NotEmpty(message = "Write name")
    @Size(min = 2,max = 20,message = "Write correct name")
    private String name;
    @Min(value = 0, message = "Min 0")
    private int age;
    @NotEmpty(message = "Write email")
    @Email(message = "Write correct email")
    private String email;
    private boolean gender;
    private String faculty;


    public Student() {
    }


    public Student(int id, String name, int age, String email, boolean gender, String faculty) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.faculty = faculty;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
