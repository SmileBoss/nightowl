package ru.itis.nightowl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.nightowl.models.ChatMessage;

import javax.transaction.Transactional;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Transactional
    @Query("select m from ChatMessage m where (m.senderId= :senderId and m.recipientId = :receiverId) OR" +
            "(m.senderId= :receiverId and m.recipientId= :senderId)")
    public List<ChatMessage> getChatMessagesBySenderIdAndRecipientId(@Param("senderId") String senderId,
                                                                     @Param("receiverId") String receiverId);

}
