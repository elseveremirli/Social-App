package com.elseveremirli.socialapp.dto.response;

import com.elseveremirli.socialapp.entities.User;

import java.time.LocalDate;

public record UserResponse(
        String username,
        String name,
        String surname,
        String email,
        LocalDate birthDate
) {
    public static UserResponse convertUserToUserResponse(User savedUser) {
        UserResponse userResponse = new UserResponse(
                savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getSurname(),
                savedUser.getEmail(),
                savedUser.getBirthdate()
        );
        return userResponse;
    }
}
