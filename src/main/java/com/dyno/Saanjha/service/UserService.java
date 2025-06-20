package com.dyno.Saanjha.service;

import com.dyno.Saanjha.model.User;
import com.dyno.Saanjha.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void save(User newUser) {
        userRepo.save(newUser);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void update(String userId, User updatedUser) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Update only the fields that are allowed to be changed
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setProfilePhoto(updatedUser.getProfilePhoto());
        existingUser.setPhone(updatedUser.getPhone());
        // Add more fields as needed

        userRepo.save(existingUser);
    }

    public void updatePassword(String userId, String newPassword) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newPassword); // Optionally hash it here
            userRepo.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
