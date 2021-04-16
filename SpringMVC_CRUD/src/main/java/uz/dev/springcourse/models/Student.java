package uz.dev.springcourse.models;

public class Student {
    private int id;
    private String name;
    private int age;
    private String email;


    private String faculty;


    public Student() {
    }

    public Student(int id, String name, int age, String email, String faculty) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
