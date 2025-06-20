package com.dyno.Saanjha.controller;

import com.dyno.Saanjha.dto.SavedPostRequestDTO;
import com.dyno.Saanjha.dto.SavedPostsDTO;
import com.dyno.Saanjha.model.Post;
import com.dyno.Saanjha.model.Save;
import com.dyno.Saanjha.model.User;
import com.dyno.Saanjha.repo.PostRepo;
import com.dyno.Saanjha.repo.SavedRepo;
import com.dyno.Saanjha.repo.UserRepo;
import com.dyno.Saanjha.service.SavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SavedController {
    @Autowired
    private SavedService savedService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private SavedRepo savedRepo;
    @GetMapping("/saved")
    public ResponseEntity<List<SavedPostsDTO>> getSavedPosts(@RequestParam String userId) {
        List<SavedPostsDTO> posts = savedService.getSavedPostsByUser(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/saved")
    public String savePost(@RequestBody SavedPostRequestDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepo.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Optional: Check if already saved
        boolean alreadySaved = savedRepo.existsByUserAndPost(user, post);
        if (alreadySaved) {
            return "Post already saved";
        }

        Save save = new Save();
        save.setUser(user);
        save.setPost(post);
        savedRepo.save(save);
        return "Post saved successfully";
    }

    @DeleteMapping("/saved")
    public String unsavePost(@RequestParam String userId, @RequestParam Long postId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        savedRepo.deleteByUserAndPost(user, post);
        return "Post unsaved successfully";
    }

}
