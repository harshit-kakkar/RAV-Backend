package com.harshit.rav.repository;

import com.harshit.rav.entity.Account;
import com.harshit.rav.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByMentee(Account mentee);
    List<Appointment> findByMentor(Account mentor);
}
