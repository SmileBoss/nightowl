package ru.itis.nightowl.dto;

import lombok.Data;

@Data
public class UserForm {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
