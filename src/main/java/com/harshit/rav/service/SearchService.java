package com.harshit.rav.service;

import com.harshit.rav.dto.SearchProfileCardDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private AccountRepository accountRepository;

    public List<SearchProfileCardDTO> searchByDomain(String domain){

        List<Account> accounts = accountRepository.findByIsMentorTrueAndDomain(domain);
        List<SearchProfileCardDTO> searchProfileCardList = new ArrayList<>();
        for( Account account : accounts){
            searchProfileCardList.add(new SearchProfileCardDTO(account.getId(), account.getName(), account.getDomain()));
        }
        return searchProfileCardList;
    }
}
