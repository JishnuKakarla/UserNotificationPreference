package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.NotificationLog;



@Repository
public interface NotificationLogRepository extends MongoRepository<NotificationLog, String> {
}