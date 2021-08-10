package com.harshit.rav.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class AppointmentRequestDTO {
    public AppointmentRequestDTO(UUID mentor, LocalDate date, Integer startTime, String location) {
        this.mentor = mentor;
        this.date = date;
        this.startTime = startTime;
        this.location = location;
    }


    public AppointmentRequestDTO() {
    }

    private UUID mentor;
    private LocalDate date;
    private Integer startTime;
    private String location;
}
