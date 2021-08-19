package com.harshit.rav.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {

    public Appointment(LocalDate appointmentDate, Integer appointmentStartTime, String location, Account mentee, Account mentor) {
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.location = location;
        this.mentee = mentee;
        this.mentor = mentor;
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

    @JoinColumn(name = "mentor_account_id")
    @ManyToOne
    private Account mentor;

    @JoinColumn(name = "mentee_account_id")
    @ManyToOne
    private Account mentee;
}
