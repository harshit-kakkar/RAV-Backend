package com.harshit.rav.dto;

import com.harshit.rav.entity.Schedule;
import lombok.Data;

import java.util.List;

@Data
public class SignupDTO {

    public SignupDTO(String email, String name, String password, List<Schedule> schedule) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.schedule = schedule;
    }

    public SignupDTO() {
    }

    private String email;
    private String name;
    private String password;
    List<Schedule> schedule;
}
