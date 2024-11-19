package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.NotificationLog;
import com.example.demo.models.NotificationRequest.Content;
import com.example.demo.repository.NotificationLogRepository;

import java.util.Date;
import java.util.Map;

@Service
public class NotificationLogService {

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    // Create a notification log
    public NotificationLog createNotificationLog(String userId, String type, String channel, String status, Content metadata) {
        NotificationLog log = new NotificationLog();
        log.setUserId(userId);
        log.setType(type);
        log.setChannel(channel);
        log.setStatus(status);
        //log.setMetadata(metadata);

        if ("sent".equals(status)) {
            log.setSentAt(new Date());
        }

        return notificationLogRepository.save(log);
    }

    
}
