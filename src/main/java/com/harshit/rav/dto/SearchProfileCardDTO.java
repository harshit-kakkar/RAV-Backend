package com.harshit.rav.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SearchProfileCardDTO {
    public SearchProfileCardDTO(UUID id, String name, String domain) {
        this.id = id;
        this.name = name;
        this.domain = domain;
    }

    public SearchProfileCardDTO() {
    }

    private UUID id;
    private String name;
    private String domain;
}
