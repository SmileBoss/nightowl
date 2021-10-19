package ru.itis.nightowl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.nightowl.models.ChatMessage;
import ru.itis.nightowl.repositories.ChatMessageRepository;

import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage save(ChatMessage message) {
        return chatMessageRepository.save(message);
    }

    @Override
    public List<ChatMessage> getMessages(String senderId, String receiverId) {
        return chatMessageRepository.getChatMessagesBySenderIdAndRecipientId(senderId, receiverId);
    }
}
