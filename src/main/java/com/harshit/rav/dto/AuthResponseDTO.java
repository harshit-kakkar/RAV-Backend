package com.harshit.rav.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    private String token;

}
