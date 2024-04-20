package com.elseveremirli.socialapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotBlank(message = "comment text must not be empty")
    @Column(columnDefinition = "TEXT")
    private String commentText;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;
}
