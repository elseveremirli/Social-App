package com.elseveremirli.socialapp.repository;

import com.elseveremirli.socialapp.entities.Comment;
import com.elseveremirli.socialapp.entities.Post;
import com.elseveremirli.socialapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);

}
