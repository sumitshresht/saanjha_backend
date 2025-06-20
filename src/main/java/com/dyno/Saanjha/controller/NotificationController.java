package com.dyno.Saanjha.controller;

import com.dyno.Saanjha.model.Notification;
import com.dyno.Saanjha.model.enums.NotificationType;
import com.dyno.Saanjha.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepo repo;


    @GetMapping
    public List<Notification> getUserNotifications(@RequestParam String userId) {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .filter(notification -> {
                    // Don't show POSTs created by this user
                    if (notification.getType() != null && notification.getType() == NotificationType.POST)
                    {
                        return !notification.getCreatedBy().getUserId().equals(userId);
                    }

                    // For REACTION: show only if this user owns the post that got reacted (optional)
                    if (notification.getType() == NotificationType.REACTION) {
                        return notification.getCreatedBy().getUserId().equals(userId) &&
                                notification.getMessage().toLowerCase().contains("your post");
                    }

                    if(notification.getType() == NotificationType.COMMENT){
                        return notification.getCreatedBy().getUserId().equals(userId) &&
                                notification.getMessage().toLowerCase().contains("your post");
                    }

                    // Add more types here if needed
                    return false;
                })
                .collect(Collectors.toList());
    }


    @DeleteMapping("/{notificationId}")
    public String deleteNotification(@PathVariable Long notificationId) {
        if (!repo.existsById(notificationId)) {
            throw new RuntimeException("Notification not found");
        }

        repo.deleteById(notificationId);
        return "Notification deleted successfully";
    }



}
