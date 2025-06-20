package com.dyno.Saanjha.repo;

import com.dyno.Saanjha.model.Notification;
import com.dyno.Saanjha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    //List<Notification> findByCreatedByNot(User user);

//    List<Notification> findByTargetUser_UserIdOrderByCreatedAtDesc(String userId);

}
