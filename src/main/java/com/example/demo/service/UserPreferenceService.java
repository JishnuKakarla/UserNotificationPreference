package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserPreference;
import com.example.demo.repository.UserPreferenceRepository;

import java.util.Optional;
import java.util.Date;

@Service
public class UserPreferenceService {

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    // Create user preferences
    public UserPreference createUserPreferences(UserPreference userPreference) {
        userPreference.setCreatedAt(new Date());
        userPreference.setLastUpdated(new Date());
        return userPreferenceRepository.save(userPreference);
    }

    // Get user preferences
    public Optional<UserPreference> getUserPreferences(String userId) {
        return Optional.ofNullable(userPreferenceRepository.findByUserId(userId));
    }

    // Update user preferences
    public UserPreference updateUserPreferences(String userId, UserPreference updatedPreference) {
        Optional<UserPreference> existingPreference = userPreferenceRepository.findById(userId);

        if (existingPreference.isPresent()) {
            UserPreference userPreference = existingPreference.get();
            userPreference.setPreferences(updatedPreference.getPreferences());
            userPreference.setLastUpdated(new Date());
            return userPreferenceRepository.save(userPreference);
        } else {
            throw new RuntimeException("User preference not found");
        }
    }

    // Delete user preferences
    public void deleteUserPreferences(String userId) {
        userPreferenceRepository.deleteById(userId);
    }
}
