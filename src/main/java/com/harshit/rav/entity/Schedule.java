package com.harshit.rav.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Schedule")
public class Schedule {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private UUID id;

    private Date startTime;

    private Date endTime;

    private String day;

}
