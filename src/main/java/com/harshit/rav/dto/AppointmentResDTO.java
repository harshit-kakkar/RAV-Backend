package com.harshit.rav.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class AppointmentResDTO {
    public AppointmentResDTO(UUID id, Integer appointmentStartTime, LocalDate appointmentDate, String location, String name) {
        this.id = id;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentDate = appointmentDate;
        this.location = location;
        this.name = name;
    }

    public AppointmentResDTO() {
    }

    private UUID id;
    private Integer appointmentStartTime;
    private LocalDate appointmentDate;
    private String location;
    private String name;
}
