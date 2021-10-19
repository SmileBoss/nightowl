package ru.itis.nightowl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nightowl.models.ChatMessage;
import ru.itis.nightowl.models.ChatRoom;
import ru.itis.nightowl.models.User;
import ru.itis.nightowl.security.details.UserDetailsImpl;
import ru.itis.nightowl.services.ChatMessageService;
import ru.itis.nightowl.services.ChatService;
import ru.itis.nightowl.services.UserService;

@Controller
public class ChatController {

    @Autowired
    UserService userService;

    @Autowired
    ChatService chatService;

    @Autowired
    ChatMessageService chatMessageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void chat(@Payload ChatMessage message) {
        ChatRoom chat = chatService.getChatRoom(message.getSenderId(), message.getRecipientId());
        message.setChatRoom(chat);
        message = chatMessageService.save(message);

        messagingTemplate.convertAndSendToUser(message.getRecipientId(),
                "queue/messages", message);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/chat")
    public String getChatPage() {
        return "chat";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/chat/{id}")
    public String getRecipientId(@PathVariable("id") Long id,
                                 RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userService.getUserById(id).orElseThrow(IllegalArgumentException::new);
        User sender = userService.getByEmail(userDetails.getUsername());
        redirectAttributes.addFlashAttribute("recipient", user);
        redirectAttributes.addFlashAttribute("sender", sender);
        return "redirect:/chat";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/users/me")
    public ResponseEntity<?> getAuthUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userService.getByEmail(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/users/messages/{receptorId}")
    public ResponseEntity<?> getAllMessages(@AuthenticationPrincipal UserDetails user,
                                            @PathVariable("receptorId") String receptorId) {
        User sender = userService.getByEmail(user.getUsername());
        return ResponseEntity.ok(chatMessageService.getMessages(String.valueOf(sender.getId()), receptorId));
    }

}
