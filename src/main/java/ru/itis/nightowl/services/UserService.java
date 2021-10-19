package ru.itis.nightowl.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.itis.nightowl.dto.IconForm;
import ru.itis.nightowl.dto.UserForm;
import ru.itis.nightowl.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();
    Page<User> getAllUserPageable(Pageable pageable, String search);
    Optional<User> getUserById(Long id);
    void signUp(UserForm userForm);
    User getByEmail(String email);
    void updateIcon(IconForm iconForm, User user);

    void banUserById(Long id);
    void activeUserById(Long id);
    void banAll();
}
