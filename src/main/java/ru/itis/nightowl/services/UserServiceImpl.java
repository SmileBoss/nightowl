package ru.itis.nightowl.services;

import org.springframework.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.nightowl.dto.IconForm;
import ru.itis.nightowl.dto.UserForm;
import ru.itis.nightowl.models.User;
import ru.itis.nightowl.repositories.UserRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Page<User> getAllUserPageable(Pageable pageable, String search) {

        if (search != null) {
            return userRepository.search(StringUtils.capitalize(search.toLowerCase(Locale.ROOT)), pageable);
        } else {
            return userRepository.findAll(pageable);
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void signUp(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .firstname(userForm.getFirstname())
                .lastname(userForm.getLastname())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .icon("/assets/icon-user.svg")
                .build();

        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public void updateIcon(IconForm iconForm, User user) {
        user.setIcon(iconForm.getImage());
        userRepository.save(user);
    }

    @Override
    public void banUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (!user.get().isAdmin()) {
                user.get().setState(User.State.BANNED);
                userRepository.save(user.get());
            }
        }
    }

    @Override
    public void activeUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (!user.get().isAdmin()) {
                user.get().setState(User.State.ACTIVE);
                userRepository.save(user.get());
            }
        }
    }

    @Override
    public void banAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.isAdmin()) {
                user.setState(User.State.BANNED);
                userRepository.save(user);
            }
        }
    }

}
