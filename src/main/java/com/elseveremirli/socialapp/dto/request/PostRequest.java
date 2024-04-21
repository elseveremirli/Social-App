package com.elseveremirli.socialapp.dto.request;

import com.elseveremirli.socialapp.entities.Post;
import com.elseveremirli.socialapp.entities.User;

import java.time.LocalDateTime;

public record PostRequest(
        String postText
) {
    public static Post convertPostRequestToPost(PostRequest postRequest, User user) {
        Post post = new Post();
        post.setPostText(postRequest.postText);
        post.setUser(user);
        post.setCreateDate(LocalDateTime.now());
        return post;
    }
}
