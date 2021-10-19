package ru.itis.nightowl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.nightowl.models.ChatRoom;
import ru.itis.nightowl.models.User;
import ru.itis.nightowl.repositories.ChatRoomRepository;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserService userService;

    @Override
    public ChatRoom getChatRoom(String sender, String recipient) {
        User firstUser = userService.getUserById(Long.valueOf(sender)).orElseThrow(IllegalArgumentException::new);
        User secondUser = userService.getUserById(Long.valueOf(recipient)).orElseThrow(IllegalArgumentException::new);
        ChatRoom chatRoom = chatRoomRepository.findChatRoomByFirstUserIdAndAndSecondUserId(firstUser, secondUser);
        if (chatRoom == null){
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setFirstUserId(firstUser);
            newChatRoom.setSecondUserId(secondUser);
            return chatRoomRepository.save(newChatRoom);
        }
        return chatRoom;
    }
}
