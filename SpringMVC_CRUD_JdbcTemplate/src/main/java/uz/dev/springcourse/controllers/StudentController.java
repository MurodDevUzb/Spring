package uz.dev.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.dev.springcourse.dao.FacultyDAO;
import uz.dev.springcourse.dao.StudentDAO;
import uz.dev.springcourse.models.Student;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {



    private final StudentDAO studentDAO;
    private final FacultyDAO facultyDAO;


    @Autowired
    public StudentController(FacultyDAO facultyDAO, StudentDAO studentDAO) {
        this.facultyDAO = facultyDAO;
        this.studentDAO = studentDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("students",studentDAO.index());

        return "student/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model){
        model.addAttribute("student", studentDAO.show(id));


        return "student/show";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student")Student student, Model model){
        List<String> faculties = facultyDAO.all();
        model.addAttribute("faculties",faculties);
        return "student/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "student/new";
        }

        studentDAO.save(student);
        return "redirect:/student";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("student", studentDAO.show(id));
        List<String> faculties = facultyDAO.all();
        model.addAttribute("faculties",faculties);
        return "student/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "student/edit";
        }
        studentDAO.update(id,student);
        return "redirect:/student";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        studentDAO.delete(id);
        return "redirect:/student";
    }

}
