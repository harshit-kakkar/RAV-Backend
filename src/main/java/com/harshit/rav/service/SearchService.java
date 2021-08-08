package com.harshit.rav.service;

import com.harshit.rav.entity.Account;
import com.harshit.rav.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> searchByDomain(String domain){

        return accountRepository.findByDomain(domain);
    }
}
