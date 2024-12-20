package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.models.Profile;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileDao profileDao;

    @Autowired
    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    // GET method to retrieve a profile by userId
    @GetMapping("/{userId}")
    public ResponseEntity<Profile> getProfile(@PathVariable("userId") int userId) {
        Profile profile = profileDao.findById(userId);

        // Return the profile
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // PUT method to update a user's profile
    @PutMapping("/{userId}")
    public ResponseEntity<Profile> updateProfile(@PathVariable("userId") int userId, @RequestBody Profile updatedProfile) {
        Profile existingProfile = profileDao.findById(userId);

        // Updates profile info
        if (existingProfile != null) {
            // Update the fields from the request body (if necessary)
            existingProfile.setFirstName(updatedProfile.getFirstName());
            existingProfile.setLastName(updatedProfile.getLastName());
            existingProfile.setPhone(updatedProfile.getPhone());
            existingProfile.setEmail(updatedProfile.getEmail());

            // Save the updated profile
            Profile updated = profileDao.update(existingProfile);

            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

