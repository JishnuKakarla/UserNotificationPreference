package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "user_preferences")
public class UserPreference {

    @Id
    private String id;

    @NotNull
    private String userId;

    @Email
    @NotNull
    private String email;

    @NotNull
    private Preferences preferences;

    @NotNull
    private String timezone;

    private Date createdAt;

    private Date lastUpdated;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // Nested Preferences and Channels classes
    public static class Preferences {
        private boolean marketing;
        private boolean newsletter;
        private boolean updates;
        private String frequency; // daily | weekly | monthly | never
        private Channels channels;

        public boolean isMarketing() {
            return marketing;
        }

        public void setMarketing(boolean marketing) {
            this.marketing = marketing;
        }

        public boolean isNewsletter() {
            return newsletter;
        }

        public void setNewsletter(boolean newsletter) {
            this.newsletter = newsletter;
        }

        public boolean isUpdates() {
            return updates;
        }

        public void setUpdates(boolean updates) {
            this.updates = updates;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public Channels getChannels() {
            return channels;
        }

        public void setChannels(Channels channels) {
            this.channels = channels;
        }

        public static class Channels {
            private boolean email;
            private boolean sms;
            private boolean push;

            public boolean isEmail() {
                return email;
            }

            public void setEmail(boolean email) {
                this.email = email;
            }

            public boolean isSms() {
                return sms;
            }

            public void setSms(boolean sms) {
                this.sms = sms;
            }

            public boolean isPush() {
                return push;
            }

            public void setPush(boolean push) {
                this.push = push;
            }
        }
    }
}
