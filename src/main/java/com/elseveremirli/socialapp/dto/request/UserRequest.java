package com.elseveremirli.socialapp.dto.request;

import com.elseveremirli.socialapp.entities.User;

import java.time.LocalDate;

public record UserRequest(
        String username,
        String name,
        String surname,
        String email,
        String password,
        LocalDate birthDate
) {

    public static User convertUserRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username);
        user.setName(userRequest.name);
        user.setSurname(userRequest.surname);
        user.setEmail(userRequest.email);
        user.setPassword(userRequest.password);
        user.setBirthdate(userRequest.birthDate);
        return user;
    }
}
