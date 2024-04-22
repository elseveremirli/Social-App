package com.elseveremirli.socialapp.services;

import com.elseveremirli.socialapp.dto.request.CommentRequest;
import com.elseveremirli.socialapp.dto.response.CommentResponse;
import com.elseveremirli.socialapp.entities.Comment;
import com.elseveremirli.socialapp.entities.Post;
import com.elseveremirli.socialapp.entities.User;
import com.elseveremirli.socialapp.repository.CommentRepository;
import com.elseveremirli.socialapp.repository.PostRepository;
import com.elseveremirli.socialapp.repository.UserRepository;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<CommentResponse> getAllCommentByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream().map(CommentResponse::converteCommnetToCommentResponse).toList();
    }

    public List<CommentResponse> getAllCommentByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Post not found"));
        List<Comment> comments = commentRepository.findByUser(user);
        return comments.stream().map(CommentResponse::converteCommnetToCommentResponse).toList();
    }

    @Transactional
    public CommentResponse createComment(String username, Long postId, CommentRequest commentRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Post not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = CommentRequest.convertCommentResponseToComment(commentRequest);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return CommentResponse.converteCommnetToCommentResponse(savedComment);
    }

    @Transactional
    public CommentResponse updateComment(String username, Long postId, Long commentId, CommentRequest commentRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Post not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Post not found"));
        if (comment.getUser().equals(user) && comment.getPost().equals(post)) {
            Optional.ofNullable(commentRequest.commentText()).ifPresent(comment::setCommentText);
            Comment savedComment = commentRepository.save(comment);
            return CommentResponse.converteCommnetToCommentResponse(savedComment);
        }
        return null;
    }

    @Transactional
    public boolean deleteComment(String username, Long postId, Long commentId) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Post not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Post not found"));
        if (comment.getUser().equals(user) && comment.getPost().equals(post)) {
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }
}
