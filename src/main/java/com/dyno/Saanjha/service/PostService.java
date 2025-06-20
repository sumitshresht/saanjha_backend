package com.dyno.Saanjha.service;

import com.dyno.Saanjha.dto.CreatePostRequest;
import com.dyno.Saanjha.dto.PostResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired private PostRepo postRepo;
    @Autowired private NotificationRepo notificationRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private LikeRepo likeRepo;

    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public PostResponse createPost(CreatePostRequest createPostRequest, String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setPostContent(createPostRequest.getPostContent());
        post.setImageUrl(createPostRequest.getImageUrl());
        post.setUser(user);

        Post saved = postRepo.save(post);

        // ✅ Save post notification to the post owner themselves (or skip if not needed)
//        Notification notification = new Notification();
//        notification.setMessage(user.getFirstName() + " " + user.getLastName() + " posted: \"" + post.getPostContent() + "\"");
//        notification.setCreatedBy(user);
//        notification.setTargetUser(user); // ✅ set the user who created the post
//        notificationRepo.save(notification);
//        List<User> allUsers = userRepo.findAll();
//        for (User u : allUsers) {
//            if (!u.getUserId().equals(user.getUserId())) {
//                Notification notification = new Notification();
//                notification.setMessage(user.getFirstName() + " " + user.getLastName() +
//                        " posted: \"" + post.getPostContent() + "\"");
//                notification.setCreatedBy(user);     // who posted
//                notification.setTargetUser(u);       // who should see this
//                notificationRepo.save(notification);
//            }
//        }
        notifyPostCreation(post, user);

        return new PostResponse(
                saved.getPostId(),
                saved.getPostContent(),
                saved.getImageUrl(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserId(),
                user.getProfilePhoto(),
                saved.getCreatedAt()
        );
    }

    public void deletePost(Long postId, String userId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this post");
        }

        likeRepo.deleteLikesByPostId(postId);
        postRepo.deleteById(postId);
    }

    public List<Post> findByUserId(String userId) {
        return postRepo.findByUser_UserId(userId);
    }

    public List<Post> searchPosts(String query) {
        return postRepo.searchPosts(query);
    }

    public List<Post> searchPostsByPeople(String query){
        return postRepo.searchPostsByPeople(query);
    }

    public void notifyPostCreation(Post post, User creator) {
        Notification notification = new Notification();
        notification.setMessage(creator.getFirstName() + " " + creator.getLastName()
                + " posted: \"" + post.getPostContent() + "\"");
        notification.setCreatedBy(creator);
        notification.setType(NotificationType.POST);

        notificationRepo.save(notification);
    }


}
