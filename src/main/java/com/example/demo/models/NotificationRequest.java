package com.example.demo.models;

public class NotificationRequest {
    private String userId;
    private String type; // e.g., "marketing", "newsletter", "updates"
    private String channel; // e.g., "email", "sms", "push"
    private Content content;

    // Inner class for content
    public static class Content {
        private String subject;
        private String body;

        // Getters and Setters
        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
