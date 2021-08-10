package com.harshit.rav.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {



    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, updatable = false)
    private UUID id;

    private LocalDate appointmentDate;

    private Integer appointmentStartDate;

    private String location;
}
