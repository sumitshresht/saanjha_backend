package com.dyno.Saanjha.service;

import com.dyno.Saanjha.dto.SavedPostsDTO;
import com.dyno.Saanjha.model.Post;
import com.dyno.Saanjha.model.Save;
import com.dyno.Saanjha.model.User;
import com.dyno.Saanjha.repo.SavedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedService {
    @Autowired
    private SavedRepo savedRepo;
    public List<SavedPostsDTO> getSavedPostsByUser(String userId) {
        List<Save> saves = savedRepo.findByUserUserId(userId);

        return saves.stream().map(save -> {
            Post post = save.getPost();
            User postUser = post.getUser();

            return new SavedPostsDTO(
                    post.getPostId(),
                    post.getPostContent(),
                    post.getImageUrl(),
                    post.getCreatedAt(),
                    postUser.getUserId(),
                    postUser.getFirstName(),
                    postUser.getLastName(),
                    postUser.getProfilePhoto()
            );
        }).toList();
    }
}
