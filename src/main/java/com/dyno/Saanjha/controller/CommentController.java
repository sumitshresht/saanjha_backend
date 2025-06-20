package com.dyno.Saanjha.controller;

import com.dyno.Saanjha.dto.CommentRequest;
import com.dyno.Saanjha.dto.CommentResponse;
import com.dyno.Saanjha.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    // Add a comment
    @PostMapping("/comments")
    public CommentResponse addComment(@RequestBody CommentRequest request) {
        return commentService.addComment(request);
    }

    // Get all comments for a post
    @GetMapping("/comments/{postId}")
    public List<CommentResponse> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }
}
