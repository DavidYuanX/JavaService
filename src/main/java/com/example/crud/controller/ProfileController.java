package com.example.crud.controller;

import com.example.crud.model.Profile;
import com.example.crud.repository.ProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public ResponseEntity<Profile> getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepository.findByUsername(username)
                .orElseGet(() -> {
                    Profile p = new Profile(username);
                    return profileRepository.save(p);
                });
        return ResponseEntity.ok(profile);
    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile body) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepository.findByUsername(username)
                .orElseGet(() -> {
                    Profile p = new Profile(username);
                    return profileRepository.save(p);
                });
        if (body.getDisplayName() != null) {
            profile.setDisplayName(body.getDisplayName());
        }
        if (body.getAvatarUrl() != null) {
            profile.setAvatarUrl(body.getAvatarUrl());
        }
        return ResponseEntity.ok(profileRepository.save(profile));
    }
}
