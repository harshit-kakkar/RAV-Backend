package com.harshit.rav.dto;

import com.harshit.rav.entity.Schedule;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
public class ProfileResponseDTO {
    public ProfileResponseDTO(UUID id, String email, String name, List<Schedule> schedule) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.schedule = schedule;
    }

    public ProfileResponseDTO() {
    }

    private UUID id;
    private String email;
    private String name;
    private List<Schedule> schedule;
}