package com.example.crud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    public Profile() {
    }

    public Profile(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
