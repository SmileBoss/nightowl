package ru.itis.nightowl.services;
import ru.itis.nightowl.models.User;

import java.util.Map;
import java.util.Set;

public interface FriendshipService {

    Map<String, Set<User>> getFriends(Long userId);

    Set<User> getAcceptedFriendshipUsers(Long id);

    void deleteFriendship(User user, Long friendId);

    void acceptFriendship(User user, Long friendId);

    void addToFriends(User user, Long friendId);

    Boolean checkFriendship(User user, User friend);

}
