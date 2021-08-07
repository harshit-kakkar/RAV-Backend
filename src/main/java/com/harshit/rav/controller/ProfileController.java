package com.harshit.rav.controller;

import com.harshit.rav.dto.ProfileResponseDTO;
import com.harshit.rav.dto.SignupDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.exception.NotFoundException;
import com.harshit.rav.exception.NullFieldsException;
import com.harshit.rav.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody SignupDTO newAccount) throws NullFieldsException, Exception {

        String accountCreationRes = profileService.signup(newAccount);
        return ResponseEntity
                .ok(accountCreationRes);
    }


    @GetMapping("/profile")
    public ProfileResponseDTO myProfile(Principal principal){
        return profileService.myProfile(principal.getName());
    }

    @GetMapping("/user")
    public ProfileResponseDTO userProfile(@RequestParam("id") UUID id) throws NotFoundException {
        return profileService.getUser(id);
    }

}
