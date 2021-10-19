package ru.itis.nightowl.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.nightowl.dto.IconForm;
import ru.itis.nightowl.models.User;
import ru.itis.nightowl.security.details.UserDetailsImpl;
import ru.itis.nightowl.services.FriendshipService;
import ru.itis.nightowl.services.UserService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class ProfileController {

    @Value("${upload.path}")
    private String path;

    @Value("${static.path}")
    private String staticPath;

    @Autowired
    UserService userService;

    @Autowired
    FriendshipService friendshipService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        User client = userService.getByEmail(user.getUsername());
        Map<String, Set<User>> friends = friendshipService.getFriends(client.getId());
        Integer sum = friends.get("friendsOfUser").size();

        model.addAttribute("user", client);
        model.addAttribute("friendsOfUser", sum);
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/{id}")
    public String getProfilePageGetById(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal UserDetailsImpl clientAut) {
        User user = userService.getUserById(id).orElseThrow(IllegalArgumentException::new);
        User client = userService.getByEmail(clientAut.getUsername());
        model.addAttribute("user", user);
        Map<String, Set<User>> friends = friendshipService.getFriends(user.getId());
        Boolean friendship = friendshipService.checkFriendship(client, user);
        Integer sum = friends.get("friendsOfUser").size();
        model.addAttribute("usersHaveFriendship", friendship);
        model.addAttribute("friendsOfUser", sum);
        return "profile-another-user";
    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/profile/icon/update")
//    public String updateIcon(IconForm icon, @AuthenticationPrincipal UserDetailsImpl client){
//        User user = userService.getByEmail(client.getUsername());
//        userService.updateIcon(icon, user);
//        return "redirect:/profile";
//    }

    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/icon/update")
    public String updateIcon(@RequestParam("image") MultipartFile file, @AuthenticationPrincipal UserDetailsImpl client) {
        User user = userService.getByEmail(client.getUsername());
        if (file != null) {
            String oldImage = staticPath + user.getIcon();
            if (Files.exists(Path.of(oldImage))) {
                File file1 = new File(oldImage);
                System.out.println(file1.getName());
                if (!file1.getName().equals("icon-user.svg")) {
                    if (file1.delete()) {
                        System.out.println(file.getName() + " deleted");
                    } else {
                        System.out.println(file.getName() + " not deleted");
                    }
                }
            }
            String uuidFile = UUID.randomUUID().toString();
            String icon = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(path + "/" + icon));
            IconForm iconForm = new IconForm();
            iconForm.setImage("/assets/" + icon);
            userService.updateIcon(iconForm, user);
        }

        return "redirect:/profile";
    }

}
