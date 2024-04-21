package com.elseveremirli.socialapp.controllers;

import com.elseveremirli.socialapp.dto.request.PostRequest;
import com.elseveremirli.socialapp.dto.response.PostResponse;
import com.elseveremirli.socialapp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/all")
    public List<PostResponse> getAllPosts() {
        return postService.getAllPost();
    }

    @GetMapping("/{username}/all")
    public List<PostResponse> getAllPostsByUser(@PathVariable String username) {
        return postService.getAllPostByUser(username);
    }

    @PostMapping("/{username}/create")
    public PostResponse createPostByUser(@PathVariable String username, @RequestBody PostRequest postRequest){
        return postService.createPostByUser(username,postRequest);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postService.updatePost(postId, postRequest));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        if (postService.deletePostByPostId(postId)) {
            return ResponseEntity.ok("Deleted post");
        } else {
            return ResponseEntity.badRequest().body("Post not found");
        }
    }



}
