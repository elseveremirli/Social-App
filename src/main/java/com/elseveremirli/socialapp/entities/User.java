package com.elseveremirli.socialapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "username must not be empty")
    @Size(min = 2, max = 30, message = "required username must be min 2, max 30 character")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "name must not be empty")
    @Size(min = 2, max = 20, message = "required name must be min 2, max 20 character")
    private String name;

    @NotBlank(message = "surname must not be empty")
    @Size(min = 2, max = 20, message = "required surname must be min 2, max 20 character")
    private String surname;

    @NotBlank(message = "email must not be empty")
    @Column(unique = true)
    @Email
    private String email;

    @NotBlank(message = "password must not be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, and 1 digit, with a minimum length of 8 characters")
    private String password;

    @NotNull(message = "birthdate must not be empty")
    @Column(columnDefinition = "DATE")
    private LocalDate birthdate;

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts;
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likes;

}
