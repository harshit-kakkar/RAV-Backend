package com.harshit.rav.service;

import com.harshit.rav.dto.ProfileResponseDTO;
import com.harshit.rav.dto.SignupDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.exception.EmailAlreadyExistsException;
import com.harshit.rav.exception.NotFoundException;
import com.harshit.rav.exception.NullFieldsException;
import com.harshit.rav.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
public class ProfileService {

    @Autowired
    private AccountRepository accountRepository;

    public String signup(SignupDTO newAccount) throws NullFieldsException, Exception {
        if(newAccount.getEmail() != null && newAccount.getPassword() != null && newAccount.getName()!=null) {
            Account account = new Account(
                    newAccount.getName(), newAccount.getEmail(), newAccount.getPassword(),
                    newAccount.getSchedule()
            );
            try {
                accountRepository.save(account);
            }catch (DataIntegrityViolationException e){
                throw new EmailAlreadyExistsException("User with this email ID already exists");
            }
            return "Account created successfully";
        }
        else{
            throw new NullFieldsException("Required fields cannot be empty");
        }
    }

    public ProfileResponseDTO myProfile(String email){
        Account account = accountRepository.findByEmail(email);
        return new ProfileResponseDTO(
                account.getId(), account.getEmail(), account.getName(), account.getSchedule()
        );
    }

    public ProfileResponseDTO getUser(UUID id) throws NotFoundException {
        Optional<Account> accountRepo = accountRepository.findById(id);
        if (accountRepo.isPresent()) {
            Account account = accountRepo.get();
            return new ProfileResponseDTO(
                    account.getId(), account.getEmail(), account.getName(), account.getSchedule()
            );
        }else{
            throw new NotFoundException("Profile with this ID is not present");
        }
    }
}
