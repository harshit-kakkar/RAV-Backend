package com.harshit.rav.service;

import com.harshit.rav.dto.ProfileResponseDTO;
import com.harshit.rav.dto.SignupDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ProfileService {

    @Autowired
    private AccountRepository accountRepository;

    public String signup(SignupDTO newAccount){
        Account account = new Account(
            newAccount.getName(), newAccount.getEmail(), newAccount.getPassword(),
            newAccount.getSchedule()
        );
        accountRepository.save(account);
        return "Account created successfully";
    }

    public ProfileResponseDTO myProfile(String email){
        Account account = accountRepository.findByEmail(email);
        return new ProfileResponseDTO(
                account.getId(), account.getEmail(), account.getName(), account.getSchedule()
        );
    }

    public ProfileResponseDTO getUser(UUID id){
        Account account = accountRepository.getById(id);
        return new ProfileResponseDTO(
                account.getId(), account.getEmail(), account.getName(), account.getSchedule()
        );
    }
}
