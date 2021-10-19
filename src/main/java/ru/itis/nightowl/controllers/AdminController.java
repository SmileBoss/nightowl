package ru.itis.nightowl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.nightowl.services.UserService;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUser());
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/ban/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        userService.banUserById(id);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/active/{id}")
    public String activeUserById(@PathVariable("id") Long id){
        userService.activeUserById(id);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/banAll")
    public String banUsers() {
        userService.banAll();
        return "redirect:/admin";
    }

}
