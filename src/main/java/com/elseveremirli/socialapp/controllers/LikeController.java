package com.elseveremirli.socialapp.controllers;

import com.elseveremirli.socialapp.dto.response.LikeCommentResponse;
import com.elseveremirli.socialapp.dto.response.LikePostResponse;
import com.elseveremirli.socialapp.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/user/{username}")
    public List<LikePostResponse> getAllUserLikeByUsername(@PathVariable String username) {
        return likeService.getAllUserLikeByUsername(username);
    }

    @GetMapping("/post/{postId}")
    public List<LikePostResponse> getAllUserLikeByPostId(@PathVariable Long postId) {
        return likeService.getAllUserLikeByPostId(postId);
    }

    @GetMapping("/comment/{commentId}")
    public List<LikeCommentResponse> getAllUserLikeByCommentId(@PathVariable Long commentId) {
        return likeService.getAllUserLikeByCommentId(commentId);
    }

    @PostMapping("/post/{username}/{postId}")
    public LikePostResponse createLikeByPostId(@PathVariable String username, @PathVariable Long postId) {
        return likeService.createLikeByPostId(username,postId);
    }

    @PostMapping("/comment/{username}/{commentId}")
    public LikeCommentResponse createLikeByCommentId(@PathVariable String username, @PathVariable Long commentId) {
        return likeService.createLikeByCommentId(username,commentId);
    }


    @DeleteMapping("/post/{username}/{postId}/{likeId}")
    public ResponseEntity<String > deleteLikeByLikeId(@PathVariable String username, @PathVariable Long likeId, @PathVariable Long postId) {
        if (likeService.deleteLikeByLikeId(username,likeId,postId)){
            return ResponseEntity.ok("Successfully deleted");
        }
        return ResponseEntity.badRequest().body("Failed to delete like");
    }

    @DeleteMapping("/comment/{username}/{commentId}/{likeId}")
    public ResponseEntity<String> deleteLikeByCommentId(@PathVariable String username, @PathVariable Long commentId, @PathVariable Long likeId) {
        if(likeService.deleteLikeByCommentId(username,likeId,commentId)){
            return ResponseEntity.ok("Successfully deleted");
        }
        return ResponseEntity.badRequest().body("Failed to delete like");
    }
}
