package com.dyno.Saanjha.repo;

import com.dyno.Saanjha.model.Like;
import com.dyno.Saanjha.model.Post;
import com.dyno.Saanjha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.id = :postId AND l.status = 1")
    Long countLikesByPostId(@Param("postId") Long postId);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.id = :postId AND l.status = -1")
    Long countDislikesByPostId(@Param("postId") Long postId);

    @Query("SELECT l.status FROM Like l WHERE l.user.id = :userId AND l.post.id = :postId")
    Optional<Integer> getUserReactionStatus(@Param("userId") String userId, @Param("postId") Long postId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Like l WHERE l.post.postId = :postId")
    void deleteLikesByPostId(@Param("postId") Long postId);



}
