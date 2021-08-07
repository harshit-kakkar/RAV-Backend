package com.harshit.rav.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Schedule")
public class Schedule {
    public Schedule(Integer startTimeHour, Integer endTimeHour, String day) {
        this.startTimeHour = startTimeHour;
        this.endTimeHour = endTimeHour;
        this.day = day;
    }

    public Schedule() {
    }

    @GeneratedValue
    @Id
    @Column(name = "id")
    private UUID id;
    private Integer startTimeHour;
    private Integer endTimeHour;
    private String day;

}
