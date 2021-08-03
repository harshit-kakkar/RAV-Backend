package com.harshit.rav.dto;

import lombok.Data;

@Data
public class AuthDTO {
    private String email;
    private String password;

    public AuthDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthDTO() {
    }
}
