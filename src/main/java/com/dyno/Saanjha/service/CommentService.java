package com.dyno.Saanjha.service;

import com.dyno.Saanjha.dto.CommentRequest;
import com.dyno.Saanjha.dto.CommentResponse;
import com.dyno.Saanjha.model.Comment;
import com.dyno.Saanjha.model.Notification;
import com.dyno.Saanjha.model.Post;
import com.dyno.Saanjha.model.User;
import com.dyno.Saanjha.model.enums.NotificationType;
import com.dyno.Saanjha.repo.CommentRepo;
import com.dyno.Saanjha.repo.NotificationRepo;
import com.dyno.Saanjha.repo.PostRepo;
import com.dyno.Saanjha.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired private NotificationRepo notificationRepo;

    public CommentResponse addComment(CommentRequest req) {
        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepo.findById(req.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setContent(req.getContent());
        comment.setUser(user);
        comment.setPost(post);

        Comment saved = commentRepo.save(comment);

        notifyPostOwner(post, user);
        return new CommentResponse(
                saved.getCommentId(),
                saved.getContent(),
                user.getFirstName(),
                user.getLastName(),
                saved.getCreatedAt()
        );
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return commentRepo.findByPostOrderByCreatedAtAsc(post).stream()
                .map(c -> new CommentResponse(
                        c.getCommentId(),
                        c.getContent(),
                        c.getUser().getFirstName(),
                        c.getUser().getLastName(),
                        c.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    private void notifyPostOwner(Post post, User reactor) {
        // Don't notify self
        if (post.getUser().getUserId().equals(reactor.getUserId())) return;

        String action = "commented";

        Notification notification = new Notification();
        notification.setMessage(reactor.getFirstName() + " " + reactor.getLastName()
                + " " + action + " your post: \"" + post.getPostContent() + "\"");
        notification.setCreatedBy(post.getUser());
        notification.setType(NotificationType.COMMENT);
        notificationRepo.save(notification);
    }
}
