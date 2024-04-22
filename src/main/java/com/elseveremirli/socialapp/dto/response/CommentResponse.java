package com.elseveremirli.socialapp.dto.response;

import com.elseveremirli.socialapp.entities.Comment;

import java.time.LocalDateTime;

public record CommentResponse(String username,
                              Long postId,
                              String commentText,
                              LocalDateTime createdDate) {
    public static CommentResponse converteCommnetToCommentResponse(Comment comment) {
        return new CommentResponse(comment.getUser().getUsername(), comment.getPost().getPostId(), comment.getCommentText(), comment.getCreatedDate());
    }
}
