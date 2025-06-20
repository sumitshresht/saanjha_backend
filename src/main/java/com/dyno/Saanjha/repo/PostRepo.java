package com.dyno.Saanjha.repo;

import com.dyno.Saanjha.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findByUser_UserId(String userId);

//    List<Post> findByPostContentContainingIgnoreCase(String query);

    @Query("SELECT p FROM Post p WHERE LOWER(p.postContent) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.user.firstName) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.user.lastName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Post> searchPosts(@Param("query") String query);

    @Query("SELECT p FROM Post p WHERE LOWER(p.user.userId) LIKE LOWER(CONCAT('%', :query, '%')) ")
    List<Post> searchPostsByPeople(@Param("query") String query);

}
