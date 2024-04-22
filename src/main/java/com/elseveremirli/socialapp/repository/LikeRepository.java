package com.elseveremirli.socialapp.repository;

import com.elseveremirli.socialapp.entities.Comment;
import com.elseveremirli.socialapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository  extends JpaRepository<Like, Long> {
    List<Like> findByUserUsername(String username);
    List<Like> findByPostPostId(Long postId);
    List<Like> findByCommentCommentId(Long commentCommentId);
}
