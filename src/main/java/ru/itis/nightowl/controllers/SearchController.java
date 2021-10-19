package ru.itis.nightowl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.nightowl.models.User;
import ru.itis.nightowl.security.details.UserDetailsImpl;
import ru.itis.nightowl.services.FriendshipService;
import ru.itis.nightowl.services.UserService;


@Controller
public class SearchController {

    @Autowired
    UserService userService;

    @Autowired
    FriendshipService friendshipService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/search")
    public String userSearchPage(Model model, @RequestParam(value = "search", required = false) String search,
                                 @AuthenticationPrincipal UserDetailsImpl user,
                                 @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 5) Pageable pageable){

        Page<User> users = userService.getAllUserPageable(pageable, search);
        User client = userService.getByEmail(user.getUsername());
        model.addAttribute("users", users);
        model.addAttribute("client", client);


        return "search-page";
    }
}
