package com.dyno.Saanjha.service;

import com.dyno.Saanjha.model.Like;
import com.dyno.Saanjha.model.Notification;
import com.dyno.Saanjha.model.Post;
import com.dyno.Saanjha.model.User;
import com.dyno.Saanjha.model.enums.NotificationType;
import com.dyno.Saanjha.repo.LikeRepo;
import com.dyno.Saanjha.repo.NotificationRepo;
import com.dyno.Saanjha.repo.PostRepo;
import com.dyno.Saanjha.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LikeService {
    @Autowired private UserRepo userRepo;
    @Autowired private PostRepo postRepo;
    @Autowired private LikeRepo likeRepo;
    @Autowired private NotificationRepo notificationRepo;

    public void reactToPost(String userId, Long postId, Integer newStatus) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<Like> existing = likeRepo.findByUserAndPost(user, post);

        if (existing.isPresent()) {
            Like like = existing.get();
            like.setStatus(like.getStatus().equals(newStatus) ? 0 : newStatus);
            likeRepo.save(like);
            notifyPostOwner(post, user, like.getStatus());
        } else {
            Like newLike = new Like();
            newLike.setUser(user);
            newLike.setPost(post);
            newLike.setStatus(newStatus);
            likeRepo.save(newLike);
            notifyPostOwner(post, user, newStatus);
        }
    }

    private void notifyPostOwner(Post post, User reactor, Integer status) {
        // Don't notify self
        if (post.getUser().getUserId().equals(reactor.getUserId())) return;

        String action = switch (status) {
            case 1 -> "liked";
            case -1 -> "disliked";
            default -> null;
        };

        if (action != null) {
            Notification notification = new Notification();
            notification.setMessage(reactor.getFirstName() + " " + reactor.getLastName()
                    + " " + action + " your post: \"" + post.getPostContent() + "\"");
            notification.setCreatedBy(post.getUser());
            notification.setType(NotificationType.REACTION);
            notificationRepo.save(notification);
        }
    }

    public Map<String, Long> getReactionCounts(Long postId) {
        Long likes = likeRepo.countLikesByPostId(postId);
        Long dislikes = likeRepo.countDislikesByPostId(postId);

        Map<String, Long> response = new HashMap<>();
        response.put("likes", likes);
        response.put("dislikes", dislikes);
        return response;
    }

    public Integer getUserReactionStatus(String userId, Long postId) {
        return likeRepo.getUserReactionStatus(userId, postId).orElse(0);
    }
}
