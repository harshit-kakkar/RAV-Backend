package com.harshit.rav.service;

import com.harshit.rav.dto.ProfileResponseDTO;
import com.harshit.rav.dto.SignupDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.entity.Schedule;
import com.harshit.rav.exception.EmailAlreadyExistsException;
import com.harshit.rav.exception.NotFoundException;
import com.harshit.rav.exception.NullFieldsException;
import com.harshit.rav.repository.AccountRepository;
import com.harshit.rav.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProfileService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public String signup(SignupDTO newAccount) throws NullFieldsException, Exception {
        if(newAccount.getEmail() != null && newAccount.getPassword() != null && newAccount.getName()!=null) {
            List<Schedule> schedulesList = newAccount.getSchedule();
            if(newAccount.getSchedule().size()>0){
                schedulesList = saveSchedule(newAccount.getSchedule());
            }
            Account account = new Account(
                    newAccount.getName(), newAccount.getEmail(), newAccount.getPassword(),
                    schedulesList, newAccount.getDomain(), newAccount.getIsMentor()
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

    private List<Schedule> saveSchedule(List<Schedule> scheduleList){
        List<Schedule> schedulesSavedList = new ArrayList<>();
        for(Schedule schedule : scheduleList){
            Schedule scheduleSaved = scheduleRepository.save(schedule);
            schedulesSavedList.add(scheduleSaved);
        }
        return schedulesSavedList;
    }

    public ProfileResponseDTO myProfile(String email){
        Account account = accountRepository.findByEmail(email);
        return new ProfileResponseDTO(
                account.getId(), account.getEmail(), account.getName(), account.getSchedule(), account.getDomain(), account.getIsMentor()
        );
    }

    public ProfileResponseDTO getUser(UUID id) throws NotFoundException {
        Optional<Account> accountRepo = accountRepository.findById(id);
        if (accountRepo.isPresent()) {
            Account account = accountRepo.get();
            return new ProfileResponseDTO(
                    account.getId(), account.getEmail(), account.getName(), account.getSchedule(), account.getDomain(), account.getIsMentor()
            );
        }else{
            throw new NotFoundException("Profile with this ID is not present");
        }
    }
}
