package com.dyno.Saanjha.controller;

import com.dyno.Saanjha.dto.CreatePostRequest;
import com.dyno.Saanjha.dto.PostResponse;
import com.dyno.Saanjha.model.Post;
import com.dyno.Saanjha.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("posts")
    public List<PostResponse> getPost(){
        return postService.findAll()
                .stream()
                .map(post -> new PostResponse(
                        post.getPostId(),
                        post.getPostContent(),
                        post.getImageUrl(),
                        post.getUser().getFirstName(),
                        post.getUser().getLastName(),
                        post.getUser().getUserId(),
                        post.getUser().getProfilePhoto(),
                        post.getCreatedAt()
                ))
                .toList();
    }

    @PostMapping("posts")
    public PostResponse createPost(@RequestBody CreatePostRequest createPostRequest, @RequestHeader("user-id") String userId){
        System.out.println("-------------createPost-----------------");
        return postService.createPost(createPostRequest, userId);
    }

    @DeleteMapping("posts/{postId}")
    public String deletePost(@PathVariable Long postId, @RequestHeader("user-id") String userId) {
        System.out.println("-------------deletePost-----------------");
        postService.deletePost(postId, userId);
        return "Post deleted successfully";
    }


    @GetMapping("posts/user/{userId}")
    public List<PostResponse> getPostsByUser(@PathVariable String userId) {
        return postService.findByUserId(userId)
                .stream()
                .map(post -> new PostResponse(
                        post.getPostId(),
                        post.getPostContent(),
                        post.getImageUrl(),
                        post.getUser().getFirstName(),
                        post.getUser().getLastName(),
                        post.getUser().getUserId(),
                        post.getUser().getProfilePhoto(),
                        post.getCreatedAt()
                ))
                .toList();
    }


    @GetMapping("posts/search")
    public List<PostResponse> searchPosts(@RequestParam String q) {
        return postService.searchPosts(q)
                .stream()
                .map(post -> new PostResponse(
                        post.getPostId(),
                        post.getPostContent(),
                        post.getImageUrl(),
                        post.getUser().getFirstName(),
                        post.getUser().getLastName(),
                        post.getUser().getUserId(),
                        post.getUser().getProfilePhoto(),
                        post.getCreatedAt()
                )).toList();
    }

    @GetMapping("posts/search_people")
    public List<PostResponse> searchPeoplePosts(@RequestParam String q) {
        return postService.searchPostsByPeople(q)
                .stream()
                .map(post -> new PostResponse(
                        post.getPostId(),
                        post.getPostContent(),
                        post.getImageUrl(),
                        post.getUser().getFirstName(),
                        post.getUser().getLastName(),
                        post.getUser().getUserId(),
                        post.getUser().getProfilePhoto(),
                        post.getCreatedAt()
                )).toList();
    }



}

