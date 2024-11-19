package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.UserPreference;
import com.example.demo.service.UserPreferenceService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/preferences")
public class UserPreferenceController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @PostMapping
    public ResponseEntity<UserPreference> create(@Valid @RequestBody UserPreference userPreference) {
        return ResponseEntity.ok(userPreferenceService.createUserPreferences(userPreference));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPreference.Preferences> getPreferences(@PathVariable String userId) {
        Optional<UserPreference> userPreference = userPreferenceService.getUserPreferences(userId);
        return userPreference.map(user -> ResponseEntity.ok(user.getPreferences()))
                             .orElse(ResponseEntity.notFound().build());
    }
    

    @PatchMapping("/{userId}")
    public ResponseEntity<UserPreference> update(@PathVariable String userId, @RequestBody UserPreference userPreference) {
        return ResponseEntity.ok(userPreferenceService.updateUserPreferences(userId, userPreference));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable String userId) {
        userPreferenceService.deleteUserPreferences(userId);
        return ResponseEntity.noContent().build();
    }
}
