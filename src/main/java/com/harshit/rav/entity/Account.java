package com.harshit.rav.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, updatable = false)
    private UUID id;

    private String name;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    @OneToMany()
    @JoinColumn(name = "account_id")
    private List<Schedule> schedule;
}
