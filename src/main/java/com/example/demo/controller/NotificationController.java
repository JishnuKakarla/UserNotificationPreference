package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.NotificationLog;
import com.example.demo.models.NotificationRequest;
import com.example.demo.models.UserPreference;
import com.example.demo.service.NotificationLogService;
import com.example.demo.service.UserPreferenceService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationLogService notificationLogService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    // Send a notification
    @PostMapping("/send")
    public ResponseEntity<NotificationLog> sendNotification(@RequestBody NotificationRequest notificationRequest) {
        // Validate user preferences and channels
        Optional<UserPreference> userPreferenceOptional = userPreferenceService.getUserPreferences(notificationRequest.getUserId());

        if (userPreferenceOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        UserPreference userPreference = userPreferenceOptional.get();
        UserPreference.Preferences preferences = userPreference.getPreferences();

        // Check if the user has enabled the type and channel
        if (!isNotificationAllowed(preferences, notificationRequest.getType(), notificationRequest.getChannel())) {
            return ResponseEntity.badRequest().body(null);
        }

        // Create the notification log
        NotificationLog notificationLog = notificationLogService.createNotificationLog(
                notificationRequest.getUserId(),
                notificationRequest.getType(),
                notificationRequest.getChannel(),
                "sent",
                notificationRequest.getContent()
        );

        return ResponseEntity.ok(notificationLog);
    }

    // // Get all notification logs for a user
    // @GetMapping("/{userId}/logs")
    // public ResponseEntity<List<NotificationLog>> getNotificationLogs(@PathVariable String userId) {
    //     List<NotificationLog> logs = notificationLogService.getLogsByUserId(userId);
    //     return ResponseEntity.ok(logs);
    // }

    // Utility function to check notification preferences
    private boolean isNotificationAllowed(UserPreference.Preferences preferences, String type, String channel) {
        // Check if the notification type is enabled
        boolean isTypeEnabled;
    switch (type) {
        case "marketing":
            isTypeEnabled = preferences.isMarketing();
            break;
        case "newsletter":
            isTypeEnabled = preferences.isNewsletter();
            break;
        case "updates":
            isTypeEnabled = preferences.isUpdates();
            break;
        default:
            isTypeEnabled = false;
            break;
    }

        // Check if the channel is enabled
        boolean isChannelEnabled;
        switch (channel) {
        case "email":
            isChannelEnabled = preferences.getChannels().isEmail();
            break;
        case "sms":
            isChannelEnabled = preferences.getChannels().isSms();
            break;
        case "push":
            isChannelEnabled = preferences.getChannels().isPush();
            break;
        default:
            isChannelEnabled = false;
            break;
        }

        return isTypeEnabled && isChannelEnabled;
    }
}
