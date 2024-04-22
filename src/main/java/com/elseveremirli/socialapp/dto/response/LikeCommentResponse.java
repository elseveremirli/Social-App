package com.elseveremirli.socialapp.dto.response;

import com.elseveremirli.socialapp.entities.Like;

import java.time.LocalDateTime;

public record LikeCommentResponse(
        Long likeId,
        String commentUsername,
        String likedUsername,
        LocalDateTime createdDate
) {
    public static LikeCommentResponse convertLikeToLikeResponse(Like like) {
        return new LikeCommentResponse(like.getLikeId(),like.getComment().getUser().getUsername(),like.getUser().getUsername(), like.getCreatedDate());
    }
}
