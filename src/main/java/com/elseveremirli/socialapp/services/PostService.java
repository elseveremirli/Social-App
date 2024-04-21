package com.elseveremirli.socialapp.services;

import com.elseveremirli.socialapp.dto.request.PostRequest;
import com.elseveremirli.socialapp.dto.response.PostResponse;
import com.elseveremirli.socialapp.entities.Post;
import com.elseveremirli.socialapp.entities.User;
import com.elseveremirli.socialapp.repository.PostRepository;
import com.elseveremirli.socialapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Transactional
    public List<PostResponse> getAllPost() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC,"createdDate"));

        return posts.stream().map(PostResponse::convertPostToPostResponse).collect(Collectors.toList());
    }
    @Transactional
    public List<PostResponse> getAllPostByUser(String username) {
       User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(PostResponse::convertPostToPostResponse).collect(Collectors.toList());
    }
    @Transactional
    public PostResponse createPostByUser(String username, PostRequest postRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        Post post  = PostRequest.convertPostRequestToPost(postRequest,user);
        postRepository.save(post);
        return PostResponse.convertPostToPostResponse(post);
    }
    @Transactional
    public PostResponse updatePost(Long postId, PostRequest postRequest) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Optional.ofNullable(postRequest.postText()).ifPresent(foundPost::setPostText);
        Post savedPost = postRepository.save(foundPost);
        return PostResponse.convertPostToPostResponse(savedPost);
    }
    @Transactional
    public Boolean deletePostByPostId(Long postId) {
        try {
            Post foundPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
            postRepository.delete(foundPost);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
