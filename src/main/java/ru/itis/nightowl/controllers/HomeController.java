package ru.itis.nightowl.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;

@Controller
public class HomeController {

    @PermitAll
    @GetMapping("/")
    public String starterPage(Authentication authentication) {
        if (authentication != null){
            return "redirect:/profile";
        } else {
            return "home";
        }
    }
}
