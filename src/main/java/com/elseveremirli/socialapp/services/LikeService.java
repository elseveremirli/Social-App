package com.elseveremirli.socialapp.services;

import com.elseveremirli.socialapp.dto.response.LikeCommentResponse;
import com.elseveremirli.socialapp.dto.response.LikePostResponse;
import com.elseveremirli.socialapp.entities.Comment;
import com.elseveremirli.socialapp.entities.Like;
import com.elseveremirli.socialapp.entities.Post;
import com.elseveremirli.socialapp.entities.User;
import com.elseveremirli.socialapp.repository.CommentRepository;
import com.elseveremirli.socialapp.repository.LikeRepository;
import com.elseveremirli.socialapp.repository.PostRepository;
import com.elseveremirli.socialapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    public List<LikePostResponse> getAllUserLikeByUsername(String username) {
        List<Like> likes = likeRepository.findByUserUsername(username);
        return likes.stream().map(LikePostResponse::convertLikeToLikeResponse).collect(Collectors.toList());
    }

    public List<LikePostResponse> getAllUserLikeByPostId(Long postId) {
        List<Like> likes = likeRepository.findByPostPostId(postId);
        return likes.stream().map(LikePostResponse::convertLikeToLikeResponse).collect(Collectors.toList());
    }

    public List<LikeCommentResponse> getAllUserLikeByCommentId(Long commentId) {
        List<Like> likes = likeRepository.findByCommentCommentId(commentId);
        return likes.stream().map(LikeCommentResponse::convertLikeToLikeResponse).collect(Collectors.toList());
    }

    @Transactional
    public LikePostResponse createLikeByPostId(String username, Long postId) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found"));
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        like.setCreatedDate(LocalDateTime.now());
        return LikePostResponse.convertLikeToLikeResponse(like);
    }

    public LikeCommentResponse createLikeByCommentId(String username, Long commentId) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("Comment not found"));
        Like like = new Like();
        like.setUser(user);
        like.setComment(comment);
        like.setCreatedDate(LocalDateTime.now());
        likeRepository.save(like);
        return LikeCommentResponse.convertLikeToLikeResponse(like);
    }

    @Transactional
    public boolean deleteLikeByLikeId(String username, Long likeId, Long postId) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found"));
        if(username.equals(user.getUsername()) && postId.equals(post.getPostId())) {
            likeRepository.deleteById(likeId);
            return true;
        }
        return false;
    }

    public boolean deleteLikeByCommentId(String username, Long likeId, Long commentId) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("Comment not found"));
        if( username.equals(user.getUsername()) && commentId.equals(comment.getCommentId()) ){
            likeRepository.deleteById(likeId);
            return true;
    }
        return false;
}
}
