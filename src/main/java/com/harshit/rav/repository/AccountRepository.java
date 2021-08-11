package com.harshit.rav.repository;

import com.harshit.rav.entity.Account;
import com.harshit.rav.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByEmail(String email);
    List<Account> findByIsMentorTrueAndDomain(String domain);

    @Query(value = "Select location from APPOINTMENT where mentor_id = ?1 AND appointment_date = ?2 AND appointment_start_time = ?3", nativeQuery = true)
    String findByMentorAppointmentDateAndAppointmentTime(UUID mentorId, LocalDate appointmentDate, Integer appointmentTime);

}
