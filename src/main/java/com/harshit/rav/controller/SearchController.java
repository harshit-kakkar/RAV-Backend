package com.harshit.rav.controller;

import com.harshit.rav.dto.SearchProfileCardDTO;
import com.harshit.rav.entity.Account;
import com.harshit.rav.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public List<SearchProfileCardDTO> searchMentor(@RequestParam("domain") String domain){
        return searchService.searchByDomain(domain);
    }
}
