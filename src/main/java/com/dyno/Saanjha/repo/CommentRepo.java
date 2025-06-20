package com.dyno.Saanjha.repo;

import com.dyno.Saanjha.model.Comment;
import com.dyno.Saanjha.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderByCreatedAtAsc(Post post);
}
