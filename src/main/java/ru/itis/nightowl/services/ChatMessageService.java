package ru.itis.nightowl.services;

import ru.itis.nightowl.models.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    public ChatMessage save(ChatMessage message);
    public List<ChatMessage> getMessages(String senderId, String receiverId);
}
