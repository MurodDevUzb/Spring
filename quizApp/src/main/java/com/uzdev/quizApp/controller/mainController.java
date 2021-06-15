package com.uzdev.quizApp.controller;

import com.uzdev.quizApp.model.Answer;
import com.uzdev.quizApp.model.Question;
import com.uzdev.quizApp.model.Questions;
import com.uzdev.quizApp.model.User;
import com.uzdev.quizApp.repository.QuestionRepo;
import com.uzdev.quizApp.service.SecurityService;
import com.uzdev.quizApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class mainController {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    UserService userService;

    Questions questions;


    @Autowired
    SecurityService securityService;

    @GetMapping("/quiz")
    public String index(Model model){
        List<Question> question = questionRepo.findAll();
        questions = new Questions(question);
        model.addAttribute("questions", questions);

        return "quiz/index";
    }

    @PostMapping("/quiz")
    public String quiz(@ModelAttribute("questions") Questions resQuestions, Model model){
//        List<Question> questions = questionRepo.findAll();

//        List<Question> questions = resQuestions.getQuestions();

        System.out.println(resQuestions.getQuestions());


//        model.addAttribute("questions", questions);

        return "quiz/index";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        if (securityService.isAuthenticated()) {
            return "redirect:/quiz";
        }
        return "quiz/login";
    }

    @GetMapping("/registration")
    public String viewRegistrationPage(Model model ) {
        if (securityService.isAuthenticated()) {
            return "redirect:/quiz";
        }

        model.addAttribute("userForm", new User());
        return "quiz/registration";
    }

    @PostMapping("/registration")
    public String postRegistrationPage(@ModelAttribute("userForm") @Valid User userFrom, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "quiz/registration";
        }
        if(!userFrom.getPassword().equals(userFrom.getPasswordConfirm())){
            model.addAttribute("passwordError", "Password mismatch");
        }
        if(!userService.saveUser(userFrom)){
            model.addAttribute("usernameError", "A user with the same name already exists");
            return "quiz/registration";
        }
        return "redirect:/quiz";
    }
}
