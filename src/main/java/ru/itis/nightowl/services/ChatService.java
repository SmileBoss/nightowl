package ru.itis.nightowl.services;

import ru.itis.nightowl.models.ChatRoom;

public interface ChatService {
    public ChatRoom getChatRoom(String sender, String recipient);
}
