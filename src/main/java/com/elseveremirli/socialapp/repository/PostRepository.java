package com.elseveremirli.socialapp.repository;

import com.elseveremirli.socialapp.entities.Post;
import com.elseveremirli.socialapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
