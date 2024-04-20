package com.elseveremirli.socialapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;
}
