package com.dyno.Saanjha.repo;

import com.dyno.Saanjha.model.Post;
import com.dyno.Saanjha.model.Save;
import com.dyno.Saanjha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SavedRepo extends JpaRepository<Save, Long> {

    List<Save> findByUserUserId(@Param("userId") String userId);

    boolean existsByUserAndPost(User user, Post post);

    @Transactional
    void deleteByUserAndPost(User user, Post post);
}
