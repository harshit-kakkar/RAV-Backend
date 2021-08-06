package com.harshit.rav.service;

import com.harshit.rav.dto.ProfileResponseDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ProfileService {

    @Autowired
    private AccountRepository accountRepository;

    public ProfileResponseDTO myProfile(String email){
        Account account = accountRepository.findByEmail(email);
        ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO(account.getId(), account.getEmail(), account.getName(), account.getSchedule());
        return profileResponseDTO;
    }
}
