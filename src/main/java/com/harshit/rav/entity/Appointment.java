package com.harshit.rav.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {

    public Appointment(LocalDate appointmentDate, Integer appointmentStartTime, String location) {
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.location = location;
    }

    public Appointment() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, updatable = false)
    private UUID id;

    private LocalDate appointmentDate;

    private Integer appointmentStartTime;

    private String location;
}
