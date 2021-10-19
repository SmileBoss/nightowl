package ru.itis.nightowl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.nightowl.models.Friendship;
import ru.itis.nightowl.models.User;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    List<Friendship> findFriendshipBySenderIdOrReceiverId(Long senderId, Long receivedId);

    @Query("SELECT f from Friendship f WHERE (f.sender.id = :userId OR f.receiver.id = :userId) " +
            "AND f.accepted = true")
    List<Friendship> findAcceptedFriendshipUsers(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Friendship f WHERE (f.sender = :user AND f.receiver = :friend) " +
            "OR (f.sender = :friend AND f.receiver = :user)")
    void deleteFriendRequests(@Param("user") User user, @Param("friend") User friend);

    @Modifying(clearAutomatically = true)
    @Query(value = "insert into friendship (user_sender, user_receiver, accepted) VALUES (:user, :friend, true)",
            nativeQuery = true)
    void addFriendship(@Param("user") User user, @Param("friend") User friend);

    @Modifying(clearAutomatically = true)
    @Query(value = "insert into friendship (user_sender, user_receiver, accepted) VALUES (:user, :friend, false)",
            nativeQuery = true)
    void addToFriends(@Param("user") User user, @Param("friend") User friend);

    @Query("SELECT case when count(f)> 0 then true else false end FROM Friendship f WHERE (f.sender = :user AND f.receiver = :friend) " +
            "OR (f.sender = :friend AND f.receiver = :user)")
    boolean checkFriendshipExists(@Param("user") User user, @Param("friend") User friend);

}
