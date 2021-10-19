package ru.itis.nightowl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.nightowl.models.Friendship;
import ru.itis.nightowl.models.User;
import ru.itis.nightowl.repositories.FriendshipRepository;
import ru.itis.nightowl.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public Map<String, Set<User>> getFriends(Long userId) {

        List<Friendship> requests = friendshipRepository.findFriendshipBySenderIdOrReceiverId(userId, userId);

        Map<Boolean, List<Friendship>> requestMap = requests.stream()
                .collect(Collectors.partitioningBy(Friendship::getAccepted));

        Set<User> usersNotAcceptedRequests = requestMap.get(false).stream()
                .filter(r -> r.getSender().getId().equals(userId))
                .map(Friendship::getReceiver)
                .collect(Collectors.toSet());

        Set<User> notAcceptedRequestsToUser = requestMap.get(false).stream()
                .filter(r -> r.getReceiver().getId().equals(userId))
                .map(Friendship::getSender)
                .collect(Collectors.toSet());

        Set<User> friendsOfUser = requestMap.get(true).stream()
                .map(r -> r.getSender().getId().equals(userId) ? r.getReceiver() : r.getSender())
                .collect(Collectors.toSet());

        Map<String, Set<User>> map = new HashMap<>();
        map.put("usersNotAcceptedRequests", usersNotAcceptedRequests);
        map.put("notAcceptedRequestsToUser", notAcceptedRequestsToUser);
        map.put("friendsOfUser", friendsOfUser);
        return map;
    }

    @Override
    @Transactional
    public Set<User> getAcceptedFriendshipUsers(Long id) {
        List<Friendship> friends = friendshipRepository.findAcceptedFriendshipUsers(id);
        return friends.stream()
                .map(r -> r.getSender().getId().equals(id) ? r.getReceiver() : r.getSender())
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void deleteFriendship(User user, Long friendId) {
        User friend = userRepository.findById(friendId).orElseThrow(IllegalArgumentException::new);
        friendshipRepository.deleteFriendRequests(user, friend);
    }

    @Override
    @Transactional
    public void acceptFriendship(User user, Long friendId) {
        User friend = userRepository.findById(friendId).orElseThrow(IllegalArgumentException::new);
        friendshipRepository.deleteFriendRequests(user, friend);
        friendshipRepository.addFriendship(user, friend);
    }

    @Override
    @Transactional
    public void addToFriends(User user, Long friendId) {
        User friend = userRepository.findById(friendId).orElseThrow(IllegalArgumentException::new);
        friendshipRepository.addToFriends(user, friend);
    }

    @Override
    @Transactional
    public Boolean checkFriendship(User user, User friend) {
        return friendshipRepository.checkFriendshipExists(user, friend);
    }

}
