package ru.itis.nightowl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.nightowl.models.User;
import ru.itis.nightowl.security.details.UserDetailsImpl;
import ru.itis.nightowl.services.FriendshipService;
import ru.itis.nightowl.services.UserService;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user/friends")
public class FriendsController {

    @Autowired
    FriendshipService friendshipService;

    @Autowired
    UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getFriends (Model model, @AuthenticationPrincipal UserDetailsImpl user) {
        User client = userService.getByEmail(user.getUsername());
        Map<String, Set<User>> friends = friendshipService.getFriends(client.getId());
        model.addAttribute("usersNotAcceptedRequests", friends.get("usersNotAcceptedRequests"));
        model.addAttribute("notAcceptedRequestsToUser", friends.get("notAcceptedRequestsToUser"));
        model.addAttribute("friendsOfUser", friends.get("friendsOfUser"));
        return "friends";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{friendId}/decline")
    public String deleteFriendship(@PathVariable Long friendId,@AuthenticationPrincipal UserDetailsImpl user) {
        User client = userService.getByEmail(user.getUsername());
        friendshipService.deleteFriendship(client, friendId);
        return "redirect:/user/friends";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{friendId}/accept")
    public String acceptFriendship(@PathVariable Long friendId, @AuthenticationPrincipal UserDetailsImpl user) {
        User client = userService.getByEmail(user.getUsername());
        friendshipService.acceptFriendship(client, friendId);
        return "redirect:/user/friends";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{friendId}/addToFriends")
    public String addToFriends(@PathVariable Long friendId, @AuthenticationPrincipal UserDetailsImpl user) {
        User client = userService.getByEmail(user.getUsername());
        friendshipService.addToFriends(client, friendId);
        return "redirect:/user/friends";
    }


}
