package ru.itis.nightowl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.nightowl.models.ChatRoom;
import ru.itis.nightowl.models.User;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("select c from ChatRoom c where (c.FirstUserId = ?1 and c.SecondUserId = ?2) or (c.FirstUserId = ?2 and c.SecondUserId = ?1)")
    ChatRoom findChatRoomByFirstUserIdAndAndSecondUserId(User firstUserId, User secondUserId);
}
