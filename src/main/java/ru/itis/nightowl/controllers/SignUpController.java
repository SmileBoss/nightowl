package ru.itis.nightowl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.nightowl.dto.UserForm;
import ru.itis.nightowl.services.UserService;

import javax.annotation.security.PermitAll;


@Controller
public class SignUpController {

    @Autowired
    UserService userService;

    @PermitAll
    @GetMapping("/signUp")
    public String getRegisterPage() {
        return "signUp";
    }
    @PermitAll
    @PostMapping("/signUp")
    public String addUser(UserForm userForm) {
       userService.signUp(userForm);
       return "redirect:/signIn";
    }
}
