package com.dyno.Saanjha.controller;

import com.dyno.Saanjha.dto.ReactionRequest;
import com.dyno.Saanjha.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping("/reaction/{postId}")
    public ResponseEntity<Map<String, Long>> getReactionCounts(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.getReactionCounts(postId));
    }

    @GetMapping("reaction")
    public ResponseEntity<Integer> getUserReactionStatus(
            @RequestParam String userId,
            @RequestParam Long postId) {
        Integer status = likeService.getUserReactionStatus(userId, postId);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/reaction")
    public ResponseEntity<String> likeOrDislikePost(@RequestBody ReactionRequest request) {
        likeService.reactToPost(request.getUserId(), request.getPostId(), request.getStatus());
        return ResponseEntity.ok("Reaction updated");
    }
}
