package com.elseveremirli.socialapp.dto.response;

import com.elseveremirli.socialapp.entities.Like;

import java.time.LocalDateTime;

public record LikePostResponse(
        Long likeId,
        String postUsername,
        String likedUsername,
        LocalDateTime createdDate
){
    public static LikePostResponse convertLikeToLikeResponse(Like like) {
        return new LikePostResponse(like.getLikeId(),like.getPost().getUser().getUsername(),like.getUser().getUsername(), like.getCreatedDate());
    }
}
