package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UserPreference;

@Repository
public interface UserPreferenceRepository extends MongoRepository<UserPreference, String> {
    UserPreference findByUserId(String userId);
}
